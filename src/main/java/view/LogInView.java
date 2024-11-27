package view;

import interface_adapter.login.LogInController;
import interface_adapter.login.LogInViewModel;
import view.components.ColouredButton;
import view.components.Heading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {
    private LogInController logInController;
    private LogInViewModel logInViewModel;

    public LogInView(LogInController logInController, LogInViewModel logInViewModel) {
        this.logInController = logInController;
        this.logInViewModel = logInViewModel;

        this.setLayout(null);
        this.setBackground(Color.decode("#FFFFFF"));

        JLabel loginLabel = new Heading("Log In", 26).getHeading();
        loginLabel.setBounds(35, 23, 191, 43);

        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 120, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailId = new JTextField();
        emailId.setBounds(35, 148, 320, 60);
        emailId.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailId.setBackground(Color.decode("#D6DCE6"));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 250, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField password = new JTextField();
        password.setBounds(35, 278, 320, 60);
        password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        password.setBackground(Color.decode("#D6DCE6"));

        JButton logInButton = new ColouredButton("Log In", "#1A1A1A", "#FFFFFF", 16).getButton();
        logInButton.setBounds(35, 480, 320, 60);

        this.add(loginLabel);
        this.add(emailIdLabel);
        this.add(emailId);
        this.add(passwordLabel);
        this.add(password);
        this.add(logInButton);

        JFrame frame = new JFrame("Log In");
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
