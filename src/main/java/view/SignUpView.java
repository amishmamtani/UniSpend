package view;

import interface_adapter.signup.SignUpController;
import interface_adapter.signup.SignUpViewModel;
import view.components.ColouredButton;
import view.components.Heading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
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

        JTextField firstName = new JTextField();
        firstName.setBounds(35, 118, 320, 60);
        firstName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        firstName.setBackground(Color.decode("#D6DCE6"));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(35, 190, 141, 19);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField lastName = new JTextField();
        lastName.setBounds(35, 218, 320, 60);
        lastName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lastName.setBackground(Color.decode("#D6DCE6"));

        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 290, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailId = new JTextField();
        emailId.setBounds(35, 318, 320, 60);
        emailId.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailId.setBackground(Color.decode("#D6DCE6"));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 390, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField password = new JTextField();
        password.setBounds(35, 418, 320, 60);
        password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        password.setBackground(Color.decode("#D6DCE6"));

        JButton signUpButton = new ColouredButton("Sign Up", "#1A1A1A", "#FFFFFF", 16).getButton();
        signUpButton.setBounds(35, 480, 320, 60);

        this.add(signUpLabel);
        this.add(firstNameLabel);
        this.add(firstName);
        this.add(lastNameLabel);
        this.add(lastName);
        this.add(emailIdLabel);
        this.add(emailId);
        this.add(passwordLabel);
        this.add(password);
        this.add(signUpButton);

        JFrame frame = new JFrame("Sign Up");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
