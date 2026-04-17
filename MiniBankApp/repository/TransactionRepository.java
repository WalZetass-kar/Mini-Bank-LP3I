package repository;

import database.DatabaseConnection;
import model.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    public boolean createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (account_id, type, amount, description, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, transaction.getAccountId());
            pstmt.setString(2, transaction.getType());
            pstmt.setLong(3, transaction.getAmount());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setString(5, transaction.getDate());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                transactions.add(new Transaction(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("type"),
                    rs.getLong("amount"),
                    rs.getString("description"),
                    rs.getString("date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
