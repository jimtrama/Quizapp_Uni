import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getUsername() {
        Login login = new Login();
        Player player;
        if(!login.checkPlayerExists("jim")) {
            login.createUser("jim");
        }
        player = new Player("jim");
        assertEquals("jim",player.getUsername());
    }


    @Test
    public void setHighestScore() {
        Login login = new Login();
        Player player;
        if(!login.checkPlayerExists("test")) {
            login.createUser("test");
        }
        player = new Player("test");
        player.setHighestScore(1000);
        int highestScore =0;

        File fileForHighScore = new File("highscore.txt");
        Scanner scanner = null;
        ArrayList<String> linesForHighScore = new ArrayList<>();
        try {
            scanner = new Scanner(fileForHighScore);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            linesForHighScore.add(scanner.nextLine());
        }
        for (String line : linesForHighScore) {
            String[] data = line.split(",");
            if (data[0].equals("test")) {
                highestScore = Integer.parseInt(data[1]);
                break;
            }
        }
        assertEquals(1000,highestScore);


    }

    @Test
    public void checkHighScore() {
        Login login = new Login();
        Player player;
        int currentPoints=1000;
        if(!login.checkPlayerExists("test")) {
            login.createUser("test");
        }
        player = new Player("test");
        boolean highScoreMade = player.checkHighScore(currentPoints);
        if(currentPoints>player.getHighestScore()){
            currentPoints=1;
        }else{
            currentPoints=0;
        }
        if(currentPoints==1){
            assertTrue(highScoreMade);
        }else{
            assertTrue(!highScoreMade);
        }


    }

    @Test
    public void setWinsInFile() {
        Login login = new Login();
        StatisticsGUI stats = new StatisticsGUI();
        Player player1;
        Player player2;
        if(!login.checkPlayerExists("test")) {
            login.createUser("test");
        }
        if(!login.checkPlayerExists("test2")){
            login.createUser("test2");
        }
        player1 = new Player("test");
        player2 = new Player("test2");
        int won = 1;
        player1.setWinsInFile(player1,player2,won);
        ArrayList<ArrayList<String>> lines = new ArrayList<>();
        File file = new File("./wins.txt");
        Scanner scanner = null;
        try{
            scanner=new Scanner(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(!line.isBlank()){
                ArrayList<String> tempA = new ArrayList<>();
                if(line.split(",")[0].equals(player1.getUsername())||line.split(",")[1].equals(player1.getUsername())){
                    tempA.add(line.split(",")[1]);
                    tempA.add(line.split(",")[2]);
                    tempA.add(line.split(",")[3]);
                    lines.add(tempA);
                }



            }
        }
        for(int i =0;i<stats.getWins(player1).size();i++){
            for(int y =0 ; y<stats.getWins(player1).get(i).size();y++){
                assertEquals(stats.getWins(player1).get(i).get(y),lines.get(i).get(y));
            }
        }


    }



}