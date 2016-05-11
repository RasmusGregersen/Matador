package mysql;

import java.sql.*;

import desktop_resources.GUI;
import entity.Gameboard;
import entity.Player;
import entity.PlayerOptions;
import entity.Rules;
import fields.Brewery;
import fields.Shipping;
import fields.Street;
import fields.Ownable;

public class Connector implements DAO,DTO {
    private static Connection con;
    private PreparedStatement psstm;
    private Statement stm;

    private String DBname = "";
    private String connectionUrl = "jdbc:mysql://dtu.czx5ninmk2ar.eu-west-1.rds.amazonaws.com:3306/";
    private final String connectionUser = "cdio";
    private final String connectionPassword = "matador.CDIO";

    public void setDBname(String DBname) {
    this.DBname = DBname;
    }

    public void ResetDatabase() throws SQLException {
        try {
            String DropField = "DROP TABLE IF EXISTS "+DBname+".Field";
            String DropPlayer = "DROP TABLE IF EXISTS "+DBname+".Player";
            String PlayerDDL =
                    "CREATE TABLE $DBname.Player (\n" +
                    "  PlayerID INT(1) NOT NULL,\n" +
                    "  Name VARCHAR(20),\n" +
                    "  Balance INT,\n" +
                    "  TotalAssets INT,\n" +
                    "  FieldPos INT(2),\n" +
                    "  Breweries INT(1),\n" +
                    "  Shipping INT(1),  \n" +
                    "  JailCards INT(1),\n" +
                    "  JailTurns INT(1),\n" +
                    "  Jailed BIT(1),\n" +
                    "  PRIMARY KEY (`PlayerID`),\n" +
                    "  UNIQUE INDEX `PlayerID_UNIQUE` (`PlayerID` ASC));";
            String FieldDDL =
                    "CREATE TABLE $DBname.Field (\n" +
                    "  FieldID INT(2) NOT NULL,\n" +
                    "  Owner INT(1),\n" +
                    "  FOREIGN KEY (`Owner`) REFERENCES "+DBname+".Player(PlayerID),\n" +
                    "  Houses VARCHAR(20) DEFAULT NULL,\n" +
                    "  Pawned BIT(1),\n" +
                    "  PRIMARY KEY (`FieldID`),\n" +
                    "  UNIQUE INDEX `FieldID_UNIQUE` (`FieldID` ASC));";
            PlayerDDL = PlayerDDL.replace("$DBname",DBname);
            FieldDDL = FieldDDL.replace("$DBname",DBname);
            stm = con.createStatement();
            stm.execute(DropField);
            stm.execute(DropPlayer);
            psstm = con.prepareStatement(PlayerDDL);
            psstm.execute();
            psstm = con.prepareStatement(FieldDDL);
            psstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            stm.close();
            psstm.close();
        }
    }

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
        return DriverManager.getConnection(connectionUrl+DBname, connectionUser, connectionPassword);
    }

    public Player getPlayer(int PlayerID) throws SQLException {
        String SQL = "SELECT * FROM $DBname.Player WHERE PlayerID = ?";
        Player player = null;
        ResultSet result = null;
        try {
            SQL = SQL.replace("$DBname",DBname);
            psstm = con.prepareStatement(SQL);
            psstm.setInt(1, PlayerID);
            result = psstm.executeQuery();
            if (result.next())
            player = new Player(result.getString("Name"),result.getInt("Balance"),result.getInt("TotalAssets"),result.getInt("FieldPos"),
                    result.getInt("Breweries"),result.getInt("Shipping"), result.getInt("JailCards"),result.getInt("JailTurns"),result.getBoolean("Jailed"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            psstm.close();
        }

        return player;
    }

    public void updatePlayer(int PlayerID) throws SQLException{
        try {
            Player p = Rules.getPlayer(PlayerID);
            String SQL =
                    "INSERT INTO $DBname.Player (PlayerID,Name,Balance,TotalAssets,FieldPos,Breweries,Shipping,JailCards,JailTurns,Jailed)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)\n" +
                    "ON DUPLICATE KEY UPDATE PlayerID=VALUES(PlayerID),Name=VALUES(Name),Balance=VALUES(Balance),TotalAssets=VALUES(TotalAssets), FieldPos=VALUES(FieldPos), Breweries=VALUES(Breweries),Shipping=VALUES(Shipping),JailCards=VALUES(JailCards),JailTurns=VALUES(JailTurns), Jailed=VALUES(Jailed);";
            SQL = SQL.replace("$DBname",DBname);
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
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            psstm.close();
        }
    }

    public void removePlayer(int PlayerID) throws SQLException{
        try {
            Player p = Rules.getPlayer(PlayerID);
            String SQL =
                    "DELETE FROM $DBname.Player WHERE PlayerID=?;" +
                    "DELETE FROM $DBname.Player WHERE Owner =?";
            SQL = SQL.replace("$DBname",DBname);
            psstm = con.prepareStatement(SQL);
            psstm.setInt(1, PlayerID);
            psstm.setInt(2, PlayerID);
            psstm.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            psstm.close();
        }
    }

    public void getField(int PlayerID) throws SQLException {
        try {
            String SQL = "SELECT * FROM $DBname.Field WHERE Owner = ?";
            SQL = SQL.replace("$DBname",DBname);
            psstm = con.prepareStatement(SQL);
            psstm.setInt(1, PlayerID);
            ResultSet result = psstm.executeQuery();
            while (result.next()) {
                int FieldID = result.getInt("FieldID");
                ((Ownable) Gameboard.getField(FieldID)).setOwner(Rules.getPlayer(PlayerID));
                GUI.setOwner(FieldID, Rules.getPlayer(PlayerID).getName());
                if (result.getBoolean("Pawned")) {
                    ((Ownable) Gameboard.getField(FieldID)).setPawned(true);
                    GUI.setSubText(FieldID, "Pantsat!");
                }
                if (Gameboard.getField(FieldID) instanceof Street) {
                    ((Street) Gameboard.getField(FieldID)).addHouses(result.getInt("Houses"));
                    PlayerOptions.HouseorHotel(FieldID);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            psstm.close();
        }
    }

    public void updateField(int PlayerID) throws SQLException {
        String SQL = "INSERT INTO $DBname.Field (FieldID,Owner,Houses,Pawned) VALUES (?,?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE FieldID=VALUES(FieldID),Owner=VALUES(Owner),Houses=VALUES(Houses),Pawned=VALUES(Pawned);";
        try {
            SQL = SQL.replace("$DBname",DBname);
            psstm = con.prepareStatement(SQL);
            for (int i = 1; i<41; i++) {
                if (Gameboard.getField(i) instanceof Street || Gameboard.getField(i) instanceof Shipping || Gameboard.getField(i) instanceof Brewery) {
                    if ((((Ownable) Gameboard.getField(i)).getOwner() == Rules.getPlayer(PlayerID))) {
                        psstm.setInt(1, i);
                        psstm.setInt(2, PlayerID);
                        if (Gameboard.getField(i) instanceof Street)
                            psstm.setInt(3, ((Street) Gameboard.getField(i)).getHouses());
                        else
                            psstm.setInt(3, 0);
                        psstm.setBoolean(4, ((Ownable) Gameboard.getField(i)).isPawned());
                        psstm.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            psstm.close();
        }
    }
}