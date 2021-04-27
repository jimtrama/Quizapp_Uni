import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The HowToPlayGUI class, a window where everything the user needs to know in order ot play the game is
 * explained to him.
 *
 * It consists of a mainFrame, that contains 3 panels (topPanel, mainPanel and bottomPanel)
 * The topPanel contains a header with the label "How to Play"
 * The mainPanel contains three panels, each containing JLabels with information about the game and how to play.
 * the bottomPanel contains a back button, for the player to return to the main menu.
 *
 */
public class HowToPlayGUI {

    private JFrame mainFrame;

    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel mainTopPanel;
    private JPanel mainMainPanel;
    private JPanel mainBottomPanel;
    private JPanel bottomPanel;

    JLabel roundInfo;
    JLabel roundInfo2;

    JRadioButton radioButtonStandard;
    JRadioButton radioButtonBetting;
    JRadioButton radioButtonTimer;
    JRadioButton radioButtonThermometer;
    JRadioButton radioButtonQuickAnswer;

    JButton backButton;

    String roundTypeSelection;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     * The constructor of the HowToPlayGUI class.
     *
     * The constructor calls the methods createPanels() and createMainFrame(), in order to create the GUI
     * elements and components.
     */
    public HowToPlayGUI(){

        roundTypeSelection = "Standard";

        createRadioButtons();
        createBackButton();
        createPanels();
        createMainFrame();

    }

    /**
     *
     * In the HowToPlayGUI, five JRadioButtons are created by this method.
     *
     * Those radioButtons let the player select which of the five types of rounds he wants to get information
     * about (Standard, Betting, Timer, Thermometer and Quick Answer).
     *
     * When selected, those radioButtons set the text of the roundInfo labels accordingly.
     * The radioButtons are added on the same ButtonGroup.
     *
     */
    public void createRadioButtons(){
        radioButtonStandard = new JRadioButton("Standard Round");
        radioButtonBetting = new JRadioButton("Betting Round");
        radioButtonTimer = new JRadioButton("Timer Round");
        radioButtonThermometer = new JRadioButton("Thermometer Round");
        radioButtonQuickAnswer = new JRadioButton("Quick Answer Round");

        roundInfo = new JLabel();
        roundInfo2 = new JLabel();

        radioButtonStandard.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonBetting.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonTimer.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonThermometer.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonQuickAnswer.setHorizontalAlignment(SwingConstants.LEFT);

        radioButtonStandard.setFocusable(false);
        radioButtonBetting.setFocusable(false);
        radioButtonTimer.setFocusable(false);
        radioButtonThermometer.setFocusable(false);
        radioButtonQuickAnswer.setFocusable(false);

        radioButtonStandard.setBackground(new Color(200, 200, 200));
        radioButtonBetting.setBackground(new Color(200, 200, 200));
        radioButtonTimer.setBackground(new Color(200, 200, 200));
        radioButtonThermometer.setBackground(new Color(200, 200, 200));
        radioButtonQuickAnswer.setBackground(new Color(200, 200, 200));

        radioButtonStandard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundInfo.setText("Every correct answer awards the player with 1000 points.");
                roundInfo2.setText("");
            }
        });
        radioButtonBetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundInfo.setText("Before the question, the players must choose an amount of points to bet.");
                roundInfo2.setText("If the player answers correctly, he wins the points he bet, otherwise he loses it.");
            }
        });
        radioButtonTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundInfo.setText("There is a timer that counts down from 5 seconds. Both players must answer within the timer.");
                roundInfo2.setText("For every correct answer, the player is awarded the points according to the milliseconds remaining * 0.2.");
            }
        });
        radioButtonThermometer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundInfo.setText("The first player to answer 5 questions correctly gets awarded 5000 points.");
                roundInfo2.setText("When someone wins those points, the count of the correct questions starts again.");
            }
        });
        radioButtonQuickAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roundInfo.setText("The first player to answer correctly wins 1000 points, while the one");
                roundInfo2.setText("that gets the correct answer second gets awarded 500 points.");
            }
        });

        ButtonGroup roundTypeGroup = new ButtonGroup();

        roundTypeGroup.add(radioButtonStandard);
        roundTypeGroup.add(radioButtonBetting);
        roundTypeGroup.add(radioButtonTimer);
        roundTypeGroup.add(radioButtonThermometer);
        roundTypeGroup.add(radioButtonQuickAnswer);
    }

    /**
     *
     * This method creates the JLabels that contain the information about the rounds
     * and then adds them to the mainMainPanel.
     *
     */
    public void createRoundPanel(){

        JLabel seperator = new JLabel("NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW");
        seperator.setFont(new Font("Arial", Font.PLAIN, 60));
        seperator.setForeground(new Color(200,200,200));

        JLabel roundGenericInfo = new JLabel();
        roundGenericInfo.setText("In this round there are questions both with and without images.");
        JLabel roundGenericInfo2 = new JLabel();
        roundGenericInfo2.setText("The player or players must choose the correct answer out of the four.");

        roundGenericInfo.setFont(new Font("Arial", Font.ITALIC, 16));
        roundGenericInfo2.setFont(new Font("Arial", Font.ITALIC, 16));
        roundInfo.setFont(new Font("Arial", Font.ITALIC, 16));
        roundInfo2.setFont(new Font("Arial", Font.ITALIC, 16));

        mainMainPanel.add(seperator);
        mainMainPanel.add(roundGenericInfo);
        mainMainPanel.add(roundGenericInfo2);
        mainMainPanel.add(roundInfo);
        mainMainPanel.add(roundInfo2);

    }

    /**
     *
     * In the HowToPlayGUI, three JPanels are created and added to the mainFrame. The topPanel, the mainPanel,
     * and the bottomPanel.
     *
     * The topPanel has a JLabel with the text "How to play." and acts as a header.
     * The mainPanel contains three JPanels, the mainTopPanel, the mainMainPanel and the mainBottomPanel.
     *
     * The mainTopPanel contains the five radioButtons for the user to select a type of round.
     * The mainMainPanel contains JLabels with information on how to play each round.
     * The mainBottomPanel contains JLabels information on the controls for one or two players.
     *
     *
     */
    public void createPanels(){
        topPanel = new JPanel();
        mainPanel = new JPanel();
        mainTopPanel = new JPanel();
        mainMainPanel = new JPanel();
        mainBottomPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setBackground(new Color(100,100,100));
        mainPanel.setBackground(new Color(200,200,200));
        mainTopPanel.setBackground(new Color(200,200,200));
        mainMainPanel.setBackground(new Color(200,200,200));
        mainBottomPanel.setBackground(new Color(200,200,200));
        bottomPanel.setBackground(new Color(200,200,200));

        JLabel header = new JLabel();
        header.setText("How to play.");
        header.setFont(new Font("Arial", Font.PLAIN, 17));
        header.setForeground(new Color(250,250,250));
        topPanel.add(header);

        mainTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,5));

        mainTopPanel.add(radioButtonStandard);
        mainTopPanel.add(radioButtonBetting);
        mainTopPanel.add(radioButtonTimer);
        mainTopPanel.add(radioButtonThermometer);
        mainTopPanel.add(radioButtonQuickAnswer);

        mainMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 500,2));
        createRoundPanel();

        JLabel seperator2 = new JLabel("NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW");
        seperator2.setFont(new Font("Arial", Font.PLAIN, 60));
        seperator2.setForeground(new Color(200,200,200));
        mainMainPanel.add(seperator2);

        JLabel controlsInfo = new JLabel();
        controlsInfo.setText("In a one player game, in order to select an answer,");
        JLabel controlsInfo2 = new JLabel();
        controlsInfo2.setText("the player simply has to click on a button with the mouse");
        JLabel controlsInfo3 = new JLabel();
        controlsInfo3.setText("When two players are playing, each of them has 4 keys to press from the keyboard.");
        JLabel controlsInfo4 = new JLabel();
        controlsInfo4.setText("1, 2, 3, 4 keys for player1 and v, b, n, m keys for player2.");

        controlsInfo.setFont(new Font("Arial", Font.ITALIC, 16));
        controlsInfo2.setFont(new Font("Arial", Font.ITALIC, 16));
        controlsInfo3.setFont(new Font("Arial", Font.ITALIC, 16));
        controlsInfo4.setFont(new Font("Arial", Font.ITALIC, 16));

        mainMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 600,2));
        mainMainPanel.add(controlsInfo);
        mainMainPanel.add(controlsInfo2);
        mainMainPanel.add(controlsInfo3);
        mainMainPanel.add(controlsInfo4);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mainTopPanel, BorderLayout.NORTH);
        mainPanel.add(mainMainPanel, BorderLayout.CENTER);
        mainPanel.add(mainBottomPanel, BorderLayout.SOUTH);


        bottomPanel.add(backButton);

    }

    /**
     * This method creates the backButton that lets the player return to the main menu.
     */
    public void createBackButton(){

        backButton = new JButton();
        backButton.setText("Back");

        backButton.setFocusable(false);
        backButton.setBackground(new Color(250,200,100));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new MenuGUI();
            }
        });

    }

    /**
     *
     * The HowToPlayGUI has a mainFrame, that is created by this method.
     *
     * The menuFrame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains\
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanels method.
     *
     * The menuFrame is not resizable and when created, it is located on the center of the screen.
     */

    public void createMainFrame(){

        mainFrame = new JFrame();

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("How To Play");
        mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);

        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}


