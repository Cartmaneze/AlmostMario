package major;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Никита on 13.07.2016.
 */
public class Controller implements KeyListener
{

    public Set<Character> getCharacterSet()
    {
        return characterSet;
    }

    public Set<Character> characterSet = new HashSet<Character>();

    private char up = 'w';
    private char down = 's';
    private char left = 'a';
    private char right = 'd';
    private char freeze = ' ';
    private char newGame = 'n';
  //  private char quickSilver = 'q';

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
//        if (e.getKeyChar() == quickSilver)
//        {
//            if (GameMain.scene.quikeSilverMode == false)
//            {
//                GameMain.scene.chrono.setPause(GameMain.scene.chrono.getPause() * 2);
//                GameMain.scene.quikeSilverMode = true;
//            } else
//            {
//                GameMain.scene.chrono.setPause(GameMain.scene.chrono.getStartPause());
//                GameMain.scene.quikeSilverMode = false;
//            }
//        }
        if (e.getKeyChar() == newGame && GameMain.scene.gameOver)
        {
            characterSet.remove(e.getKeyChar());
            GameMain.scene.newLevelNewBegin();
        }
        if (e.getKeyChar() == freeze && GameMain.scene.gameFreeze == false && GameMain.scene.player.getShadowDiametr() > 0)
        {
            GameMain.scene.gameFreeze = true;
        } else if (e.getKeyChar() == freeze && GameMain.scene.gameFreeze == true && GameMain.scene.player.getShadowDiametr() / 2 >= GameMain.scene.player.getABForShadowDiametr())
        {
            GameMain.scene.gameFreeze = false;
        }

        if (e.getKeyChar() == 'd')
        {
            GameMain.scene.player.setTournRight();
        } else if (e.getKeyChar() == 'a')
        {
            GameMain.scene.player.setTournLeft();
        }

        characterSet.add(e.getKeyChar());

        if (GameMain.scene.gameFreeze == true && !GameMain.scene.gameOver)
        {
            if (!GameMain.scene.player.isShadowAlready())
            {
                GameMain.scene.player.createShadow();
                GameMain.scene.player.setShadowAlready(true);
            }

            GameMain.scene.player.setMove(false);

            if (characterSet.contains(right) && characterSet.contains(up))
            {
                GameMain.scene.gameCordinate.setDx(2);
                GameMain.scene.player.setY(-5);
            } else if (characterSet.contains(right) && characterSet.contains(down))
            {
                GameMain.scene.gameCordinate.setDx(2);
                GameMain.scene.player.setY(5);
            } else if (characterSet.contains(left) && characterSet.contains(up))
            {
                GameMain.scene.gameCordinate.setDx(-2);
                GameMain.scene.player.setY(-5);
            } else if (characterSet.contains(left) && characterSet.contains(down))
            {
                GameMain.scene.gameCordinate.setDx(-2);
                GameMain.scene.player.setY(5);
            } else if (characterSet.contains(right))
            {
                GameMain.scene.gameCordinate.setDx(2);
            } else if (characterSet.contains(left))
            {
                GameMain.scene.gameCordinate.setDx(-2);
            } else if (characterSet.contains(down))
            {
                GameMain.scene.player.setY(5);

            } else if (characterSet.contains(up))
            {
                GameMain.scene.player.setY(-5);
            }
        } else
        {
            if (characterSet.size() >= 2)
            {
                if (characterSet.contains(up) && characterSet.contains(right) && GameMain.scene.player.isFallingDown() == false)
                {
                    GameMain.scene.player.hasOverHeadBarrier();
                    GameMain.scene.player.setJumping(true);
                    GameMain.scene.contact.checkPlayerContactRight();

                    if (!GameMain.scene.player.isHasBarrierInRight())
                    {
                        GameMain.scene.gameCordinate.setDx(2);
                    }

                    GameMain.scene.player.setMove(true);
                    GameMain.scene.player.setFallingDown(true);
                } else if (characterSet.contains(up) && characterSet.contains(left) && GameMain.scene.player.isFallingDown() == false)
                {
                    GameMain.scene.player.hasOverHeadBarrier();
                    GameMain.scene.player.setJumping(true);
                    GameMain.scene.contact.checkPlayerContactLeft();

                    if (!GameMain.scene.player.isHasBarrierInLeft())
                    {
                        GameMain.scene.gameCordinate.setDx(-2);
                    }

                    GameMain.scene.player.setMove(true);
                    GameMain.scene.player.setFallingDown(true);
                }
            } else
            {
                if (characterSet.contains(right))
                {
                    GameMain.scene.player.setHasBarrierInLeft(false);
                    if (GameMain.scene.player.isHasBarrierInRight())
                    {
                        GameMain.scene.gameCordinate.setDx(0);
                        GameMain.scene.player.setMove(false);
                    } else
                    {
                        GameMain.scene.gameCordinate.setDx(2);
                        GameMain.scene.player.setMove(true);
                    }
                } else if (characterSet.contains(left))
                {
                    GameMain.scene.player.setHasBarrierInRight(false);
                    if (GameMain.scene.player.isHasBarrierInLeft())
                    {
                        GameMain.scene.gameCordinate.setDx(0);
                        GameMain.scene.player.setMove(false);
                    } else
                    {
                        GameMain.scene.gameCordinate.setDx(-2);
                        GameMain.scene.player.setMove(true);
                    }
                } else if (characterSet.contains(down))
                {
                    GameMain.scene.player.setHasBarrierInRight(false);
                    GameMain.scene.player.setHasBarrierInLeft(false);

                } else if (characterSet.contains(up) && GameMain.scene.player.isFallingDown() == false)
                {
                    GameMain.scene.player.setHasBarrierInRight(false);
                    GameMain.scene.player.setHasBarrierInLeft(false);
                    GameMain.scene.player.hasOverHeadBarrier();
                    GameMain.scene.player.setJumping(true);
                    GameMain.scene.player.setFallingDown(true);
                }

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if ((e.getKeyChar() == right || e.getKeyChar() == left) && (!characterSet.contains(right) || !characterSet.contains(left)))
        {
            GameMain.scene.gameCordinate.setDx(0);
            GameMain.scene.player.setMove(false);
        }
        characterSet.remove(e.getKeyChar());

    }
}
