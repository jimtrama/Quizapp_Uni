import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * The BetRoundGUI class, a window that represents a Betting Round, where the user(s) play the game by betting
 * points, and then answering the questions, winning or losing their bet, depending on their answer.
 *
 * The BetRoundGUI class is a child class of the RoundGUI class.
 *
 * The BetRoundGUI contains a betFrame, where the user(s) select the amount to bet.
 * The frame contains a buttonGroup of JRadioButtons, that get the selection of bet from the user(s).
 * The user cannot click the betButton that makes the game proceed to the round, unless all of the players(one or both)
 * have selected an amount to bet. The betFrame window is not closable, and to proceed the user(s) must select a bet and
 * click on the betButton to proceed to the roundFrame window.
 *
 * When the betButton is clicked, the betFrame is disposed, and a new RoundGUI object is created, opening the window
 * for the round to show questions and answers.
 *
 */
public class BetRoundGUI extends RoundGUI{

    BetRound betRound;

    JPanel bettingPanel;

    JRadioButton bet250;
    JRadioButton bet500;
    JRadioButton bet750;
    JRadioButton bet1000;

    /**
     *
     * The constructor of the BetRoundGUI class.
     *
     * The constructor first calls the constructor of the parent class RoundGUI (super();) and then
     * sets the mainFrame, that is created by that class, to not be visible, because it does not need to show
     * it yet.
     *
     * A new betRound object is created, that handles the logic of this type of round.
     * Finally, depending on the number of players, the createBetFrame() or createTwoPlayerBetFrame() is called,
     * to create the appropriate betFrame, so that the user(s) can choose an amount to bet.
     *
     */
    public BetRoundGUI(){
        super();
        mainFrame.setVisible(false);
        betRound = new BetRound();

        if(game.getNumOfPlayers() == 1){
            createBetFrame();
        }
        else{
            createTwoPlayerBetFrame();
        }
    }

    /**
     *
     * The BetRoundGUI has a betFrame, that is created by this method, when the game is a one player game.
     *
     * The betFrame has a size of 400x250 and contains three panels, the topBetPanel, the bettingPanel and the
     * bottomBetPanel.
     *
     * The topBetPanel has a JLabel prompting the user(s) to choose an amount to bet.
     * the bottomBetPanel has a JButton that if the player has chosen an amount to bet makes the bet
     * and proceeds to the question, by making the mainFrame visible and disposing of the betFrame.
     *
     * the bettingPanel contains 4 JRadioButtons, each representing an amount of bet (250,500,750 and 1000 points)
     * that the user can click to select his bet. Those JRadioButtons are created by the createBetRadioButtons()
     * method that is documented below.
     *
     */
    public void createBetFrame(){

        JFrame betFrame = new JFrame("Bet");
        betFrame.setLayout(new BorderLayout());

        betFrame.setResizable(false);
        betFrame.setSize(400,250);
        betFrame.setLocationRelativeTo(null);
        betFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        betFrame.setVisible(true);

        bettingPanel = new JPanel();
        bettingPanel.setBackground(new Color(200,200,200));
        bettingPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));

        JPanel topBetPanel = new JPanel();
        JPanel bottomBetPanel = new JPanel();

        JButton betButton = new JButton("Bet");
        betButton.setPreferredSize(new Dimension(70,35));
        betButton.setFocusable(false);
        betButton.setFont(new Font("Arial", Font.BOLD, 13));
        betButton.setBackground(new Color(250,200,10));

        JLabel topLabel = new JLabel("Choose your bet");
        topLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        topLabel.setForeground(new Color(200,200,200));

        topBetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topBetPanel.setBackground(new Color(100,100,100));
        topBetPanel.add(topLabel);

        bottomBetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomBetPanel.setBackground(new Color(200,200,200));
        bottomBetPanel.add(betButton);

        createBetRadioButtons();
        bettingPanel.add(bet250, Component.CENTER_ALIGNMENT);
        bettingPanel.add(bet500, Component.CENTER_ALIGNMENT);
        bettingPanel.add(bet750, Component.CENTER_ALIGNMENT);
        bettingPanel.add(bet1000, Component.CENTER_ALIGNMENT);

        betButton.addActionListener(e -> {
            if(game.getNumOfPlayers() == 1){
                if (betRound.getBetOfPlayer1() !=0){

                    mainFrame.setVisible(true);
                    betFrame.dispose();
                }
            }
        });

        betFrame.add(topBetPanel,BorderLayout.NORTH);
        betFrame.add(bettingPanel, BorderLayout.CENTER);
        betFrame.add(bottomBetPanel, BorderLayout.SOUTH);
    }

    /**
     *
     * The BetRoundGUI has a betFrame that is created by this method, when the game is a two player game.
     *
     * The betFrame has a size of 400x250 and contains three panels, the topBetPanel, the bettingPanel and the
     * bottomBetPanel.
     *
     * The topBetPanel has a JLabel prompting the user(s) to choose an amount to bet.
     * the bottomBetPanel has a JButton that if the player has chosen an amount to bet makes the bet
     * and proceeds to the question, by making the mainFrame visible and disposing of the betFrame.
     *
     * the bettingPanel contains two JPanels, the betLeftPanel and the betRightPanel.
     *
     * The betLeftPanel has a gridLayout and contains a JLabel with the name of player1 and the 4
     * JRadioButtons that player1 can choose to bet (those JRadioButtons are created in this method).
     * The betRightPanel has a gridLayout and contains a JLabel with the name of player2 and the 4
     *JRadioButtons that player2 can choose to bet (those JRadioButtons are created in this method).
     *
     * The JRadioButtons are added to the appropriate ButtonGroup (playerOneButtonGroup and playerTwoButtonGroup)
     * and have ActionListeners to appropriately set the betOfPlayer1 and betOfPlayer2 of the BetRound class.
     *
     */
    public void createTwoPlayerBetFrame(){
        JFrame betFrame = new JFrame("Bet");
        betFrame.setLayout(new BorderLayout());

        betFrame.setResizable(false);
        betFrame.setSize(400,250);
        betFrame.setLocationRelativeTo(null);
        betFrame.setVisible(true);
        betFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        bettingPanel = new JPanel();
        bettingPanel.setBackground(new Color(200,200,200));
        bettingPanel.setLayout(new FlowLayout(FlowLayout.CENTER,80,10));

        JPanel betLeftPanel = new JPanel();
        betLeftPanel.setBackground(new Color(200,200,200));
        betLeftPanel.setLayout(new GridLayout(5,1));

        JPanel betRightPanel = new JPanel();
        betRightPanel.setBackground(new Color(200,200,200));
        betRightPanel.setLayout(new GridLayout(5,1));

        JPanel topBetPanel = new JPanel();
        JPanel bottomBetPanel = new JPanel();

        JButton betButton = new JButton("Bet");
        betButton.setPreferredSize(new Dimension(70,35));
        betButton.setFocusable(false);
        betButton.setFont(new Font("Arial", Font.BOLD, 13));
        betButton.setBackground(new Color(250,200,10));

        JLabel topLabel = new JLabel("Choose your bet");
        topLabel.setFont(new Font("Arial", Font.ITALIC, 20));
        topLabel.setForeground(new Color(200,200,200));

        topBetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topBetPanel.setBackground(new Color(100,100,100));
        topBetPanel.add(topLabel);

        bottomBetPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomBetPanel.setBackground(new Color(200,200,200));
        bottomBetPanel.add(betButton);

        JRadioButton playerOne250 = new JRadioButton("250");
        JRadioButton playerOne500 = new JRadioButton("500");
        JRadioButton playerOne750 = new JRadioButton("750");
        JRadioButton playerOne1000 = new JRadioButton("1000");

        JRadioButton playerTwo250 = new JRadioButton("250");
        JRadioButton playerTwo500 = new JRadioButton("500");
        JRadioButton playerTwo750 = new JRadioButton("750");
        JRadioButton playerTwo1000 = new JRadioButton("1000");

        playerOne250.setBackground(new Color(200,200,200));
        playerOne500.setBackground(new Color(200,200,200));
        playerOne750.setBackground(new Color(200,200,200));
        playerOne1000.setBackground(new Color(200,200,200));

        playerTwo250.setBackground(new Color(200,200,200));
        playerTwo500.setBackground(new Color(200,200,200));
        playerTwo750.setBackground(new Color(200,200,200));
        playerTwo1000.setBackground(new Color(200,200,200));

        playerOne250.addActionListener(e -> betRound.setBetOfPlayer1(250));

        playerOne500.addActionListener(e -> betRound.setBetOfPlayer1(500));

        playerOne750.addActionListener(e -> betRound.setBetOfPlayer1(750));

        playerOne1000.addActionListener(e -> betRound.setBetOfPlayer1(1000));

        playerTwo250.addActionListener(e -> betRound.setBetOfPlayer2(250));

        playerTwo500.addActionListener(e -> betRound.setBetOfPlayer2(500));

        playerTwo750.addActionListener(e -> betRound.setBetOfPlayer2(750));

        playerTwo1000.addActionListener(e -> betRound.setBetOfPlayer2(1000));

        ButtonGroup playerOneButtonGroup = new ButtonGroup();
        ButtonGroup playerTwoButtonGroup = new ButtonGroup();

        playerOneButtonGroup.add(playerOne250);
        playerOneButtonGroup.add(playerOne500);
        playerOneButtonGroup.add(playerOne750);
        playerOneButtonGroup.add(playerOne1000);

        playerTwoButtonGroup.add(playerTwo250);
        playerTwoButtonGroup.add(playerTwo500);
        playerTwoButtonGroup.add(playerTwo750);
        playerTwoButtonGroup.add(playerTwo1000);

        JLabel playerOneLabel = new JLabel(game.getPlayers().get(0).getUsername());
        JLabel playerTwoLabel = new JLabel(game.getPlayers().get(1).getUsername());

        betLeftPanel.add(playerOneLabel, Component.LEFT_ALIGNMENT);
        betRightPanel.add(playerTwoLabel, Component.RIGHT_ALIGNMENT);

        betLeftPanel.add(playerOne250, Component.LEFT_ALIGNMENT);
        betRightPanel.add(playerTwo250, Component.RIGHT_ALIGNMENT);

        betLeftPanel.add(playerOne500, Component.LEFT_ALIGNMENT);
        betRightPanel.add(playerTwo500, Component.RIGHT_ALIGNMENT);

        betLeftPanel.add(playerOne750, Component.LEFT_ALIGNMENT);
        betRightPanel.add(playerTwo750, Component.RIGHT_ALIGNMENT);

        betLeftPanel.add(playerOne1000, Component.LEFT_ALIGNMENT);
        betRightPanel.add(playerTwo1000, Component.RIGHT_ALIGNMENT);

        bettingPanel.add(betLeftPanel, Component.LEFT_ALIGNMENT);
        bettingPanel.add(betRightPanel, Component.RIGHT_ALIGNMENT);

        betButton.addActionListener(e -> {
            if(game.getNumOfPlayers() == 2){
                if((betRound.getBetOfPlayer1()!=0) && (betRound.getBetOfPlayer2() !=0)){
                    mainFrame.setVisible(true);
                    betFrame.dispose();
                }
            }
        });

        betFrame.add(topBetPanel,BorderLayout.NORTH);
        betFrame.add(bettingPanel, BorderLayout.CENTER);
        betFrame.add(bottomBetPanel, BorderLayout.SOUTH);

    }

    /**
     *
     * This is a method that creates 4 JRadioButtons for the one player betFrame.
     * The radioButtons are created, added to a ButtonGroup and then given an ActionListener,
     * that appropriately sets the betOfPlayer1 in the betRound class.
     *
     */
    public void createBetRadioButtons(){
        bet250 = new JRadioButton("250");
        bet500 = new JRadioButton("500");
        bet750 = new JRadioButton("750");
        bet1000 = new JRadioButton("1000");

        bet250.setFocusable(false);
        bet500.setFocusable(false);
        bet750.setFocusable(false);
        bet1000.setFocusable(false);

        bet250.setBackground(new Color(200,200,200));
        bet500.setBackground(new Color(200,200,200));
        bet750.setBackground(new Color(200,200,200));
        bet1000.setBackground(new Color(200,200,200));

        ButtonGroup betRadioGroup = new ButtonGroup();
        betRadioGroup.add(bet250);
        betRadioGroup.add(bet500);
        betRadioGroup.add(bet750);
        betRadioGroup.add(bet1000);

        bet250.addActionListener(e -> betRound.setBetOfPlayer1(250));
        bet500.addActionListener(e -> betRound.setBetOfPlayer1(500));
        bet750.addActionListener(e -> betRound.setBetOfPlayer1(750));
        bet1000.addActionListener(e -> betRound.setBetOfPlayer1(1000));

    }

    /**
     *
     * This is a method, that overrides the method of the same name in the RoundGUI parent class.
     *
     * This is the ActionListener for the four answer buttons. This ActionListener calls the checkIfCorrect() method
     * of the PlayGame class to check if the answer given by the user (that clicked the answer button) is correct.
     *
     * If the answer was correct, it awards the player with the amount he had bet in the betFrame. Otherwise, it subtracts
     * that amount from the player.
     *
     * Afterwards, it calls the setCorrectAnswerButtonGreen() method, so that the correct answer can be displayed to the
     * user.
     *
     * The questionsCount field of the PlayGame class is incremented, and a timer starts until the program proceeds to
     * the next question or next round, by calling the checkForFinish method, which is documented below.
     *
     * Finally, it makes the four answer buttons disabled, so that the user cannot click them anymore.
     *
     *
     * @param answer an int answer variable indicating which answer the player has chosen.
     * @return an ActionListener to be added in the answer buttons.
     */
    protected ActionListener AnswerBtnClicked(int answer){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!player1Answered) {
                    player1Answered = true;
                    boolean correct = game.checkIfCorrect(currentQuestion, answer);
                    if (correct) {
                        game.getPlayer().setPoints(game.getPlayer().getPoints() + betRound.getBetOfPlayer1());
                    } else {
                        game.getPlayer().setPoints(game.getPlayer().getPoints() - betRound.getBetOfPlayer1());
                    }
                    setCorrectAnswerButtonGreen();
                    game.setQuestionsCount(game.getQuestionsCount() + 1);
                    timerGreen = new Timer();
                    int delay = 0;
                    int period = 1000;
                    intervalGreen = 2;
                    timerGreen.scheduleAtFixedRate(new TimerTask() {

                        public void run() {
                            checkForFinish();
                        }
                    }, delay, period);

                    answerButton1.removeActionListener(this);
                    answerButton2.removeActionListener(this);
                    answerButton3.removeActionListener(this);
                    answerButton4.removeActionListener(this);
                }
            }
        };
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
     * if the player who pressed the key, has not answered before, then the program checks if the key he
     * pressed corresponds with a valid key for the answer, and if his answer is correct, rewards him with
     * the bet amount he has chosen in the betFrame. If the answer is wrong, it subtracts the bet amount from
     * his points.
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
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + betRound.getBetOfPlayer1());
                }else{
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() - betRound.getBetOfPlayer1());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '2') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + betRound.getBetOfPlayer1());
                }else{
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() - betRound.getBetOfPlayer1());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '3') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + betRound.getBetOfPlayer1());
                }else{
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() - betRound.getBetOfPlayer1());
                }
                player1Answered = true;
            }
            if (e.getKeyChar() == '4') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() + betRound.getBetOfPlayer1());
                }else{
                    game.getPlayers().get(0).setPoints(game.getPlayers().get(0).getPoints() - betRound.getBetOfPlayer1());
                }
                player1Answered = true;
            }
        }
        if(!player2Answered) {
            if (e.getKeyChar() == 'v') {
                boolean correct = game.checkIfCorrect(currentQuestion, 1);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + betRound.getBetOfPlayer2());
                }else{
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() - betRound.getBetOfPlayer2());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'b') {
                boolean correct = game.checkIfCorrect(currentQuestion, 2);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + betRound.getBetOfPlayer2());
                }else{
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() - betRound.getBetOfPlayer2());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'n') {
                boolean correct = game.checkIfCorrect(currentQuestion, 3);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + betRound.getBetOfPlayer2());
                }else{
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() - betRound.getBetOfPlayer2());
                }
                player2Answered = true;
            }
            if (e.getKeyChar() == 'm') {
                boolean correct = game.checkIfCorrect(currentQuestion, 4);
                if (correct) {
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() + betRound.getBetOfPlayer2());
                }else{
                    game.getPlayers().get(1).setPoints(game.getPlayers().get(1).getPoints() - betRound.getBetOfPlayer2());
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
     *
     * the ifNextQuestion method checks if there is a next question to be shown to the player(s) for
     * the current round.
     *
     * When called, it checks to see if the questionCount has reached the numberOfQuestionsPerRound
     * setting of the game (AND the intervalGreen of the timer has reached zero). If it has,
     * it disposes of the mainFrame and calls the ifNextRound() method, to check if there is
     * another round to be played. If the numberOfQuestionsPerRound has not been reached, then
     * if the intervalGreen of the timer has reached zero, it disposes of the mainFrame and
     * creates a new BetRoundGUI, so that the nextQuestion of this round can be played.
     *
     */
    protected void ifNextQuestion(){
        if(game.getQuestionsCount()==game.getNumberOfQuestionsPerRound()&&intervalGreen==0){
            mainFrame.dispose();
            ifNextRound();
        }else if(intervalGreen==0) {
            mainFrame.dispose();
            new BetRoundGUI();
        }
    }

}


