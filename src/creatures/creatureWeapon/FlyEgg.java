package creatures.creatureWeapon;

import major.GameMain;
import stuff.Objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 23.07.2016.
 */
public class FlyEgg extends CreatureWeapon
{
    public int getY()
    {
        return y;
    }

    @Override
    public int getX() {return x;}

    @Override
    public int getLength() {return length;}

    @Override
    public int getHeight() {return height;}

    public FlyEgg(int x, int y)
    {
        this.x = x;
        this.y = y;

        length = 15;
        height = 20;

        icoCreWeapon = new ImageIcon(getClass().getResource("/creatures/imageCre/egg.png"));
        this.imgCreWeapon = this.icoCreWeapon.getImage();
    }

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
        this.y += 3;
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(this.imgCreWeapon, this.x, this.y, null);
    }

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
}
