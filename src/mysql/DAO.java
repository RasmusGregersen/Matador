package mysql;

import entity.Player;

import java.sql.SQLException;

interface DAO {
    Player getPlayer(int PlayerID) throws SQLException;

    void getField(int PlayerID) throws SQLException;
}
