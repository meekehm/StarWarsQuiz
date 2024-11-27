import java.util.ArrayList;
import java.util.List;

// represents a Quiz with a list of questions
public class Quiz {
    private List<Question> questions; // list to store questions
    private int currentQuestionIndex; // index to keep track of the current question

    // constructor to initialize the Quiz with an empty list of questions
    public Quiz() {
        questions = new ArrayList<>();
        currentQuestionIndex = 0;
    }

    // adds a question to the list of questions in the Quiz
    public void addQuestion(Question question) {
        questions.add(question);
    }

    // gets the current question in the Quiz
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    // checks if there is a next question in the Quiz
    public boolean hasNextQuestion() {
        return currentQuestionIndex < questions.size() - 1;
    }

    // moves to the next question in the Quiz if available
    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentQuestionIndex++;
        }
    }
}
