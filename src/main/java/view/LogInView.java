package view;

import interface_adapter.login.LogInController;
import interface_adapter.login.LogInState;
import interface_adapter.login.LogInViewModel;
import interface_adapter.signup.SignUpState;
import view.components.ColouredButton;
import view.components.Heading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * LogInView class represents the user interface for the login screen.
 * It allows users to log into their account with their email ID and password.
 */
public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "log in";

    /** The controller for managing login actions */
    private LogInController logInController;

    /** The view model for managing login state */
    private LogInViewModel logInViewModel;

    /**
     * Constructs the LogInView with the given controller and view model.
     * Initializes the user interface components including labels, text fields, buttons, and their actions.
     *
     * @param logInController The controller responsible for handling login actions.
     * @param logInViewModel The view model managing the login state.
     */
    public LogInView(LogInController logInController, LogInViewModel logInViewModel) {
        this.logInController = logInController;
        this.logInViewModel = logInViewModel;

        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));

        // Create and configure the "Log In" heading label
        JLabel loginLabel = new Heading("Log In", 26).getHeading();
        loginLabel.setBounds(35, 23, 191, 43);

        // Create and configure the email label and text field
        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 120, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailId = new JTextField();
        emailId.setBounds(35, 148, 320, 60);
        emailId.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailId.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 250, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField password = new JPasswordField();
        password.setBounds(35, 278, 320, 60);
        password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        password.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the sign-up prompt labels
        JLabel noAccountLabel = new JLabel("Don't have an account?");
        JLabel signUp = new JLabel("<html><u>Sign Up</u></html>");
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUp.setFont(new Font("Arial", Font.PLAIN, 14));
        noAccountLabel.setBounds(35, 358, 157,20);
        signUp.setBounds(192, 358, 60,20);

        // Create and configure the login button
        JButton logInButton = new ColouredButton("Log In", "#1A1A1A", "#FFFFFF", 16).getButton();
        logInButton.setBounds(35, 480, 320, 60);

        // Create and configure the login image
        Image logInImageScaled = new ImageIcon("src/main/resources/login.png").getImage()
                .getScaledInstance(340, 340, Image.SCALE_SMOOTH);
        JLabel logInImage = new JLabel(new ImageIcon(logInImageScaled));
        logInImage.setBounds(425, 75, 340, 340);

        // Add components to the panel
        this.add(loginLabel);
        this.add(emailIdLabel);
        this.add(emailId);
        this.add(passwordLabel);
        this.add(password);
        this.add(noAccountLabel);
        this.add(signUp);
        this.add(logInButton);
        this.add(logInImage);

        // Add listener for the sign-up label
        signUp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                logInController.switchToSignUp(); // Switch to the sign-up view
            }
        });

        // Add listener for the log-in button
        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Log In Clicked");
                String emailID = emailId.getText(); // Get the entered email ID
                String pass = password.getText(); // Get the entered password
                logInController.execute(emailID, pass); // Execute the login action
                LogInState logInState = logInViewModel.getState();
                String error = logInState.getLogInError();
                if(error != null) {
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Error");
                    dialog.setSize(300, 200);
                    dialog.setLayout(new GridBagLayout());
                    dialog.setModal(true);
                    JLabel errorLabel = new JLabel(error);
                    dialog.add(errorLabel);
                    dialog.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty implementation for ActionListener interface
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Empty implementation for PropertyChangeListener interface
        final LogInState state = (LogInState) evt.getNewValue();
    }

    /**
     * Retrieves the view name for the log in view.
     *
     * @return The name of the view.
     */
    public String getViewName() {
        return viewName;
    }
}
