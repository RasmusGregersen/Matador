package JUnitTestClass;

import entity.Gameboard;
import entity.Player;
import entity.Rules;
import fields.Ownable;
import fields.Street;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBoardTest {


    private Player player;

    @Before
    public void setUp() throws Exception {
        Gameboard.CreateGUI();
        player = new Player("Test", 30000, 0, 1, 0, 0, 0, 0, false);
    }

    @Test
    public void HouseRentTest() {
        ((Street) Gameboard.getField(2)).addHouses(2);
        int Expected = 750;
        int Result = ((Street) Gameboard.getField(2)).getRent();
        assertEquals(Expected, Result);
    }

    @Test
    public void IsPropertyReadyTest() {
        ((Ownable) Gameboard.getField(2)).setOwner(player);
        ((Ownable) Gameboard.getField(4)).setOwner(player);
        boolean Expected = true;
        boolean Result = Gameboard.IsPropertyReady(player, "Lyseblå");
        assertEquals(Expected, Result);
    }

    @Test
    public void IsPropertyNotReadyTest() {
        ((Ownable) Gameboard.getField(7)).setOwner(player);
        ((Ownable) Gameboard.getField(9)).setOwner(player);
        boolean Expected = false;
        boolean Result = Gameboard.IsPropertyReady(player, "Orange");
        assertEquals(Expected, Result);
    }

    @Test
    public void ShippingRentTest() {
        ((Ownable) Gameboard.getField(6)).setOwner(player);
        player.setShipping();
        ((Ownable) Gameboard.getField(16)).setOwner(player);
        player.setShipping();
        int Expected = 1000;
        int Result = ((Ownable) Gameboard.getField(6)).getRent();
        assertEquals(Expected, Result);
    }

    @Test
    public void BreweryRentTest() {
        ((Ownable) Gameboard.getField(13)).setOwner(player);
        player.setBreweries();
        ((Ownable) Gameboard.getField(29)).setOwner(player);
        player.setBreweries();
        Rules.rollDice();
        int Expected = Rules.getDiceSum() * 200;
        int Result = ((Ownable) Gameboard.getField(13)).getRent();
        assertEquals(Expected, Result);
    }

    @Test
    public void LandonFieldStreetTest() {
        Player Test = new Player("Test", 30000, 0, 0, 0, 0, 0, 0, false);
        ((Ownable) Gameboard.getField(10)).setOwner(Test);
        player.setFieldPos(10);
        Gameboard.setField(player.getFieldPos(), player);
        int expected = 30000 - ((Street) Gameboard.getField(10)).getRent();
        int actual = this.player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void LandonFieldStreetHousesTest() {
        Player Test = new Player("Test", 30000, 0, 0, 0, 0, 0, 0, false);
        ((Ownable) Gameboard.getField(14)).setOwner(Test);
        ((Street) Gameboard.getField(14)).addHouses(3);
        player.setFieldPos(14);
        Gameboard.setField(player.getFieldPos(), player);
        int expected = 30000 - ((Ownable) Gameboard.getField(14)).getRent();
        int actual = this.player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void LandonFieldShippingTest() {
        Player Test = new Player("Test", 30000, 0, 0, 0, 2, 0, 0, false);
        ((Ownable) Gameboard.getField(6)).setOwner(Test);
        player.setFieldPos(6);
        Gameboard.setField(player.getFieldPos(), player);
        int expected = 30000 - ((Ownable) Gameboard.getField(6)).getRent();
        int actual = this.player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void LandonFieldParkingTest() {
        player.setFieldPos(21);
        Gameboard.setField(player.getFieldPos(), player);
        int expected = 30000;
        int actual = this.player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void MoveToFieldTest() {
        player.setFieldPos(37);
        player.moveToFieldPos(10);
        int expected = 30000 + 4000;
        int actual = this.player.getBalance();
        assertEquals(expected, actual);
    }

    @After
    public void TearDown() throws Exception {
        player = null;
    }
}

