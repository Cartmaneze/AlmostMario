package major;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Никита on 29.09.2016.
 */
public class ShadowPlayer
{
    private ImageIcon icoPers;
    private Image imgPers;

    public int getX()
    {
        return x;
    }

    private int x;

    public int getY()
    {
        return y;
    }

    private int y;

    public void setChangeColor(boolean changeColor)
    {
        this.changeColor = changeColor;
    }

    private boolean changeColor;

    private int centerOfPlayerX;
    private int centerOfPlayerY;

    private int moveZoneRadius;
    private int grayZoneRadius;

    private Ellipse2D moveZone;
    private Ellipse2D grayZone;
    private Ellipse2D blackZone;

    public ShadowPlayer(int x, int y, int shadowDiametr, int heightOfPlayer, int lenghtOfPlayer)
    {
        icoPers = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
        this.imgPers = this.icoPers.getImage();
        moveZone = new Ellipse2D.Float(x - moveZoneRadius/2 + centerOfPlayerX ,y-moveZoneRadius/2 + centerOfPlayerY ,moveZoneRadius, moveZoneRadius);
        grayZone = new Ellipse2D.Float(x - grayZoneRadius/2 + centerOfPlayerX ,y-grayZoneRadius/2 + centerOfPlayerY ,grayZoneRadius, grayZoneRadius);
        blackZone = new Ellipse2D.Float(x - 2000,y - 2000, 4000, 4000);
        this.x = x;
        this.y = y;
        moveZoneRadius = shadowDiametr;
        grayZoneRadius = shadowDiametr + 200;
        centerOfPlayerX = lenghtOfPlayer / 2;
        centerOfPlayerY = heightOfPlayer / 2;
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.black);
        g.fill(blackZone);
        g.draw(blackZone);
        if (changeColor)
        {
            g.setColor(Color.red);
            g.fill(grayZone);
            g.draw(grayZone);
        } else
        {
            g.setColor(Color.green);
            g.fill(grayZone);
            g.draw(grayZone);
        }
        g.setColor(Color.lightGray);
        g.fill(moveZone);
        g.draw(moveZone);
        g.drawImage(imgPers, x-5, y, null);
    }

    public void update(int dx, int dy)
    {
        this.x -= dx;
        this.y -= dy;
        moveZone.setFrame(x - moveZoneRadius/2 + centerOfPlayerX ,y-moveZoneRadius/2 + centerOfPlayerY ,moveZoneRadius, moveZoneRadius);
        grayZone.setFrame(x - grayZoneRadius/2 + centerOfPlayerX ,y-grayZoneRadius/2 + centerOfPlayerY ,grayZoneRadius, grayZoneRadius);
        blackZone.setFrame(x - 2000,y - 2000, 4000, 4000);
    }
}
