import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * This class represents the game logic that the user(s) plays (with one or two players).
 *
 */
public class PlayGame {

    private static int numberOfRounds;
    private int numOfPlayers ;
    private static Player player;
    private static ArrayList<Player> players;
    private Settings settings;
    private static String typeOfRound;
    private static String categoryOfQuestions;
    private static HashMap<String,Category> questions;
    private int numberOfQuestionsPerRound;
    private static int roundCount;
    private static int questionsCount;
    private static ArrayList<Question> questionsToReturn;

    /**
     * This is the default constructor for this class.
     * It initializes an object of the Settings class, and gets the settings for
     * the number of rounds, the number of questions per round and the number of
     * the players.
     *
     * Then if the number of players is one, it gets the player object from the settings.
     * Otherwise, it gets both of the player objects and adds them to the players ArrayList.
     *
     */
    public PlayGame() {
        settings = new Settings();
        PlayGame.numberOfRounds = settings.getNumOfRounds();
        this.numberOfQuestionsPerRound =  settings.getNumOfQuestionsPerRound();
        this.numOfPlayers = settings.getNumOfPlayers();
        if(numOfPlayers==1){
            player=settings.getPlayer();
        }else{
            players = new ArrayList<>();
            players.addAll(settings.getPlayers());
        }
    }

    /**
     *
     * This is a constructor of the PlayGame class that is called in the main menu,
     * so that the questions HashMap can get initialized.
     *
     * After the HashMap is initialized, it is filled with all the questions from all the categories.
     *
     * @param temp an int parameter, so that this constructor is called.
     */
    public PlayGame(int temp){
        questions = new HashMap<>();
        ArrayList<String> tempCategories = new ArrayList<>(Arrays.asList("films","sports","football","generalknowledge","worldhistory","science"));
        for(String category :tempCategories){
            Category objCategory = new Category(category);
            questions.put(category,objCategory);
        }
    }

    /**
     *
     * This is a constructor of the PlayGame class that is called in everyRound to
     * initialize the questionsCount, since in every round it needs to be reset.
     *
     * After the initialization/reset of the questionsCount, the constructor does
     * the same things as the default constructor of the class.
     *
     * It initializes an object of the Settings class, and gets the settings for the number
     * of rounds, the number of questions per round and the number of the players.
     * Then if the number of players is one, it gets the player object from the settings.
     * Otherwise, it gets both of the player objects and adds them to the players ArrayList.
     *
     * @param temp a String parameter, so that this constructor is called.
     */
    public PlayGame(String temp){

        questionsCount=0;

        settings = new Settings();
        PlayGame.numberOfRounds = settings.getNumOfRounds();
        this.numberOfQuestionsPerRound =  settings.getNumOfQuestionsPerRound();
        this.numOfPlayers = settings.getNumOfPlayers();
        if(numOfPlayers==1){
            player=settings.getPlayer();
        }else{
            players = new ArrayList<>();
            players.addAll(settings.getPlayers());
        }
    }

    public  int getQuestionsCount() {
        return questionsCount;
    }

    public  void setQuestionsCount(int questionsCount) {
        PlayGame.questionsCount = questionsCount;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public String getCategoryOfQuestions() {
        return categoryOfQuestions;
    }

    public void setCategoryOfQuestions(String categoryOfQuestions) {
        PlayGame.categoryOfQuestions = categoryOfQuestions;
    }

    public String getTypeOfRound() {
        return typeOfRound;
    }

    public void setTypeOfRound(String typeOfRound) {
        PlayGame.typeOfRound = typeOfRound;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        PlayGame.numberOfRounds = numberOfRounds;
    }
    public int getNumberOfRounds() {
        return PlayGame.numberOfRounds;
    }

    public int getNumberOfQuestionsPerRound() {
        return numberOfQuestionsPerRound;
    }
    public void setNumberOfQuestionsPerRound(int x) {
         numberOfQuestionsPerRound=x;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }


    public Player getPlayer() {
        return player;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Question> getQuestions(){
        return questionsToReturn;
    }

    /**
     *
     * This method checks to see which of the six question categories
     * are available to play for the next round.
     * A category is available when the number of the questions of this category,
     * that have not been already played, are equal, or more than the nymberOfQuestionsPerRound setting.
     *
     * this emthod creates an ArrayList that will store the names of the available categories.
     * Then for every category, it checks if the number of the questions that remain is bigger than
     * the numberOfQuestionsPerRound. If it is, then it adds this category to the availableCategories
     * ArrayList.
     *
     * @return an ArrayList with the names of the available categories.
     */
    public ArrayList<String> availableCategories(){
        ArrayList<String> availableCategories = new ArrayList<>();
        for(Category category : questions.values()){
            if(category.getQuestions().size()>=numberOfQuestionsPerRound){
                availableCategories.add(category.getType());
            }
        }
        return availableCategories;
    }

    /**
     *
     * Initializes the RoundCount, when a new game begins.
     */
    public void initializeRoundCount(){
        roundCount = 0;
    }

    /**
     * Checks if the player has chosen the correct answer for the question, by calling the
     * checkIfCorrect method of the current question object, and passing as arguments the
     * ArrayList containing the answers and an int answer index, that shows what answer
     * the player has chosen.
     *<p>
     *      @param question the question object that is currently been asked from the player.
     *      @param answer The answer index the player has chosen
     *      @return returns true if the player's answer was correct, otherwise it returns false.
     */
    public boolean checkIfCorrect(Question question, int answer){

        if(!question.checkCorrectAnswer(question.getAnswers().get(answer -1))){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * This method initializes the questionArrayList, and then fills it with
     * questions in a random order, by calling the getRandomOrderQuestions() method
     * of the Category class (the question category that the player has chosen to play).
     *
     */
    public void initializeQuestionsArray(){
        questionsToReturn = new ArrayList<>();
        questionsToReturn = questions.get(categoryOfQuestions).getRandomOrderQuestions(numberOfQuestionsPerRound);
    }
}