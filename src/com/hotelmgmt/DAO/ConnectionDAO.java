package com.hotelmgmt.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.RootLogger;


public class ConnectionDAO {      //For estabilishing the connection to Database
	static Connection con;
	static Logger logger=RootLogger.getLogger(ConnectionDAO.class.getName());
	
	static Connection connection; 
	public static Connection openConnection() {
		logger.info("You are in ConnectionDAO");
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String username = "system";
		String password = "system";
		PropertyConfigurator.configure("log4j.properties");
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {      // For Closing the connection from Database

		try {
			if (con != null)
				con.close();
			logger.info("You have closed the Connection");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}