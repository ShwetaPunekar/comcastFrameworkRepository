package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class DatabaseUtility 
{
	//declare globally so that we can use to this for another method
	Connection conn;
	public void getDBConnection(String url, String username, String password) throws SQLException
	{
		//if connection is not happend put this in try and catch block
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		conn=DriverManager.getConnection(url, username, password);
		}catch(Exception e) {}
	}
	//have to use based on order to connect to data base 
	public void getDBConnection() throws SQLException
	{
		//if connection is not happend put this in try and catch block
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		}catch(Exception e) {}
	}
	
	public void closeDBConnection() throws SQLException
	{
		//connection itself not happend close try catch block will handle
		try {
			conn.close();
		} catch (Exception e) {
		}
		
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{
		ResultSet result=null;
		try {
		Statement stat = conn.createStatement();
		result = stat.executeQuery(query);
		}catch(Exception e) {}
		
		//this method result set is table in result we have entire table
		return result;
	}
	
	public int excecuteNonSelectQuery(String query)
	{
		int result=0;
		try {
			Statement stat=conn.createStatement();
			result=stat.executeUpdate(query);
		} catch (Exception e) {}
		
		return result;
	}
}
