package ua.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.Entity.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	private final Connection connection;
	
	public UserDao(Connection connection) {
		super();
		this.connection = connection;
	}

	public void save(User user) throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into users(name, password)" + " values (?, ?)");
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.execute();
	}

	public List<User> findAll() throws SQLException {
		ResultSet resultSet = connection.prepareStatement("select id, name, password from users").executeQuery();
		List<User> users = new ArrayList<>();
		while (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString("name"));
			user.setPassword(resultSet.getString("password"));
			users.add(user);
		}
		return users;
	}

	public void deleteByNameAndPassword(String name, String password) throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("delete from users where name = ? and password = ?");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		preparedStatement.execute();
	}
	
	public void updateByName(String name) throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("update users set name = 'updatedName' where name = ?");
		preparedStatement.setString(1, name);
		preparedStatement.execute();
	}
	
	public User findOneUserByName(String name) throws SQLException{
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("select id, name, password from users where name = ?");
		preparedStatement.setString(1, name);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = new User();
		if (resultSet.next()){
		user.setId(resultSet.getInt(1));
		user.setName(resultSet.getString(2));
		user.setPassword(resultSet.getString(3));
		System.out.println(user);
		}
		return user;
	}
	
	
	public User login(String name, String password) throws SQLException{
		 PreparedStatement preparedStatement= 
				 (PreparedStatement) connection
				 .prepareStatement("select id, name, password from users"
				+ " where name = ? and password = ?");
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		User user = new User();
		if(resultSet.next()){
			user.setId(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
		}
		return user;
	}
}
