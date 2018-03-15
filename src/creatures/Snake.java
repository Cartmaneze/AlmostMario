package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Никита on 26.07.2016.
 */
public class Snake extends Creatures implements RunCrawl
{
    private int xStopMin;
    private int xStopMaximum;

    @Override
    public ArrayList<CreatureWeapon> getWeapon()
    {
        return new ArrayList<CreatureWeapon>();
    }

    public Snake(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 30;
        height = 30;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Up_Left.png"));
        this.img = this.ico.getImage();

        xStopMin = x - 200;
        xStopMaximum = x + 200;

        steps = 10;

        tournLeft = true;
        tournRight = false;
        isAlive = true;

        isFighter = true;
        hasWeapon = false;
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
        this.xStopMin -= dx;
        this.xStopMaximum -= dx;
        if (!GameMain.scene.gameFreeze)
        {
            action();
        }
    }

    public void action()
    {
        drawOfMove();
        running();
    }

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
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Up_Right.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Down_Right.png"));
            }
        } else if (tournLeft)
        {
            if (steps > numSteps)
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Up_Left.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Down_Left.png"));
            }
        }
        this.img = imageIcon.getImage();
    }

    @Override
    public void running()
    {
        if (tournRight)
        {
            if (x < xStopMaximum)
            {
                this.x++;
            } else
            {
                tournLeft = true;
                tournRight = false;
            }

        }

        if (tournLeft)
        {
            if (x > xStopMin)
            {
                this.x--;
            } else
            {
                tournLeft = false;
                tournRight = true;
            }
        }
    }
}
