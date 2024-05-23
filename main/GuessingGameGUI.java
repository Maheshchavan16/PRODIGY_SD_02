import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGameGUI extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JTextArea feedbackArea;
    private GuessingGame game;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        startNewGame();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel promptLabel = new JLabel("Enter your number:");
        promptLabel.setBounds(30, 30, 150, 25);
        panel.add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(180, 30, 100, 25);
        panel.add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(130, 70, 100, 25);
        panel.add(guessButton);

        feedbackArea = new JTextArea();
        feedbackArea.setBounds(30, 110, 340, 20);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setEditable(false);
        panel.add(feedbackArea);

        guessButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });

        add(panel);
    }

    private void startNewGame() {
        game = new GuessingGame(99);
        feedbackArea.setText("I'm thinking of a number between 1 and 99. Try to guess it!");
        guessField.setText("");
        guessField.requestFocus();
    }

    private void makeGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            String feedback = game.makeGuess(guess);
            feedbackArea.setText(feedback);

            if (game.isGameOver()) {
                int response = JOptionPane.showConfirmDialog(this, feedback + " Would you like to play again?",
                        "Game Over", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    startNewGame();
                } else {
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            guessField.setText("");
            guessField.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GuessingGameGUI gui = new GuessingGameGUI();
            gui.setVisible(true);
        });
    }
}
