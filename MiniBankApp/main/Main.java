package main;

import database.DatabaseInitializer;
import view.LoginView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Initialize database
        DatabaseInitializer.initialize();
        
        // Start application
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}
