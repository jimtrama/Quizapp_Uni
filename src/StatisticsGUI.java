import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 *
 * The StatisticsGUI class, the window where the user inputs a username and is shown statistics for the player with that
 * specific username.
 *
 * In this window there is a JTextField where the user inputs the username of the user he wants to see statistics for.
 * There is a JLabel that shows the highScore of said user and there is a JTextArea with statistics for the wins this user
 * has against other users.
 *
 * When the user is done, he can click the back button to return to the main menu.
 *
 */
public class StatisticsGUI {

    JFrame mainFrame;

    JPanel topPanel;
    JPanel mainTopPanel;
    JPanel mainMainPanel;
    JPanel mainBottomPanel;
    JPanel mainPanel;
    JPanel bottomPanel;
    JTextField usernameTextField;

    JTable table;
    JScrollPane scrollPane;

    JScrollPane winsScrollPane;
    JTextArea winsTextArea;
    private Login login;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /**
     *
     * The constructor of the StatisticsGUI class, that initialises an object of the Login class and calls the
     * createFrame method, to create the frame of this GUI.
     *
     */
    public StatisticsGUI(){
        login = new Login();
        createFrame();
    }

    /**
     * The StatisticsGUI class has a frame, that is created by this method.
     *
     * The frame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanel method.
     *
     * The menuFrame is not resizable and when created, it is located on the center of the screen.
     */
    public void createFrame(){
        createPanels();

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Statistics");
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
     * In the StatisticsGUI class, there are three JPanels that are created and then added to the frame. The topPanel
     * the mainPanel and the bottomPanel. There are three more JPanels created and added inside the mainPanel. Those are
     * the mainTopPanel, the mainMainPanel and the mainBottomPanel.
     *
     * The topPanel contains a JLabel with the word Statistics.
     *
     * In the mainTopPanel, there is a JLabel prompting the player to enter a username and a JTextField for this action.
     * In the mainMainPanel, there is a JLabel that shows the highscore of every player the user enters.
     * In the mainBottomPanel, there is a JTextArea that shows the wins of every player the user enters has against other players.
     * The JTextArea is scrollable and is created in another method.
     *
     * In the bottomPanel, there is a JButton that the user can click to return to the mainMenu.
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

        JLabel headerLabel = new JLabel("Statistics");
        headerLabel.setForeground(new Color(250,250,250));
        headerLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        topPanel.add(headerLabel);
        JLabel highScoreLabel = new JLabel();
        highScoreLabel.setText("");
        JLabel usernameEnterLabel = new JLabel("Enter a username: ");
        mainTopPanel.add(usernameEnterLabel);
        usernameTextField = new JTextField(20);
        usernameTextField.addKeyListener(KeyPress(highScoreLabel));
        mainTopPanel.add(usernameTextField);


        highScoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        mainMainPanel.add(highScoreLabel);

        mainMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 600,20));



//        mainBottomPanel.add(winsScrollPane);
//        mainBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 600,20));

        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.setBackground(new Color(250,200,100));
        backButton.setPreferredSize(new Dimension(70,35));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                new MenuGUI();
            }
        });

        bottomPanel.add(backButton);

        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200,10));
        mainPanel.add(mainTopPanel);
        mainPanel.add(mainMainPanel);
        mainPanel.add(mainBottomPanel);

    }



    /**
     *
     * This is a KeyListener for the JTextfield where the user inputs the username.
     *
     * This KeyListener makes the JTextField uneditable when the user inputs a space, or other irrelevant keys,
     * or if the player has added too many characters on the box.
     *
     * Afterwards, the KeyListener checks if the username exists in the database. If it does, then it gets the
     * users highscore and sets it on the JLabel that needs to display it and calls the TODO writeWinsInTextArea method.
     *
     * If the username does not exist, then it just sets the text of the JLabel to "".
     *
     * @param label a JLabel that displays the highscore of a user.
     * @return a KeyListener to be added on the JTextfield of the mainTopPanel.
     */
    private KeyListener KeyPress(JLabel label) {
        KeyListener KeyListener = new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                usernameTextField.setEditable(true);
                if(e.getKeyChar()==' '){
                    usernameTextField.setEditable(false);
                }
                if(usernameTextField.getText().length()>13){
                    usernameTextField.setEditable(false);
                }
                if(e.getKeyCode()==8){
                    usernameTextField.setEditable(true);
                }
                if(login.checkPlayerExists(usernameTextField.getText().strip()+e.getKeyChar())){
                    Player player = new Player(usernameTextField.getText().strip()+e.getKeyChar());
                    label.setText("High Score :"+player.getHighestScore());
                    writeWinsInTextArea(player);
                    mainBottomPanel.add(scrollPane);
                }else{
                    label.setText("");
                    try{
                        mainBottomPanel.remove(scrollPane);
                    }
                    catch(Exception err){

                    }
                }
            }


        };
        return KeyListener;
    }

    /**
     * This Function writes the data from the getWins() in a table adds some
     * statistical values such as the percent of wining a user
     * and displays it to the user
     * @param player
     */
    public void writeWinsInTextArea(Player player){

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Opponent");
        model.addColumn("Your Wins");
        model.addColumn("Your Losses");
        model.addColumn("% Won");
        int wins=0;
        int looses=0;
        for(ArrayList<String> row : getWins(player)){
            String [] rowTemp = new String[4];
            System.out.println(row.get(0));
            wins+=Integer.parseInt(row.get(1));
            looses+=Integer.parseInt(row.get(2));
            rowTemp[0]=row.get(0);
            rowTemp[1]=row.get(1);
            rowTemp[2]=row.get(2);
            rowTemp[3] = (Integer.parseInt(row.get(1))*100/(Integer.parseInt(row.get(1))+Integer.parseInt(row.get(2))))+"%";
            model.addRow(rowTemp);
        }

        if(wins==0&&looses==0){
            scrollPane=new JScrollPane();
            scrollPane.setVisible(false);
            return;
        }
        String [] rowTemp = new String[4];
        rowTemp[0]="";
        rowTemp[1]="";
        rowTemp[2]="";
        rowTemp[3] = "";
        model.addRow(rowTemp);
        rowTemp[0]="Total";
        rowTemp[1]=wins+"";
        rowTemp[2]=looses+"";

        rowTemp[3] = wins*100/(wins+looses)+"%";
        model.addRow(rowTemp);
        table = new JTable(model);
        table.setGridColor(new Color(200,200,200));
        table.setPreferredScrollableViewportSize(new Dimension(450,200));
        table.setFillsViewportHeight(true);

        scrollPane=new JScrollPane(table);
        scrollPane.setVisible(true);

    }

    /**
     * This Function gets the wins of the passed player with all the users as opponents
     * by reading the wins.txt file line by line and splitting each line in ',' to get the info
     * then it return an ArrayList<ArrayList<String>> that for each element (an opponent) has
     * a List with the data
     * @param player
     * @return
     */
    public ArrayList<ArrayList<String>> getWins(Player player){
        ArrayList<ArrayList<String>> toReturn = new ArrayList<>();
        ArrayList<String[]> lines = player.readLinesFromFile();

        for(int i =0;i< lines.size();i++){

            if(lines.get(i)[0].equals(player.getUsername())){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(lines.get(i)[1]);
                temp.add(lines.get(i)[2]);
                temp.add(lines.get(i)[3]);
                toReturn.add(temp);

            }
            if(lines.get(i)[1].equals(player.getUsername())){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(lines.get(i)[0]);
                temp.add(lines.get(i)[3]);
                temp.add(lines.get(i)[2]);
                toReturn.add(temp);
            }
        }
        return toReturn;
    }
}
