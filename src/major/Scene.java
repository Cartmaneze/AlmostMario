package major;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 13.07.2016.
 */
public class Scene extends JPanel
{
    public static Player player;
    public static GameBack gameBack;
    public static GameCordinate gameCordinate;
    public Controller controller;
    public static Contact contact;
    public boolean gameOver;
    public boolean gameWin;
    public boolean gameFreeze;
    public boolean quikeSilverMode;
    private byte level;
    public Chrono chrono;

    public Scene()
    {
        super();
        level = 1;

        chrono = new Chrono();
        Thread chronoEcran = new Thread(chrono);


        newLevelNewBegin();

        this.setFocusable(true);
        this.requestFocusInWindow();


        chronoEcran.start();
    }

    public void newLevelNewBegin()
    {
        this.removeKeyListener(controller);
        gameOver = false;
        gameFreeze = false;
        quikeSilverMode = false;
        chrono.setPause(chrono.getStartPause());

        gameBack = new GameBack();
        player = new Player();
        gameCordinate = new GameCordinate();
        controller = new Controller();
        contact = new Contact(controller, gameCordinate, player, gameBack);

        this.addKeyListener(controller);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        this.update();

        gameBack.draw(g2);
        player.draw(g2);
    }

    public void update()
    {
        if (!gameOver && !gameWin)
        {
            gameCordinate.setAbsDx(gameCordinate.getDx());
            gameBack.update(gameCordinate.getDx(), gameCordinate.getDy());
            player.update();
        }
    }

    public void noWayLeftInBegin()
    {
        if(gameCordinate.getAbsDx() > player.getX())
        {
            gameBack.update(gameCordinate.getDx(), gameCordinate.getDy());
            player.update();
        }
    }
}
