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

public class HomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "home";
    private final HomeController homeController;
    private final HomeViewModel homeViewModel;
    private String userFirstName = "";
    private JPanel mainPanel;

    public HomeView(HomeController homeController, HomeViewModel homeViewModel) {
        this.homeController = homeController;
        this.homeViewModel = homeViewModel;
        this.homeViewModel.addPropertyChangeListener(this);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(Color.decode("#FFFFFF"));

        MongoUserRepository mongoUserRepository = new MongoUserRepository();
        User user = mongoUserRepository.getUserByEmail(homeViewModel.getState().getEmailId());
//        String helloString = "Hi there, " + this.userFirstName;
//        JLabel helloLabel = new Heading(helloString, 30).getHeading();
//        helloLabel.setBounds(35, 38, 400, 43);

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

        // Compare Budget Button
//        JPanel compareBudgetButton = new PanelButton("comparebudget.png");
//        JLabel compareBudget = new Heading("<html>Compare Budget</html>", 26).getHeading();
//        compareBudget.setForeground(Color.decode("#FFFFFF"));
//        compareBudget.setBounds(20,20, 120, 65);

//        JLabel compareBudgetDescription = new JLabel("<html>Track your progress easily—" +
//                "compare your budget with one tap!</html>");
//        compareBudgetDescription.setForeground(Color.decode("#FFFFFF"));
//        compareBudgetDescription.setFont(new Font("Arial", Font.PLAIN, 12));
//        compareBudgetDescription.setBounds(20,90, 200, 40);

//        compareBudgetButton.add(compareBudgetDescription);
//        compareBudgetButton.add(compareBudget);
//        compareBudgetButton.setComponentZOrder(compareBudgetDescription,0);
//        compareBudgetButton.setComponentZOrder(compareBudget,0);
//        compareBudgetButton.setBounds(532, 371, 264, 144);

        //this.add(helloLabel);
        this.add(welcomeLabel);
        this.add(makeBudgetButton);
        this.add(trackBudgetButton);
        this.add(chatbotButton);
        mainPanel = this;
        //this.add(compareBudgetButton);


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

//        compareBudgetButton.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Compare Budget Clicked");
////                homeController.switchToBudgetCompare();
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("PropertyChange "+ evt.getNewValue());
        final HomeState state = (HomeState) evt.getNewValue();
        MongoUserRepository mongoUserRepository = new MongoUserRepository();
        User user = mongoUserRepository.getUserByEmail(state.getEmailId());
        userFirstName = user.getFirstName();
        System.out.println(userFirstName);
        String helloString = "Hi there, " + userFirstName;
        JLabel helloLabel = new Heading(helloString, 30).getHeading();
        helloLabel.setBounds(35, 38, 400, 43);
        mainPanel.add(helloLabel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    public String getViewName() {
        return viewName;
    }
}
