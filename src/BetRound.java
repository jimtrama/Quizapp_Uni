import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the bet type of round in the game.
 * This class represents the logic behind the BetRoundGUI.
 */
public class BetRound {

    private static int betOfPlayer1;
    private static int betOfPlayer2;

    /**
     * The constructor of the BetRound class.
     */
    public BetRound(){
        betOfPlayer1=0;
        betOfPlayer2=0;
    }

    public int getBetOfPlayer1() {
        return betOfPlayer1;
    }

    public void setBetOfPlayer1(int betOfPlayer1) {
        this.betOfPlayer1 = betOfPlayer1;
    }

    public int getBetOfPlayer2() {
        return betOfPlayer2;
    }

    public void setBetOfPlayer2(int betOfPlayer2) {
        this.betOfPlayer2 = betOfPlayer2;
    }



}
