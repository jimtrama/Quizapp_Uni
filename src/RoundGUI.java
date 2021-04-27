import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 *
 * The RoundGUI class, a window where the user(s) play the game by answering the questions.
 *
 * The RoundGUI is the parent class of all the GUI classes that represent different types of rounds (BettingRoundGUI,
 * TimerRoundGUI, ThermometerRoundGUI and QuickAnswerRoundGUI.
 *
 * It contains the question and the 4 possible answer buttons that every child class inherits.
 * There are two different ways for the user to answer the question, depending on if this is a 1 or 2 player game.
 *
 * In the standard round, every correct answer awards the player(s) with 1000 points.
 *
 * The user can click on the backButton to return back to the main menu.
 *
 */
public class RoundGUI {

    protected JFrame mainFrame;
    private String roundType;
    private String questionCategory;

    protected PlayGame game;
    private ArrayList<Question> questions;

    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel mainTopPanel;
    private JPanel mainMainPanel;
    private JPanel mainBottomPanel;
    private JPanel bottomPanel;
    protected JPanel bottomTopPanel;
    private JPanel bottomBottomPanel;

    protected JButton answerButton1;
    protected JButton answerButton2;
    protected JButton answerButton3;
    protected JButton answerButton4;
    protected JButton backButton;

    protected JLabel scoreLabel1;
    protected JLabel scoreLabel2;

    private String temporaryString;

    protected Question currentQuestion;
    protected boolean player1Answered;
    protected boolean player2Answered;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    public KeyListener listener;
    protected int intervalGreen;
    protected Timer timerGreen;

    /**
     *
     * The constructor of the RoundGUI class.
     *
     * The constructor initializes objects of the PlayGame class, the Question class and an arrayList that
     * contains the questions for this round. Then it sets the roundType and questionCategory fields by getting
     * the necessary values from the PlayGame class.
     *
     * Finally, depending on the number of players, it calls the appropriate method to create the main frame.
     *
     */
    public RoundGUI(){

        game = new PlayGame();
        currentQuestion = new Question();
        roundType = game.getTypeOfRound();
        questionCategory = game.getCategoryOfQuestions();
        temporaryString = roundType + " Round";

        questions = new ArrayList<>();
        if(game.getNumOfPlayers()==1){
            createStandardRoundFrame();
        }else if(game.getNumOfPlayers()==2){
            createStandardRoundFrameForTwoPlayers();
        }
    }

    /**
     *
     * In the RoundGUI class, there are 5 JButtons that are created by this method.
     *
     * Four of those JButtons are the answer buttons and contain four answers. One of them contains the correct one.
     * Depending on whether or not the question has an image, the answer buttons have an appropriate size.
     *
     * The backButton is also created and has an ActionListener that lets the player return to the main menu.
     *
     * @param withImage a boolean variable that is true when the current question contains an image and false if it doesn't.
     */
    public void creteButtons(boolean withImage){

        answerButton1 = new JButton();
        answerButton2 = new JButton();
        answerButton3 = new JButton();
        answerButton4 = new JButton();
        backButton = new JButton("Back");

        answerButton1.setFont(new Font("Arial", Font.ITALIC, 9));
        answerButton2.setFont(new Font("Arial", Font.ITALIC, 9));
        answerButton3.setFont(new Font("Arial", Font.ITALIC, 9));
        answerButton4.setFont(new Font("Arial", Font.ITALIC, 9));
        backButton.setFont(new Font("Arial", Font.ITALIC, 9));

        answerButton1.setFocusable(false);
        answerButton2.setFocusable(false);
        answerButton3.setFocusable(false);
        answerButton4.setFocusable(false);
        backButton.setFocusable(false);

        answerButton1.setBackground(new Color(250,200,100));
        answerButton2.setBackground(new Color(250,200,100));
        answerButton3.setBackground(new Color(250,200,100));
        answerButton4.setBackground(new Color(250,200,100));
        backButton.setBackground(new Color(250,200,100));

        answerButton1.setFont(new Font("Arial", Font.ITALIC, 13));
        answerButton2.setFont(new Font("Arial", Font.ITALIC, 13));
        answerButton3.setFont(new Font("Arial", Font.ITALIC, 13));
        answerButton4.setFont(new Font("Arial", Font.ITALIC, 13));

        Dimension dimension;
        if(withImage){
            dimension = new Dimension(300,25);
        }else{
            dimension = new Dimension(300,70);
        }

        answerButton1.setPreferredSize(dimension);
        answerButton2.setPreferredSize(dimension);
        answerButton3.setPreferredSize(dimension);
        answerButton4.setPreferredSize(dimension);
        backButton.setPreferredSize(new Dimension(80, 50));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuGUI();
                mainFrame.dispose();
            }
        });
    }

    /**
     *
     * In the RoundGUI, there are three panels that are created and added to the mainFrame. Those are the topPanel,
     * the mainPanel and the bottomPanel.
     *
     * The topPanel has a JLabel indicating the number of the current round (e.g. Round 1, Round 2 etc.)
     * the mainPanel contains three JPanels that are also created in this method. the mainTopPanel, the mainMainPanel
     * and the mainBottomPanel.
     *
     * The mainTopPanel contains the question and it's image (if it exists).
     * the mainMainPanel contains the 4 answer JButtons.
     * the mainBottomPanel contains a JPanel called bottomTopPanel that contains JLabels for the current score of the player(s).
     *
     * the bottomPanel contains a JPanel called bottomBottomPanel that contains the backButton, that is created by the
     * createButtons() method.
     *
     */
    public void createPanels(){
        topPanel = new JPanel();
        mainPanel = new JPanel();
        mainTopPanel = new JPanel();
        mainMainPanel = new JPanel();
        mainBottomPanel = new JPanel();
        bottomPanel = new JPanel();
        bottomTopPanel = new JPanel();
        bottomBottomPanel = new JPanel();

        topPanel.setBackground(new Color(100,100,100));
        mainPanel.setBackground(new Color(200,200,200));
        mainTopPanel.setBackground(new Color(200,200,200));
        mainMainPanel.setBackground(new Color(200,200,200));
        mainBottomPanel.setBackground(new Color(200,200,200));
        bottomPanel.setBackground(new Color(200,200,200));

        bottomTopPanel.setBackground(new Color(200,200,200));
        bottomBottomPanel.setBackground(new Color(200,200,200));

        JLabel roundHeader = new JLabel("ROUND_HEADER");
        roundHeader.setText("Round " + (game.getRoundCount()+1));
        roundHeader.setFont(new Font("Arial", Font.BOLD, 20));
        roundHeader.setForeground(new Color(200,200,200));
        topPanel.add(roundHeader);

        JLabel question = new JLabel("_QUESTION?");

        questions = game.getQuestions();
        currentQuestion = questions.get(game.getQuestionsCount());
        question.setText(currentQuestion.getQuestion());
        question.setFont(new Font("Arial", Font.ITALIC, 13));
        mainTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 180,10));
        String imageName = currentQuestion.getImage();

        if(!imageName.equals("null")){

            creteButtons(true);

            ImageIcon icon = new ImageIcon("./images/" + imageName);
            JLabel iconLabel = new JLabel();
            iconLabel.setIcon(icon);
            mainTopPanel.add(iconLabel);
            mainTopPanel.setPreferredSize(new Dimension(200,240));
            question.setFont(new Font("Arial", Font.ITALIC, 13));

        }else{
            creteButtons(false);

        }
        mainTopPanel.add(question);


        ArrayList<String> answers = currentQuestion.getAnswers();
        answerButton1.setText(answers.get(0));
        answerButton2.setText(answers.get(1));
        answerButton3.setText(answers.get(2));
        answerButton4.setText(answers.get(3));
        switch (currentQuestion.getCorrectAnswerIndex()){
            case 1:
                answerButton1.setText(answerButton1.getText()+"\n This Is The Correct");
                break;
            case 2:
                answerButton2.setText(answerButton2.getText()+"\n This Is The Correct");
                break;
            case 3:
                answerButton3.setText(answerButton3.getText()+"\n This Is The Correct");
                break;
            case 4:
                answerButton4.setText(answerButton4.getText()+"\n This Is The Correct");
                break;

        }


        mainMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 12));
        mainMainPanel.add(answerButton1);
        mainMainPanel.add(answerButton2);
        mainMainPanel.add(answerButton3);
        mainMainPanel.add(answerButton4);

        scoreLabel1 = new JLabel();
        scoreLabel2 = new JLabel();


        if(game.getNumOfPlayers() == 1){
            scoreLabel1.setText(game.getPlayer().getUsername() + ": " + game.getPlayer().getPoints());
            bottomTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 450,1));
            bottomBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
            bottomTopPanel.add(scoreLabel1);
            bottomBottomPanel.add(backButton);
        }
        else{
            bottomTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 450,1));
            bottomBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
            scoreLabel1.setText(game.getPlayers().get(0).getUsername() + ": " + game.getPlayers().get(0).getPoints());
            scoreLabel2.setText(game.getPlayers().get(1).getUsername() + ": " + game.getPlayers().get(1).getPoints());

            bottomTopPanel.add(scoreLabel1);
            bottomBottomPanel.add(backButton);
            bottomTopPanel.add(scoreLabel2);
        }


        bottomPanel.add(bottomBottomPanel);
        mainBottomPanel.add(bottomTopPanel);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mainTopPanel, BorderLayout.NORTH);
        mainPanel.add(mainMainPanel, BorderLayout.CENTER);
        mainPanel.add(mainBottomPanel, BorderLayout.SOUTH);

    }

    /**
     *
     * The RoundGUI has a mainFrame, that is created by this method.
     *
     * The mainFrame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains\
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanels method.
     *
     * The mainFrame is not resizable and when created, it is located on the center of the screen.
     *
     * If the number of players for this game is 1 (meaning that this is a one player game), the AnswerButtonClicked
     * ActionListener is added to the four answer buttons.
     */
    public void createStandardRoundFrame(){
        mainFrame = new JFrame(temporaryString);
        createPanels();
        mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        mainFrame.setLayout(new BorderLayout());

        if(game.getNumOfPlayers() == 1){

            answerButton1.addActionListener(AnswerBtnClicked(1));

            answerButton2.addActionListener(AnswerBtnClicked(2));

            answerButton3.addActionListener(AnswerBtnClicked(3));

            answerButton4.addActionListener(AnswerBtnClicked(4));
        }

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     *
     * When the game is played by two players, this method is called to create the mainFrame.
     *
     * This method initializes two boolean variables player1Answerd and player2Answered that represents whether the
     * respective player has answered the question.
     *
     * Then calls the createStandardRoundFrame() method to create the mainFrame.
     * Finally it adds the KeyListener keyPressedForAnswer to the mainFrame.
     *
     */
    protected void createStandardRoundFrameForTwoPlayers(){
        player1Answered=false;
        player2Answered=false;
        createStandardRoundFrame();
        mainFrame.addKeyListener(keyPressedForAnswer());
    }

    /**
     *
     * This is the ActionListener for the four answer buttons. This ActionListener calls the checkIfCorrect() method
     * of the PlayGame class to check if the answer given by the user (that clicked the answer button) is correct.
     *
     * If the answer was correct, it awards the player with 1000 points, according to the rules of the standard round.
     *
     * Afterwards, it calls the setCorrectAnswerButtonGreen() method, so that the correct answer can be displayed to the
     * user.
     *
     * The questionsCount field of the PlayGame class is incremented, and a timer starts until the program proceeds to
     * the next question or next round, by calling the checkForFinish method, which is documented below.
     *
     * Finally, it makes the four answer buttons disabled, so that the user cannot click them anymore.
     *
     * @param answer an int answer variable indicating which answer the player has chosen.
     * @return an ActionListener to be added in the answer buttons.
     */
    protected ActionListener AnswerBtnClicked(int answer){
        ActionListener listener =  new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean correct =  game.checkIfCorrect(currentQuestion,answer);
                if(correct){
                    game.getPlayer().setPoints(game.getPlayer().getPoints()+1000);
                }
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


                answerButton1.setEnabled(false);
                answerButton2.setEnabled(false);
                answerButton3.setEnabled(false);
                answerButton4.setEnabled(false);

            }
        };
        answerButton1.removeActionListener(listener);
        answerButton2.removeActionListener(listener);
        answerButton3.removeActionListener(listener);
        answerButton4.removeActionListener(listener);
        return listener;
    }


    /**
     *
     * This is a KeyListener for the mainFrame, where the users have to press buttons in order to select
     * their answer.
     *
     * This KeyListener gets the key that is pressed by the users and calls the checkCorrectAnswer() method
     * with the said key as a parameter.
     *
     * @return a KeyListener to be added in the mainFrame of the RoundGUI class.
     */
    protected KeyListener keyPressedForAnswer(){
        listener = new KeyAdapter() {
            /**
             * Invoked when a key has been pressed.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                checkCorrectAnswer(e);
            }
        };
        return listener;
    }

    /**
     *
     * Whenever the player (or both the players, if it is a two player game) has selected his answer, the
     * button that has the correct answer turns into a green color, in order to show the player what the correct
     * answer was.
     *
     * To find the correct answer, this method calls the getCorrectAnswerIndex of the currentQuestion object
     * of the Question class, that represents the current question of the round. The index shows which of the four
     * buttons contains the correct answer.
     *
     */
    protected void setCorrectAnswerButtonGreen(){
        int index=currentQuestion.getCorrectAnswerIndex();
        if(index!=0){
            switch (index){
                case 1:
                    answerButton1.setBackground(new Color(0,130,90));
                    break;
                case 2:
                    answerButton2.setBackground(new Color(0,130,90));
                    break;
                case 3:
                    answerButton3.setBackground(new Color(0,130,90));
                    break;
                case 4:
                    answerButton4.setBackground(new Color(0,130,90));
                    break;

            }
        }
    }

    /**
     *
     * This method is called to check for the correct answer of the current question of the round,
     * when there are two player's playing.
     *
     * A KeyEvent e, is passed, containing the key that has been pressed by one of the players.
     *
     * if the player who pressed the key, has not answered before, then the program checks if the key he
     * pressed corresponds with a valid key for the answer, and if his answer is correct, rewards him with
     * 1000 points.
     *
     * In the end, the bothPlayersAnswered() method is called, which is documented below, in order to check
     * if both the players have answered, so that the game can move on.
     *
     * @param e a KeyEvent, that represents the key that one of the two players has pressed to give his answer.
     */
    protected void checkCorrectAnswer(KeyEvent e){
        int correct = currentQuestion.getCorrectAnswerIndex();
        if(!player1Answered) {
            if(e.getKeyChar()=='1'||e.getKeyChar()=='2'||e.getKeyChar()=='3'||e.getKeyChar()=='4') {
                if (Integer.parseInt(e.getKeyChar() + "") == correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + 1000);
                }
                player1Answered=true;
            }
        }
        if(!player2Answered) {
            if(e.getKeyChar()=='v'||e.getKeyChar()=='b'||e.getKeyChar()=='n'||e.getKeyChar()=='m')player2Answered=true;
            if (e.getKeyChar() == 'v'&&correct==1) {
                game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
            }
            if (e.getKeyChar() == 'b'&&correct==2) {
                game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
            }
            if (e.getKeyChar() == 'n'&&correct==3) {
                game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
            }
            if (e.getKeyChar() == 'm'&&correct==4) {
                game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + 1000);
            }
        }
        bothPlayersAnswered();

    }

    /**
     *
     * This method checks if both of the players have answered in a two player game.
     *
     * If indeed, both of the players have given an answer, the KeyListener of the mainFrame is removed, so
     * the players can not press other buttons.
     *
     * Then, the setCorrectAnswerButtonGreen() method is called, to turn the JButton containing the correct
     * answer green, and start a timer, before the program proceeds to the next question or next round,
     * by calling the checkForFinish method, which is documented below.
     *
     * Finally, the questionCount field of the game object is incremented.
     *
     */
    private void bothPlayersAnswered(){

        if(player1Answered&&player2Answered){

            mainFrame.removeKeyListener(listener);
            setCorrectAnswerButtonGreen();

            timerGreen = new Timer();
            int delay = 0;
            int period=1000;
            intervalGreen=2;
            timerGreen.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    checkForFinish();
                }
            },delay,period);

            game.setQuestionsCount(game.getQuestionsCount()+1);
        }
    }

    /**
     *
     * The ifNextRound method checks if there is a next round to be played in the current game.
     *
     * When called, it increments the roundCount of the game and then checks to see if the count
     * has reached the numberOfRounds setting of the game. If it has, it creates a new GameOverGUI
     * object, else it creates a new PlayGameGUI, to continue the game with the next round.
     *
     */
    protected void ifNextRound(){
        game.setRoundCount(game.getRoundCount()+1);
        if(game.getRoundCount()==game.getNumberOfRounds()){
            new GameOverGUI();
        }else{
            new PlayGameGUI();
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
     * creates a new RoundGUI, so that the nextQuestion of this round can be played.
     *
     */
    protected void ifNextQuestion(){
        if(game.getQuestionsCount()==game.getNumberOfQuestionsPerRound()&&intervalGreen==0){
            mainFrame.dispose();
            ifNextRound();
        }else if(intervalGreen==0) {
            mainFrame.dispose();
            new RoundGUI();
        }
    }

    /**
     *
     * The checkForFinish method checks if the timer, that starts after the player (or both players if
     * it is a two player game) have answered the question, has reached zero (intervalGreen == 0).
     * If it does, then the timer is canceled and the ifNextQuestion() method is called so that the
     * program can check if there is another question to be played in this round, or to advance to the next one.
     * If the timer has not reached zero (intervalGreen == 0), then it the intervalGreen of the timer is decremented.
     *
     */
    protected void checkForFinish() {
            if(intervalGreen==0){
                timerGreen.cancel();
                ifNextQuestion();
            }else{
                intervalGreen--;
            }
    }
}
