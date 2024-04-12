import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BigWords extends JPanel {
    private JFrame frame;
    private JLabel textLabel;

    public void mainGUI() {
        // Create JFrame
        frame = new JFrame("Big Words");

        // Create Label
        textLabel = new JLabel("Hello");
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 90));
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