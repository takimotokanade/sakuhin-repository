package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseConnection;
import dto.UsersDTO;

public class UsersDAO {
	//データベースから
	////入力されたログインIDとパスワードが一致するか確認する処理
	
	public UsersDTO login(String username, String password) {
		String sql = "SELECT id, password, is_admin, last_name, first_name FROM users WHERE username = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement prst = con.prepareStatement(sql)) {
            
            prst.setString(1, username);
            System.out.println(prst.toString()); // デバッグ用
            
            try (ResultSet rs = prst.executeQuery()) {
                if (rs.next()) {
                    String dbPassword = rs.getString("password");
                    boolean isAdmin = rs.getBoolean("is_admin");
                    String lastName = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    
                    // パスワードが一致するか確認
                    if (dbPassword.equals(password)) {
                        return new UsersDTO(rs.getInt("id"), username, password, isAdmin, lastName, firstName);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null; // ログイン失敗
	}
}
