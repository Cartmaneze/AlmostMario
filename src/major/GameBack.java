package major;

import creatures.*;
import stuff.Castle;
import stuff.Ground;
import stuff.Objects;
import stuff.TeleportationPlus;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Никита on 13.07.2016.
 */
public class GameBack
{
    private ImageIcon icoFond;
    private Image imgFond1;
    private Image imgFond2;

    private ImageIcon icoWasted;
    private Image imgWasted;

    private ImageIcon icoWin;
    private Image imgWin;

    private int fondSize;

    private int xFond1;
    private int xFond2;

    final int xSizeOfGameGrid;
    final int ySizeOfGameGrid;

    public static Castle castle;
    public ShadowPlayer shadowPlayer;

    private ArrayList<String[]> strings = new ArrayList<String[]>();

    public ArrayList<Objects> getBlocks()
    {
        return blocks;
    }

    private ArrayList<Objects> blocks = new ArrayList<Objects>();

    public ArrayList<Objects> getObjects()
    {
        return objects;
    }

    private ArrayList<Objects> objects = new ArrayList<Objects>();

    public ArrayList<Creatures> getCreatures() {return creatures;}

    private ArrayList<Creatures> creatures = new ArrayList<Creatures>();

    public GameBack()
    {
        this.xSizeOfGameGrid = 30;
        this.ySizeOfGameGrid = 30;

        this.fondSize = 1110;
        this.xFond1 = -50;
        this.xFond2 = fondSize - 50;

        icoFond = new ImageIcon(getClass().getResource("/images/16019-game-backgrounds.jpg"));
        this.imgFond1 = this.icoFond.getImage();
        this.imgFond2 = this.icoFond.getImage();

        icoWasted = new ImageIcon(getClass().getResource("/images/wasted.png"));
        imgWasted = this.icoWasted.getImage();

        icoWin = new ImageIcon(getClass().getResource("/images/win.png"));
        imgWin = this.icoWin.getImage();

        castle = new Castle();

        stringsGetInf();
        makeGround();
        makeCreaturesAndObjects();
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(this.imgFond1, this.xFond1, 0, null);
        g.drawImage(this.imgFond2, this.xFond2, 0, null);

        if (shadowPlayer != null)
        {
            shadowPlayer.draw(g);
        }

        castle.draw(g);

        for (Objects b : blocks)
        {
            b.draw(g);
        }

        for (Objects o : objects)
        {
            o.draw(g);
        }

        for (Creatures c : creatures)
        {
            c.draw(g);
        }

        if (GameMain.scene.gameOver == true)
        {
            g.drawImage(this.imgWasted, GameMain.scene.gameCordinate.getDx() + 100, 220, null);
        }

        if (GameMain.scene.gameWin == true)
        {
            g.drawImage(this.imgWin, GameMain.scene.gameCordinate.getDx() + 100, 220, null);
        }
    }

    public void update(int dx, int dy)
    {
        this.xFond1 -= dx;
        this.xFond2 -= dx;

        if (this.xFond1 <= -fondSize)
        {
            this.xFond1 = fondSize;
        } else if(this.xFond2 <= -fondSize)
        {
            this.xFond2 = fondSize;
        } else if(this.xFond1 >= fondSize)
        {
            this.xFond1 = -fondSize;
        } else if(this.xFond2 >= fondSize)
        {
            this.xFond2 = -fondSize;
        }

        castle.update(dx, dy);

        for (Objects b : blocks)
        {
            b.update(dx, dy);
        }

        for (Objects o : objects)
        {
            o.update(dx, dy);
        }

        for (Creatures c : creatures)
        {
            c.update(dx, dy);
        }

        if (shadowPlayer!= null)
        {
            shadowPlayer.update(dx, dy);
        }
    }

    public void makeGround()
    {
        int level = -1;
        int nextGr = -1;

        for (int i=0; i< strings.size(); i++)
        {
            String[] str = strings.get(i);
            level++;
            for (String s : str)
            {
                nextGr++;
                if (s.equals("1"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    Ground ground = new Ground(x, y, xSizeOfGameGrid, ySizeOfGameGrid);
                    blocks.add(ground);
                }
            }
            nextGr = -1;
        }
    }

    public void makeCreaturesAndObjects()
    {
        int level = -1;
        int nextGr = -1;

        for (int i=0; i< strings.size(); i++)
        {
            String[] str = strings.get(i);
            level++;
            for (String s : str)
            {
                nextGr++;
                if (s.equals("b"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    EggBird eggBird = new EggBird(x, y);
                    creatures.add(eggBird);
                } else if (s.equals("s"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    Snake snake = new Snake(x, y-5);
                    creatures.add(snake);
                } else if (s.equals("h"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    Hedgehog hedgehog = new Hedgehog(x, y-5);
                    creatures.add(hedgehog);
                } else if (s.equals("f"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    Frog frog = new Frog(x, y+10);
                    creatures.add(frog);
                } else if (s.equals("L"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    Lynx lynx = new Lynx(x, y-5);
                    creatures.add(lynx);
                } else if (s.equals("S"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    SuicideBird suicideBird = new SuicideBird(x, y-5);
                    creatures.add(suicideBird);
                } else if (s.equals("t"))
                {
                    int x = nextGr*ySizeOfGameGrid;
                    int y = level*xSizeOfGameGrid;

                    TeleportationPlus teleportationPlus = new TeleportationPlus(x, y, xSizeOfGameGrid, ySizeOfGameGrid);
                    objects.add(teleportationPlus);
                }
            }
            nextGr = -1;
        }
    }

    public void stringsGetInf()
    {
        TxtToArrayGround txtToArrayGround = new TxtToArrayGround();
        try
        {
            strings = txtToArrayGround.stringsGetInf();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
