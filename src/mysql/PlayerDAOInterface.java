package mysql;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAOInterface {
    PlayerDTO getPlayer(int PlayerID) throws SQLException;
    List<PlayerDTO> getPlayerList() throws SQLException;
    void createPlayer(PlayerDTO p) throws SQLException;
    void updatePlayer(PlayerDTO p) throws SQLException;
}
