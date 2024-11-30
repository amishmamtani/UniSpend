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

        JTextField firstName = new JTextField();
        firstName.setBounds(35, 118, 200, 60);
        firstName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        firstName.setBackground(Color.decode("#D6DCE6"));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(260, 90, 141, 19);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField lastName = new JTextField();
        lastName.setBounds(260, 118, 200, 60);
        lastName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lastName.setBackground(Color.decode("#D6DCE6"));

        JLabel emailIdLabel = new JLabel("Email Id:");
        emailIdLabel.setBounds(35, 210, 141, 19);
        emailIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField emailId = new JTextField();
        emailId.setBounds(35, 238, 425, 60);
        emailId.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        emailId.setBackground(Color.decode("#D6DCE6"));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(35, 330, 141, 19);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField password = new JTextField();
        password.setBounds(35, 358, 425, 60);
        password.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        password.setBackground(Color.decode("#D6DCE6"));

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
        this.add(firstName);
        this.add(lastNameLabel);
        this.add(lastName);
        this.add(emailIdLabel);
        this.add(emailId);
        this.add(passwordLabel);
        this.add(password);
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

        JFrame frame = new JFrame("Sign Up");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setContentPane(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("signup property change: "+ evt.getNewValue());
        final SignUpState state = (SignUpState) evt.getNewValue();
    }

    public String getViewName(){
        return viewName;
    }
}
