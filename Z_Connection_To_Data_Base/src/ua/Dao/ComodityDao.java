package ua.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ua.Entity.Commodity;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ComodityDao {
	private final Connection connection;

	public ComodityDao(Connection connection) {
		this.connection = connection;
	}

	public List<Commodity> findAll() throws SQLException {
		ResultSet resultSet = connection.prepareStatement(
				"select id, name, price, counter " + "from commodity")
				.executeQuery();
		List<Commodity> commodities = new ArrayList<>();
		while (resultSet.next()) {
			Commodity commodity = new Commodity();
			commodity.setId(resultSet.getInt(1));
			commodity.setName(resultSet.getString(2));
			commodity.setPrice(resultSet.getDouble(3));
			commodity.setCounter(resultSet.getInt(4));
			commodities.add(commodity);
		}
		return commodities;
	}
  
	public void addCommodity(Commodity commodity, int counter) throws SQLException{
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("insert into commodity (name, price, counter) values (?, ?, ?)");
		preparedStatement.setString(1, commodity.getName());
		preparedStatement.setDouble(2, commodity.getPrice());
		preparedStatement.setInt(3, counter);
		preparedStatement.execute();
	}
	
	public void updateCommodityByName(String name) throws SQLException{
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement("update commodity set name = 'newComName' where name = ? ");
				
		preparedStatement.setString(1, name);

		preparedStatement.execute();
	}
	
	
	
	
}
