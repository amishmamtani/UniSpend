package view;

import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpState;
import interface_adapter.signup.SignUpViewModel;
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
import java.io.File;
import java.util.ArrayList;

/**
 * SignUpView class represents the user interface for the sign-up screen.
 * It allows users to create a new account by entering their first name, last name, email, and password.
 */
public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {

    /** The name of the view */
    private final String viewName = "sign up";

    /** The controller for managing sign-up actions */
    private SignUpController signUpController;

    /** The view model for managing sign-up state */
    private SignUpViewModel signUpViewModel;

    /** The main panel containing the components */
    private JPanel mainPanel;

    /**
     * Constructs the SignUpView with the given controller and view model.
     * Initializes the user interface components including labels, text fields, buttons, and their actions.
     *
     * @param signUpController The controller responsible for handling sign-up actions.
     * @param signUpViewModel The view model managing the sign-up state.
     */
    public SignUpView(SignUpController signUpController, SignUpViewModel signUpViewModel) {
        this.signUpController = signUpController;
        this.signUpViewModel = signUpViewModel;

        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));

        // Create and configure the "Sign Up" heading label
        JLabel signUpLabel = new Heading("Sign Up", 26).getHeading();
        signUpLabel.setBounds(35, 23, 191, 43);

        // Create and configure the first name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(35, 90, 141, 19);
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(35, 118, 200, 60);
        firstNameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        firstNameField.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the last name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(260, 90, 141, 19);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(260, 118, 200, 60);
        lastNameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lastNameField.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the email label and text field
        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 210, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailIdField = new JTextField();
        emailIdField.setBounds(35, 238, 425, 60);
        emailIdField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailIdField.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 330, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(35, 358, 425, 60);
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordField.setBackground(Color.decode("#D6DCE6"));

        // Create and configure the sign-up prompt labels
        JLabel yesAccountLabel = new JLabel("Already have an account?");
        JLabel logIn = new JLabel("<html><u>Log In</u></html>");
        yesAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        logIn.setFont(new Font("Arial", Font.PLAIN, 14));
        yesAccountLabel.setBounds(36, 438, 172,20);
        logIn.setBounds(212, 438, 60,20);

        // Create and configure the sign-up button
        JButton signUpButton = new ColouredButton("Sign Up", "#1A1A1A", "#FFFFFF",
                16).getButton();
        signUpButton.setBounds(35, 480, 425, 60);

        // Create and configure the sign-up image
        Image signUpImageScaled = new ImageIcon("src/main/resources/signup.png").getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel signUpImage = new JLabel(new ImageIcon(signUpImageScaled));
        signUpImage.setBounds(495, 140, 300, 300);

        // Add components to the panel
        this.add(signUpLabel);
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(emailIdLabel);
        this.add(emailIdField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(yesAccountLabel);
        this.add(logIn);
        this.add(signUpButton);
        this.add(signUpImage);
        mainPanel = this;

        // Add listener for the "Log In" label
        logIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                signUpController.switchToLogInView(); // Switch to the login view
                System.out.println("logIn clicked");
            }
        });

        // Add listener for the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText().trim(); // Get the first name
                String lastName = lastNameField.getText().trim(); // Get the last name
                String emailId = emailIdField.getText().trim(); // Get the email ID
                String password = passwordField.getText().trim(); // Get the password

                ArrayList<String> errorList = new ArrayList<>();
                // Validate the input fields
                if(firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || password.isEmpty()) {
                    errorList.add("Please fill all the Fields");
                }
                if(!firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
                    errorList.add("Names can only contain letters");
                }
                if(!emailId.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                    errorList.add("Invalid Email Id");
                }
                if (errorList.isEmpty()){
                    signUpController.execute(firstName, lastName, emailId, password); // Execute sign-up action
                }
                else{
                    // Display error messages if validation fails
                    System.out.println(errorList);
                    JDialog dialog = new JDialog();
                    dialog.setTitle("Error");
                    dialog.setSize(300, 200);
                    dialog.setLayout(new GridBagLayout());
                    dialog.setModal(true);

                    String errorMessage = "<html>";
                    for(String error : errorList){
                        errorMessage += error + "<br>";
                    }
                    errorMessage += "</html>";
                    JLabel errorLabel = new JLabel(errorMessage);
                    dialog.add(errorLabel);
                    dialog.setVisible(true);
                }

                // Display error message from the view model if it exists
                SignUpState signUpState = signUpViewModel.getState();
                String error = signUpState.getError();
                if (error != null){
                    JLabel errorLabel = new JLabel(error);
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                    errorLabel.setBounds(35, 64, 400,20);
                    mainPanel.add(errorLabel);
                    mainPanel.repaint();
                    mainPanel.revalidate();
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
        // Handle property change event for the sign-up view
        System.out.println("signup property change: "+ evt.getOldValue());
        final SignUpState state = (SignUpState) evt.getNewValue();
        System.out.println("signup property change: "+ evt.getNewValue());
    }

    /**
     * Retrieves the view name for the sign-up view.
     *
     * @return The name of the view.
     */
    public String getViewName(){
        return viewName;
    }
}
