import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 *
 * The PlayGameGUI class, the window where the user(s) can select what kind of round he (or they) want
 * to play next.
 *
 * There are 2 panels with two ButtonGroups. One for the type of Round and one for the Category of the
 * questions. The user has to make his selections and click on the play button to continue to the round.
 *
 * The players also have the option to click the back button and return to the main menu, canceling their
 * current game.
 *
 */
public class PlayGameGUI {

    private final PlayGame game;
    private JFrame playFrame;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;

    JRadioButton radioButtonStandard;
    JRadioButton radioButtonBetting;
    JRadioButton radioButtonTimer;
    JRadioButton radioButtonThermometer;
    JRadioButton radioButtonQuickAnswer;

    JRadioButton radioButtonFilms;
    JRadioButton radioButtonSports;
    JRadioButton radioButtonFootball;
    JRadioButton radioButtonGeneralKnowledge;
    JRadioButton radioButtonScience;
    JRadioButton radioButtonWorldHistory;

    JLabel selectionReminder;

    String roundTypeSelection;
    String questionCategorySelection;

    JButton backButton;
    JButton playRoundButton;

    private ArrayList<String> availableCategories;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     *
     * One constructor of the PlayGameGUI class, that initializes a new
     * PlayGame object and calls the initialiseRoundCount() method of the
     * that object.
     *
     * @param temp an temporary int so that this constructor gets called.
     */
    public PlayGameGUI(int temp){
        game = new PlayGame();
        game.initializeRoundCount();
    }

    /**
     *
     * The default constructor of the PlayGameGUI class, that initializes a new
     * PlayGame object by calling it's constructor that initializes the questionsHashMap.
     * Afterwards it calls the availableCategories() method of the game object, to find
     * out what are the available categories that the player can choose.
     *
     * Afterwards it calls the createPanel() and createFrame() methods, to create the GUI
     * elements and components.
     * Finally it initializes the roundTypeSelection and questionCategorySelection fields to
     * null.
     *
     */
    public PlayGameGUI(){

        game = new PlayGame("temp");
        availableCategories=game.availableCategories();
        createPanel();
        createFrame();

        roundTypeSelection = null;
        questionCategorySelection = null;
    }

    /**
     *
     * In the PlayGameGUI class, there are three JPanels that are created and then added to the frame.
     * The topPanel, the centerPanel and the bottomPanel.
     *
     * The centerPanel has a flowLayout and contains a JLabel prompting the player to select a type of round
     * and 2 or 5 (depending on the number of players) JRadioButtons that let the user(s) to select the type
     * of round.
     * The centerPanel also contains another JLabel prompting the player to select a question category and
     * 6 JRadioButtons that let the user(s) select the category of the questions for the next round.
     * When there is only one player, a JLabel containing the score is added in the centerPanel, above the backButton.
     * If there are 2 players, then the JLabels for the scores of the players are added in the bottomPanel, to the left
     * and right of the backButton.
     * In the bottomPanel, there is a backButton that lets the user return to the main menu and cancel the current game.
     *
     */
    public void createPanel(){
        centerPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        createRadioButtons();
        createButtons();

        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 10));
        centerPanel.setBackground(new Color(200, 200, 200));
        bottomPanel.setBackground(new Color(200, 200, 200));
        topPanel.setBackground(new Color(100, 100, 100));
        JLabel topLabel = new JLabel("You are playing BuzzQuizWorld");
        topLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        topLabel.setForeground(Color.WHITE);
        topPanel.add(topLabel);

        JLabel roundLabel = new JLabel("Choose the type of the next Round");
        centerPanel.add(roundLabel);
        String seperatorText = "NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOW";
        JLabel seperator = new JLabel(seperatorText);
        seperator.setFont(new Font("Arial", Font.PLAIN, 5));
        seperator.setForeground(new Color(200,200,200));
        centerPanel.add(seperator);

        selectionReminder = new JLabel("Please select a type of round and a question category");
        selectionReminder.setForeground(new Color(200,200,200));
        selectionReminder.setFont(new Font("Arial", Font.ITALIC, 12));


        centerPanel.add(radioButtonStandard);
        centerPanel.add(radioButtonBetting);
        if(game.getNumOfPlayers() == 2){
            centerPanel.add(radioButtonTimer);
            centerPanel.add(radioButtonThermometer);
            centerPanel.add(radioButtonQuickAnswer);
        }


        JLabel seperator2 = new JLabel(seperatorText);
        seperator2.setFont(new Font("Arial", Font.PLAIN, 25));
        seperator2.setForeground(new Color(200,200,200));
        centerPanel.add(seperator2);

        JLabel questionCategoryLabel = new JLabel("Choose the Category of the Questions");
        centerPanel.add(questionCategoryLabel);
        JLabel seperator3 = new JLabel(seperatorText);
        seperator3.setFont(new Font("Arial", Font.PLAIN, 5));
        seperator3.setForeground(new Color(200,200,200));
        centerPanel.add(seperator3);

        centerPanel.add(radioButtonFilms);
        centerPanel.add(radioButtonSports);
        centerPanel.add(radioButtonFootball);
        centerPanel.add(radioButtonGeneralKnowledge);
        centerPanel.add(radioButtonScience);
        centerPanel.add(radioButtonWorldHistory);

        JLabel seperator4 = new JLabel(seperatorText);
        seperator4.setFont(new Font("Arial", Font.PLAIN, 65));
        seperator4.setForeground(new Color(200,200,200));
        centerPanel.add(seperator4);

        centerPanel.add(selectionReminder, Component.CENTER_ALIGNMENT);

        JLabel seperator5 = new JLabel(seperatorText);
        seperator5.setFont(new Font("Arial", Font.PLAIN, 20));
        seperator5.setForeground(new Color(200,200,200));
        centerPanel.add(seperator5);

        centerPanel.add(playRoundButton, Component.CENTER_ALIGNMENT);

        JLabel seperator6 = new JLabel(seperatorText);
        seperator6.setFont(new Font("Arial", Font.PLAIN, 20));
        seperator6.setForeground(new Color(200,200,200));
        centerPanel.add(seperator6);

        JLabel playerOneScore = new JLabel("PlayerOneScore");
        JLabel playerTwoScore = new JLabel("PlayerTwoScore");

        if(game.getNumOfPlayers() == 1){

            if(!(game.getRoundCount() == 0)){

                playerOneScore.setText("Score: " + game.getPlayer().getPoints());
                playerOneScore.setFont(new Font("Arial", Font.PLAIN, 11));

                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,500,2));
                centerPanel.add(playerOneScore);
                bottomPanel.add(backButton);
            }
        }
        else{

            if(!(game.getRoundCount() == 0)){

                playerOneScore.setText(game.getPlayers().get(0).getUsername() +": " + game.getPlayers().get(0).getPoints());
                playerTwoScore.setText(game.getPlayers().get(1).getUsername() +": " + game.getPlayers().get(0).getPoints());

                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,200,10));
                bottomPanel.add(playerOneScore);
                bottomPanel.add(backButton);
                bottomPanel.add(playerTwoScore);
            }
        }
    }

    /**
     *
     * In the PlayGameGUI, there are 2 JButtons that are added to the centerPanel and bottomPanel.
     *
     * In the centerPanel, there is the playRoundButton, that the user clicks when he is ready to play
     * the next round. The playRoundButton has an ActionListener that first checks if the player has made
     * a selection on the roundType and the questionCategory.
     *
     * If the player has made both selections, then a new object of the appropriate class is created and the
     * playFrame is disposed, in order for the round to begin.
     *
     * If the player has not made one or both selections, then the JLabel selectionReminder gets a red color
     * and becomes visible to the user.
     *
     * In the bottomPanel, there is a backButton, that disposes of the playFrame and creates a new object of
     * the MenuGUI class, so that the user can return to the main menu.
     *
     */
    public void createButtons(){
        backButton = new JButton("Back");
        playRoundButton = new JButton("Play");

        backButton.setBackground(new Color(250, 200, 100));
        playRoundButton.setBackground(new Color(250, 200, 100));

        backButton.setFocusable(false);
        playRoundButton.setFocusable(false);

        backButton.setPreferredSize(new Dimension(70, 25));
        playRoundButton.setPreferredSize(new Dimension(70, 25));

        backButton.addActionListener(e -> {
            playFrame.dispose();
            new MenuGUI();
        });

        playRoundButton.addActionListener(e -> {

            if(questionCategorySelection != null && roundTypeSelection != null){

                game.setCategoryOfQuestions(questionCategorySelection);
                game.setTypeOfRound(roundTypeSelection);
                game.initializeQuestionsArray();
                switch (roundTypeSelection){

                    case "Standard":
                        new RoundGUI();
                        break;
                    case "Betting":
                        new BetRoundGUI();
                        break;
                    case "Timer":
                        new TimerRoundGUI();
                        break;
                    case "Thermometer":
                        new ThermometerRoundGUI();
                        break;
                    case "QuickAnswer":
                        new QuickAnswerRoundGUI();
                        break;
                }
                playFrame.dispose();
            }
            else{
                selectionReminder.setForeground(Color.RED);
            }
        });

    }

    /**
     *
     * In the centerPanel, there are two ButtonGroups containing JRadioButtons, that let the user
     * make selections regarding the type of round he wants to play and the category of the question
     * to use in said round.
     * Those JRadioButtons are created in this method.
     *
     * The radioButtons for the Timer, Thermometer and QuickAnswer are added to the ButtonGroup (and
     * the panel) only when it is a two player game, because those rounds only make sense for two players.
     *
     * For the questionCategory radioButtons, the method checks if their respective category is available,
     * by checking if the availableCategories ArrayList contains this category.
     *
     * Finally, the ActionListeners for the JRadioButtons set the roundTypeSelection and questionCategorySelection
     * fields of this class accordingly.
     *
     */
    public void createRadioButtons(){

        radioButtonStandard = new JRadioButton("Standard Round");
        radioButtonBetting = new JRadioButton("Betting Round");
        radioButtonTimer = new JRadioButton("Timer Round");
        radioButtonThermometer = new JRadioButton("Thermometer Round");
        radioButtonQuickAnswer = new JRadioButton("Quick Answer Round");

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

        radioButtonStandard.addActionListener(e -> roundTypeSelection = "Standard");
        radioButtonBetting.addActionListener(e -> roundTypeSelection = "Betting");
        radioButtonTimer.addActionListener(e -> roundTypeSelection = "Timer");
        radioButtonThermometer.addActionListener(e -> roundTypeSelection = "Thermometer");
        radioButtonQuickAnswer.addActionListener(e -> roundTypeSelection = "QuickAnswer");

        ButtonGroup roundTypeGroup = new ButtonGroup();
        roundTypeGroup.add(radioButtonStandard);
        roundTypeGroup.add(radioButtonBetting);

        if(game.getNumOfPlayers() == 2){
            roundTypeGroup.add(radioButtonTimer);
            roundTypeGroup.add(radioButtonThermometer);
            roundTypeGroup.add(radioButtonQuickAnswer);
        }

        radioButtonFilms = new JRadioButton("Films");
        radioButtonSports = new JRadioButton("Sports");
        radioButtonFootball = new JRadioButton("Football");
        radioButtonGeneralKnowledge = new JRadioButton("General Knowledge");
        radioButtonScience = new JRadioButton("Science");
        radioButtonWorldHistory = new JRadioButton("WorldHistory");

        if(!(availableCategories.contains("worldhistory"))){
            radioButtonWorldHistory.setEnabled(false);
        }
        if(!(availableCategories.contains("science"))){
            radioButtonScience.setEnabled(false);
        }
        if(!(availableCategories.contains("generalknowledge"))){
            radioButtonGeneralKnowledge.setEnabled(false);
        }
        if(!(availableCategories.contains("football"))){
            radioButtonFootball.setEnabled(false);
        }
        if(!(availableCategories.contains("sports"))){
            radioButtonSports.setEnabled(false);
        }
        if(!(availableCategories.contains("films"))){
            radioButtonFilms.setEnabled(false);
        }


        radioButtonFilms.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonSports.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonFootball.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonGeneralKnowledge.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonScience.setHorizontalAlignment(SwingConstants.LEFT);
        radioButtonWorldHistory.setHorizontalAlignment(SwingConstants.LEFT);

        radioButtonFilms.setFocusable(false);
        radioButtonSports.setFocusable(false);
        radioButtonFootball.setFocusable(false);
        radioButtonGeneralKnowledge.setFocusable(false);
        radioButtonScience.setFocusable(false);
        radioButtonWorldHistory.setFocusable(false);

        radioButtonFilms.setBackground(new Color(200, 200, 200));
        radioButtonSports.setBackground(new Color(200, 200, 200));
        radioButtonFootball.setBackground(new Color(200, 200, 200));
        radioButtonGeneralKnowledge.setBackground(new Color(200, 200, 200));
        radioButtonScience.setBackground(new Color(200, 200, 200));
        radioButtonWorldHistory.setBackground(new Color(200, 200, 200));

        radioButtonFilms.addActionListener(e -> questionCategorySelection = "films");
        radioButtonSports.addActionListener(e -> questionCategorySelection = "sports");
        radioButtonFootball.addActionListener(e -> questionCategorySelection = "football");
        radioButtonGeneralKnowledge.addActionListener(e -> questionCategorySelection = "generalknowledge");
        radioButtonScience.addActionListener(e -> questionCategorySelection = "science");
        radioButtonWorldHistory.addActionListener(e -> questionCategorySelection = "worldhistory");

        ButtonGroup QuestionCategoryTypeGroup = new ButtonGroup();
        QuestionCategoryTypeGroup.add(radioButtonFilms);
        QuestionCategoryTypeGroup.add(radioButtonSports);
        QuestionCategoryTypeGroup.add(radioButtonFootball);
        QuestionCategoryTypeGroup.add(radioButtonGeneralKnowledge);
        QuestionCategoryTypeGroup.add(radioButtonScience);
        QuestionCategoryTypeGroup.add(radioButtonWorldHistory);

    }

    /**
     *
     * The PlayGameGUI class has a frame, that is created by this method.
     *
     * The frame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains
     * the three panels (centerPanel, topPanel and bottomPanel) created by the createPanel method.
     *
     * The playFrame is not resizable and when created, it is located on the center of the screen.
     */
    public void createFrame(){

        playFrame = new JFrame();

        playFrame.setTitle("Play Quizworld");
        playFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        playFrame.setLayout(new BorderLayout());

        playFrame.add(centerPanel, BorderLayout.CENTER);
        playFrame.add(topPanel, BorderLayout.NORTH);
        playFrame.add(bottomPanel, BorderLayout.SOUTH);
        playFrame.setLocationRelativeTo(null);
        playFrame.setResizable(false);
        playFrame.setVisible(true);
    }

}
