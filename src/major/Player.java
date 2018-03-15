package major;

import creatures.Creatures;
import creatures.creatureWeapon.CreatureWeapon;
import stuff.Objects;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Никита on 13.07.2016.
 */
public class Player
{
    private ImageIcon icoPers;
    private Image imgPers;

    private int height;

    public int getLength()
    {
        return length;
    }

    private int length;

    private int x;
    private int y;

    public long getTimeOfHurt()
    {
        return timeOfHurt;
    }

    public void setTimeOfHurt(long timeOfHurt)
    {
        this.timeOfHurt = timeOfHurt;
    }

    private long timeOfHurt;

    public boolean isNoPain()
    {
        return noPain;
    }

    public void setNoPain(boolean noPain)
    {
        this.noPain = noPain;
    }

    private boolean noPain;

    private double gGravity;
    private int steps;
    private boolean moving;
    private boolean tournLeft;
    private boolean tournRight;

    public boolean isShadowAlready()
    {
        return isShadowAlready;
    }

    public void setShadowAlready(boolean isShadowAlready)
    {
        this.isShadowAlready = isShadowAlready;
    }

    private boolean isShadowAlready;

    public int getABForShadowDiametr()
    {
        return ABForShadowDiametr;
    }

    private int ABForShadowDiametr;

    private boolean rewrightShadowDiametr;

    public void minusShadowDiametr(int AB)
    {
        shadowDiametr -= AB;
        if (shadowDiametr < 50)
        {
            shadowDiametr = 0;
        }
    }

    public void plusShadowDiametr(int increse)
    {
        shadowDiametr += increse;
        if (shadowDiametr > 600)
        {
            shadowDiametr = 600;
        }
    }

    public int getShadowDiametr()
    {
        return shadowDiametr;
    }

    private int shadowDiametr;

    public int getHp() {return hp;}

    public void setHp(int hp) {this.hp = hp;}

    private int hp;

    public boolean isAlive() {return isAlive;}

    public void setAlive(boolean isAlive) {this.isAlive = isAlive;}

    private boolean isAlive;

    public int getMaxHighOfJump()
    {
        return maxOfJump;
    }

    public void setMaxHighOfJump(int maxHighOfJump)
    {
        this.maxOfJump = maxHighOfJump;
    }

    private int maxOfJump;

    private int maxOfSmallJump;

    private int enableOfSmallJump;

    public int getEnableHighOfJump()
    {
        return enableHighOfJump;
    }

    public void setEnableHighOfJump(int enableHighOfJump)
    {
        this.enableHighOfJump = enableHighOfJump;
    }

    private int enableHighOfJump;
    private boolean maxJumpIsUsed;
    private int highToBarrier;

    private boolean hasBarrierInRight;
    private boolean hasBarrierInLeft;

    public boolean isHasBarrierOverHead()
    {
        return hasBarrierOverHead;
    }

    public void setHasBarrierOverHead(boolean hasBarrierOverHead)
    {
        this.hasBarrierOverHead = hasBarrierOverHead;
    }

    private boolean hasBarrierOverHead;

    private boolean isFallingDown;

    public boolean isJumping()
    {
        return isJumping;
    }

    public void setJumping(boolean isJumping)
    {
        this.isJumping = isJumping;
    }

    private boolean isJumping;

    public boolean isUnderGround()
    {
        return isUnderGround;
    }

    public void setUnderGround(boolean isUnderGround)
    {
        this.isUnderGround = isUnderGround;
    }

    private boolean isUnderGround;

    public ArrayList<Objects> contactGround;
    public ArrayList<Objects> contactRight;
    public ArrayList<Objects> contactLeft;

    public int getHeight() {
        return height;}

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public void setY(int dy)
    {
        y += dy;
    }
    public void setYY(int dy)
    {
        y = dy;
    }

    public void setMove(boolean b)
    {
        this.moving = b;
    }

    public void setTournLeft() {
        this.tournLeft = true;
        this.tournRight = false;}

    public void setTournRight() {
        this.tournRight = true;
        this.tournLeft = false;}

    public boolean isTournLeft()
    {
        return tournLeft;
    }

    public boolean isTournRight()
    {
        return tournRight;
    }

    public boolean isMoving()
    {
        return moving;
    }

    public boolean isHasBarrierInRight()
    {
        return hasBarrierInRight;
    }

    public void setHasBarrierInRight(boolean hasBarrierInRight)
    {
        this.hasBarrierInRight = hasBarrierInRight;
    }

    public boolean isHasBarrierInLeft()
    {
        return hasBarrierInLeft;
    }

    public void setHasBarrierInLeft(boolean hasBarrierInLeft)
    {
        this.hasBarrierInLeft = hasBarrierInLeft;
    }

    public boolean isFallingDown()
    {
        return isFallingDown;
    }

    public void setFallingDown(boolean isFallingDown)
    {
        this.isFallingDown = isFallingDown;
    }

    public Image getImgPers() {
        return imgPers;}

    private PlayersViewScreen playersViewScreen;

    public Player()
    {
        icoPers = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
        this.imgPers = this.icoPers.getImage();
        height = 50;
        length = 20;
        x = 300;
        y = 460;

        playersViewScreen = new PlayersViewScreen();

        maxOfJump = 95;
        maxOfSmallJump = 56;
        enableHighOfJump = maxOfSmallJump;
        highToBarrier = Integer.MAX_VALUE;

        shadowDiametr = 300;

        steps = 10;
        tournLeft = false;
        tournRight = true;
        moving = false;
        isAlive = true;
        isShadowAlready = false;
        hasBarrierInRight = false;
        hasBarrierInLeft = false;
        hasBarrierOverHead = false;
        isFallingDown = true;
        contactGround = new ArrayList<Objects>();
        contactLeft = new ArrayList<Objects>();
        contactRight = new ArrayList<Objects>();
        gGravity = 0;
        hp = 3;
        timeOfHurt = Long.MAX_VALUE;
        noPain = false;
    }

    public void draw(Graphics2D g)
    {
        if (isAlive && noPain==false)
        {
            drawOfMove();
        } else if (isAlive && noPain==true)
        {
            drawOfHurtMove();
        } else
        {
            icoPers = new ImageIcon(getClass().getResource("/images/depart.png"));
            this.imgPers = this.icoPers.getImage();
        }
        g.drawImage(imgPers, x - 5, y, null);
        playersViewScreen.draw(g);
    }

    public void update()
    {
        if (System.currentTimeMillis() > timeOfHurt + 1000)
        {
            timeOfHurt = Long.MAX_VALUE;
            noPain = false;
        }

        if (!GameMain.scene.gameFreeze)
        {
            if (rewrightShadowDiametr)
            {
                minusShadowDiametr(ABForShadowDiametr);
                rewrightShadowDiametr = false;
            }
            action();
            GameMain.scene.gameBack.shadowPlayer = null;
            setShadowAlready(false);
        } else
        {
            rewrightShadowDiametr = true;
            ABForShadowDiametr = GameMain.scene.contact.contactInShadow(this, GameMain.scene.gameBack.shadowPlayer);
        }
    }

    public void createShadow()
    {
        GameMain.scene.gameBack.shadowPlayer = new ShadowPlayer(x, y, shadowDiametr, height, length);
    }

    public void action()
    {
        GameMain.scene.contact.contactOfPlayer();

        if (y >= 630)
        {
            GameMain.scene.gameOver = true;
        }

        if (GameMain.scene.gameCordinate.getAbsDx() >= 10000)
        {
            GameMain.scene.gameWin = true;
        }

        if (hp <= 0)
        {
            isAlive = false;
        }

        if (!isAlive && !isFallingDown())
        {
            GameMain.scene.gameOver = true;
        }

        if (isJumping)
        {
            jump();
        } else
        {
            if (isFallingDown)
            {
                y += 3;
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
            }
        }

        playersViewScreen.setHp(hp);
        playersViewScreen.setShadowDiametr(shadowDiametr);
    }

    public void jump()
    {
        if (enableHighOfJump >= 60)
        {
            if (maxJumpIsUsed == false)
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            } else
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            }
        } else if (enableHighOfJump >= 40)
        {
            if (maxJumpIsUsed == false)
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            } else
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            }
        } else if (enableHighOfJump >= 20)
        {
            if (maxJumpIsUsed == false)
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            } else
            {
                y -= 3;
                enableHighOfJump -= 3;
                highToBarrier -= 3;
            }
        } else if (enableHighOfJump >= 4)
        {
            if (maxJumpIsUsed == false)
            {
                y -= 4;
                enableHighOfJump -= 4;
                highToBarrier -= 4;
            } else
            {
                y -= 2;
                enableHighOfJump -= 2;
                highToBarrier -= 2;
            }
        }else
        {
            y -= enableHighOfJump;

            if (GameMain.scene.controller.getCharacterSet().contains('w') && maxJumpIsUsed == false && !hasBarrierOverHead)
            {
                if (highToBarrier < maxOfJump - maxOfSmallJump)
                {
                    enableHighOfJump = highToBarrier;
                } else
                {
                    enableHighOfJump = maxOfJump - maxOfSmallJump;
                }

                maxJumpIsUsed = true;
            } else
            {
                isJumping = false;
                enableHighOfJump = maxOfSmallJump;
                maxJumpIsUsed = false;
                highToBarrier = Integer.MAX_VALUE;
            }
        }


    }

    public void drawOfMove()
    {
        int numSteps = 10;
        ImageIcon imageIcon = icoPers;
        if (moving)
        {
            steps++;
            if (steps > 20)
            {
                steps = 0;
            }
            if (tournRight)
            {
                if (steps > numSteps)
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/marioArretDroite.png"));
                } else
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/marioMarcheDroite.png"));
                }
            } else if (tournLeft)
            {
                if (steps > numSteps)
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/marioArretGauche.png"));
                } else
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/marioMarcheGauche.png"));
                }
            }
        } else
        {
            if (tournRight)
            {
                imageIcon = new ImageIcon(getClass().getResource("/images/marioArretDroite.png"));
            } else if (tournLeft)
            {
                imageIcon = new ImageIcon(getClass().getResource("/images/marioArretGauche.png"));
            }
        }
        this.imgPers = imageIcon.getImage();
    }

    public void drawOfHurtMove()
    {
        int numSteps = 10;
        ImageIcon imageIcon = icoPers;
        if (moving)
        {
            steps++;
            if (steps > 20)
            {
                steps = 0;
            }
            if (tournRight)
            {
                if (steps > numSteps)
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretDroite.png"));
                } else
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretDroite.png"));
                }
            } else if (tournLeft)
            {
                if (steps > numSteps)
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
                } else
                {
                    imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
                }
            }
        } else
        {
            if (tournRight)
            {
                imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretDroite.png"));
            } else if (tournLeft)
            {
                imageIcon = new ImageIcon(getClass().getResource("/images/tortueArretGauche.png"));
            }
        }
        this.imgPers = imageIcon.getImage();
    }

    public boolean contact(Objects object)
    {
        // check contact presens
        if(this.x + this.length < object.getX() || this.x > object.getX() + object.getLength() || this.y + this.height < object.getY() || this.y > object.getY() + object.getHeight())
        {
            return false;
        } else
        {
            // check contact right
            if (this.x + this.length > object.getX() && this.x < object.getX() && this.y + this.height > object.getY() && this.y < object.getY() + object.getHeight() && isTournRight())
            {
                hasBarrierInRight = true;
            }
            // check contact left
            if (this.x < object.getX() + object.getLength() && this.x > object.getX() && this.y + this.height > object.getY() && this.y < object.getY() + object.getHeight() && isTournLeft())
            {
                hasBarrierInLeft = true;
            }
            return true;
        }
    }

    public boolean contact(Creatures object)
    {
        // check contact presens
        if(this.x + this.length < object.getX() || this.x > object.getX() + object.getLength() || this.y + this.height < object.getY() || this.y > object.getY() + object.getHeight())
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean contact(CreatureWeapon object)
    {
        // check contact presens
        if(this.x + this.length < object.getX() || this.x > object.getX() + object.getLength() || this.y + this.height < object.getY() || this.y > object.getY() + object.getHeight())
        {
            return false;
        } else
        {
            return true;
        }
    }

    public void hasOverHeadBarrier()
    {
        int compareHighOfJump = 0;

        for (Objects o : GameMain.scene.gameBack.getBlocks())
        {
            if ((x <= o.getX() && x + length >= o.getX()) || (x > o.getX() && o.getX() + o.getLength() > x))
            {
                if (y > o.getY() + o.getHeight() && y - maxOfJump < o.getY() + o.getHeight())
                {
                    compareHighOfJump = y - o.getY() - o.getHeight();
                    if (compareHighOfJump < highToBarrier && compareHighOfJump > 0)
                    {
                        highToBarrier = compareHighOfJump;
                        enableHighOfJump = highToBarrier;
                    }
                }
            }
        }
    }
}