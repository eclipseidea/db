package ua.lviv.controller;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import ua.Dao.ComodityDao;
import ua.Dao.OrderDao;
import ua.Dao.UserDao;
import ua.Entity.Commodity;
import ua.Entity.User;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Main_Database {
	final static String URL = "jdbc:mysql://localhost/comodityUser?autoReconnect=true&useSSL=false";
	final static String USER = "root";
	final static String PASSWORD = "root";
	public static Scanner scanner = new Scanner(System.in);
	static Connection connection;

	public static void main(String[] args) throws SQLException {
		connection = (Connection) DriverManager.getConnection(URL, USER,
				PASSWORD);
		createTables(connection);
		User cuurentUser = null;
		UserDao userDao = new UserDao(connection);
		ComodityDao commodityDao = new ComodityDao(connection);
		OrderDao orderDao = new OrderDao(connection);

		while (true) {

			System.out.println("enter 1 for create new user");
			System.out.println("enter 2 for login");
			System.out.println("enter 3 to select commodity from list");
			System.out.println("enter 4 to add commodity");
			System.out.println("enter 5 to update commodity");
			System.out.println("enter 6 to update user");
			System.out.println("enter 7 to delete user");
			System.out.println("enter 8 to find user data by user name");
			System.out.println("enter 0 to program exit");
			String choise = scanner.next();

			switch (choise) {
			case "1": {
				System.out.println("enter name");
				String name = scanner.next();
				System.out.println("enter password");
				String password = scanner.next();
				User user = new User(name, password);
				userDao.save(user);
				break;
			}
			case "2": {
				while (cuurentUser == null) {
					System.out.println("enter name for login");
					String name = scanner.next();
					System.out.println("enter password for login");
					String password = scanner.next();
					cuurentUser = userDao.login(name, password);
				}
				System.out.println(cuurentUser);
				break;
			}
			case "3": {
				for (Commodity commodity : commodityDao.findAll()) {System.out.println(commodity);}
				System.out.println("enter name of commodity");
				String userCommodity = scanner.next();
				System.out.println(userCommodity);
				for (Commodity commodity : commodityDao.findAll()) {
					if (commodity.getName().equals(userCommodity)) {
						System.out.println("enter number of commodity which you want to get");
						int number = scanner.nextInt();
						commodity.setCounter(number);
						commodity.setCounter(commodity.getCounter() - number);
						cuurentUser.getCommodities().add(commodity);
					}
				}
				orderDao.save(orderDao.order(cuurentUser));
				cuurentUser = null;

			}
			// to add commodity
			case "4": {
				System.out.println("enter name of commodity");
				String commodityName = scanner.next();
				System.out.println("enter price of commodity");
				double price = scanner.nextDouble();
				System.out.println("enter quantity of commodities");
				int counter = scanner.nextInt();
				Commodity commodity = new Commodity(commodityName, price);
				System.out.println(commodity);
				commodityDao.addCommodity(commodity, counter);
				break;
			}
			// to update commodity
			case "5": {
				System.out.println("enter name");
				String name = scanner.next();
				commodityDao.updateCommodityByName(name);
				break;
			}
			// to update user 
			case "6": {
				System.out.println("enter name");
				String name = scanner.next();
				userDao.updateByName(name);
				break;

			}
			//to delete user +
			case "7": {
				System.out.println("enter name");
				String name = scanner.next();
				System.out.println("enter password");
				String password = scanner.next();
				userDao.deleteByNameAndPassword(name, password);
				break;
			}
			case "8": {
				System.out.println("enter name");
				String name = scanner.next();
				userDao.findOneUserByName(name);
				break;
			}
					
			case "0": {
				System.exit(5);
				break;
			}

			}
		}

	}

	public final static void createTables(Connection connection)
			throws SQLException {
		try {
			PreparedStatement preparedStatement;

			preparedStatement = (PreparedStatement) connection
					.prepareStatement("create table users(id int primary key auto_increment, name varchar(40), password  varchar(50))");

			preparedStatement.execute();

			preparedStatement = (PreparedStatement) connection
					.prepareStatement("create table commodity(id int primary key auto_increment, name varchar(40), price double, counter int)");

			preparedStatement.execute();

			preparedStatement = (PreparedStatement) connection
					.prepareStatement("create table orders(id int primary key auto_increment,order_date date, user_id int, commodity_id int, counter int)");

			preparedStatement.execute();

			preparedStatement = (PreparedStatement) connection
					.prepareStatement("alter table orders add constraint foreign key(user_id) references users(id)");

			preparedStatement.execute();

			preparedStatement = (PreparedStatement) connection
					.prepareStatement("alter table orders add constraint foreign key(commodity_id) references commodity(id)");

			preparedStatement.execute();

		} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
			System.out.println("Tables already exist");
		}
	}

}
