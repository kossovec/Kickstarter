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
	private HashMap<String, ConnectionPool> pools = new HashMap<>();
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
		InputStream is = getClass().getResourceAsStream("/db.properties");
		Properties dbProps = new Properties();
		try {
			dbProps.load(is);
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
		loadDrivers(dbProps);
		createPools(dbProps);
	}

	/** Loading the drivers*/
	private void loadDrivers(Properties props) {
		String driverClass = props.getProperty("drivers");
		try {
			Driver driver = (Driver)Class.forName(driverClass).newInstance();
			DriverManager.registerDriver(driver);			
		}
		catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	/** Create the pool*/
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		int max = 0;
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				max = Integer.parseInt(maxconn);
				ConnectionPool pool = new ConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
			}
		}
	}

	/** Get ConnectionPool by the name*/
	public Connection getConnection(String name) {
		ConnectionPool pool = (ConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/** Free the connection*/
	public void freeConnection(String name, Connection con) {
		ConnectionPool pool = (ConnectionPool) pools.get(name);
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
		Collection<ConnectionPool> allPools = pools.values();
		Iterator poolsIterator = allPools.iterator();
		while (poolsIterator.hasNext()) {
			ConnectionPool pool = (ConnectionPool) poolsIterator.next();
			pool.release();
		}
		try {
			DriverManager.deregisterDriver(driver);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}