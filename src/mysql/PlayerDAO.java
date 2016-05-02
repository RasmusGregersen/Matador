package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PlayerDAO implements PlayerDAOInterface {

    public PlayerDTO getPlayer(int PlayerID) throws SQLException {
        ResultSet rs = Connector.doQuery("SELECT * FROM Player WHERE PlayerID = " + PlayerID);
        try {
            if (!rs.first()) throw new DALException("Operatoeren " + PlayerID + " findes ikke");
            return new PlayerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
        }
        catch (SQLException e) {throw new DALException(e); }

    }

    public void createPlayer(PlayerDTO p) throws DALException {
        Connector.doUpdate(
                "INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES " +
                        "(" + opr.getOprId() + ", '" + opr.getOprNavn() + "', '" + opr.getIni() + "', '" +
                        opr.getCpr() + "', '" + opr.getPassword() + "')"
        );
    }

    public void updatePlayer(PlayerDTO p) throws DALException {
        Connector.doUpdate(
                "UPDATE operatoer SET  opr_navn = '" + opr.getOprNavn() + "', ini =  '" + opr.getIni() +
                        "', cpr = '" + opr.getCpr() + "', password = '" + opr.getPassword() + "' WHERE opr_id = " +
                        opr.getOprId()
        );
    }

    public List<PlayerDTO> getPlayerList() throws DALException {
        List<PlayerDTO> list = new ArrayList<PlayerDTO>();
        ResultSet rs = Connector.doQuery("SELECT * FROM Player");
        try
        {
            while (rs.next())
            {
                list.add(new PlayerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
            }
        }
        catch (SQLException e) { throw new DALException(e); }
        return list;
    }
}
