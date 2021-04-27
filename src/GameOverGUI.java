import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The GameOverGUI class, the window that opens when a game has ended, to show the user(s) the score
 * of the player(s) and if it was a two player game, the result of the game.
 *
 * If the player (or any of the two players for a two player game) have achieved a new highscore, the
 * GameOverGUI window displays a message to inform the player.
 *
 * In the bottom of the window, there is a button that the user(s) can click to continue to the main menu.
 *
 */
public class GameOverGUI {

    PlayGame game;

    private JFrame mainFrame;

    private JPanel topPanel;
    private JPanel mainTopPanel;
    private JPanel mainMainPanel;
    private JPanel mainBottomPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     *
     * The constructor of the GameOverGUI class.
     * The constructor calls the createFrame() method, to create the frame and all the
     * GUI elements and components.
     *
     */
    public GameOverGUI(){
        createFrame();
    }

    /**
     * The GameOverGUI class has a frame, that is created by this method.
     *
     * The frame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanels method.
     *
     * The mainFrame is not resizable and when created, it is located on the center of the screen.
     */
    public void createFrame(){
        game = new PlayGame();

        mainFrame = new JFrame();
        createPanels();

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Quizworld");
        mainFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);



        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    /**
     *
     * In the GameOverGUI class, there are three JPanels that are created and then added to the frame. The topPanel,
     * the mainPanel and the bottomPanel.
     *
     *
     *
     * The topPanel contains a JLabel, with the text "Game Over!!!".
     *
     * The mainPanel contains three JPanels, the mainTopPanel, the mainMainPanel and the mainBottomPanel that are also created
     * by this method.
     *
     * If it is a one player game, the mainTopPanel contains a JLabel with the name of the player, and the
     * mainBottomPanel a JLabel with the score of the player.
     *
     * If it is a two player game, the mainTopPanel contains a JLabel with the result of the game (Who won, or if it is a tie),
     * the mainMainPanel contains JLabels for the names of both players. Finally, the mainBottomPanel contains JLabels for the scores of both players.
     *
     * The bottomPanel contains a continueButton that disposes the frame of the GameOverGUI and creates
     * a new MenuGUI object, sending the user to the main menu.
     *
     */
    public void createPanels(){

        topPanel = new JPanel();
        mainTopPanel = new JPanel();
        mainMainPanel = new JPanel();
        mainBottomPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setBackground(new Color(100,100,100));
        mainTopPanel.setBackground(new Color(200,200,200));
        mainMainPanel.setBackground(new Color(200,200,200));
        mainBottomPanel.setBackground(new Color(200,200,200));
        mainPanel.setBackground(new Color(200,200,200));
        bottomPanel.setBackground(new Color(200,200,200));

        JLabel gameOverLabel = new JLabel("GAME OVER!!!");
        gameOverLabel.setForeground(new Color(200,200,200));
        gameOverLabel.setFont(new Font("Arial",Font.PLAIN, 20));

        if(game.getNumOfPlayers() == 1){
            JLabel playerNameLabel = new JLabel();
            playerNameLabel.setText(game.getPlayer().getUsername());
            JLabel scoreLabel = new JLabel();
            String score = "You have finished the game with " + game.getPlayer().getPoints() + " points.";
            if(game.getPlayer().checkHighScore(game.getPlayer().getPoints())){
                scoreLabel.setText(score + ". This is a new Highscore!!!");
            }else{
                scoreLabel.setText(score);
            }

            playerNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));

            mainTopPanel.add(playerNameLabel);
            mainBottomPanel.add(scoreLabel);

        }
        else if(game.getNumOfPlayers() == 2){
            JLabel playerOneNameLabel = new JLabel();
            JLabel playerTwoNameLabel = new JLabel();

            playerOneNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            playerTwoNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            playerOneNameLabel.setText(game.getPlayers().get(0).getUsername());
            playerTwoNameLabel.setText(game.getPlayers().get(1).getUsername());

            JLabel playerOneScoreLabel = new JLabel();
            JLabel playerTwoScoreLabel = new JLabel();

            int playerOneScore = game.getPlayers().get(0).getPoints();
            int playerTwoScore = game.getPlayers().get(1).getPoints();

            playerOneScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            playerTwoScoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            if(game.getPlayers().get(0).checkHighScore(playerOneScore)){
                playerOneScoreLabel.setText("Score: " + playerOneScore + ". This is a new Highscore!!!");
            }else{
                playerOneScoreLabel.setText("Score: " + playerOneScore);
            }
            if(game.getPlayers().get(1).checkHighScore(playerTwoScore)){
                playerTwoScoreLabel.setText("Score: " + playerTwoScore + ". This is a new Highscore!!!");
            }else{
                playerTwoScoreLabel.setText("Score: " + playerTwoScore);
            }

            JLabel matchResultLabel = new JLabel();
            matchResultLabel.setFont(new Font("Arial", Font.PLAIN, 18));

            if(playerOneScore > playerTwoScore){
                matchResultLabel.setText(playerOneNameLabel.getText() + " won the game against " + playerTwoNameLabel.getText() + ".");
                game.getPlayers().get(0).setWinsInFile(game.getPlayers().get(0), game.getPlayers().get(1), 1);
            }else if(playerOneScore < playerTwoScore){
                matchResultLabel.setText(playerTwoNameLabel.getText() + " won the game against " + playerOneNameLabel.getText() + ".");
                game.getPlayers().get(1).setWinsInFile(game.getPlayers().get(0), game.getPlayers().get(1), 2);
            }else{
                matchResultLabel.setText("The match between " + playerOneNameLabel.getText() + " and " + playerTwoNameLabel.getText() + " was a tie.");
            }
            mainTopPanel.add(matchResultLabel);

            mainMainPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 200, 10));
            mainMainPanel.add(playerOneNameLabel);
            mainMainPanel.add(playerTwoNameLabel);
            mainBottomPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 200, 10));
            mainBottomPanel.add(playerOneScoreLabel);
            mainBottomPanel.add(playerTwoScoreLabel);
        }


        topPanel.add(gameOverLabel);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 400,3));

        JLabel seperator = new JLabel("NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOOOOOOOOOOOOOOW!!!");
        seperator.setFont(new Font("Arial", Font.PLAIN, 60));
        seperator.setForeground(new Color(200,200,200));
        JLabel seperator2 = new JLabel("NOBODY IS GOING TO KNOW NOBODY IS GOING TO KNOOOOOOOOOOOOOOW!!!");
        seperator2.setFont(new Font("Arial", Font.PLAIN, 60));
        seperator2.setForeground(new Color(200,200,200));

        mainPanel.add(seperator);
        mainPanel.add(mainTopPanel);
        mainPanel.add(seperator2);
        mainPanel.add(mainMainPanel);
        mainPanel.add(mainBottomPanel);

        JButton continueButton = new JButton();
        continueButton.setText("Continue");
        continueButton.setFont(new Font("Arial", Font.PLAIN, 10));
        continueButton.setPreferredSize(new Dimension(90, 45));
        continueButton.setBackground(new Color(250,200,100));
        continueButton.setFocusable(false);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new MenuGUI();
            }
        });

        bottomPanel.add(continueButton);
    }

}
