package major;

/**
 * Created by Никита on 13.07.2016.
 */
public class GameCordinate
{

    public int getDx()
    {
        return dx;
    }

    public void setDx(int dx)
    {
        this.dx = dx;
    }

    public int getDy()
    {
        return dy;
    }

    public int getAbsDx()
    {
        return absDx;
    }

    public void setAbsDx(int i)
    {
        this.absDx += i;
        if (this.absDx < GameMain.scene.player.getX())
        {
            this.absDx = GameMain.scene.player.getX();
        }
    }

    private int dx;
    private int dy;
    private int absDx;

    public GameCordinate()
    {
        dx = 0;
        dy = 0;
        absDx = GameMain.scene.player.getX();
    }
}
