package JUnitTestClass;

import com.sun.tools.classfile.Field;
import com.sun.tools.javac.comp.Check;
import controller.Game;
import entity.Gameboard;
import entity.PlayerOptions;
import entity.Rules;
import fields.Ownable;
import fields.Shipping;
import fields.Street;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import entity.Player;
import static org.junit.Assert.*;
import desktop_resources.GUI;

/**
 * Created by Moulvad on 11/05/16.
 */
public class    PlayerOptionsTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        Gameboard.CreateGUI();
        player = new Player("Test", 30000, 0, 0, 0, 0, 0, 0, false);
    }

    @Test
    //BuyPropertyTest, we want to buy 2 houses on the "Lyseblå" field. We expect the BuyProperty method to place the houses best strategicly
    //and withdraw the correct amount to the player
    public void BuyPropertyTest () {
        ((Ownable) Gameboard.getField(2)).setOwner(player); ((Ownable) Gameboard.getField(4)).setOwner(player);
        ((Street) Gameboard.getField(2)).addHouses(2);  ((Street) Gameboard.getField(4)).addHouses(3);
        PlayerOptions.HouseorHotel(2);  PlayerOptions.HouseorHotel(4);
        PlayerOptions.BuyProperty(player);
        int expected = 30000 - (((Street) Gameboard.getField(2)).getHousePrice() * 2);
        int actual = player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    //SellPropertyTest, we want to sell 2 houses on the "Lyseblå" field. We expect the SellProperty method to substract the houses best strategicly
    //and deposit the correct amount to the player
    public void SellPropertyTest () {
        ((Ownable) Gameboard.getField(2)).setOwner(player); ((Ownable) Gameboard.getField(4)).setOwner(player);
        ((Street) Gameboard.getField(2)).addHouses(3);  ((Street) Gameboard.getField(4)).addHouses(4);
        PlayerOptions.HouseorHotel(2);  PlayerOptions.HouseorHotel(4);
        PlayerOptions.SellProperty(player);
        int expected = 30000 +((((Street) Gameboard.getField(2)).getHousePrice() * 2)/2);
        int actual = player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    //SetPawnedTest, we want to pledge field nr. 26. We expect to see the GUI. show that the field is pawned.
    //And afterwards deposit the correct amount to the players balance.
    public void SetPawnedTest () {
        ((Ownable) Gameboard.getField(26)).setOwner(player);
        PlayerOptions.SetPawned(player);
        int expected = (int) (30000 + (((Ownable) Gameboard.getField(26)).getPrice() * 0.9));
        int actual = player.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    //BuyPawnedTest, we want to buy back the pledged field nr. 26. We expect to see the GUI. show that the field is no longer pawned.
    //And afterwards withdraw the correct amount to the players balance.
    public void BuyPawnedTest () {
        ((Ownable) Gameboard.getField(26)).setOwner(player);
        ((Ownable) Gameboard.getField(26)).setPawned(true);
        PlayerOptions.BuyPawned(player);
        int expected = 30000 - ((Ownable) Gameboard.getField(26)).getPrice();
        int actual = player.getBalance();
        assertEquals(expected, actual);
    }


    @After
    public void TearDown() throws Exception {
        player = null;
    }
}