package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	private static Connection con;
    private static Statement stm;

	private static String connectionUrl = "jdbc:mysql://dtu.czx5ninmk2ar.eu-west-1.rds.amazonaws.com:3306/Matador";
	private static String connectionUser = "cdio";
	private static String connectionPassword = "matador.CDIO";

    public Connector()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        con	= connectToDatabase();
        stm	= con.createStatement();
    }


    public static Connection connectToDatabase()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return (Connection) DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
    }

	public static ResultSet doQuery(String query)
            throws DALException
    {
        try
        {
            return stm.executeQuery(query);
        }
        catch (SQLException e)
        {
            throw new DALException(e);
        }
    }

    public static void doUpdate(String query)
            throws DALException
    {
        try
        {
            stm.executeUpdate(query);
        }
        catch  (SQLException e)
        {
            throw new DALException(e);
        }
    }
}