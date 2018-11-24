package other;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
		private String DB_Name;
		// JDBC驱动名和数据库的URL
		private final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		private String DB_URL; 
		// 数据库的用户名和密码
		private final static String  USER = "root";
		private final static String PASSWORD = "123456";
		private Connection connection;
		
	public Database(String dbName) {
		this.DB_Name = dbName;
		DB_URL = "jdbc:mysql://localhost:3306/"+DB_Name+"?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";;

		init();
	}
	
	public void init() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean delete(String sql) {
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			return(1 == ptmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		// TODO Auto-generated method stub
		
	}

	public boolean update(String sql, ArrayList<String> information) {
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			int i = 1;
			for(String info: information) {
				ptmt.setString(i, info);
				i++;
			}
			return(1 == ptmt.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public ResultSet querry(String sql) {
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			ResultSet resultSet = ptmt.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

		// TODO Auto-generated method stub
		
	}

	public void add(String sql, ArrayList<String> information) throws IOException {
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
			int i = 1;
			for(String info: information) {
				ptmt.setString(i, info);
				i++;
			}
			ptmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
