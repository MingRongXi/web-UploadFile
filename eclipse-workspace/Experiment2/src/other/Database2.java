package other;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database2 {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/loginformation?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
	// 数据库的用户名和密码
	static final String  USER = "root";
	static final String PASSWORD = "123456";
	// 数据库的连接
	static Connection connection;
	
	public Database2() {	
		init();
	}
	
	private void init() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("database");
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet querry(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM id_password WHERE id = " + id;
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ResultSet resultSet = ptmt.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

