package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import JUnitTestClass.SQL;
import entity.Player;
import entity.Rules;

public class Connector implements DAO,DTO {
	private static Connection con;
    private PreparedStatement psstm;

	private final String connectionUrl = "jdbc:mysql://dtu.czx5ninmk2ar.eu-west-1.rds.amazonaws.com:3306/Matador";
	private final String connectionUser = "cdio";
	private final String connectionPassword = "matador.CDIO";

    public Connector()
    {
        try {
            con	= connectToDatabase();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Connection connectToDatabase()
            throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
    }

    public Player getPlayer(int PlayerID) throws SQLException {
            String SQL = "SELECT * FROM Player WHERE PlayerID = ?";
            psstm = con.prepareStatement(SQL);
            psstm.setInt(1, PlayerID);
            ResultSet result = psstm.executeQuery();
            result.next();
            Player player = new Player(result.getString("Name"),result.getInt("Balance"),result.getInt("TotalAssets"),result.getInt("FieldPos"),
                    result.getInt("Breweries"),result.getInt("Shipping"), result.getInt("JailCards"),result.getInt("JailTurns"),result.getBoolean("Jailed"));
            return player;
    }

    public void updatePlayer(int PlayerID) throws SQLException{
        Player p = Rules.getPlayer(PlayerID);
        String SQL = "INSERT INTO Matador.Player " +
                "SET PlayerID = ? ON DUPLICATE KEY UPDATE " +
                "Name = ?, Balance = ?, TotalAssets = ?, FieldPos = ?, Breweries = ?, Shipping = ?, JailCards = ?, JailTurns = ?, Jailed = ?";
      psstm = con.prepareStatement(SQL);
        psstm.setInt(1, PlayerID);
        psstm.setString(2, p.getName());
        psstm.setInt(3, p.getBalance());
        psstm.setInt(4, p.getTotalAssets());
        psstm.setInt(5, p.getFieldPos());
        psstm.setInt(6, p.getBreweries());
        psstm.setInt(7, p.getShipping());
        psstm.setInt(8, p.getJailcard());
        psstm.setInt(9, p.getJailturns());
        psstm.setBoolean(10, p.isJailed());
        psstm.executeUpdate();
    }

    public void getField(int PlayerID) throws SQLException {

    }

    public void updateField(int FieldID) throws SQLException {

    }

}