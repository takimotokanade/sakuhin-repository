package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	//定数宣言
	private static final String DATABASE_NAME = "sakuhin_sample";
	private static final String PROPATIES = "?characterEncoding=utf-8";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPATIES;
	private static final String USER = "root";
	private static final String PASS = "";
	
	// JDBCドライバを1回だけ読み込む
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("JDBCドライバの読み込みに失敗しました: " + e.getMessage());
        }
    }
	
	//インスタンス化を防ぐ
	private DatabaseConnection() {
		
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
	// データベース接続をクローズ
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("データベース接続を閉じました");
            } catch (SQLException e) {
                System.err.println("データベース接続のクローズに失敗しました: " + e.getMessage());
            }
        }
    }
}
