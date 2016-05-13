package mysql;

import java.sql.SQLException;

interface DTO {
    void removePlayer(int PlayerID) throws SQLException;

    void updatePlayer(int PlayerID) throws SQLException;

    void updateField(int FieldID) throws SQLException;
}
