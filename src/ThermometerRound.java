/**
 * This is the Thermometer type of round in the game.
 * This class represents the logic behind the ThermometerRoundGUI.
 */
public class ThermometerRound {

    private static int playerTwoCorrectAnswers;
    private static int playerOneCorrectAnswers;

    /**
     *
     * This is the constructor of the ThermometerRound class.
     * It initializes the static playerOneCorrectAnswers and playerTwoCorrectAnswers fields
     * and gives them the 0 value.
     * This constructor is called only for the first question of the round.
     *
     */
    public ThermometerRound(){
        playerOneCorrectAnswers = 0;
        playerTwoCorrectAnswers = 0;
    }

    /**
     *
     * This is the constructor of the ThermometerRound class, that is called for every
     * question of this round, except the first, where the static fields need to be initialized.
     *
     * @param i a int i variable, to call this constructor.
     */
    public ThermometerRound(int i){
    }

    /**
     * A method to increment the playerOneCorrectAnswers field.
     */
    public void incrementPlayerOneCorrectAnswers(){
        playerOneCorrectAnswers++;
    }

    /**
     * A method to increment the playerTwoCorrectAnswers field.
     */
    public void incrementPlayerTwoCorrectAnswers(){
        playerTwoCorrectAnswers++;
    }

    public int getPlayerOneCorrectAnswers() {
        return playerOneCorrectAnswers;
    }

    public void setPlayerOneCorrectAnswers(int correctAnswersPlayer1) {
        ThermometerRound.playerOneCorrectAnswers = correctAnswersPlayer1;
    }

    public int getPlayerTwoCorrectAnswers() {
        return playerTwoCorrectAnswers;
    }

    public void setPlayerTwoCorrectAnswers(int correctAnswersPlayer2) {
        ThermometerRound.playerTwoCorrectAnswers = correctAnswersPlayer2;
    }
}
