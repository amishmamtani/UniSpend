package view;

import view.components.Heading;

import javax.swing.*;

public class LoginView {
    public LoginView() {

        JPanel LoginPanel = new JPanel();
        LoginPanel.setLayout(null);

        JLabel LoginLabel = new Heading("Log In", 26).getHeading();
        JFrame frame = new JFrame("Log In");
        frame.setSize(830, 600);
        frame.setResizable(false);
        frame.setContentPane(LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
