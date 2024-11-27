import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the gui application for the quiz
public class QuizApp {
    private JFrame frame; // main frame for the GUI
    private Quiz quiz; // instance of the Quiz class
    private JTextArea questionTextArea; // text area to display the current question
    private JRadioButton[] optionButtons; // radio buttons for answer options
    private JButton nextButton; // button to move to the next question

    private String userResult; // string to store user's selected answers
	private ImageIcon resultImageIcon;

    // constructor to initialize the QuizApp with a Quiz instance
    public QuizApp(Quiz quiz) {
        this.quiz = quiz;
        this.userResult = "";

        frame = new JFrame("Are you a part of the rebels, dark side, or none?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(103, 128, 159)); // set background color of frame

        // set the font for the entire gui
        Font font = new Font("Century Gothic", Font.BOLD, 14);
        UIManager.put("Button.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("TextArea.font", font);

        questionTextArea = new JTextArea(5, 20);
        questionTextArea.setFont(font);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setLineWrap(true);
        questionTextArea.setOpaque(false);
        questionTextArea.setEditable(false);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1));
        optionsPanel.setBackground(new Color(103, 128, 159));
        optionButtons = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setForeground(new Color(36, 37, 42)); // the radio button color
            optionButtons[i].setFont(font);
            optionsPanel.add(optionButtons[i]);
            buttonGroup.add(optionButtons[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAnswer();
            }
        });
        nextButton.setFont(font);

        frame.add(questionTextArea, BorderLayout.NORTH);
        frame.add(optionsPanel, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);

        updateQuestion();

        frame.setVisible(true);
    }

    // handles the user's answer to the current question
    private void handleAnswer() {
        Question currentQuestion = quiz.getCurrentQuestion();
        String selectedAnswer = getSelectedAnswer();

        if (selectedAnswer != null) {
            userResult += selectedAnswer + "\n";

            if (quiz.hasNextQuestion()) {
                quiz.moveToNextQuestion();
                updateQuestion();
            } else {
                showResult();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select an answer.");
        }
    }

    // gets the selected answer from the radio buttons
    private String getSelectedAnswer() {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                return optionButtons[i].getText();
            }
        }
        return null;
    }

    // displays the result based on the user's choices
    private void showResult() {
        // determine the results based on user choices
        String resultMessage = "";
        ImageIcon resultImageIcon = null;

        if (userResult.contains("Green") && userResult.contains("C-3PO") && userResult.contains("Luke Skywalker") || (userResult.contains("Blue") && userResult.contains("R2-D2") && userResult.contains("Obi-wan")) || (userResult.contains("Blue") && userResult.contains("BB8")) || (userResult.contains("Green") && userResult.contains("BB8")) || (userResult.contains("Green") && userResult.contains("Luke Skywalker")) || (userResult.contains("Blue") && userResult.contains("Obi-wan")) || userResult.contains("Blue") && userResult.contains("C-3PO") ||  userResult.contains("Blue") && userResult.contains("Obi-wan") || userResult.contains("Green") && userResult.contains("Obi-wan")) {
            resultMessage = "You are a Rebel! May the force be with you.";
            resultImageIcon = new ImageIcon("jedi.png");
        } else if (userResult.contains("Red") && userResult.contains("Darth Vader")) {
            resultMessage = "You have embraced the Dark Side!";
            resultImageIcon = new ImageIcon("darkside.png");
        } else {
            resultMessage = "You may be a Mandalorian or have no affiliation.";
            resultImageIcon = new ImageIcon("mando.png");
        }

        Image resultImage = resultImageIcon.getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        resultImageIcon = new ImageIcon(resultImage);

        // Create a JPanel to hold the image and result
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(resultImageIcon), BorderLayout.NORTH);
        panel.add(new JLabel(resultMessage), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(frame, panel, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // updates the ui with the current question and answer options
    private void updateQuestion() {
        Question currentQuestion = quiz.getCurrentQuestion();
        questionTextArea.setText(currentQuestion.getQuestion());

        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setSelected(false);
        }
    }

    // the questions
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        quiz.addQuestion(new Question("What color is your lightsaber?", new String[]{"Green", "Blue", "Red", "Black"}));
        quiz.addQuestion(new Question("Choose a droid:", new String[]{"R2-D2", "C-3PO", "BB8", "None"}));
        quiz.addQuestion(new Question("Who would you team up with?:", new String[]{"Luke Skywalker", "Obi-wan", "Darth Vader", "The Mandolorian"}));

        new QuizApp(quiz);
    }
}