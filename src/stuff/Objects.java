package stuff;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 14.07.2016.
 */
public abstract class Objects implements ObjMeth
{
    int length;
    int height;

    ImageIcon ico;
    Image img;

    int x;
    int y;

    boolean isBreakable;

    public int getLength() {return length;}

    public int getHeight() {return height;}

    public int getY()
    {
        return y;
    }

    public int getX()
    {
        return x;
    }
}
