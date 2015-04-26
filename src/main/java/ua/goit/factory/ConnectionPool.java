package ua.goit.factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The ConnectionPool class represents a pool of connections to one database.
 */
public class ConnectionPool {
	private String name;
	private String URL;
	private String user;
	private String password;
	private int maxConn;
	private int checkedOut;
	private Vector<Connection> freeConnections = new Vector<>();

	/** The ConnectionPool constructor calls from DBConnectionManager while creating
	 * instance of ConnectionPool class, and take parameters value from properties file*/ 
	public ConnectionPool(String name, String URL, String user,
			String password, int maxConn) {
		this.name = name;
		this.URL = URL;
		this.user = user;
		this.password = password;
		this.maxConn = maxConn;
	}

	/** Return an existing Connection if one is available, otherwise create a new Connection.
	 * If no Connection is available and the max number of connections have been reached, 
	 * method returns null
	 */
	public synchronized Connection getConnection() {
		Connection con = null;
		if (freeConnections.size() > 0) {
			// Pick the first Connection in the Vector
			con = (Connection) freeConnections.firstElement();
			freeConnections.removeElementAt(0);
			try {
				if (con.isClosed()) {
					// Try again recursively
					con = getConnection();
				}
			}
			catch (SQLException e) {
				// Try again recursively
				con = getConnection();
			}
		}
		else if (maxConn == 0 || checkedOut < maxConn) { //A maxConn value of 0 means "no limit".
			con = newConnection();
		}
		if (con != null) {
			checkedOut++;
		}
		return con;
	}

	/** Create and return new connection to database with connection's parameters*/
	private Connection newConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, user, password);
		}
		catch (SQLException e) {			
			throw new RuntimeException(e);			
		}
		return con;
	}

	/** Return a connection to the pool.*/
	public synchronized void freeConnection(Connection con) {
		freeConnections.addElement(con);
		checkedOut--;
		notifyAll();
	}

	/** Method loops through the freeConnections Vector and closes all Connections.
	 * When all Connections have been closed they are removed from the Vector.
	 */
	public synchronized void release() {
		Enumeration<Connection> allConnections = freeConnections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
			}
			catch (SQLException e) {
				throw new RuntimeException ("Can't close connection for pool " + name, e);
			}
		}
		freeConnections.removeAllElements();
	}
}
