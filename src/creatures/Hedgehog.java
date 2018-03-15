package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import creatures.creatureWeapon.Needle;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Никита on 05.09.2016.
 */
public class Hedgehog extends Creatures implements RunCrawl
{
    private ArrayList<CreatureWeapon> needlesList = new ArrayList<CreatureWeapon>();
    private ArrayList<CreatureWeapon> CWMustBeDestroyed = new ArrayList<CreatureWeapon>();

    private long startOfNeedle;
    private long endOfNeedle;

    @Override
    public void drawOfMove()
    {
        // NON
    }

    @Override
    public ArrayList<CreatureWeapon> getWeapon()
    {
        return needlesList;
    }

    public Hedgehog(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 30;
        height = 30;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/hedgehog-revised-th.png"));
        this.img = this.ico.getImage();

        tournLeft = true;
        tournRight = false;
        isAlive = true;

        isFighter = true;
        hasWeapon = true;
    }


    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(this.img, this.x-10, this.y+5, null);

        for (CreatureWeapon needle : needlesList)
        {
            needle.draw(g);
        }
    }

    @Override
    public void update(int dx, int dy)
    {
        this.x -= dx;
        this.y -= dy;

        if (needlesList.isEmpty())
        {
            Needle needle1 = new Needle(x, y+10, "left");
            Needle needle2 = new Needle(x, y+5, "leftUp");
            Needle needle3 = new Needle(x+10, y, "up");
            Needle needle4 = new Needle(x, y+5, "rightUp");
            Needle needle5 = new Needle(x, y+10, "right");
            needlesList.add(needle1);
            needlesList.add(needle2);
            needlesList.add(needle3);
            needlesList.add(needle4);
            needlesList.add(needle5);
            startOfNeedle = new Date().getTime();
        }
        for (CreatureWeapon needle : needlesList)
        {
            needle.update(dx,dy);
            if(GameMain.scene.contact.checkContactCWbackGround(needle, this))
            {
                CWMustBeDestroyed.add(needle);
            }
        }

        if(!GameMain.scene.gameFreeze)
        {
            action();
        }

        if(!CWMustBeDestroyed.isEmpty())
        {
            for (CreatureWeapon cW : CWMustBeDestroyed)
            {
                needlesList.remove(cW);
            }
            CWMustBeDestroyed.clear();
        }
    }

    public void action()
    {
        if (new Date().getTime() > startOfNeedle + 2000)
        {
            for (CreatureWeapon needle : needlesList)
            {
                CWMustBeDestroyed.add(needle);
            }
        }
    }

    @Override
    public void running()
    {
        // NON
    }
}
