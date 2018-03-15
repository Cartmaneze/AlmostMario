package major;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Никита on 05.10.2016.
 */
public class PlayersViewScreen
{
    private ImageIcon icoHeart0;
    private ImageIcon icoHeart1;
    private ImageIcon icoHeart2;
    private ImageIcon icoHeart3;
    private Image imgHeart;

    private ImageIcon icoTeleport0;
    private ImageIcon icoTeleport1;
    private ImageIcon icoTeleport2;
    private ImageIcon icoTeleport3;
    private ImageIcon icoTeleport4;
    private Image imgTeleport;

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    private int hp;

    public void setShadowDiametr(int shadowDiametr)
    {
        this.shadowDiametr = shadowDiametr;
    }

    private int shadowDiametr;

    private int xHeart;
    private int yHeart;

    private int xTeleport;
    private int yTeleport;

    public PlayersViewScreen()
    {
        icoHeart0 = new ImageIcon(getClass().getResource("/images/heart-0.png"));
        icoHeart1 = new ImageIcon(getClass().getResource("/images/heart-1.png"));
        icoHeart2 = new ImageIcon(getClass().getResource("/images/heart-2.png"));
        icoHeart3 = new ImageIcon(getClass().getResource("/images/heart-3.png"));
        this.imgHeart = this.icoHeart1.getImage();

        icoTeleport0 = new ImageIcon(getClass().getResource("/images/teleport0.png"));
        icoTeleport1 = new ImageIcon(getClass().getResource("/images/teleport1.png"));
        icoTeleport2 = new ImageIcon(getClass().getResource("/images/teleport2.png"));
        icoTeleport3 = new ImageIcon(getClass().getResource("/images/teleport3.png"));
        icoTeleport4 = new ImageIcon(getClass().getResource("/images/teleport4.png"));
        this.imgTeleport = this.icoTeleport4.getImage();

        xHeart = 20;
        yHeart = 15;

        xTeleport = 20;
        yTeleport = 60;
    }

    public void draw(Graphics2D g)
    {
        if (hp == 0)
        {
            imgHeart = this.icoHeart0.getImage();
        } else if (hp == 1)
        {
            imgHeart = this.icoHeart1.getImage();
        } else if (hp == 2)
        {
            imgHeart = this.icoHeart2.getImage();
        } else if (hp == 3)
        {
            imgHeart = this.icoHeart3.getImage();
        }

        if (shadowDiametr < 50)
        {
            imgTeleport = this.icoTeleport0.getImage();
        } else if (shadowDiametr >= 50 && shadowDiametr < 170)
        {
            imgTeleport = this.icoTeleport1.getImage();
        } else if (shadowDiametr >= 170 && shadowDiametr < 310)
        {
            imgTeleport = this.icoTeleport2.getImage();
        } else if (shadowDiametr >= 310 && shadowDiametr < 500)
        {
            imgTeleport = this.icoTeleport3.getImage();
        } else if (shadowDiametr >= 500)
        {
            imgTeleport = this.icoTeleport4.getImage();
        }


        g.drawImage(imgHeart, xHeart, yHeart, null);
        g.drawImage(imgTeleport, xTeleport, yTeleport, null);
    }
}
