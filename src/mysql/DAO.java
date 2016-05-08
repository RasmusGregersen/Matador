package mysql;

import entity.Player;

import java.sql.SQLException;

public interface DAO {
    Player getPlayer(int PlayerID) throws SQLException;
    void getField(int PlayerID) throws  SQLException;
}
