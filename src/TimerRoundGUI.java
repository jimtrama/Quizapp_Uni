import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * The TimerRoundGUI class, a window that represents a Timer Round, where the user(s) play the game by answering the
 * questions, before the timer ends, and if the answer is correct, be awarded points depending on the time left on the
 * timer.
 *
 * The TimerRoundGUI class is a child class of the RoundGUI class. The frame and all the panels are the same with
 * the RoundGUI window, except that the TimerRoundGUI window has an extra JLabel that shows the timer.
 *
 */
public class TimerRoundGUI extends RoundGUI{

    private Timer timerR;
    private int intervalRefresh;
    JLabel timerLabel;
    private TimerRound timerRound;

    /**
     *
     * The constructor of the TimerRoundClass.
     *
     * The constructor first calls the constructor of the parent class RoundGUI (super();) and then
     * calls the addTimerLabel() method, that is documented below.
     *
     * Finally, it initializes an object of the TimerRound class, that implements the logic of this round and
     * then starts the timer.
     *
     */
    public TimerRoundGUI(){
        super();
        addTimerLabel();
        timerRound = new TimerRound();
        timerRound.startTimer();
        startTimerR();
    }

    /**
     * A method that starts the timerR.
     */
    public void startTimerR(){
        int delay = 0;
        int period = 500;
        timerR = new Timer();
        intervalRefresh = 5000;
        timerR.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                checkIfTimeHasElapsedR();
                refresh();
            }
        }, delay, period);
    }

    /**
     * A method to refresh the timerR.
     */
    private void refresh() {
        int dec = (intervalRefresh%1000)/100;
        timerLabel.setText("Timer: "+intervalRefresh/1000+","+dec);
    }

    /**
     *
     * A method that checks if the timerR has finished. If the timer has finished, then
     * it is canceled, the KeyListener from the mainFrame is removed, so no user can press any more keys,
     * the setCorrectAnswerButtonGreen() method is called, that shows the correct answer to the user(s) and
     * the questionCount is incremented.
     *
     * Finally a Timer object is created and the timer starts, before the program proceeds to the next question
     * or next round, by calling the checkForFinish method, which is documented below.
     *
     */
    private void checkIfTimeHasElapsedR(){
        if(intervalRefresh==0){
            timerR.cancel();
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

        }else{
            intervalRefresh-=500;
        }
    }

    /**
     * A method that creates a JLabel that displays the timer to the user(s).
     * It is added to the bottomTopPanel of the mainFrame of the super (RoundGUI) class.
     */
    public void addTimerLabel(){
        timerLabel = new JLabel();
        timerLabel.setText("Timer: "+intervalRefresh/10);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        super.bottomTopPanel.remove(super.scoreLabel2);
        super.bottomTopPanel.add(timerLabel);
        super.bottomTopPanel.add(super.scoreLabel2);
        super.bottomTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
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
     * pressed corresponds with a valid key for the answer, and if his answer is correct, rewards him with
     * the amount of points according to the timer round rules.
     * The amount of points is calculated in the getPointsForRemainingTime() method of the TimerRound class.
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
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '2') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '3') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '4') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player1Answered = true;
            }
        }
        if(!player2Answered) {
            if (e.getKeyChar() == 'v') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'b') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'n') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'm') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + timerRound.getPointsForRemainingTime());
                }
                player2Answered = true;
            }
        }
        if(player1Answered&&player2Answered){
            timerR.cancel();
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
     *
     * the ifNextQuestion method checks if there is a next question to be shown to the player(s) for
     * the current round.
     *
     * When called, it checks to see if the questionCount has reached the numberOfQuestionsPerRound
     * setting of the game (AND the intervalGreen of the timer has reached zero). If it has,
     * it disposes of the mainFrame and calls the ifNextRound() method, to check if there is
     * another round to be played. If the numberOfQuestionsPerRound has not been reached, then
     * if the intervalGreen of the timer has reached zero, it disposes of the mainFrame and
     * creates a new TimerRoundGUI, so that the nextQuestion of this round can be played.
     *
     */
    protected void ifNextQuestion(){
        if(game.getQuestionsCount()==game.getNumberOfQuestionsPerRound()&&intervalGreen==0){
            mainFrame.dispose();
            ifNextRound();
        }else if(intervalGreen==0) {
            mainFrame.dispose();
            new TimerRoundGUI();
        }
    }




}
