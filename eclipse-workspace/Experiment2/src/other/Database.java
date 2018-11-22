package other;

import java.io.IOException;
import java.io.InputStream;
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
		private String DB_URL = "jdbc:mysql://localhost:3306/goods?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL=false";;
		// 数据库的用户名和密码
		private final static String  USER = "root";
		private final static String PASSWORD = "123456";
		private Connection connection;
		
	public Database() {
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
	
	
	public boolean delete() {
		String sql = "Delete From goodsinfo where customerId=?";
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

	public boolean update(String id,  ArrayList<String> information) {
		String sql = "UPDATE goodsinfo set path=? WHERE id="+id;
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

	public ResultSet querry(String id) {
		String sql;
		if(!id.equals("")) {
			 sql = "SELECT FROM goodsinfo WHERE id=" + id;
		}
		else {
			sql = "SELECT path,id,name,product,type,typeNumber,description FROM goodsinfo";
		}
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

	public void add(ArrayList<String> information) throws IOException {
		String sql = "INSERT INTO goodsinfo(path,id,name,product,type,typeNumber,description)values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ptmt = connection.prepareStatement(sql);
//			ptmt.setString(1, goods.getPath());
//			ptmt.setString(2, goods.getId());
//			ptmt.setString(3, goods.getName());
//			ptmt.setString(4, goods.getProduct());
//			ptmt.setString(5, goods.getType());
//			ptmt.setString(6, goods.getTypeNumber());
//			ptmt.setString(7, goods.getDescription());
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
