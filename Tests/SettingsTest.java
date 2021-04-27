import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SettingsTest {

    @Test
    public void setNumberOfRounds() {
        Settings setting1 = new Settings();
        setting1.setNumberOfRounds(3);
        Settings settings2 = new Settings();
        assertEquals(3,settings2.getNumOfRounds());

    }

    @Test
    public void setNumberOfQuestionsPerRound() {
        Settings setting1 = new Settings();
        setting1.setNumberOfQuestionsPerRound(3);
        Settings settings2 = new Settings();
        assertEquals(3,settings2.getNumOfQuestionsPerRound());
    }

    @Test
    public void getNumOfPlayers() {
        Settings setting1 = new Settings();
        setting1.setNumOfPlayers(2);
        Settings settings2 = new Settings();
        assertEquals(2,settings2.getNumOfPlayers());
    }

    @Test
    public void setNumOfPlayers() {
        Settings setting1 = new Settings();
        setting1.setNumOfPlayers(1);
        Settings settings2 = new Settings();
        assertEquals(1,settings2.getNumOfPlayers());
    }

    @Test
    public void getNumOfRounds() {
        Settings setting1 = new Settings();
        setting1.setNumberOfRounds(3);
        Settings settings2 = new Settings();
        assertEquals(3,settings2.getNumOfRounds());
    }

    @Test
    public void getNumOfQuestionsPerRound() {
        Settings setting1 = new Settings();
        setting1.setNumberOfQuestionsPerRound(6);
        Settings settings2 = new Settings();
        assertEquals(6,settings2.getNumOfQuestionsPerRound());
    }

    @Test
    public void setPlayer() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        Player player = new Player("jim");
        Settings setting1 = new Settings();
        setting1.setPlayer(player);
        Settings settings2 = new Settings();
        assertEquals(player,settings2.getPlayer());
    }

    @Test
    public void setPlayers() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        if(!login.checkPlayerExists("tram")){
            login.createUser("tram");
        }
        Player player1 = new Player("jim");
        Player player2 = new Player("tram");
        Settings setting1 = new Settings();
        setting1.setPlayers(new ArrayList<>(Arrays.asList(player1,player2)));
        Settings settings2 = new Settings();
        assertEquals(player1,settings2.getPlayers().get(0));
        assertEquals(player2,settings2.getPlayers().get(1));
    }

    @Test
    public void getPlayer() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        Player player = new Player("jim");
        Settings setting1 = new Settings();
        setting1.setPlayer(player);
        Settings settings2 = new Settings();
        assertEquals(player,settings2.getPlayer());
    }

    @Test
    public void getPlayers() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        if(!login.checkPlayerExists("tram")){
            login.createUser("tram");
        }
        Player player1 = new Player("jim");
        Player player2 = new Player("tram");
        Settings setting1 = new Settings();
        setting1.setPlayers(new ArrayList<>(Arrays.asList(player1,player2)));
        Settings settings2 = new Settings();
        assertEquals(player1,settings2.getPlayers().get(0));
        assertEquals(player2,settings2.getPlayers().get(1));
    }
}