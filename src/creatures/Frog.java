package creatures;

import creatures.creatureWeapon.CreatureWeapon;
import major.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Никита on 06.09.2016.
 */
public class Frog extends Creatures implements RunCrawl
{
    private int enableHighOfJump;
    private boolean isJumping;
    private boolean isOnGround;
    private boolean isFallingDown;
    private int startY;
    private long timeOfJump;

    public Frog(int x, int y)
    {
        this.x = x;
        this.y = y;
        startY = y;

        length = 30;
        height = 20;

        ico = new ImageIcon(getClass().getResource("/creatures/imageCre/frog_down.png"));
        this.img = this.ico.getImage();

        tournLeft = true;
        tournRight = false;
        isAlive = true;

        isFighter = true;
        hasWeapon = false;
        isJumping = false;
        isOnGround = true;
        enableHighOfJump = 250;
        timeOfJump = System.currentTimeMillis();
    }
    @Override
    public void drawOfMove()
    {
        if (isJumping)
        {
            ico = new ImageIcon(getClass().getResource("/creatures/imageCre/frog_up.png"));
            this.img = this.ico.getImage();
        } else if (isOnGround)
        {
            ico = new ImageIcon(getClass().getResource("/creatures/imageCre/frog_down.png"));
            this.img = this.ico.getImage();
        }
    }

    @Override
    public ArrayList<CreatureWeapon> getWeapon()
    {
        return new ArrayList<CreatureWeapon>();
    }

    @Override
    public void draw(Graphics2D g)
    {
        drawOfMove();
        g.drawImage(this.img, this.x, this.y, null);
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
        if (isOnGround && System.currentTimeMillis() > timeOfJump + 3000)
        {
            isJumping = true;
            isOnGround = false;
            isFallingDown = false;
        }

        if (isJumping)
        {
            running();
        }

        if (isFallingDown)
        {
            y += 2;
            String s = String.valueOf(y);
            int count = s.length();
            String ss = s.substring(count - 1);
            if (ss.equals("8"))
            {
                y += 2;
            } else if (ss.equals("9"))
            {
                y += 1;
            }

            if (y >= startY)
            {
                timeOfJump = System.currentTimeMillis();
                isFallingDown = false;
                isJumping = false;
                isOnGround = true;
                y = startY;
                enableHighOfJump = 250;
            }
        }
    }

    @Override
    public void running()
    {
        if (enableHighOfJump >= 150)
        {
            y -= 30;
            enableHighOfJump -= 30;
        } else if (enableHighOfJump >= 80)
        {
            y -= 20;
            enableHighOfJump -= 20;
        } else if (enableHighOfJump >= 20)
        {
            y -= 10;
            enableHighOfJump -= 10;
        } else
        {
            isFallingDown = true;
            isOnGround = false;
            isJumping = false;
        }
    }
}
