package major;

/**
 * Created by Никита on 13.07.2016.
 */
public class Chrono implements Runnable
{
    public int getPause()
    {
        return pause;
    }

    public void setPause(int pause)
    {
        this.pause = pause;
    }

    private int pause = 10;

    public int getStartPause()
    {
        return startPause;
    }

    private final int startPause = pause;

    @Override
    public void run()
    {
        while (true)
        {
            GameMain.scene.repaint();
            try
            {
                Thread.sleep(pause);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
