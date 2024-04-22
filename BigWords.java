import java.awt.Color;
import java.awt.Font;
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

    public void mainGUI() {
        // Create JFrame
        frame = new JFrame("Big Words");
        font = new Font("SansSerif", Font.BOLD, 90);

        // Create Label
        textLabel = new JLabel("");
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
        
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textLabel.setText("");
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    textLabel.setText(
                        textLabel.getText().substring(
                            0, textLabel.getText().length() - 1
                        )
                    );
                } else if (font.canDisplay(e.getKeyChar())) {
                    textLabel.setText(textLabel.getText()
                        + String.valueOf(e.getKeyChar()));
                }
            }
        });
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