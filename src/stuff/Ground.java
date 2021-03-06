package stuff;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 14.07.2016.
 */
public class Ground extends Objects
{
    public Ground(int x, int y, int length, int height)
    {
        this.length = length;
        this.height = height;

        this.x = x;
        this.y = y;

        isBreakable = true;

        this.ico = new ImageIcon(getClass().getResource("/images/bloc.png"));
        this.img = this.ico.getImage();
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
    }
}
