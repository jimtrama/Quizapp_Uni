import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PlayGameTest {


    @Test
    public void getNumOfPlayers() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        Settings settings = new Settings();
        settings.setNumOfPlayers(1);
        Player player = new Player("jim");
        settings.setPlayer(player);
        PlayGame game = new PlayGame();
        assertEquals(1,game.getNumOfPlayers());
    }

    @Test
    public void getPlayer() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        Player player = new Player("jim");
        Settings settings = new Settings();
        settings.setPlayer(player);
        PlayGame game = new PlayGame();

        assertNotEquals(null,game.getPlayer());
    }

    @Test
    public void getPlayers() {
        Login login = new Login();
        if(!login.checkPlayerExists("jim")){
            login.createUser("jim");
        }
        if(!login.checkPlayerExists("tram")){
            login.createUser("tram");
        }
        Player player1 = new Player("jim");
        Player player2 = new Player("tram");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Settings settings = new Settings();
        settings.setNumOfPlayers(2);
        settings.setPlayers(players);
        PlayGame game = new PlayGame();
        assertEquals(players.get(0),game.getPlayers().get(0));
        assertEquals(players.get(1),game.getPlayers().get(1));
    }

    @Test
    public void getQuestions() {
        PlayGame game = new PlayGame(1);
        game.setNumberOfQuestionsPerRound(5);
        ArrayList<String> tempCategories = game.availableCategories();
        for(String category :tempCategories){
            game.setCategoryOfQuestions(category);
            game.initializeQuestionsArray();
            assertEquals(5,game.getQuestions().size());
            assertEquals(category,game.getCategoryOfQuestions());
        }
    }

    @Test
    public void availableCategories() {
        PlayGame game = new PlayGame(1);
        int numOfQuestions=10;
        game.setNumberOfQuestionsPerRound(numOfQuestions);
        ArrayList<String> allCategories = new ArrayList<>(Arrays.asList("films","sports","football","generalknowledge","worldhistory","science"));
        ArrayList<String> availableCategories = game.availableCategories();
        for(String category :allCategories){
            game.setCategoryOfQuestions(category);
            game.initializeQuestionsArray();
            if(availableCategories.contains(category)){
                assertTrue(game.getQuestions().size()>=numOfQuestions);
            }else{
                assertTrue(game.getQuestions().size()<numOfQuestions);
            }
        }
    }

    @Test
    public void initializeRoundCount() {
        PlayGame game = new PlayGame(1);
        game.initializeRoundCount();
        assertEquals(0,game.getRoundCount());
    }

    @Test
    public void checkIfCorrect() {
        PlayGame game = new PlayGame(1);
        int numOfQuestions=10;
        game.setNumberOfQuestionsPerRound(numOfQuestions);
        ArrayList<String> allCategories = new ArrayList<>(Arrays.asList("films","sports","football","generalknowledge","worldhistory","science"));
        ArrayList<String> availableCategories = game.availableCategories();
        for(String category :allCategories){
            game.setCategoryOfQuestions(category);
            game.initializeQuestionsArray();
            if(availableCategories.contains(category)){
                for(Question q : game.getQuestions()){
                    int index = q.getCorrectAnswerIndex();
                    assertTrue(game.checkIfCorrect(q,index));
                }
            }
        }

    }

    @Test
    public void initializeQuestionsArray() {
        PlayGame game = new PlayGame(1);
        game.setNumberOfQuestionsPerRound(10);
        game.setCategoryOfQuestions("films");
        game.initializeQuestionsArray();
        try{
            game.getQuestions();
            assertTrue(true);
        }catch (Exception e){
            assertTrue(false);
        }
    }
}