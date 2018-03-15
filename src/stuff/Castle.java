package stuff;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 13.07.2016.
 */
public class Castle implements ObjMeth
{
    private ImageIcon icoCast;
    private Image imgCast;

    public int getxFond()
    {
        return xFond;
    }

    private int xFond;
    private int yFond;

    public Castle()
    {
        this.xFond = 20;
        this.yFond = 340;
        icoCast = new ImageIcon(getClass().getResource("/images/chateau1.png"));
        this.imgCast = this.icoCast.getImage();
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(this.imgCast, this.xFond, this.yFond, null);
    }

    public void update(int dx, int dy)
    {
        this.xFond -= dx;
        this.yFond -= dy;
    }
}
