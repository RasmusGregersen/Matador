package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import com.mysql.jdbc.Driver;

public class Connector {
	public static void Connect() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String connectionUrl = "jdbc:mysql://dtu.czx5ninmk2ar.eu-west-1.rds.amazonaws.com:3306/Matador";
		String connectionUser = "cdio";
		String connectionPassword = "matador.CDIO";
			try {	
				new com.mysql.jdbc.Driver();
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM employees");
				}
			catch (Exception e) {
				e.printStackTrace();
				} 	
			finally {
				try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
				try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
	}
}