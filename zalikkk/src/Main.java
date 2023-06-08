import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Question> questions = new ArrayList<>();

        questions.add(new Question("Question 1?",
                new ArrayList<>(Arrays.asList("Answer 1", "Answer 2", "Answer 3")),"2"));
        questions.add(new Question("Question 2?",
                new ArrayList<>(Arrays.asList("Answer 1", "Answer 2", "Answer 3")),"2"));
        questions.add(new Question("Question 3?",
                new ArrayList<>(Arrays.asList("Answer 1", "Answer 2", "Answer 3","Answer 4")),"4"));
        questions.add(new Question("Question 4?",
                new ArrayList<>(Arrays.asList("Answer 1", "Answer 2", "Answer 3")),"1"));
        questions.add(new Question("Question 5?",
                new ArrayList<>(Arrays.asList("Answer 1", "Answer 2", "Answer 3")),"3"));

        Test test = new Test(questions);


        test.showTest();
    }
}