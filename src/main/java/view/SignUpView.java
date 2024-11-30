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

public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "sign up";
    private SignUpController signUpController;
    private SignUpViewModel signUpViewModel;

    public SignUpView(SignUpController signUpController, SignUpViewModel signUpViewModel) {
        this.signUpController = signUpController;
        this.signUpViewModel = signUpViewModel;

        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));

        JLabel signUpLabel = new Heading("Sign Up", 26).getHeading();
        signUpLabel.setBounds(35, 23, 191, 43);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(35, 90, 141, 19);
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(35, 118, 200, 60);
        firstNameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        firstNameField.setBackground(Color.decode("#D6DCE6"));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(260, 90, 141, 19);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(260, 118, 200, 60);
        lastNameField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lastNameField.setBackground(Color.decode("#D6DCE6"));

        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 210, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailIdField = new JTextField();
        emailIdField.setBounds(35, 238, 425, 60);
        emailIdField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailIdField.setBackground(Color.decode("#D6DCE6"));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 330, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField passwordField = new JTextField();
        passwordField.setBounds(35, 358, 425, 60);
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        passwordField.setBackground(Color.decode("#D6DCE6"));

        JLabel yesAccountLabel = new JLabel("Already have an account?");
        JLabel logIn = new JLabel("<html><u>Log In</u></html>");
        yesAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        logIn.setFont(new Font("Arial", Font.PLAIN, 14));
        yesAccountLabel.setBounds(36, 438, 172,20);
        logIn.setBounds(212, 438, 60,20);

        JButton signUpButton = new ColouredButton("Sign Up", "#1A1A1A", "#FFFFFF", 16).getButton();
        signUpButton.setBounds(35, 480, 425, 60);


        Image signUpImageScaled = new ImageIcon("src/main/resources/signup.png").getImage()
                .getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel signUpImage = new JLabel(new ImageIcon(signUpImageScaled));
        signUpImage.setBounds(495, 140, 300, 300);



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

        logIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                signUpController.switchToLogInView();
                System.out.println("logIn clicked");
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String emailId = emailIdField.getText();
                String password = passwordField.getText();
                signUpController.execute(firstName, lastName, emailId, password);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("signup property change: "+ evt.getOldValue());
        final SignUpState state = (SignUpState) evt.getNewValue();
        System.out.println("signup property change: "+ evt.getNewValue());
    }

    public String getViewName(){
        return viewName;
    }
}
