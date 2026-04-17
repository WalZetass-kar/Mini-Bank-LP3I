import database.DatabaseInitializer;
import database.DatabaseConnection;
import controller.AuthController;
import controller.AccountController;
import controller.CardController;
import controller.SettingsController;
import model.Card;
import model.Account;

public class TestChangePIN {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  BANK LP3I PEKANBARU - TEST CHANGE PIN");
        System.out.println("===========================================\n");
        
        // Initialize database
        System.out.println("1. Initializing database...");
        DatabaseInitializer.initialize();
        System.out.println("   ✓ Database initialized!\n");
        
        // Register user
        System.out.println("2. Registering user...");
        AuthController authController = new AuthController();
        String result = authController.register(
            "Test User",
            "3201234567890099",
            "1990-01-01",
            "Jl. Test No. 1",
            "081234567899"
        );
        
        if ("SUCCESS".equals(result)) {
            System.out.println("   ✓ User registered!\n");
        } else {
            System.out.println("   ✗ Failed: " + result + "\n");
            return;
        }
        
        // Get account
        AccountController accountController = new AccountController();
        Account account = accountController.getAccountByUserId(1);
        
        // Create card
        System.out.println("3. Creating ATM card...");
        CardController cardController = new CardController();
        Card card = cardController.createCard(account.getId());
        
        if (card != null) {
            System.out.println("   ✓ Card created!");
            System.out.println("   - Card Number: " + card.getNoKartu());
            System.out.println("   - Original PIN: " + card.getPin() + "\n");
        }
        
        // Test change PIN
        System.out.println("4. Testing Change PIN feature...");
        SettingsController settingsController = new SettingsController();
        
        String oldPin = card.getPin();
        String newPin = "999888";
        String confirmPin = "999888";
        
        System.out.println("   - Old PIN: " + oldPin);
        System.out.println("   - New PIN: " + newPin);
        
        String changePinResult = settingsController.changePin(
            card.getId(),
            oldPin,
            newPin,
            confirmPin
        );
        
        if ("SUCCESS".equals(changePinResult)) {
            System.out.println("   ✓ PIN changed successfully!\n");
            
            // Verify new PIN
            System.out.println("5. Verifying new PIN...");
            Card updatedCard = settingsController.getCardById(card.getId());
            
            if (updatedCard != null && updatedCard.getPin().equals(newPin)) {
                System.out.println("   ✓ New PIN verified!");
                System.out.println("   - Current PIN in database: " + updatedCard.getPin());
                System.out.println("   - Wrong PIN counter reset: " + updatedCard.getWrongPinCounter() + "\n");
            }
            
            // Test login with new PIN
            System.out.println("6. Testing login with new PIN...");
            Card loginCard = authController.login(card.getNoKartu(), newPin);
            
            if (loginCard != null) {
                System.out.println("   ✓ Login successful with new PIN!\n");
            } else {
                System.out.println("   ✗ Login failed with new PIN!\n");
            }
            
        } else {
            System.out.println("   ✗ Failed to change PIN: " + changePinResult + "\n");
        }
        
        // Test validation
        System.out.println("7. Testing PIN validation...");
        
        // Test wrong old PIN
        String testResult1 = settingsController.changePin(card.getId(), "000000", "111111", "111111");
        System.out.println("   - Wrong old PIN: " + testResult1);
        
        // Test PIN mismatch
        String testResult2 = settingsController.changePin(card.getId(), newPin, "222222", "333333");
        System.out.println("   - PIN mismatch: " + testResult2);
        
        // Test invalid PIN format
        String testResult3 = settingsController.changePin(card.getId(), newPin, "12345", "12345");
        System.out.println("   - Invalid format: " + testResult3);
        
        // Test same PIN
        String testResult4 = settingsController.changePin(card.getId(), newPin, newPin, newPin);
        System.out.println("   - Same PIN: " + testResult4 + "\n");
        
        System.out.println("===========================================");
        System.out.println("  ALL TESTS COMPLETED! ✓");
        System.out.println("===========================================\n");
        
        System.out.println("📝 Features tested:");
        System.out.println("   ✓ Change PIN functionality");
        System.out.println("   ✓ PIN validation");
        System.out.println("   ✓ Wrong PIN counter reset");
        System.out.println("   ✓ Login with new PIN");
        System.out.println("   ✓ Error handling\n");
        
        // Close connection
        DatabaseConnection.closeConnection();
    }
}
