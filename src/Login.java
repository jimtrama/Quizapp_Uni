import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * The Login class is the logic behind the LoginGUI class.
 *
 * This class has methods to check if a player exists, log in a player, log in two players and
 * create a user that does not exist.
 *
 */

public class Login {

    private Settings settings;
    private int numOfPlayers;

    /**
     *
     * The constructor of the Login class. It initialises an object of the Settings class and
     * gets from it the numOfPlayers.
     *
     */
    public Login(){
        settings = new Settings();
        numOfPlayers = settings.getNumOfPlayers();
    }

    public int getNumOfPlayers(){
        return numOfPlayers;
    }

    /**
     *
     * A method to check if the given username coresponds to a username on the database.
     * using a Scanner and the users.txt file.
     *
     * @param username the username given by the user, to check if it exists on the users.txt file.
     * @return a boolean variable userExists. True if the username exists and false if it doesn't.
     */
    public boolean checkPlayerExists(String username){
        boolean userExists=false;

        File usersFile = new File("users.txt");
        Scanner filescanner = null;

        try {
            filescanner = new Scanner(usersFile);
        } catch (Exception e) {
            System.out.println("File not Found");
        }

        while (filescanner.hasNextLine()) {
            String line = filescanner.nextLine();
            if (line.equals(username)&&!line.isEmpty()) {
                userExists = true;
                break;
            }
        }
        return userExists;
    }

    /**
     *
     * A method to login the player, given a username.
     *
     * To login a player, this method first calls the method checkPlayerExists() in order to find out if the
     * given username exists in the users.txt file. If it does, then it creates a new object of the Player
     * class with this username and adds it to the settings with the settings.setPlayer method.
     *
     * @param username1 the username given by the user in the LoginGUI.
     * @return a boolean userExists variable. True if the username142m,bn1241241,4bnm2124mbmn,12 exists and has logged in correctly, or false
     * if the username does not exist.
     */
    public boolean loginPlayer(String username1){
        boolean userExists = checkPlayerExists(username1);
        if(userExists){
            Player player = new Player(username1);
            settings.setPlayer(player);
        }
        return userExists;

    }

    /**
     *
     * A method to login the two players, given two usernames.
     *
     * It creates an ArrayList players to store the two PlayerObject it is about to log in.
     * This method checks if both the players exist, calling the checkPlayerExists method. If both of them
     * exist, it adds them to the ArrayList, otherwise it returns an integer that indicates which of the
     * usernames does not exist, so that the user can be created.
     *
     * @param username1 the username given by the user for player1 on the first JTextField
     * @param username2 the username given by the user for player2 on the second JTextField
     * @return an int users variable, that indicates whether or not both users were logged in correctly
     * or if one or both of them didn't exist (0 for both logging in, 1 for username1 not existing,
     * 2 for username2 not existing and 12 for both usernames not existing.
     */
    public int loginPlayers(String username1, String username2){

        ArrayList<Player> players = new ArrayList<>();
        int users = 0;
        boolean userExists = checkPlayerExists(username1);

        if(userExists){
            Player player1 = new Player(username1);
            players.add(player1);
        }else{
            users=1;
        }
        userExists = checkPlayerExists(username2);

        if(userExists) {
            Player player2 = new Player(username2);
            players.add(player2);
        }else{
            if(users==1){
                users=12;
            }else{
                users=2;
            }
        }
        if(users==0) {
            settings.setPlayers(players);
        }
        return users;
    }

    public boolean createUser(String username){
        try {
            if(!checkPlayerExists(username)) {
                Files.write(Paths.get("users.txt"), ("\n" + username).getBytes(), StandardOpenOption.APPEND);
                Files.write(Paths.get("highscore.txt"), ("\n" + username + "," + "0").getBytes(), StandardOpenOption.APPEND);
                return true;
            }else{
                return false;
            }
        }catch (Exception e) {
            System.out.println("Something Went Wrong creating te user");
            return false;
        }
    }

}
