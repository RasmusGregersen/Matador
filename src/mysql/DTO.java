package mysql;

import java.sql.SQLException;

public interface DTO {
    void updatePlayer(int PlayerID) throws SQLException;
    void updateField(int FieldID) throws SQLException;
}
