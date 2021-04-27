import java.util.Timer;
import java.util.TimerTask;

/**
 * This is the timer type of round in the game.
 * This class represents the logic behind the TimerRoundGUI.
 */
public class TimerRound {
    private Timer timer;
    private int interval;

    /**
     * The default constructor of the class.
     */
    public TimerRound(){
    }

    /**
     * A method that starts the timer, that counts down the time the players have
     * until they answer.
     */
    public void startTimer(){
        int delay = 0;
        int period = 1;
        timer = new Timer();
        interval = 0;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                checkIfTimeHasElapsed();
            }
        }, delay, period);
    }

    /**
     * A method to stop the timer.
     */
    public void stopTimer(){
        timer.cancel();
    }

    /**
     * This method calculates the amount of points that need to be rewarded to a plyer
     * for his correct answer. This amount is the number of milliseconds remaining * 0.2 .
     *
     * @return an int value variable, containing the amount of points to be rewarded to the
     * player.
     */
    public int getPointsForRemainingTime(){
        int value;
        double dvalue = (5000-interval)*0.2;
        value = (int) dvalue;
        return value;
    }

    /**
     * A method to check if the timer has ended.
     */
    private void checkIfTimeHasElapsed(){
        if(interval==5000){
            timer.cancel();
        }else{
            interval++;
        }
    }
}
