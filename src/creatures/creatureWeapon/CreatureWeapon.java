package creatures.creatureWeapon;

import stuff.ObjMeth;
import stuff.Objects;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 23.07.2016.
 */
public abstract class CreatureWeapon implements ObjMeth
{
    public int length;
    public int height;

    public ImageIcon icoCreWeapon;
    public Image imgCreWeapon;

    public int x;
    public int y;

    public abstract int getY();
    public abstract int getX();

    public abstract int getLength();
    public abstract int getHeight();

    public abstract boolean contact(Objects o);
}
