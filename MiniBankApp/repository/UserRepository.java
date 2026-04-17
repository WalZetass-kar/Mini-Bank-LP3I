package repository;

import database.DatabaseConnection;
import model.User;
import java.sql.*;

public class UserRepository {
    public int createUser(User user) {
        String sql = "INSERT INTO users (nama, nik, tanggal_lahir, alamat, no_hp, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, user.getNama());
            pstmt.setString(2, user.getNik());
            pstmt.setString(3, user.getTanggalLahir());
            pstmt.setString(4, user.getAlamat());
            pstmt.setString(5, user.getNoHp());
            pstmt.setString(6, user.getCreatedAt());
            
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

    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("no_hp"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isNIKExists(String nik) {
        String sql = "SELECT COUNT(*) FROM users WHERE nik = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nik);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserByNIK(String nik) {
        String sql = "SELECT * FROM users WHERE nik = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nik);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("no_hp"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
