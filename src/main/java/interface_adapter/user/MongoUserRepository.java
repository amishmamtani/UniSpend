package interface_adapter.user;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.User;
import org.bson.Document;
import use_case.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.github.cdimascio.dotenv.Dotenv;



public class MongoUserRepository implements UserRepository {
    private final MongoCollection<Document> usersCollection;

    public MongoUserRepository() {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("DATABASE_URL");
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("UniSpend");

        usersCollection = database.getCollection("users");
    }

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

    @Override
    public User getUserByLastName(String lastName) {
        Document query = new Document("lastName", lastName);
        Document userDocument = usersCollection.find(query).first();

        if (userDocument != null) {
            return fromDocument(userDocument);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (Document doc : usersCollection.find()) {
            users.add(fromDocument(doc));
        }
        return users;
    }

    public void deleteUserByLastName(String lastName) {
        usersCollection.deleteOne(new Document("lastName", lastName));
    }

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

    private Map<String, Double> budgetDocToMap(Document document) {
        Map<String, Double> map = new HashMap<>();
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            map.put(entry.getKey(), ((Number) entry.getValue()).doubleValue());
        }
        return map;
    }
}
