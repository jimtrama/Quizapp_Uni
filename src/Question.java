import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * this class represents a question
 * it has all the information that a question object needs
 */
public class Question {
    private ArrayList<String> answers;
    private String question;
    private String correctAnswer;
    private String image;

    public Question(){

    }

    public void setImage(String image) {
        if(image.length()!=0)
            this.image = image;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    /**
     * this setter is a normal setter for the 4 possible answers,
     * it takes a List with the 4 answers and
     * it places the answers  in a random order every time in the variable of the class "answers"
     * @param answers
     */
    public void setAnswers(List<String> answers) {

        ArrayList<String> randomAnswers = new ArrayList<>();
        Random rnd = new Random();
        while(answers.size()>0){
            if(answers.size()==1){
                randomAnswers.add(answers.get(0));
                answers.remove(0);
            }else{
                int index = rnd.nextInt(answers.size());
                randomAnswers.add(answers.get(index));
                answers.remove(index);
            }

        }
        this.answers = randomAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        if(!question.isBlank())
            this.question = question;
        else
            System.out.println("Question can't be blank");
    }

    public String getCorrectAnswer(){
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        if(!correctAnswer.isBlank())
            this.correctAnswer = correctAnswer;
        else
            System.out.println("Answer can't be blank");
    }
    public Question(String question){
        this.question=question;
        image="null";
    }

    /**
     * This function check if the user's answer is the correct one
     * @param answer user's answer
     * @return bool
     */
    public boolean checkCorrectAnswer(String answer){
        if(answer.equals(correctAnswer))
            return true;
        else
            return false;
    }
    /**
     * This function returns the correct answer's index of the answers List
     * @return index of the correct answer
     */
    public int getCorrectAnswerIndex(){

        int i =1;

        for(String answer : answers){

            if(answer.equals(getCorrectAnswer())){
                return i;
            }else{
                i++;
            }
        }
        return 0;
    }
}
