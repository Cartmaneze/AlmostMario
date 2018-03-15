package major;

import javax.swing.*;

/**
 * Created by Никита on 13.07.2016.
 */
public class GameMain
{
    public static Scene scene;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 630);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setAlwaysOnTop(false);

        scene = new Scene();
        frame.setContentPane(scene);
        frame.setVisible(true);
    }
}
