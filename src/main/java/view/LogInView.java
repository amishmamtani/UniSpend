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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "log in";
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

        JLabel noAccountLabel = new JLabel("Don't have an account?");
        JLabel signUp = new JLabel("<html><u>Sign Up</u></html>");
        noAccountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUp.setFont(new Font("Arial", Font.PLAIN, 14));
        noAccountLabel.setBounds(35, 358, 157,20);
        signUp.setBounds(192, 358, 60,20);

        JButton logInButton = new ColouredButton("Log In", "#1A1A1A", "#FFFFFF", 16).getButton();
        logInButton.setBounds(35, 480, 320, 60);

        Image logInImageScaled = new ImageIcon("src/main/resources/login.png").getImage()
                .getScaledInstance(340, 340, Image.SCALE_SMOOTH);
        JLabel logInImage = new JLabel(new ImageIcon(logInImageScaled));
        logInImage.setBounds(425, 75, 340, 340);

        this.add(loginLabel);
        this.add(emailIdLabel);
        this.add(emailId);
        this.add(passwordLabel);
        this.add(password);
        this.add(noAccountLabel);
        this.add(signUp);
        this.add(logInButton);
        this.add(logInImage);


        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Log In Clicked");
                logInController.prepareSuccessView();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LogInState state = (LogInState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }
}
