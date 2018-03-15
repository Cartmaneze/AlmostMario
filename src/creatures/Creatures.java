package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import stuff.ObjMeth;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Никита on 23.07.2016.
 */
public abstract class Creatures implements ObjMeth
{
    public int length;
    public int height;

    public ImageIcon ico;
    public Image img;

    public int steps;

    public int x;
    public int y;

    public boolean isFighter;
    public boolean hasWeapon;
    public boolean tournLeft;
    public boolean tournRight;
    public boolean isAlive;

    public abstract void drawOfMove();

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getLength()
    {
        return length;
    }

    public int getHeight()
    {
        return height;
    }

    public abstract ArrayList<CreatureWeapon> getWeapon();
}
