// represents a quiz question with a question prompt and a set of options.

public class Question {
    private String question;     // stores the question prompt
    private String[] options;    // stores the options for the question

    /**
     * constructs a Question object with the specified question and options.
     *
     * @param question the question prompt.
     * @param options  an array of options for the question.
     */
    public Question(String question, String[] options) {
        this.question = question;
        this.options = options;
    }
    /**
     * gets the question prompt.
     *
     * @return the question prompt.
     */
    public String getQuestion() {
        return question;
    }
    /**
     * gets the array of options for the question.
     *
     * @return an array of options.
     */
    public String[] getOptions() {
        return options;
    }
}
