import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BigWords {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Big Words");

        JLabel textLabel = new JLabel("Hello");
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.BOLD, 90));
        textLabel.setForeground(Color.WHITE);

        frame.add(textLabel);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setVisible(true);
    }
}