import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void getNumOfPlayers() {
        MenuGUI gui = new MenuGUI();
        Login login = new Login();
        int value = login.getNumOfPlayers();
        assertEquals(1,value);
    }

    @Test
    public void checkPlayerExists() {
        Login login = new Login();
        login.createUser("jim");
        boolean userExists = login.checkPlayerExists("jim");
        assertEquals(true,userExists);

    }

    @Test
    public void loginPlayer() {
        Login login = new Login();
        Settings settings = new Settings();
        boolean userExits = login.checkPlayerExists("jim");
        if(!userExits){
            login.createUser("jim");
        }
        boolean valueToAssert = login.loginPlayer("jim");
        assertEquals("jim",settings.getPlayer().getUsername());
        assertEquals(true,valueToAssert);
    }

    @Test
    public void loginPlayers() {
        Login login = new Login();
        Settings settings = new Settings();
        int usersToCreate = login.loginPlayers("jim","tram");
        if(usersToCreate==1){
            login.createUser("jim");
        }else if(usersToCreate==2){
            login.createUser("tram");
        }else if(usersToCreate==12){
            login.createUser("jim");
            login.createUser("tram");
        }
        boolean user1ShouldExist = login.checkPlayerExists("jim");
        boolean user2ShouldExist = login.checkPlayerExists("tram");
        assertEquals("jim",settings.getPlayers().get(0).getUsername());
        assertEquals("tram",settings.getPlayers().get(1).getUsername());
        assertEquals(true,user1ShouldExist);
        assertEquals(true,user2ShouldExist);
    }

    @Test
    public void createUser() {
        Random random = new Random();
        Login login = new Login();
        String username="a";
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("d","g","r","f","j","o"));
        while(login.checkPlayerExists(username)){
            username+=letters.get(random.nextInt(letters.size()));
        }
        login.createUser(username);
        boolean userShouldExistNow = login.checkPlayerExists(username);
        assertEquals(true,userShouldExistNow);
    }
}