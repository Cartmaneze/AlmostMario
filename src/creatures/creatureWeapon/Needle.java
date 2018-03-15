package creatures.creatureWeapon;

import major.GameMain;
import stuff.Objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 05.09.2016.
 */
public class Needle extends CreatureWeapon
{
    public String getWay()
    {
        return way;
    }

    private String way;

    public Needle(int x, int y, String way)
    {
        this.x = x;
        this.y = y;

        this.way = way;

        length = 10;
        height = 10;

        if (way.equals("left"))
        {
            icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/nedle_left.png"));
        } else if (way.equals("leftUp"))
        {
            icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/nedle_Up_left.png"));
        } else if(way.equals("up"))
        {
            icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/nedle_up.png"));
        } else if(way.equals("rightUp"))
        {
            icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/nedle_Up_right.png"));
        } else if(way.equals("right"))
        {
            icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/nedle_right.png"));
        }
        this.imgCreWeapon = this.icoCreWeapon.getImage();
    }
    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getLength()
    {
        return length;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public boolean contact(Objects object)
    {
        // check contact presens
        if(this.x + this.length < object.getX() || this.x > object.getX() + object.getLength() || this.y + this.height < object.getY() || this.y > object.getY() + object.getHeight())
        {
            return false;
        } else
        {
            return true;
        }
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.drawImage(this.imgCreWeapon, this.x, this.y, null);
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
        if (way.equals("left"))
        {
            this.x -= 2;
        } else if (way.equals("leftUp"))
        {
            this.x -= 1;
            this.y -= 1;
        } else if(way.equals("up"))
        {
            this.y -= 2;
        } else if(way.equals("rightUp"))
        {
            this.x += 1;
            this.y -= 1;
        } else if(way.equals("right"))
        {
            this.x += 2;
        }
    }
}
