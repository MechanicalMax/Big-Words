import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class BigWords {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Big Words");

        JTextPane textPane = new JTextPane();

        frame.add(textPane);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setBackground(Color.WHITE);

        frame.setVisible(true);
    }
}