import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class QuestionTest {


    @Test
    public void setAnswers(){
        Question question = new Question("what is your name?");
        ArrayList<String> answers = new ArrayList<>(Arrays.asList("jim","tram","iordanis","paok"));
        ArrayList<String> answersToTest = new ArrayList<>(answers);
        question.setAnswers(answers);
        int i =0;
        int j = 0;
        for(;j<10;j++) {
            for (int y = 0; y < question.getAnswers().size(); y++) {
                if (question.getAnswers().get(y).equals(answersToTest.get(y))) {
                    i++;
                }
            }
            if(i==4){
                j++;
            }
            answers.addAll(answersToTest);
            question.setAnswers(answers);
        }
        assertNotEquals(9,j);
    }

    @Test
    public void checkCorrectAnswer(){
        Question question = new Question("what is your name?");
        question.setCorrectAnswer("jim");
        ArrayList<String> answers = new ArrayList<>(Arrays.asList("jim","tram","iordanis","paok"));
        question.setAnswers(answers);
        assertTrue(question.checkCorrectAnswer("jim"));
    }

    @Test
    public void getCorrectAnswerIndex() {
        Question question = new Question("what is your name?");
        question.setCorrectAnswer("jim");
        ArrayList<String> answers = new ArrayList<>(Arrays.asList("jim","tram","iordanis","paok"));
        question.setAnswers(answers);
        int i =1;
        for(String answer : question.getAnswers()){
            if(answer.equals(question.getCorrectAnswer())){
                break;
            }
            i++;
        }
        assertEquals(i,question.getCorrectAnswerIndex());
    }
}