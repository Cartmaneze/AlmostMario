package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Никита on 08.09.2016.
 */
public class Lynx extends Creatures implements RunCrawl
{
    public Lynx(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 10;
        height = 10;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/snake_Up_Left.png"));
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

        if(!GameMain.scene.gameFreeze)
        {
            action();
        }
    }

    public void action()
    {
        drawOfMove();
        if (x < GameMain.scene.player.getX() + 500)
        {
            running();
        }
    }

    @Override
    public void running()
    {
        x -= 1;
    }
}
