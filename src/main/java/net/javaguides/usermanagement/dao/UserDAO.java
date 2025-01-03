package net.javaguides.usermanagement.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import net.javaguides.usermanagement.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/ishara?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "12345";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (name, nic, email, address, comments, date) VALUES" + "(?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,name,nic,email,address,comments,date from users where id=?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,nic= ?, email=?, address=?, comments=?, date=? where id=?;";
	
	
	public UserDAO() {
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(User user) throws SQLException{
		System.out.println(INSERT_USERS_SQL);
		try(Connection connection = getConnection();
				PreparedStatement preparedStatment = (PreparedStatement) connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatment.setString(1, user.getName());
			preparedStatment.setString(2, user.getNic());
			preparedStatment.setString(3, user.getEmail());
			preparedStatment.setString(4, user.getAddress());
			preparedStatment.setString(5, user.getComments());
			preparedStatment.setString(6, user.getDate());
			preparedStatment.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	private void printSQLException(SQLException e) {
		// TODO Auto-generated method stub
		
	}

	public boolean updateUser(User user) throws SQLException{
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(UPDATE_USERS_SQL);){
			statement.setString(1, user.getName());
			statement.setString(2, user.getNic());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getComments());
			statement.setString(6, user.getDate());
			statement.setInt(7, user.getId());
			
			rowUpdated = statement.executeUpdate()>0;
		}
		return rowUpdated;
	}
	
	public User selectUser(int id) {
		User user = null;
		
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_USER_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("name");
				String nic = rs.getString("nic");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String comments = rs.getString("comments");
				String date = rs.getString("date");
				user = new User(id,name,nic,email,address,comments,date);
			};
		}catch(SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SELECT_ALL_USERS);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String nic = rs.getString("nic");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String comments = rs.getString("comments");
				String date = rs.getString("date");
				users.add(new User(id,name,nic,email,address,comments,date));
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public boolean deleteUser(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate()>0;
		}
		return rowDeleted;
	}
	
	private void printSQLException1(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
