package JUnitTestClass;

import com.sun.tools.javac.comp.Check;
import entity.Gameboard;
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
public class PlayerOptionsTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        Gameboard.CreateGUI();
        player = new Player("Test", 30000, 0, 1, 0, 0, 0, 0, false);
    }

    @After
    public void TearDown() throws Exception {
        player = null;
    }
}