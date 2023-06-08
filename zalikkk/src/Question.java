
import java.util.List;
import java.util.Scanner;

public class Question {

    private String _question;
    private List<String> _questions;
    private String _correctAnswer;

    public Question(String question, List<String> questions, String correctAnswer) {
        _question = question;
        _questions = questions;
        _correctAnswer = correctAnswer;
    }

    public Question() {

    }

    public boolean askQuestion() {
        System.out.println(_question);
        Scanner scanner = new Scanner(System.in);
        for (Integer i = 0; i < _questions.size(); i++) {
            System.out.println((i + 1) + ") " + _questions.get(i));

        }
        System.out.println("Сhoose the answer(number):");
        String userAnswer = scanner.nextLine();
        if (userAnswer.equals(_correctAnswer)) {
            return true;
        }

        return false;

    }

}
