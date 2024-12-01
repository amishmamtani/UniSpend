package app;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {

        AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addBudgetMaker()
                                            .addBudgetTracker()
                                            .addChatbot()
                                            .addLogIn()
                                            .addSignUp()
                                            .addHome()
                                            .build();
        application.setVisible(true);


    }
}