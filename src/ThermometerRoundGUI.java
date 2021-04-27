import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * The ThermometerRoundGUI class, a window that represents a Betting Round, where the user(s) play the game
 * answering the questions, and winning 5000 points if reaching 5 correct answers.
 *
 * The ThermometerRoundGUI class is a child class of the RoundGUI class. The frame and all the panels are the same with
 * the RoundGUI window, except that the ThermometerRoundGUI window has two extra JLabels that show the number
 * of correct answers each player has on this round.
 *
 */
public class ThermometerRoundGUI extends RoundGUI{

    private JLabel playerOneCorrectAnswersLabel;
    private JLabel playerTwoCorrectAnswersLabel;

    private ThermometerRound thermometerRound;

    /**
     *
     * The constructor of the ThermometerRoundGUI class.
     *
     * The constructor first calls the constructor of the parent class RoundGUI (super();) and then
     * checks if this is the first question of the round, in order to create an object of the ThermometerRound
     * by calling the appropriate constructor.
     *
     * Finally, it calls the addCorrectAnswersToFrame() method which is documented below.
     *
     */
    public ThermometerRoundGUI(){
        super();
        if(game.getQuestionsCount() == 0){
            thermometerRound = new ThermometerRound();
        }else{
            thermometerRound = new ThermometerRound(1);
        }
        addCorrectAnswersToFrame();
    }

    /**
     *
     * A method that creates two JLabels that display the number of correct answers each of the player has in
     * this round.
     *
     * The JLabels are created, get their text from the getPlayerOneCorrectAnswers() and getPlayerTwoCorrectAnswers()
     * methods and are added to the bottomTopPanel of the RoundGUI class.
     *
     */
    public void addCorrectAnswersToFrame(){

        playerOneCorrectAnswersLabel = new JLabel();
        playerTwoCorrectAnswersLabel = new JLabel();

        playerOneCorrectAnswersLabel.setText("Correct Anwsers:" + thermometerRound.getPlayerOneCorrectAnswers());
        playerTwoCorrectAnswersLabel.setText("Correct Anwsers:" + thermometerRound.getPlayerTwoCorrectAnswers());

        playerOneCorrectAnswersLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        playerTwoCorrectAnswersLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        super.bottomTopPanel.remove(super.scoreLabel2);
        super.bottomTopPanel.add(playerOneCorrectAnswersLabel);
        super.bottomTopPanel.add(playerTwoCorrectAnswersLabel);
        super.bottomTopPanel.add(super.scoreLabel2);
        super.bottomTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));

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
     * pressed corresponds with a valid key for the answer, and if his answer is correct, it calls the method
     * of the ThermometerRound class that increments his correct answer field. Then, if the player has reached
     * 5 correct answers in this round, he is awarded 5000 points, and then the correct answer fields for both
     * players are reset to 0, and the KeyListener is removed from the mainFrame.
     *
     * In the end, the program checks if both the players have answered. If yes, then the KeyListener from the mainFrame
     * is removed so that the players can not press other buttons.
     *
     * Then, the setCorrectAnswerButtonGreen() method is called, to turn the JButton containing the correct
     * answer green, and start a timer, before the program proceeds to the next question or next round,
     * by calling the checkForFinish of the parent (RoundGUI) class.
     * Also the questionCount field of the game object is incremented.
     *
     * @param e a KeyEvent, that represents the key that one of the two players has pressed to give his answer.
     */
    protected void checkCorrectAnswer(KeyEvent e){

        if(!player1Answered) {

            if (e.getKeyChar() == '1') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    thermometerRound.incrementPlayerOneCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()==5&&thermometerRound.getPlayerTwoCorrectAnswers()<5) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player2Answered = true;
                    }
                }
                player1Answered = true;

            }
            if (e.getKeyChar() == '2') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    thermometerRound.incrementPlayerOneCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()==5&&thermometerRound.getPlayerTwoCorrectAnswers()<5) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player2Answered = true;
                    }
                }
                player1Answered = true;

            }
            if (e.getKeyChar() == '3') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    thermometerRound.incrementPlayerOneCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()==5&&thermometerRound.getPlayerTwoCorrectAnswers()<5) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player2Answered = true;
                    }
                }
                player1Answered = true;

            }
            if (e.getKeyChar() == '4') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    thermometerRound.incrementPlayerOneCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()==5&&thermometerRound.getPlayerTwoCorrectAnswers()<5) {
                        game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player2Answered = true;
                    }
                }
                player1Answered = true;

            }

        }
        if(!player2Answered) {

            if (e.getKeyChar() == 'v') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    thermometerRound.incrementPlayerTwoCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()<5&&thermometerRound.getPlayerTwoCorrectAnswers()==5) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player1Answered = true;
                    }
                }
                player2Answered = true;

            }
            if (e.getKeyChar() == 'b') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    thermometerRound.incrementPlayerTwoCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()<5&&thermometerRound.getPlayerTwoCorrectAnswers()==5) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player1Answered = true;
                    }
                }
                player2Answered = true;

            }
            if (e.getKeyChar() == 'n') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    thermometerRound.incrementPlayerTwoCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()<5&&thermometerRound.getPlayerTwoCorrectAnswers()==5) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player1Answered = true;
                    }
                }
                player2Answered = true;

            }
            if (e.getKeyChar() == 'm') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    thermometerRound.incrementPlayerTwoCorrectAnswers();
                    if(thermometerRound.getPlayerOneCorrectAnswers()<5&&thermometerRound.getPlayerTwoCorrectAnswers()==5) {
                        game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 5000);
                        thermometerRound.setPlayerOneCorrectAnswers(0);
                        thermometerRound.setPlayerTwoCorrectAnswers(0);
                        mainFrame.removeKeyListener(listener);
                        player2Answered = true;
                    }
                }
                player2Answered = true;

            }

        }
        if(player1Answered&&player2Answered){
            mainFrame.removeKeyListener(listener);
            setCorrectAnswerButtonGreen();
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
     * the ifNextQuestion method checks if there is a next question to be shown to the player(s) for
     * the current round.
     *
     * When called, it checks to see if the questionCount has reached the numberOfQuestionsPerRound
     * setting of the game (AND the intervalGreen of the timer has reached zero). If it has,
     * it disposes of the mainFrame and calls the ifNextRound() method, to check if there is
     * another round to be played. If the numberOfQuestionsPerRound has not been reached, then
     * if the intervalGreen of the timer has reached zero, it disposes of the mainFrame and
     * creates a new ThermometerRoundGUI, so that the nextQuestion of this round can be played.
     *
     */
    protected void ifNextQuestion(){
        if(game.getQuestionsCount()==game.getNumberOfQuestionsPerRound()&&intervalGreen==0){
            mainFrame.dispose();
            ifNextRound();
        }else if(intervalGreen==0) {
            mainFrame.dispose();
            new ThermometerRoundGUI();
        }
    }

}
