import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This Class is to represent a player in the game, with fields for a username,
 * his points and his highscore.
 */

public class Player {
    private String username;
    private int points;
    private int highestScore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getHighestScore() {
        return highestScore;
    }

    /**
     * The setter of the highscore is a normal setter but it writes the highscore
     * in the correct file for the current username
     * @param highestScore
     */
    public void setHighestScore(int highestScore) {
        HashMap<String,String> map =null;
        try {
            File fileRead = new File("highscore.txt");
            Scanner fileScanner = new Scanner(fileRead);
            map = new HashMap<>();
            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                if(!line.isBlank()) {
                    String linesep[] = line.split(",");
                    map.put(linesep[0], linesep[1]);
                }
            }
            map.put(username,highestScore+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("highscore.txt");
            for (String user : map.keySet()) {
                fileWriter.write("\n" + user + "," + map.get(user));
            }
            fileWriter.close();
        }catch (Exception e){
            System.out.println("error Occurred");
        }
        this.highestScore = highestScore;
    }


    /**
     * Default constructor of the Player Class
     * sets the points of the player at zero
     */
    public Player() {
        setPoints(0);
    }

    /**
     * This constructor loads a player from the Database(text files) based on the username.
     * The highscore
     * sets the points of the player at zero
     * @param username
     */
    public Player(String username) {

            //FOR HIGH-SCORE
            File fileForHighScore = new File("highscore.txt");
            Scanner scannerForHighScore = null;
            ArrayList<String> linesForHighScore = new ArrayList<>();
            try {
                scannerForHighScore = new Scanner(fileForHighScore);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (scannerForHighScore.hasNextLine()) {
                linesForHighScore.add(scannerForHighScore.nextLine());
            }
            for (String line : linesForHighScore) {
                String[] data = line.split(",");
                if (data[0].equals(username)) {
                    this.username = username;
                    this.highestScore = Integer.parseInt(data[1]);
                    break;
                }
            }

        //FOR POINTS
        setPoints(0);



    }

    /**
     * This function returns if the current score of the player is better from his highscore
     * @param points
     * @return bool
     */
    public boolean checkHighScore(int points){
        if (points > highestScore){
            setHighestScore(points);
            return true;
        }
        return false;
    }

    /**
     * This Function write in file wins.txt the wins of each player
     * For example , you pass the player1,player2 and who won so 1 fro player1 and 2 for player2
     * Then it read the file wins.txt and it finds a line with the two players usernames
     * it writes in the following format
     * player1.username,player2.username,player1.wins,player2.wins
     * it does matter which player is passed first or with which order the are written in the file
     * cause the function takes care of that
     * @param player1
     * @param player2
     * @param won
     */
    public void setWinsInFile(Player player1,Player player2,int won){
        if(won!=1&&won!=2){
            System.out.println("Won must be 1 or 2");
            return;
        }
        ArrayList<String[]> lines =readLinesFromFile();
        boolean flag = false;
        for(String [] line : lines){
            if(line[0].equals(player1.username)&&line[1].equals(player2.username)){
                flag=true;
                if(won==1){
                    line[2]= (Integer.parseInt(line[2])+1)+"";
                }else{
                    line[3]= (Integer.parseInt(line[3])+1)+"";
                }


            }
            if(line[1].equals(player1.username)&&line[0].equals(player2.username)){
                flag=true;
                if(won==1){
                    line[3]= (Integer.parseInt(line[3])+1)+"";
                }else {
                    line[2]= (Integer.parseInt(line[2])+1)+"";
                }
            }

        }
        if(!flag){
                if(won==1){
                    writeLinesInFile(lines,player1.getUsername()+","+player2.getUsername()+","+"1"+","+"0",true);
                }else{
                    writeLinesInFile(lines,player1.getUsername()+","+player2.getUsername()+","+"0"+","+"1",true);
                }
        }else{
            writeLinesInFile(lines,"",false);
        }
    }

    /**
     * This function is a helper fro setWinsInFile it takes the lines
     * and writes it in the file wins.txt
     * if there was no entry of a match for the two users you pass the flag as true
     * and appends the line1 at the end of the lines
     * @param lines
     * @param line1
     * @param flag
     */
    private void writeLinesInFile(ArrayList<String[]> lines,String line1,boolean flag){
        try {
            FileWriter fileWriter = new FileWriter("wins.txt");
            for (String[] line : lines) {
                String strLine = "";
                for (int i = 0; i < line.length; i++) {
                    if (i == line.length - 1)
                        strLine += line[i];
                    else
                        strLine += line[i] + ",";
                }

                fileWriter.write("\n" + strLine);
            }
            if(flag){
                fileWriter.write("\n" + line1);
            }
            fileWriter.close();
        }catch (Exception e){

        }
    }

    /**
     * This function is a helper function fro writeWinsInFile
     * it read the wins.txt line by line and returns an ArrayList<String[]> with all the lines
     * in the file and each line is split in ','
     * @return linesThatWasRead
     */
    public ArrayList<String[]> readLinesFromFile(){
        ArrayList<String[]> linesToReturn = new ArrayList<>();
        File file = new File("./wins.txt");
        Scanner scanner = null;
        try{
            scanner=new Scanner(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        while(scanner.hasNextLine()){
            String temp = scanner.nextLine();
            if(!temp.isBlank())
                linesToReturn.add(temp.split(","));
        }
        return linesToReturn;
    }

}
