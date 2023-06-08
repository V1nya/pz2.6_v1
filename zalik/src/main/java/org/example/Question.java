package org.example;

import java.util.List;

public class Question {

    private String _question;
    private List<String> _questions;
    private String _correctAnswer;

    public Question(String question, List<String> questions, String correctAnswer) {
        _question = question;
        _questions = questions;
        _correctAnswer = correctAnswer;
    }

    public boolean askQuestion() {
        boolean response = false;
        System.out.println(_question);
        Scanner scanner = new Scanner(System.in);
        for (Integer i = 0; i < _questions.size(); i++) {
            System.out.println((i+1)+") "+ _questions.get(i));
        }


        return response;

    }

}
