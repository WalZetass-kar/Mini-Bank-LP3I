package repository;

import database.DatabaseConnection;
import model.Account;
import java.sql.*;

public class AccountRepository {
    public int createAccount(Account account) {
        String sql = "INSERT INTO accounts (user_id, no_rekening, saldo, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, account.getUserId());
            pstmt.setString(2, account.getNoRekening());
            pstmt.setLong(3, account.getSaldo());
            pstmt.setString(4, account.getCreatedAt());
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Account getAccountByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Account(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("no_rekening"),
                    rs.getLong("saldo"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Account(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("no_rekening"),
                    rs.getLong("saldo"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccountByNoRekening(String noRekening) {
        String sql = "SELECT * FROM accounts WHERE no_rekening = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, noRekening);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Account(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("no_rekening"),
                    rs.getLong("saldo"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSaldo(int accountId, long newSaldo) {
        String sql = "UPDATE accounts SET saldo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, newSaldo);
            pstmt.setInt(2, accountId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
