package Calculator.Gui;
import javax.swing.*;
import java.awt.*;


public class MainWindow {

    public MainWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setSize(400, 300);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
