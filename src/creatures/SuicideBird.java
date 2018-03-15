package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Никита on 23.07.2016.
 */
public class SuicideBird extends Creatures implements Flyable
{
    public SuicideBird(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 30;
        height = 25;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/Duck_Down.png"));
        this.img = this.ico.getImage();

        steps = 10;

        tournLeft = true;
        tournRight = false;
        isAlive = true;

        isFighter = true;
        hasWeapon = false;
    }
    @Override
    public void drawOfMove()
    {
        int numSteps = 10;
        ImageIcon imageIcon = ico;
        steps++;
        if (steps > 20)
        {
            steps = 0;
        }
        if (tournRight)
        {
            if (steps > numSteps)
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/Duck_Up.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/Duck_Down.png"));
            }
        } else if (tournLeft)
        {
            if (steps > numSteps)
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/Duck_Up.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/Duck_Down.png"));
            }
        }
        this.img = imageIcon.getImage();
    }

    @Override
    public ArrayList<CreatureWeapon> getWeapon()
    {
        return new ArrayList<CreatureWeapon>();
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(this.img, this.x, this.y, null);
    }

    @Override
    public void update(int dx, int dy)
    {
        this.x -= dx;
        this.y -= dy;
        if (!GameMain.scene.gameFreeze)
        {
            action();
        }
    }

    public void action()
    {
        drawOfMove();
        if (x < GameMain.scene.player.getX() + 400)
        {
            fly();
        }
    }

    @Override
    public void fly()
    {
        x -= 3;
    }
}
