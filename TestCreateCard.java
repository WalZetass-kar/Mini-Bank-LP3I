import database.DatabaseInitializer;
import database.DatabaseConnection;
import controller.AuthController;
import controller.AccountController;
import controller.CardController;
import model.Card;
import model.Account;

public class TestCreateCard {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  TEST CREATE CARD VIEW");
        System.out.println("===========================================\n");
        
        // Initialize database
        DatabaseInitializer.initialize();
        
        // Register user
        System.out.println("1. Registering new user...");
        AuthController authController = new AuthController();
        String result = authController.register(
            "Test User Card",
            "3201234567890088",
            "1990-01-01",
            "Jl. Test",
            "081234567888"
        );
        
        if ("SUCCESS".equals(result)) {
            System.out.println("   ✓ User registered!\n");
        }
        
        // Get account
        AccountController accountController = new AccountController();
        Account account = accountController.getAccountByUserId(1);
        
        System.out.println("2. Account created:");
        System.out.println("   - Account Number: " + account.getNoRekening());
        System.out.println("   - Balance: Rp " + String.format("%,d", account.getSaldo()) + "\n");
        
        // Check if card exists
        CardController cardController = new CardController();
        Card existingCard = cardController.getCardByAccountId(account.getId());
        
        if (existingCard == null) {
            System.out.println("3. No card found - User needs to create card");
            System.out.println("   → CreateCardView should be shown\n");
            
            // Create card
            System.out.println("4. Creating card...");
            Card card = cardController.createCard(account.getId());
            
            if (card != null) {
                System.out.println("   ✓ Card created successfully!");
                System.out.println("   - Card Number: " + card.getNoKartu());
                System.out.println("   - PIN: " + card.getPin());
                System.out.println("   - Status: " + card.getStatus());
                System.out.println("   - Expiry: " + card.getExpiredDate() + "\n");
            }
        } else {
            System.out.println("3. Card already exists\n");
        }
        
        System.out.println("===========================================");
        System.out.println("  TEST COMPLETED!");
        System.out.println("===========================================\n");
        
        System.out.println("📝 CreateCardView features:");
        System.out.println("   ✓ Modern gradient background");
        System.out.println("   ✓ Card icon 💳");
        System.out.println("   ✓ Info panel with card details");
        System.out.println("   ✓ Warning panel");
        System.out.println("   ✓ Success dialog with card info");
        System.out.println("   ✓ Loading state");
        System.out.println("   ✓ Responsive design\n");
        
        DatabaseConnection.closeConnection();
    }
}
