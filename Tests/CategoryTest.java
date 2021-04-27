import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CategoryTest {

    @Test
    public void getQuestions() {
        Category category = new Category("films");
        boolean expectedValue = false;
        if(category.getQuestions().size()!=0){
            expectedValue=true;
        }
        assertTrue(expectedValue);
    }

    @Test
    public void getType() {
        Category category = new Category("films");
        boolean expectedValue = false;
        if(category.getType().equals("films")){
            expectedValue=true;
        }
        assertTrue(expectedValue);
    }

    @Test
    public void getRandomOrderQuestions() {
        Category category = new Category("films");
        ArrayList<Question> list1 ;
        ArrayList<Question> list2 ;
        list1 = category.getRandomOrderQuestions(10);
        list2 = category.getRandomOrderQuestions(10);
        boolean expectedValue = false;
        if(!list1.equals(list2)){
            expectedValue=true;
        }
        assertTrue(expectedValue);
    }


}