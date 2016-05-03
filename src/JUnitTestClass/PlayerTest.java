package JUnitTestClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entity.Player;
import static org.junit.Assert.*;

/**
 * Created by Moulvad on 03/05/16.
 */
public class PlayerTest {
    Player p;
    @Before
    public void setUp() throws Exception {
        p = new Player("Test");
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


    @After
    public void TearDown() throws Exception {
        p =null;
    }

}