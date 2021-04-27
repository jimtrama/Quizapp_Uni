import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * The Settings GUI class, the window where you can adjust the settings of the game.
 *
 * In this window you can tweak to your liking two settings for the game from two JTextFields. The number
 * of rounds and the number of questions per round.
 *
 * When you're done, you simply press the back button to go back to the menu.
 *
 */
public class SettingsGUI {
    private final Settings settingLogic;
    private JFrame frame;
    private JTextField numOfRoundsTextField;
    private JTextField numOfQuestionsPerRoundTextField;

    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JPanel topPanel;

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    private JLabel wrongInputLabel;

    /**
     * The constructor of the SettingsGUI class, that initializes a Settings object and then
     * calls the createPanel and createMainFrame methods, to create the GUI elements and components.
     */
    public SettingsGUI(){
        settingLogic = new Settings();
        createPanel();
        CreateMainFrame();
    }


    /**
     *
     * In the SettingsGUI class, there are three JPanels that are created and then added to the frame. The topPanel,
     * the mainPanel and the bottomPanel.
     *
     * The mainPanel has a flowLayout and contains the two JTextFields (numOfRoundsTextField and numOfQuestionsPerRoundTextField)
     * that are used to get input from the player for his desired settings. The mainPanel also
     * contains a wrongInput label, that has the same color with the background of the panel,
     * but turns red (hence visible) when the user(s) try to put incorrect input.
     *
     * The topPanel contains a JLabel, prompting the user to choose his settings.
     *
     * The bottomPanel contains a backButton that disposes the frame of the SettingsGUI and creates
     * a new MenuGUI object, effectively "sending" the player back to the main menu.
     */
    public void createPanel(){
        JLabel numOfRoundsLabel = new JLabel("Number of Rounds: ");
        JLabel numOfQuestionsPerRoundsLabel = new JLabel("Number of Questions Per Rounds: ");
        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(100, 50));
        backBtn.setFocusable(false);
        backBtn.setBackground(new Color(250, 200, 100));
        numOfRoundsTextField = new JTextField(20);
        numOfQuestionsPerRoundTextField = new JTextField(20);
        numOfRoundsTextField.addKeyListener(KeyPressRounds());
        numOfQuestionsPerRoundTextField.addKeyListener(KeyPressQuestions());
        backBtn.addActionListener(BackBtnClicked());
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(200,200,200));
        topPanel = new JPanel();

        JLabel settingsLabel = new JLabel("Choose your settings");
        wrongInputLabel = new JLabel("The number of rounds and number of questions per round must be numbers between 1 and 10.");
        wrongInputLabel.setForeground(new Color(200,200,200));
        wrongInputLabel.setFont(new Font("Arial", Font.ITALIC, 8));

        mainPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 250, 10));
        topPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 300, 5));
        JLabel space = new JLabel("nobody is going to know nobody is going to know nobody will ever knwoooooooow");
        space.setForeground(new Color(200,200,200));
        space.setFont(new Font("Arial", Font.PLAIN, 50));
        mainPanel.add(wrongInputLabel, Component.CENTER_ALIGNMENT);
        mainPanel.add(space);
        mainPanel.add(numOfRoundsLabel, Component.LEFT_ALIGNMENT);
        mainPanel.add(numOfRoundsTextField, Component.RIGHT_ALIGNMENT);
        mainPanel.add(numOfQuestionsPerRoundsLabel, Component.LEFT_ALIGNMENT);
        mainPanel.add(numOfQuestionsPerRoundTextField, Component.RIGHT_ALIGNMENT);

        settingsLabel.setForeground(new Color(250,250,250));
        settingsLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        topPanel.add(settingsLabel);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(backBtn);

        topPanel.setBackground(new Color(100, 100, 100));
        mainPanel.setBackground(new Color(200, 200, 200));
        bottomPanel.setBackground(new Color(200, 200, 200));


    }

    /**
     * The SettingsGUI class has a frame, that is created by this method.
     *
     * The frame has a size of SCREEN_WIDTH (800) x SCREEN_HEIGHT (600), has a BorderLayout and contains
     * the three panels (mainPanel, topPanel and bottomPanel) created by the createPanel method.
     *
     * The mainFrame is not resizable and when created, it is located on the center of the screen.
     */
    private void CreateMainFrame(){
        frame = new JFrame("Settings");
        frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * This is the ActionListener for the backBtn, that disposes the Settings frame and creates a new MenuGUI object.
     * @return an ActionListener to be added to the Back button.
     */
    private ActionListener BackBtnClicked(){
        return e->{
            frame.dispose();
            new MenuGUI();
        };
    }

    /**
     *
     * This is a KeyListener for the JTextfield where the user inputs the number of rounds he
     * wants to have in his game.
     *
     * This KeyListener makes the JTextField uneditable when the user inputs something else than a number, or
     * if the number the user inputs is not between 1 and 10.
     * If that happens, a red JLabel warning him to enter valid input appears.
     *
     * If the input is correct, the KeyListener sets the number of rounds of the Settings class to the
     * number the user has input.
     *
     *
     * @return a KeyListener to be added on the JTextfield numOfRoundsTextField of the mainPanel.
     */
    private KeyListener KeyPressRounds() {
        return new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                    String text;

                    if(e.getKeyCode()==8){
                        numOfRoundsTextField.setEditable(true);

                    }else if(e.getKeyCode()>='0'&&e.getKeyCode()<='9'){
                        numOfRoundsTextField.setEditable(true);

                        text = numOfRoundsTextField.getText() + e.getKeyChar();
                        int number = Integer.parseInt(text);

                        if(number>10||number<=0){
                            numOfRoundsTextField.setEditable(false);
                            wrongInputLabel.setForeground(Color.RED);
                        }else{
                            settingLogic.setNumberOfRounds(number);
                        }
                    }else{
                        numOfRoundsTextField.setEditable(false);
                        wrongInputLabel.setForeground(Color.RED);
                    }
                }


        };
    }

    /**
     *
     * This is a KeyListener for the JTextfield where the user inputs the number of question he
     * wants to have in each round of his game.
     *
     * This KeyListener makes the JTextField uneditable when the user inputs something else than a number, or
     * if the number the user inputs is not between 1 and 10.
     * If that happens, a red JLabel warning him to enter valid input appears.
     *
     * If the input is correct, the KeyListener sets the number of questions per round of the Settings class
     * to the number the user has input.
     *
     *
     * @return a KeyListener to be added on the JTextfield numOfQuestionsPerRoundTextField of the mainPanel.
     */
    private KeyListener KeyPressQuestions() {
        return new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                String text;
                if(e.getKeyCode()==8){
                    numOfQuestionsPerRoundTextField.setEditable(true);
                }else if(e.getKeyCode()>='0' && e.getKeyCode()<='9'){
                    numOfQuestionsPerRoundTextField.setEditable(true);
                    text=numOfQuestionsPerRoundTextField.getText() + e.getKeyChar();
                    int number = Integer.parseInt(text);
                    if(number>10||number<=0){
                        numOfQuestionsPerRoundTextField.setEditable(false);
                        wrongInputLabel.setForeground(Color.RED);
                    }else{
                        settingLogic.setNumberOfQuestionsPerRound(number);
                    }
                }else{
                    numOfQuestionsPerRoundTextField.setEditable(false);
                    wrongInputLabel.setForeground(Color.RED);
                }
            }
        };
    }
}
