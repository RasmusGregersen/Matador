package JUnitTestClass;

import entity.Player;
import entity.Rules;
import mysql.Connector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Player Test class that runs Player Junit tests.
 */

public class PlayerTest {
    private Player p;

    /**
     * Setup method that runs before each test.
     */

    @Before
    public void setUp() throws Exception {
        p = new Player("Test", 30000, 0, 0, 0, 0, 0, 0, false);
    }

    @Test
    public void NameTest() {
        String Expected = p.getName();
        String Result = "Test";
        assertEquals(Expected, Result);

    }

    @Test
    public void DepositBalanceTest() {
        p.depositBalance(1000);
        int Expected = 31000;
        int Result = p.getBalance();
        assertEquals(Expected, Result);
    }

    @Test
    public void WithdrawBalanceTest() {
        p.withdrawBalance(1000);
        int Expected = 29000;
        int Result = p.getBalance();
        assertEquals(Expected, Result);
    }

    @Test
    public void SetJailTurnsTest() {
        p.setJailturns(3);
        int Expected = 3;
        int Result = p.getJailturns();
        assertEquals(Expected, Result);
    }

    @Test
    public void getPlayerTest() throws SQLException {
        Connector con = new Connector();
        if (!Connector.isOffline()) {
            con.setDBname("Test");
            con.ResetDatabase();
            Rules.setPlayer(0, p);
            con.updatePlayer(0);
            p = con.getPlayer(0);
        }
        String expected = "Test";
        String result = p.getName();
        assertEquals(expected, result);
    }

    /**
     * Teardown method. That runs after each test.
     */

    @After
    public void TearDown() throws Exception {
        p = null;
    }
}
