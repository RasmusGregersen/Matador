package JUnitTestClass;

import entity.Rules;
import mysql.Connector;
import org.junit.Before;
import org.junit.Test;
import entity.Player;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SQL {

    private Connector con;

    @Before
    public void setUp() throws Exception {
        con = new Connector();
    }

    @Test
    public void getPlayerTest() throws SQLException {
        Player p = new Player("Test", 1000, 0, 0, 0, 0, 0, 0, false);
        Rules.setPlayer(0, p);
        con.updatePlayer(0);
        Player p2 = con.getPlayer(0);
        String expected = "Test";
        String result = p2.getName();
        assertEquals(expected, result);
    }

    @Test
    public void getFieldTest() throws SQLException {

    }
}
