package database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();

            // Create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nama TEXT NOT NULL, " +
                    "nik TEXT UNIQUE NOT NULL, " +
                    "tanggal_lahir TEXT NOT NULL, " +
                    "alamat TEXT NOT NULL, " +
                    "no_hp TEXT NOT NULL, " +
                    "created_at TEXT NOT NULL)";
            stmt.execute(createUsersTable);

            // Create accounts table
            String createAccountsTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "no_rekening TEXT UNIQUE NOT NULL, " +
                    "saldo INTEGER NOT NULL, " +
                    "created_at TEXT NOT NULL, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id))";
            stmt.execute(createAccountsTable);

            // Create cards table
            String createCardsTable = "CREATE TABLE IF NOT EXISTS cards (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "account_id INTEGER NOT NULL, " +
                    "no_kartu TEXT UNIQUE NOT NULL, " +
                    "pin TEXT NOT NULL, " +
                    "status TEXT NOT NULL, " +
                    "expired_date TEXT NOT NULL, " +
                    "wrong_pin_counter INTEGER DEFAULT 0, " +
                    "FOREIGN KEY(account_id) REFERENCES accounts(id))";
            stmt.execute(createCardsTable);

            // Create transactions table
            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "account_id INTEGER NOT NULL, " +
                    "type TEXT NOT NULL, " +
                    "amount INTEGER NOT NULL, " +
                    "description TEXT, " +
                    "date TEXT NOT NULL, " +
                    "FOREIGN KEY(account_id) REFERENCES accounts(id))";
            stmt.execute(createTransactionsTable);

            stmt.close();
            System.out.println("Database initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
