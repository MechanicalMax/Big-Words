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
    private final static Font DEFAULT_FONT = new Font("SansSerif", Font.BOLD, 90);
    private final static int ITERATION_LIMIT = 100;
    private final static int FONT_THRESHOLD = 30;

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
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setScaledText("");
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    setScaledText(
                        textLabel.getText().substring(
                            0, textLabel.getText().length() - 1
                        )
                    );
                } else if (font.canDisplay(e.getKeyChar())) {
                    setScaledText(textLabel.getText()
                        + String.valueOf(e.getKeyChar())
                    );
                }
            }
        });
    }
    
    private void setScaledText(String newText) {
        fontMetrics = getFontMetrics(font);
        textHeight = fontMetrics.getHeight();
        textWidth = fontMetrics.stringWidth(newText);
        
        float scale = 1f;

        if (textWidth == 0 || textHeight == 0) {
            textLabel.setFont(DEFAULT_FONT);
            textLabel.setText(newText);
            return;
        }

        if (textHeight < textWidth) {
            int iteration = 0;
            while ((frame.getSize().width - textWidth < 0 
                || frame.getSize().width - textWidth > FONT_THRESHOLD)
                && iteration++ < ITERATION_LIMIT
            ) {
                scale = (textWidth - frame.getSize().width) < 0 ? 1 : -1;
                font = font.deriveFont(font.getSize() + scale);
                textLabel.setFont(font);
                
                fontMetrics = getFontMetrics(font);
                textHeight = fontMetrics.getHeight();
                textWidth = fontMetrics.stringWidth(newText);
            }
            font = font.deriveFont(font.getSize() - 1f);
            textLabel.setFont(font);
            textLabel.setText(newText);
        } else {
            int iteration = 0;
            while ((frame.getSize().height - textHeight < 0
                || frame.getSize().height - textHeight > FONT_THRESHOLD)
                && iteration++ < ITERATION_LIMIT
            ) {
                scale = (textHeight - frame.getSize().height) < 0 ? 1 : -1;
                font = font.deriveFont(font.getSize() + scale);
                textLabel.setFont(font);
                
                fontMetrics = getFontMetrics(font);
                textHeight = fontMetrics.getHeight();
                textWidth = fontMetrics.stringWidth(newText);
            }
            font = font.deriveFont(font.getSize() - 1f);
            textLabel.setFont(font);
            textLabel.setText(newText);
        }
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