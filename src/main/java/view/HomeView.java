package view;

import entity.User;
import interface_adapter.home.HomeController;
import interface_adapter.home.HomeState;
import interface_adapter.home.HomeViewModel;
import interface_adapter.user.MongoUserRepository;
import view.components.Heading;
import view.components.PanelButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The HomeView class represents the home screen of the application, where users can select various options
 * such as making a budget, tracking the budget, interacting with the chatbot, or comparing the budget.
 */
public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "home";

    /** The controller for managing home interactions */
    private final HomeController homeController;

    /** The view model for managing home state */
    private final HomeViewModel homeViewModel;

    /** The first name of the current user */
    private String userFirstName = "";

    /** The main panel of the view */
    private JPanel mainPanel;

    /**
     * Constructs the HomeView with the given controller and view model.
     * Initializes various UI components such as buttons, labels, and user details.
     *
     * @param homeController The controller responsible for handling home interactions.
     * @param homeViewModel The view model managing the home state.
     */
    public HomeView(HomeController homeController, HomeViewModel homeViewModel) {
        this.homeController = homeController;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this); // Listen for property changes

        this.setLayout(null);  // Set layout to null for absolute positioning
        this.setVisible(true);
        this.setBackground(Color.decode("#FFFFFF")); // Set background color to white

        MongoUserRepository mongoUserRepository = new MongoUserRepository();
        User user = mongoUserRepository.getUserByEmail(homeViewModel.getState().getEmailId());

        // Create and configure the "What would you like to do?" heading
        JLabel welcomeLabel = new Heading("What would you like to do?", 30).getHeading();
        welcomeLabel.setBounds(35, 78, 400, 43);

        // Make Budget Button
        JPanel makeBudgetButton = new PanelButton("makebudget.png");
        JLabel makeBudget = new Heading("<html>Make Budget</html>", 26).getHeading();
        makeBudget.setForeground(Color.decode("#FFFFFF"));
        makeBudget.setBounds(20,20, 105, 65);

        JLabel makeBudgetDescription = new JLabel("<html>Ready to take control of your money? " +
                "Tap to create your budget!</html>");
        makeBudgetDescription.setForeground(Color.decode("#FFFFFF"));
        makeBudgetDescription.setFont(new Font("Arial", Font.PLAIN, 12));
        makeBudgetDescription.setBounds(20,90, 180, 45);

        makeBudgetButton.add(makeBudgetDescription);
        makeBudgetButton.add(makeBudget);
        makeBudgetButton.setComponentZOrder(makeBudgetDescription,0);
        makeBudgetButton.setComponentZOrder(makeBudget,0);
        makeBudgetButton.setBounds(35, 211, 227,304);

        // Track Budget Button
        JPanel trackBudgetButton = new PanelButton("trackbudget.png");
        JLabel trackBudget = new Heading("<html>Track Budget</html>", 26).getHeading();
        trackBudget.setForeground(Color.decode("#FFFFFF"));
        trackBudget.setBounds(20,20, 105, 65);

        JLabel trackBudgetDescription = new JLabel("<html>Stay on top of your budget game. " +
                "Track every expense here!</html>");
        trackBudgetDescription.setForeground(Color.decode("#FFFFFF"));
        trackBudgetDescription.setFont(new Font("Arial", Font.PLAIN, 12));
        trackBudgetDescription.setBounds(20,90, 180, 45);

        trackBudgetButton.add(trackBudgetDescription);
        trackBudgetButton.add(trackBudget);
        trackBudgetButton.setComponentZOrder(trackBudgetDescription,0);
        trackBudgetButton.setComponentZOrder(trackBudget,0);
        trackBudgetButton.setBounds(276, 211, 242, 304);

        // Chatbot Button
        JPanel chatbotButton = new PanelButton("chatbot.png");
        JLabel chatbot = new Heading("<html>Chatbot</html>", 26).getHeading();
        chatbot.setForeground(Color.decode("#FFFFFF"));
        chatbot.setBounds(20,20, 100, 30);

        JLabel chatbotDescription = new JLabel("<html>Your personal budget buddy is here. Ask away!</html>");
        chatbotDescription.setForeground(Color.decode("#FFFFFF"));
        chatbotDescription.setFont(new Font("Arial", Font.PLAIN, 12));
        chatbotDescription.setBounds(20,60, 185, 30);

        chatbotButton.add(chatbotDescription);
        chatbotButton.add(chatbot);
        chatbotButton.setComponentZOrder(chatbotDescription,0);
        chatbotButton.setComponentZOrder(chatbot,0);
        chatbotButton.setBounds(532, 211, 264,304);

        // Add components to the panel
        this.add(welcomeLabel);
        this.add(makeBudgetButton);
        this.add(trackBudgetButton);
        this.add(chatbotButton);
        mainPanel = this;

        // Add mouse listeners to handle button clicks
        makeBudgetButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Make Budget Clicked");
                homeController.switchToBudgetMaker();
            }
        });

        trackBudgetButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Track Budget Clicked");
                homeController.switchToBudgetTracker();
            }
        });

        chatbotButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("Chatbot Clicked");
                homeController.switchToChatBot();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty implementation for ActionListener interface
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update the user's first name based on the updated state
        System.out.println("PropertyChange "+ evt.getNewValue());
        final HomeState state = (HomeState) evt.getNewValue();
        MongoUserRepository mongoUserRepository = new MongoUserRepository();
        User user = mongoUserRepository.getUserByEmail(state.getEmailId());
        userFirstName = user.getFirstName();
        System.out.println(userFirstName);

        // Update the welcome message with the user's first name
        String helloString = "Hi there, " + userFirstName;
        JLabel helloLabel = new Heading(helloString, 30).getHeading();
        helloLabel.setBounds(35, 38, 400, 43);
        mainPanel.add(helloLabel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * Retrieves the view name for the home view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return viewName;
    }
}
