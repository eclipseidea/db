package ua.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import ua.Entity.Orders;
import ua.Entity.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class OrderDao {
	private final Connection connection;

	public OrderDao(Connection connection) {
		this.connection = connection;
	}

	public Orders order(User user) {
		Orders orders = new Orders();
		orders.setLocalDate(LocalDate.now());
		orders.setUser(user);
		orders.setCommodity(user.getCommodities().get(
				user.getCommodities().size() - 1));
		orders.setCounter(user.getCommodities()
				.get(user.getCommodities().size() - 1).getCounter());
		return orders;

	}

	public void save(Orders orders) throws SQLException {
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into orders "
						+ "(order_date, user_id, commodity_id, counter)"
						+ " values (?,?,?,?)");
		preparedStatement.setString(1, orders.getLocalDate().toString());
		preparedStatement.setInt(2, orders.getUser().getId());
		preparedStatement.setInt(3, orders.getCommodity().getId());
		preparedStatement.setInt(4, orders.getCommodity().getCounter());
		preparedStatement.execute();
	}
  
	
//	public void sssss(int a) throws SQLException{
//		PreparedStatement preparedStatement= 
//				 (PreparedStatement) connection
//				 .prepareStatement("select  counter from commodity where commodity.name = 'r' ");
//				ResultSet resultSet = preparedStatement.executeQuery();
//		if(resultSet.next())
//			resultSet.getInt(4);
//			if (resultSet.getType()>=a){System.out.println("dhdhdhdh");}
//		else{System.out.println("not");}
//	}
}
