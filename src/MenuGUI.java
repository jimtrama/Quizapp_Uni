import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Main Menu GUI class, the first window that opens.
 *
 * In this window, the user can choose the number of players via two JRadioButtons (1 or 2) that will be playing the game.
 * Then he can choose one of the 5 JButtons in the window.
 * <li> Play, that starts the game, by creating a new PlayGameGUI object and disposing of the current menu frame. </li>
 * <li> Settings, that disposes the current menu frame and creates a new SettingsGUI object, where you can tweak the game settings.</li>
 * <li> Statistics, that disposes the current menu frame and creates a new StatisticsGUI object, where you are shown statistics for the players.</li>
 * <li> How to play, that open's a new frame, where you are given instruction on how to play the game.</li>
 * <li> Quit, that disposes the main frame and exits the program</li>
 */
public class MenuGUI {

    private JFrame menuFrame;
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private SettingsGUI settingsFrame;
    private Settings settings;
    private ArrayList<Player> players;


    private JButton playButton;
    private JButton settingsButton;
    private JButton statisticsButton;
    private JButton howToPlayButton;
    private JButton quitButton;

    private JRadioButton onePlayerRadioButton;
    private JRadioButton twoPlayerRadioButton;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;


    /**
     * The constructor of the MenuGUI class.
     *
     * The constructor initialises a settings object, and then calls on the createButtons(), createRadioButtons(), createPanel(), and createFrame() functions
     * to create the GUI elements and components.
     */
    public MenuGUI(){

        settings = new Settings();
        PlayGame tempObj = new PlayGame(1);
        createButtons();
        createRadioButtons();
        createPanel();
        createFrame();
    }

    /**
     *
     * The method that creates all of the JButtons seen in the MenuGUI.
     *
     * 5 JButtons are created. playButton, settingsButton, statisticsButton, howToPlayButton and quitButton.
     * They are all set to the same style, and given anonymous ActionListener Classes, as actionListeners, so that each of them
     * can do a specific action, that has already been described.
     */
    public void createButtons(){
        playButton = new JButton("Play");
        settingsButton = new JButton("Settings");
        statisticsButton = new JButton("Statistics");
        howToPlayButton = new JButton("How to Play");
        quitButton= new JButton("Quit");

        playButton.setFocusable(false);
        settingsButton.setFocusable(false);
        statisticsButton.setFocusable(false);
        howToPlayButton.setFocusable(false);
        quitButton.setFocusable(false);

//
        playButton.setPreferredSize(new Dimension(150,50));
        settingsButton.setPreferredSize(new Dimension(150,50));
        statisticsButton.setPreferredSize(new Dimension(150,50));
        howToPlayButton.setPreferredSize(new Dimension(150,50));
        quitButton.setPreferredSize(new Dimension(150,50));

        playButton.setBackground(new Color(250,200,100));
        settingsButton.setBackground(new Color(250,200,100));
        statisticsButton.setBackground(new Color(250,200,100));
        howToPlayButton.setBackground(new Color(250,200,100));
        quitButton.setBackground(new Color(250,200,100));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginGUI();
                menuFrame.dispose();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsGUI();
                menuFrame.dispose();
            }
        });

        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StatisticsGUI();
                menuFrame.dispose();
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HowToPlayGUI();
                menuFrame.dispose();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
            }
        });

    }

    /**
     *
     * In the MenuGUI class, two radioButtons are created by this method.
     *
     * Those two radioButtons, are representing a selection for one or two player game. They are created with the same style, and then
     * the 1 player button is selected as default. The radioButtons are given anonymous ActionListener classes, so that when selected,
     * they set the number of players (of the settings object) to 1 or 2 respectively.
     * Finally theyy're added on a ButtonGroup so that their selections are mutually exclusive.
     */
    public void createRadioButtons(){
        ButtonGroup playerNumberGroup = new ButtonGroup();

        onePlayerRadioButton = new JRadioButton("1 player");
        twoPlayerRadioButton = new JRadioButton("2 players");

        onePlayerRadioButton.setFocusable(false);
        twoPlayerRadioButton.setFocusable(false);

        onePlayerRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
        twoPlayerRadioButton.setHorizontalAlignment(SwingConstants.LEFT);

        onePlayerRadioButton.setBackground(new Color(200, 200, 200));
        onePlayerRadioButton.setSelected(true);
        settings.setNumOfPlayers(1);
        twoPlayerRadioButton.setBackground(new Color(200, 200, 200));

        onePlayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setNumOfPlayers(1);
            }
        });

        twoPlayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setNumOfPlayers(2);
            }
        });

        playerNumberGroup.add(onePlayerRadioButton);
        playerNumberGroup.add(twoPlayerRadioButton);

    }

    /**
     *
     * In the MenuGUI class, three JPanels are created and added to the frame. Those are the mainPanel, the topPanel
     * and the bottomPanel.
     *
     * The mainPanel has a flowLayout and contains the 4 buttons for play, settings and statistics.
     * The topPanel contains a JLabel welcoming players to the game.
     * The bottomPanel has a flowLayout and contains the quitButton.
     *
     * The topPanel has a slightly different colour, to stand out as a header.
     * The mainPanel also contains a space JLabel, that has the same color with the background of the mainPanel, blending in and acting as space.
     */
    public void createPanel(){
        mainPanel = new JPanel();
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 15));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        mainPanel.add(playButton);
        mainPanel.add(settingsButton);
        mainPanel.add(statisticsButton);
        mainPanel.add(howToPlayButton);
        JLabel space = new JLabel("nobody is going to know nobody is going to know nobody is going to know nobody is going to know nobody is going to know nobody is going to know nobody is going to know");
        space.setForeground(new Color(200,200,200));
        space.setFont(new Font("Arial", Font.PLAIN, 60));
        mainPanel.add(space);
        mainPanel.add(quitButton);
        bottomPanel.add(onePlayerRadioButton, Component.RIGHT_ALIGNMENT);
        bottomPanel.add(twoPlayerRadioButton, Component.RIGHT_ALIGNMENT);


        JLabel welcomeText = new JLabel("Welcome to BuzzQuizWorld");

        welcomeText.setForeground(new Color(250,250,250));
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 20));
        topPanel.add(welcomeText);

        topPanel.setBackground(new Color(100, 100, 100));
        mainPanel.setBackground(new Color(200, 200, 200));
        bottomPanel.setBackground(new Color(200, 200, 200));
    }

    /**
     *
     * The MenuGUI has a menuFrame, that is created by this method.
     *
     * The menuFrame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains\
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanel method.
     *
     * The menuFrame is not resizable and when created, it is located on the center of the screen.
     */
    public void createFrame(){

        menuFrame = new JFrame();
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setTitle("Quizworld");
        menuFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);



        menuFrame.setLayout(new BorderLayout());
        menuFrame.add(mainPanel, BorderLayout.CENTER);
        menuFrame.add(topPanel, BorderLayout.NORTH);
        menuFrame.add(bottomPanel, BorderLayout.SOUTH);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setVisible(true);

    }

}
