package repository;

import database.DatabaseConnection;
import model.Card;
import java.sql.*;

public class CardRepository {
    public int createCard(Card card) {
        String sql = "INSERT INTO cards (account_id, no_kartu, pin, status, expired_date, wrong_pin_counter) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, card.getAccountId());
            pstmt.setString(2, card.getNoKartu());
            pstmt.setString(3, card.getPin());
            pstmt.setString(4, card.getStatus());
            pstmt.setString(5, card.getExpiredDate());
            pstmt.setInt(6, card.getWrongPinCounter());
            
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

    public Card getCardByAccountId(int accountId) {
        String sql = "SELECT * FROM cards WHERE account_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Card(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("no_kartu"),
                    rs.getString("pin"),
                    rs.getString("status"),
                    rs.getString("expired_date"),
                    rs.getInt("wrong_pin_counter")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Card getCardByNoKartu(String noKartu) {
        String sql = "SELECT * FROM cards WHERE no_kartu = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, noKartu);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Card(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("no_kartu"),
                    rs.getString("pin"),
                    rs.getString("status"),
                    rs.getString("expired_date"),
                    rs.getInt("wrong_pin_counter")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateWrongPinCounter(int cardId, int counter) {
        String sql = "UPDATE cards SET wrong_pin_counter = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, counter);
            pstmt.setInt(2, cardId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCardStatus(int cardId, String status) {
        String sql = "UPDATE cards SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, cardId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePin(int cardId, String newPin) {
        String sql = "UPDATE cards SET pin = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newPin);
            pstmt.setInt(2, cardId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Card getCardById(int cardId) {
        String sql = "SELECT * FROM cards WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, cardId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Card(
                    rs.getInt("id"),
                    rs.getInt("account_id"),
                    rs.getString("no_kartu"),
                    rs.getString("pin"),
                    rs.getString("status"),
                    rs.getString("expired_date"),
                    rs.getInt("wrong_pin_counter")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
