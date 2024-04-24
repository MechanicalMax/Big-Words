import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BigWords extends JPanel {
    private JFrame frame;
    private JLabel textLabel;
    private Font font;
    private FontMetrics fontMetrics;
    private int textHeight;
    private int textWidth;
    private final static Font DEFAULT_FONT = new Font("SansSerif", Font.BOLD, 500);
    private final static int ITERATION_LIMIT = 200;
    private final static int FONT_THRESHOLD = 30;

    private final static int DEFAULT_MODE = 0;
    private final static int ONE_WORD_MODE = 1;
    private static boolean lastInputWasSpace = false;
    private int inputMode = DEFAULT_MODE;

    public void mainGUI() {
        // Create JFrame
        frame = new JFrame("Big Words");
        font = DEFAULT_FONT;

        // Create Label
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(font);
        textLabel.setForeground(Color.WHITE);

        frame.add(textLabel);

        // Maximize Window and Remove Cursor
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
            new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB),
            new Point(0, 0), "blank cursor"));

        // Display Frame to User
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        frame.setVisible(true);

        setScaledText("");
        
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && e.isShiftDown()) {
                    inputMode = inputMode == DEFAULT_MODE ? ONE_WORD_MODE : DEFAULT_MODE;
                }

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setScaledText("");
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    setScaledText(
                        textLabel.getText().substring(
                            0, textLabel.getText().length() - 1
                        )
                    );
                } else if (font.canDisplay(e.getKeyChar())) {
                    if (inputMode == ONE_WORD_MODE) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            lastInputWasSpace = true;
                            return;
                        }

                        if (lastInputWasSpace) {
                            textLabel.setText("");
                        }
                    }

                    if (e.getKeyChar() != 'W') {
                        setScaledText(textLabel.getText()
                            + String.valueOf(e.getKeyChar())
                        );
                    } else {
                        // Capital "W" causes other letters to be placed
                        // on top of it when typing with large font,
                        // So just remove "W" from being typed
                        // replace with "w"
                        setScaledText(textLabel.getText()
                            + "w"
                        );
                    }

                    lastInputWasSpace = false;
                }
            }
        });
    }
    
    private void setScaledText(String newText) {
        if (newText.isBlank()) {
            font = DEFAULT_FONT;
            textLabel.setFont(font);
            textLabel.setText(newText);
            return;
        }
        
        fontMetrics = getFontMetrics(font);
        textHeight = fontMetrics.getHeight();
        textWidth = fontMetrics.stringWidth(newText);
        
        float scale = 1f;
        int iteration = 0;
        while (frame.getSize().height - textHeight > FONT_THRESHOLD
            && (frame.getSize().width - textWidth < 0 
            || frame.getSize().width - textWidth > FONT_THRESHOLD)
            && iteration++ < ITERATION_LIMIT
        ) {
            scale = (textWidth - frame.getSize().width) < 0 ? 1 : -1;
            font = font.deriveFont(font.getSize() + scale);
            
            fontMetrics = getFontMetrics(font);
            textHeight = fontMetrics.getHeight();
            textWidth = fontMetrics.stringWidth(newText);
        }

        font = font.deriveFont(font.getSize() - 2f);
        textLabel.setFont(font);
        textLabel.setText(newText);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BigWords bigWords = new BigWords();
                bigWords.mainGUI();
            }
        });
    }
}