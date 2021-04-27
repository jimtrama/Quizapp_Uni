import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * The Login GUI class, that opens when the user clicks the play button in the main menu.
 *
 * In this window, depending on whether the user has selected one or two players for the game, has to put
 * one or two usernames, depending on the choice he made in the main menu.
 * He then can hit login, to play the game, or click the "X" button to close the window and return to the
 * main menu.
 * Whenever the user gives a username that does not exist in the database, a small window opens asking him
 * if he wants to create a new user with that username. He can accept, or decline and put another username.
 */
public class LoginGUI {
    private Login loginLogic;
    private JTextField username1;
    private JTextField username2;
    private JFrame mainFrame;
    private JPanel topPanel;
    private JPanel mainPanel;
    private JPanel bottomPanel;

    JButton loginButton;
    JLabel alertLabel;


    /**
     *
     * The constructor of the LoginGUI class.
     *
     * The constructor initialises a Login class object, a JTextfield and then calls the createButton(), createPanel()
     * and the createMainFrame() methods.
     */
    public LoginGUI(){
        loginLogic = new Login();
        createButton();
        createPanel(loginLogic.getNumOfPlayers());
        CreateMainFrame();
    }

    /**
     *
     * The method creates the loginButton in the loginGUI.
     *
     * The user clicks this button when he wants to continue to play the game.
     * The ActionListener LoginBtnClicked is documented below.
     */
    public void createButton(){
        loginButton = new JButton("Login");
        loginButton.addActionListener(loginBtnClicked());
        loginButton.setPreferredSize(new Dimension(100, 50));
        loginButton.setBackground(new Color(250, 200, 100));
        loginButton.setFocusable(false);
    }

    /**
     *
     * In the MenuGUI class, three JPanels are created and added to the frame. Those are the mainPanel, the topPanel
     * and the bottomPanel.
     *
     * The mainPanel has a flowLayout and contains either one JTextField or two JTextfields, along with prompts
     * for the user to enter a username. This depends on the numOfPlayers parameter. It also contains a hidden
     * alert label, that becomes visible if the user has entered invalid usernames, or if the two usernames are the
     * same and displays an appropriate message.
     *
     * The topPanel contains a header JLabel, prompting the user to login.
     *
     * the bottomPanel contains the loginButton.
     *
     * @param numOfPlayers
     */
    public void createPanel(int numOfPlayers){
        topPanel = new JPanel();
        mainPanel = new JPanel();
        bottomPanel = new JPanel();

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 15));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel header = new JLabel("Please login.");
        header.setForeground(new Color(200,200,200));
        header.setFont(new Font("Arial", Font.PLAIN, 20));

        alertLabel = new JLabel();
        alertLabel.setText("_ALERT_ALERT_ALERT");
        alertLabel.setForeground(new Color(200,200,200));
        alertLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        mainPanel.add(alertLabel);

        topPanel.add(header);

        username1 = new JTextField(20);
        username1.addKeyListener(KeyPress(username1));

        if(numOfPlayers==1){
            mainPanel.add(new JLabel("Enter Your Username"), Component.CENTER_ALIGNMENT);
            mainPanel.add(username1, Component.CENTER_ALIGNMENT);
        }else{

            mainPanel.add(new JLabel("Enter Your Username For Player1"), Component.CENTER_ALIGNMENT);
            mainPanel.add(username1, Component.CENTER_ALIGNMENT);

            username2 = new JTextField(20);
            username2.addKeyListener(KeyPress(username2));
            mainPanel.add(new JLabel("Enter Your Username For Player2"), Component.CENTER_ALIGNMENT);
            mainPanel.add(username2, Component.CENTER_ALIGNMENT);
        }

        bottomPanel.add(loginButton);

        topPanel.setBackground(new Color(100, 100, 100));
        mainPanel.setBackground(new Color(200, 200, 200));
        bottomPanel.setBackground(new Color(200, 200, 200));

    }

    /**
     *
     * The LoginGUI has a mainFrame, that is created by this method.
     *
     * The mainFrame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanel method.
     *
     * It also has a custom WindowListener, to dispose the frame and return to the main menu, instead of exiting
     * the programm when clicking on the "X" button.
     *
     * The mainFrame is not resizable and when created, it is located on the center of the screen.
     */
    private void CreateMainFrame(){
        mainFrame = new JFrame("Login");
        mainFrame.setSize(350, 350);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                new MenuGUI();
                mainFrame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(topPanel, BorderLayout.NORTH);
        mainFrame.add(mainPanel,BorderLayout.CENTER);
        mainFrame.add(bottomPanel,BorderLayout.SOUTH);
        mainFrame.setVisible(true);

    }

    /**
     *
     * This method is called whenever the login button is clicked and the username the user has input does
     * not exist in the database. A new userFrame is created and appears above the mainFrame of the LoginGUI.
     *
     * This userFrame has a size of 350 x 175 and contains the two panels that are also created in this method
     * (topPanel and mainPanel)
     *
     * In the top Panel there is a JLabel asking the user to confirm that he wants to create one or two new players
     * with the usernames he has input in the mainFrame's JTextFields.
     *
     * The mainPanel contains two JButtons, one for the user to accept and continue to the game and one for the
     * user to decline and go back to input the username(s).
     *
     * The userFrame is not resizable and when created, it is located on the center of the screen. You can close
     * the frame and go back to the mainFrame of the LoginGUI class by clicking the "X" button.
     *
     * The two buttons have ActionListeners that are documented below.
     *
     * @param userToCreate a number that indicates which of the usernames do not exist in the database.
     *                     <li>1 for the username in username1 JTextfield</li>
     *                     <li>2 for the username in username2 JTextfield</li>
     *                     <li>12 for the usernames in both the JTextfields</li>
     */
    private void createUserFrame(int userToCreate){
        JFrame userFrame = new JFrame();
        userFrame.setLayout(new BorderLayout());
        userFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        userFrame.setSize(350, 175);
        userFrame.setResizable(false);
        userFrame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        JPanel mainPanel = new JPanel();

        topPanel.setBackground(new Color(100, 100, 100));
        mainPanel.setBackground(new Color(200,200,200));


        JButton confirmBtn= new JButton("Yes");
        JButton declineBtn = new JButton("No");

        confirmBtn.setFocusable(false);
        declineBtn.setFocusable(false);

        confirmBtn.setBackground(new Color(250, 200, 100));
        declineBtn.setBackground(new Color(250, 200, 100));

        confirmBtn.addActionListener(YesBtnClicked(userFrame));
        declineBtn.addActionListener(NoBtnClicked(userFrame));

        confirmBtn.setPreferredSize(new Dimension(100, 50));
        declineBtn.setPreferredSize(new Dimension(100, 50));

        JLabel confirmLabel = new JLabel();
        if(userToCreate == 1){
            confirmLabel.setText("Do you want to create the user " + username1.getText() + "?");
        }
        if(userToCreate == 2){
            confirmLabel.setText("Do you want to create the user " + username2.getText() + "?");
        }
        if(userToCreate == 12){
            confirmLabel.setText("Do you want to create the users " + username1.getText() +" & "+ username2.getText() + "?");
        }
        confirmLabel.setForeground(Color.WHITE);
        confirmLabel.setFont(new Font("Arial", Font.ITALIC, 9));

        topPanel.add(confirmLabel);

        JLabel space = new JLabel("nobody is going to know nobody is going to know nobody is going to know nobody is going to know");
        space.setFont(new Font("Arial", Font.PLAIN, 20));
        space.setForeground(new Color(200,200,200));
        mainPanel.add(space);

        mainPanel.add(confirmBtn);
        mainPanel.add(declineBtn);

        userFrame.add(topPanel, BorderLayout.NORTH);
        userFrame.add(mainPanel, BorderLayout.CENTER);
        userFrame.setVisible(true);

    }

    /**
     *
     * This is the ActionListener for the loginButton.
     *
     * When the loginButton is clicked, this ActionListener first checks if the game is for one or two players.
     *
     * Then checks if the usernames are valid, or if the two usernames are the same. If not, the alertLabel
     * becomes visible in a red color, prompting the player to add a valid username, or that the two usernames
     * can't be the same.
     *
     * Afterwards it calls the loginPlayer() function from the Login Class, to check if the user exists (passing
     * the text from the JTextfields). If one of the usernames or both of them do not exist, then the createUserFrame()
     * method is called, with the appropriate integer passed as a parameter. If all the usernames the user has input
     * exist in the database, then the mainFrame is disposed and a new PlayGameGUI object is created.
     *
     * @return an ActionListener to be added to the LoginButton.
     */
    private ActionListener loginBtnClicked(){
        return e->{
            if(loginLogic.getNumOfPlayers()==1){
                if(username1.getText().length()==0){
                    alertLabel.setText("This is not a valid username.");
                    alertLabel.setForeground(Color.RED);
                }else{
                    boolean userExists = loginLogic.loginPlayer(username1.getText().strip());
                    if(!userExists){
                        createUserFrame(1);
                    }else{
                        mainFrame.dispose();
                        new PlayGameGUI(1);
                        new PlayGameGUI();
                    }

                }
            }else{
                if(username1.getText().length()==0||username2.getText().length()==0){
                    alertLabel.setText("This is not a valid username.");
                    alertLabel.setForeground(Color.RED);
                }else if(username1.getText().strip().equals(username2.getText().strip())){
                    alertLabel.setText("The two usernames can't be the same.");
                    alertLabel.setForeground(Color.RED);
                }else{
                    int users = loginLogic.loginPlayers(username1.getText().strip(),username2.getText().strip());
                    if(users==1){
                        createUserFrame(1);
                    }else if(users==2){
                        createUserFrame(2);
                    }else if(users==12){
                        createUserFrame(12);
                    }else{
                        mainFrame.dispose();
                        new PlayGameGUI(1);
                        new PlayGameGUI();
                    }

                }

            }
        };
    }

    /**
     *
     * This is the ActionListener for the confirmButton of the userFrame. If the game has 1 player, the
     * ActionListener calls the createUser() and loginPlayer() methods from the Login class to create the user
     * and log him in. If the game has 2 players, it does the same for both of them and calls the loginPlayers()
     * method of the Login class.
     *
     * Afterwards a new PlayGameGUI is created and both the frames of the LoginGUI are disposed.
     *
     * @param frame the userFrame of the LoginGUI class.
     * @return an ActionListener to be added to the confirmButton of the userFrame.
     */
    private ActionListener YesBtnClicked(JFrame frame){
        return e->{

            if(loginLogic.getNumOfPlayers()==1){
                loginLogic.createUser(username1.getText().strip());
                loginLogic.loginPlayer(username1.getText().strip());
            }else{
                loginLogic.createUser(username1.getText().strip());
                loginLogic.createUser(username2.getText().strip());
                loginLogic.loginPlayers(username1.getText().strip(),username2.getText().strip());
            }
            new PlayGameGUI(1);
            new PlayGameGUI();
            frame.dispose();
            mainFrame.dispose();
        };
    }

    /**
     *
     * This is the ActionListener for the declineButton of the userFrame. It simply disposes of the userFrame.
     *
     * @param frame the userFrame of the LoginGUI class.
     * @return an ActionListener to be added to the declineButton of the userFrame.
     */
    private ActionListener NoBtnClicked(JFrame frame){
        return e->{
            frame.dispose();

        };
    }

    /**
     *
     * This is a KeyListener for the JTextfields where the user inputs the username.
     *
     * This KeyListener makes the JTextField uneditable when the user inputs a space, or other irrelevant keys,
     * or if the player has added too many characters on the box.
     *
     * @param textbox the JTextField for the user's input.
     * @return a KeyListener to be added on the JTextFields of the mainFrame.
     */
    private KeyListener KeyPress(JTextField textbox) {
        KeyListener KeyListener = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                textbox.setEditable(true);
                if(e.getKeyChar()==' '){
                    textbox.setEditable(false);
                }
                if(textbox.getText().length()>13){
                    textbox.setEditable(false);
                }
                if(e.getKeyCode()==8){
                    textbox.setEditable(true);
                }


            }


        };
        return KeyListener;
    }

}

