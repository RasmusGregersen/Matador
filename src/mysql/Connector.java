package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.Driver;

public class Connector {
	static Connection conn = null;
	static final String connectionUrl = "jdbc:mysql://dtu.czx5ninmk2ar.eu-west-1.rds.amazonaws.com:3306/Matador";
	static final String connectionUser = "cdio";
	static final String connectionPassword = "matador.CDIO";
	
	public static void Connect() {
			try {	
				new com.mysql.jdbc.Driver();
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
				}
			catch (Exception e) {
				e.printStackTrace();
				} 	
			finally {
				try { if (conn == null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}

	}
	public static String getPlayer() {
		Statement stmt = null;
		ResultSet rs = null;
		String name = null;
		try {
			Connector.Connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Player");
			while (rs.next()) {
				//String PlayerID = rs.getString("PlayerID");
				name = rs.getString("Name");
				//String Balance = rs.getString("Balance");

			}
		Connector.conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
}