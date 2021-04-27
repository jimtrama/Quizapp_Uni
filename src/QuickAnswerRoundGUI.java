import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * The QuickAnswerRoundGUI class, a window that represents a Quick Answer round, where the user(s) answer
 * questions and the first one to answer correctly wins 1000 points, while the second user that answers
 * correctly wins 500 points.
 *
 * The QuickAnswerRoundGUI class is a child class of the RoundGUI class. The frame and all the panels are
 * the same with the RoundGUI window, except that the QuickAnswerRoundGUI window turns the scoreLabel JLabel
 * of the player who was the first to answer correctly into a green color.
 *
 */
public class QuickAnswerRoundGUI extends RoundGUI {

    private int firstToAnswerCorrectly;

    /**
     *
     * The constructor of the QuickAnswerRoundGUI, that calls the constructor of the parent class.
     */
    public QuickAnswerRoundGUI(){
        super();
    }

    /**
     *
     * This is a method, that overrides the method of the same name in the RoundGUI parent class.
     *
     * This method is called to check for the correct answer of the current question of the round,
     * when there are two player's playing.
     *
     * A KeyEvent e, is passed, containing the key that has been pressed by one of the players.
     *
     * If the player who pressed the key, has not answered before, then the program checks if the key he
     * pressed corresponds with a valid key for the answer, and if his answer is correct, checks if the other
     * player has already answered. If not, he is the first to answer and is awarded 1000 points, otherwise
     * he is rewarded with 500 points.
     *
     * In the end, the program checks if both the players have answered. If yes, then the KeyListener from the mainFrame
     * is removed so that the players can not press other buttons.
     *
     * Then, the setCorrectAnswerButtonGreen() method is called, to turn the JButton containing the correct
     * answer green, and the scoreLabel of the first player to answer correctly turns green, and then start
     * a timer, before the program proceeds to the next question or next round, by calling the checkForFinish
     * of the parent (RoundGUI) class.
     *
     * Also the questionCount field of the game object is incremented.
     *
     * @param e a KeyEvent, that represents the key that one of the two players has pressed to give his answer.
     */
    protected void checkCorrectAnswer(KeyEvent e){

        if(!player1Answered) {
            if (e.getKeyChar() == '1') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    if (!player2Answered) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 1000);
                        firstToAnswerCorrectly = 0;
                    } else {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 500);
                    }
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '2') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    if (!player2Answered) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 1000);
                        firstToAnswerCorrectly = 0;
                    } else {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 500);
                    }
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '3') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    if (!player2Answered) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 1000);
                        firstToAnswerCorrectly = 0;
                    } else {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 500);
                    }
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '4') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    if (!player2Answered) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 1000);
                        firstToAnswerCorrectly = 0;
                    } else {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 500);
                    }
                }
                player1Answered = true;
            }
        }
        if(!player2Answered) {
            if (e.getKeyChar() == 'v') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    if (!player1Answered) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
                        firstToAnswerCorrectly = 1;
                    } else {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 500);
                    }
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'b') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    if (!player1Answered) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
                        firstToAnswerCorrectly = 1;
                    } else {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 500);
                    }
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'n') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    if (!player1Answered) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
                        firstToAnswerCorrectly = 1;
                    } else {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 500);
                    }
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'm') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    if (!player1Answered) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
                        firstToAnswerCorrectly = 1;
                    } else {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 500);
                    }
                }
                player2Answered = true;
            }
        }

        if(player1Answered&&player2Answered){
            mainFrame.removeKeyListener(listener);
            setCorrectAnswerButtonGreen();
            if(firstToAnswerCorrectly == 0){
                super.scoreLabel1.setForeground(new Color(0,150,90));
            }else{
                super.scoreLabel2.setForeground(new Color(0,150,90));
            }

            game.setQuestionsCount(game.getQuestionsCount()+1);
            timerGreen = new Timer();
            int delay = 0;
            int period=1000;
            intervalGreen=2;
            timerGreen.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    checkForFinish();
                }
            },delay,period);
        }

    }

    /**
     *
     * The ifNextQuestion method checks if there is a next question to be shown to the player(s) for
     * the current round.
     *
     * When called, it checks to see if the questionCount has reached the numberOfQuestionsPerRound
     * setting of the game (AND the intervalGreen of the timer has reached zero). If it has,
     * it disposes of the mainFrame and calls the ifNextRound() method, to check if there is
     * another round to be played. If the numberOfQuestionsPerRound has not been reached, then
     * if the intervalGreen of the timer has reached zero, it disposes of the mainFrame and
     * creates a new QuickAnswerRoundGUI, so that the nextQuestion of this round can be played.
     *
     */
    protected void ifNextQuestion(){
        if(game.getQuestionsCount()==game.getNumberOfQuestionsPerRound()&&intervalGreen==0){
            mainFrame.dispose();
            ifNextRound();
        }else if(intervalGreen==0) {
            mainFrame.dispose();
            new QuickAnswerRoundGUI();
        }
    }
}
