package interface_adapter.user;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.User;
import org.bson.Document;
import use_case.user.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository implementation for managing user data in a MongoDB database.
 */
public class MongoUserRepository implements UserRepository {
    /** The MongoDB collection for storing user documents */
    private final MongoCollection<Document> usersCollection;

    /**
     * Constructs a MongoUserRepository and initializes the connection to the MongoDB database.
     */
    public MongoUserRepository() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("DATABASE_URL");
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("UniSpend");

        usersCollection = database.getCollection("users");
    }

    /**
     * Saves or updates a user in the database.
     * If the user exists, updates their information; otherwise, inserts a new user.
     *
     * @param user The user to save or update.
     */
    @Override
    public void saveUser(User user) {
        Document query = new Document("lastName", user.getLastName());
        Document existingUser = usersCollection.find(query).first();
        if (existingUser != null) {
            Document updateDoc = new Document("$set", toDocument(user));
            usersCollection.updateOne(query, updateDoc);
        } else {
            usersCollection.insertOne(toDocument(user));
        }
    }

    /**
     * Deletes a user from the database by their email address.
     *
     * @param email The email address of the user to delete.
     */
    public void deleteUserByEmail(String email) {
        usersCollection.deleteOne(new Document("email", email));
    }

    /**
     * Retrieves a user from the database by their last name.
     *
     * @param lastName The last name of the user.
     * @return The user if found, otherwise null.
     */
    @Override
    public User getUserByLastName(String lastName) {
        Document query = new Document("lastName", lastName);
        Document userDocument = usersCollection.find(query).first();

        if (userDocument != null) {
            return fromDocument(userDocument);
        }
        return null;
    }

    /**
     * Retrieves a user from the database by their email address.
     *
     * @param email The email address of the user.
     * @return The user if found, otherwise null.
     */
    @Override
    public User getUserByEmail(String email) {
        Document query = new Document("email", email);
        Document userDocument = usersCollection.find(query).first();

        if (userDocument != null) {
            return fromDocument(userDocument);
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : usersCollection.find()) {
            users.add(fromDocument(doc));
        }
        return users;
    }

    /**
     * Deletes a user from the database by their last name.
     *
     * @param lastName The last name of the user to delete.
     */
    public void deleteUserByLastName(String lastName) {
        usersCollection.deleteOne(new Document("lastName", lastName));
    }

    /**
     * Converts a User object to a MongoDB document.
     *
     * @param user The user to convert.
     * @return A MongoDB document representing the user.
     */
    private Document toDocument(User user) {
        Document document = new Document()
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("password", user.getPassword())
                .append("email", user.getEmail())
                .append("income", user.getIncome());

        if (user.getBudget() != null) {
            document.append("budget", new Document(user.getBudget()));
        }
        if (user.getBudgetTracker() != null) {
            document.append("budgetTracker", new Document(user.getBudgetTracker()));
        }
        return document;
    }

    /**
     * Converts a MongoDB document to a User object.
     *
     * @param document The MongoDB document to convert.
     * @return A User object.
     */
    private User fromDocument(Document document) {
        User user = new User(
                document.getString("firstName"),
                document.getString("lastName"),
                document.getString("password"),
                document.getString("email")
        );
        user.setIncome(document.getDouble("income"));

        Document budgetDoc = (Document) document.get("budget");
        if (budgetDoc != null) {
            user.setBudget((HashMap<String, Double>) budgetDocToMap(budgetDoc));
        }

        Document budgetTrackerDoc = (Document) document.get("budgetTracker");
        if (budgetTrackerDoc != null) {
            user.setBudgetTracker((HashMap<String, Double>) budgetDocToMap(budgetTrackerDoc));
        }

        return user;
    }

    /**
     * Converts a MongoDB document representing a budget to a map.
     *
     * @param document The MongoDB document to convert.
     * @return A map representing the budget.
     */
    private Map<String, Double> budgetDocToMap(Document document) {
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            map.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
        }
        return map;
    }
}
