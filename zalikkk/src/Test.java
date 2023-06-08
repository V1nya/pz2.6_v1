import java.util.List;

public class Test {
    private List<Question> questions;

    public Test(List<Question> questions) {
        this.questions = questions;
    }

    public void showTest() {
        Integer correctAnswers = 0;
        for (Question q : questions) {
            if (q.askQuestion())
                correctAnswers++;
        }
        System.out.println("You answered " + correctAnswers + " out of " + questions.size() + " questions");
    }
}
