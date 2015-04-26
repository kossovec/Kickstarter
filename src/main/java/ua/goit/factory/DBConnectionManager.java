package ua.goit.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

/**
 * The DBConnectionManager class manages multiple connection pools. It
 * loads and registers JDBC driver, creates ConnectionPool objects
 * based on properties defined in a properties file, maps connection pool 
 * names to ConnectionPool instances, keeps track of connection pool 
 * clients to shut down all pools gracefully when the last client is done.
 * The DBConnectionManager class is implemented according to the Singleton pattern. 
 */
public class DBConnectionManager {
	private static DBConnectionManager instance;
	private static int clients;
	private ConnectionPool pool;
	private Driver driver;


	private DBConnectionManager() {
		init();
	}

	/** Return one instance of DBConnectionManager class*/
	public static synchronized DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}

	/** Read properties from file, load the drivers, create the ConnectionPool */
	private void init() {
		loadDrivers();
		createPools();
	}

	/** Loading the drivers*/
	private void loadDrivers() {
		try {
			Driver driver = (Driver)Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);			
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	/** Create the pool*/
	private void createPools() {
		String url = Constants.DB_URL;
		String user = Constants.USER;
		String password = Constants.PASS;
		int maxConn = Constants.MAX_CONN;
		pool = new ConnectionPool(url, user, password, maxConn);		
	}

	/** Get ConnectionPool by the name*/
	public Connection getConnection() {
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/** Free the connection*/
	public void freeConnection(Connection con) {
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	/** Shutdown the ConnectionPool. The release() method is called by 
	 * each client during shutdown and the client counter is decremented. 
	 * When the last client calls release(), the DBConnectionManager 
	 * calls release() on all DBConnectionPool objects to close all connections.*/
	public synchronized void release() {
		// Wait until called by the last client
		if (--clients != 0) {
			return;
		}
		pool.release();
		try {
			DriverManager.deregisterDriver(driver);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}