import java.util.ArrayList;

/**
 * This class is responsible for the settings of the game.
 * The game has two settings that the player can adjust.
 * The number of Rounds and the number of Questions per round.
 */
public class Settings {

    private static Player player1;
    private static Player player2;
    private static int numberOfRounds;
    private static int numOfQuestionPerRound;
    private static int numOfPlayers;
    private static ArrayList<Player> players;

    /**
     * The default Settings class Constructor reads form the DataBase the selected settings
     * for the questions per round and the rounds for the game and applies them to the current game
     */
    public Settings(){

    }

    /**
     * TODO*TODO*TODO
     *
     * @param temp
     */
    public Settings(int temp){
        numberOfRounds = 5;
        numOfQuestionPerRound=5;
        numOfPlayers=1;
    }

    /**
     * A setter for the numberOfRounds setting. This setting must be a number between 1 and 10.
     *
     * @param numOfRounds the numberOfRounds that the setting needs to be set to.
     */
    public void setNumberOfRounds(int numOfRounds){
        if(numOfRounds>0&&numOfRounds<=10){
            Settings.numberOfRounds=numOfRounds;
        }else{
            System.out.println("num of rounds out of limits 0 to 9");
        }
    }

    /**
     * A setter for the numberOfQuestions setting. This setting must be a number between 1 and 10.
     *
     * @param numOfQuestions the numberOfQuestions that the setting needs to be set to.
     */
    public void setNumberOfQuestionsPerRound(int numOfQuestions){
        if(numOfQuestions>0&&numOfQuestions<=10){
            numOfQuestionPerRound=numOfQuestions;
        }else{
            System.out.println("num of Questions per round out of limits 0 to 9");
        }
    }

    public static int getNumOfPlayers() {
        return numOfPlayers;
    }

    public static void setNumOfPlayers(int numOfPlayers) {
       Settings.numOfPlayers = numOfPlayers;
    }

    public static int getNumOfRounds(){
        return numberOfRounds;
    }

    public static int getNumOfQuestionsPerRound(){
        return numOfQuestionPerRound;
    }

    public static void setPlayer(Player player){player1 = player;}

    /**
     *
     * A method that initialises the ArrayList containing the two playerObjects.
     * To avoid exceptions, it first checks if the param ArrayList is not empty, and then
     * it adds it's elements to the players ArrayList of the Settings class.
     *
     * @param players an ArrayList containing two Player Objects.
     */
    public static void setPlayers(ArrayList<Player> players){

        if(!players.isEmpty()){
            Settings.players = new ArrayList<>();
            Settings.players.addAll(players);
        }

    }
    public static Player getPlayer(){return player1;}

    public static  ArrayList<Player> getPlayers(){
        return players;
    }

}
