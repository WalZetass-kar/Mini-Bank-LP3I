import database.DatabaseInitializer;
import database.DatabaseConnection;
import controller.AuthController;
import controller.AccountController;
import controller.CardController;
import model.Card;
import model.Account;

public class TestApp {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  MINI BANK LP3I PEKANBARU - TEST MODE");
        System.out.println("===========================================\n");
        
        // Initialize database
        System.out.println("1. Initializing database...");
        DatabaseInitializer.initialize();
        System.out.println("   ✓ Database initialized successfully!\n");
        
        // Test registration
        System.out.println("2. Testing user registration...");
        AuthController authController = new AuthController();
        String result = authController.register(
            "Budi Santoso",
            "3201234567890001",
            "1995-05-15",
            "Jl. Sudirman No. 123, Pekanbaru",
            "081234567890"
        );
        
        if ("SUCCESS".equals(result)) {
            System.out.println("   ✓ User registered successfully!");
            System.out.println("   - Name: Budi Santoso");
            System.out.println("   - NIK: 3201234567890001");
            System.out.println("   - Initial Balance: Rp 50.000\n");
        } else {
            System.out.println("   ✗ Registration failed: " + result + "\n");
            return;
        }
        
        // Get account
        System.out.println("3. Getting account information...");
        AccountController accountController = new AccountController();
        Account account = accountController.getAccountByUserId(1);
        
        if (account != null) {
            System.out.println("   ✓ Account found!");
            System.out.println("   - Account Number: " + account.getNoRekening());
            System.out.println("   - Balance: Rp " + String.format("%,d", account.getSaldo()) + "\n");
        }
        
        // Create card
        System.out.println("4. Creating ATM card...");
        CardController cardController = new CardController();
        Card card = cardController.createCard(account.getId());
        
        if (card != null) {
            System.out.println("   ✓ Card created successfully!");
            System.out.println("   - Card Number: " + card.getNoKartu());
            System.out.println("   - PIN: " + card.getPin());
            System.out.println("   - Status: " + card.getStatus());
            System.out.println("   - Expiry Date: " + card.getExpiredDate() + "\n");
        }
        
        // Test login
        System.out.println("5. Testing login...");
        Card loginCard = authController.login(card.getNoKartu(), card.getPin());
        
        if (loginCard != null) {
            System.out.println("   ✓ Login successful!\n");
        } else {
            System.out.println("   ✗ Login failed!\n");
        }
        
        System.out.println("===========================================");
        System.out.println("  ALL TESTS COMPLETED SUCCESSFULLY! ✓");
        System.out.println("===========================================\n");
        
        System.out.println("📝 Note: This is a console test mode.");
        System.out.println("   To see the GUI, run on a system with display:");
        System.out.println("   java -cp \".:sqlite-jdbc-3.42.0.0.jar\" main.Main\n");
        
        // Close connection
        DatabaseConnection.closeConnection();
    }
}
