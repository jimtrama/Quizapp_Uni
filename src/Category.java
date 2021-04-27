import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This Class represent a Category
 * it has all the Questions of the current Category type
 * it is needed to have a database in memory for the Questions that are played
 */
public class Category {
    private List<Question> questions;
    private String type;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Constructor of the Category Class
     * @param type the type of the category
     */
    public Category(String type){
        this.type=type;
        questions = new ArrayList<>();
        loadQuestionsFromFile();
    }

    /**
     * Function that Loads the questions from the text file
     * by reading line by line the questions.txt splits in '/'
     * and adds the info in a Question object and in the end adds that question object
     * in a List<Question> questions
     */
    private void loadQuestionsFromFile()  {

        List<String> lines=new ArrayList<>();
        File file = new File("questions.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()){
            lines.add(scanner.nextLine());
        }
        //int i =0;
        for (String line:lines) {
            //System.out.println(i);
            //i++;
            if(!line.isBlank()) {
                String[] data = line.split("/");
                Question question = null;
                if (data[0].equals(type)) {
                    question = new Question(data[1]);
                    List<String> answers = new ArrayList<>(Arrays.asList(data).subList(2, 6));
                    question.setAnswers(answers);
                    question.setCorrectAnswer(data[6]);
                    //for img
                    //expect index out of range error
                    if(data[7]!=null){
                        question.setImage(data[7]);
                    }

                }
                if (question != null){
                    questions.add(question);
                }else {

                }
            }

        }

    }
    /**
     * A Function tha return the num of questions that the round needs in a random selection
     * @param numOfQuestions
     * @return questionsForRound
     */
    public ArrayList<Question> getRandomOrderQuestions(int numOfQuestions){
        if(numOfQuestions>questions.size()){
            System.out.println("There are not "+numOfQuestions+" questions in this category");
            return new ArrayList<>();
        }
        ArrayList<Question> questionsToReturn = new ArrayList<>();
        Random random = new Random();
        while (questions.size()>0&&questionsToReturn.size()<numOfQuestions){
            int index = random.nextInt(questions.size());
            questionsToReturn.add(questions.get(index));
            questions.remove(index);
        }

        return questionsToReturn;
    }

}

