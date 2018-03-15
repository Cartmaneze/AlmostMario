package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import creatures.creatureWeapon.FlyEgg;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Никита on 23.07.2016.
 */
public class EggBird extends Creatures implements Flyable
{
    private int xStopMin;
    private int xStopMaximum;

    private ArrayList<CreatureWeapon> flyEggs = new ArrayList<CreatureWeapon>();
    private ArrayList<CreatureWeapon> CWMustBeDestroyed = new ArrayList<CreatureWeapon>();

    @Override
    public ArrayList<CreatureWeapon> getWeapon()
    {
        return flyEggs;
    }

    public EggBird(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 10;
        height = 10;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/bird1_Up_left.png"));
        this.img = this.ico.getImage();

        xStopMin = x - 200;
        xStopMaximum = x + 200;

        steps = 10;

        tournLeft = true;
        tournRight = false;
        isAlive = true;

        isFighter = false;
        hasWeapon = true;
    }

    public void update(int dx, int dy)
    {
        this.x -= dx;
        this.y -= dy;
        this.xStopMin -= dx;
        this.xStopMaximum -= dx;

        if (!flyEggs.isEmpty())
        {
            for (CreatureWeapon flyEgg : flyEggs)
            {
                flyEgg.update(dx,dy);
                if(GameMain.scene.contact.checkContactCWbackGround(flyEgg, this))
                {
                    CWMustBeDestroyed.add(flyEgg);
                }
            }
            if(!CWMustBeDestroyed.isEmpty())
            {
                for (CreatureWeapon cW : CWMustBeDestroyed)
                {
                    flyEggs.remove(cW);
                }
                CWMustBeDestroyed.clear();
            }
        }

        if (!GameMain.scene.gameFreeze)
        {
            action();
        }
    }

    public void action()
    {
        fly();

        drawOfMove();

        double randNumber = Math.random();
        int d = (int) (randNumber * 100);
        if (d == 50)
        {
            throwEggs();
            for (Iterator<CreatureWeapon> flyEggIterator = flyEggs.iterator(); flyEggIterator.hasNext(); ) {
                CreatureWeapon flyEgg = flyEggIterator.next();
                if (flyEgg.getY() > 630)
                {
                    flyEggIterator.remove();
                }
            }
        }
    }

    @Override
    public void fly()
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

    public void throwEggs()
    {
        int xPlus = 0;
        int yPlus = 0;
        if (tournRight)
        {
            xPlus = 5;
            yPlus = 70;
        }
        if (tournLeft)
        {
            xPlus = 60;
            yPlus = 70;
        }
        FlyEgg flyEgg = new FlyEgg(this.x + xPlus, this.y + yPlus);
        flyEggs.add(flyEgg);
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(this.img, this.x, this.y, null);

        for (CreatureWeapon flyEgg : flyEggs)
        {
            flyEgg.draw(g);
        }
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
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/bird1_Up_right.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/bird1_Down_right.png"));
            }
        } else if (tournLeft)
        {
            if (steps > numSteps)
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/bird1_Up_left.png"));
            } else
            {
                imageIcon = new ImageIcon(getClass().getResource("/creatures/imageCre/bird1_Down_left.png"));
            }
        }
        this.img = imageIcon.getImage();
    }

}
