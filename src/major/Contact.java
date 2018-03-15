package major;

import creatures.Creatures;
import creatures.creatureWeapon.CreatureWeapon;
import stuff.Objects;
import stuff.TeleportationPlus;

import java.util.ArrayList;

/**
 * Created by Никита on 11.08.2016.
 */
public class Contact
{
    Controller controller;
    GameCordinate gameCordinate;
    Player player;
    GameBack gameBack;

    // тупое решение, надо наловчиться с итераторами
    ArrayList<CreatureWeapon> cWMustBeDestroyed;
    ArrayList<Objects> objectMustBeDestroyed;

    public Contact(Controller controller, GameCordinate gameCordinate, Player player, GameBack gameBack)
    {
        this.controller = controller;
        this.gameCordinate = gameCordinate;
        this.player = player;
        this.gameBack = gameBack;
        cWMustBeDestroyed = new ArrayList<CreatureWeapon>();
        objectMustBeDestroyed = new ArrayList<Objects>();
    }

    public void contactOfPlayer()
    {
        checkContactWithBlocks();
        checkContactWithCreatures();
        checkContactWithObjects();

        checkPlayerContactGround();
        checkPlayerContactLeft();
        checkPlayerContactRight();

        for (char c : controller.characterSet)
        {
            if (c == 'd' && !player.isHasBarrierInRight())
            {
                gameCordinate.setDx(2);
            }
            if (c == 'a' && !player.isHasBarrierInLeft())
            {
                gameCordinate.setDx(-2);
            }
        }
    }

    public int contactInShadow(Player player, ShadowPlayer shadowPlayer)
    {
        int xCenterPlayer = player.getX() + player.getLength()/2;
        int yCenterPlayer = player.getY() + player.getHeight()/2;
        int xCenterShadow = shadowPlayer.getX() + player.getLength()/2;
        int yCenterShadow = shadowPlayer.getY() + player.getHeight()/2;

        int AB = (int) Math.sqrt((xCenterPlayer - xCenterShadow)*(xCenterPlayer - xCenterShadow) + (yCenterPlayer - yCenterShadow)*(yCenterPlayer - yCenterShadow));
        if (AB > player.getShadowDiametr()/2)
        {
            shadowPlayer.setChangeColor(true);
        } else
        {
            shadowPlayer.setChangeColor(false);
        }
        return AB;
    }


    public void checkPlayerContactGround()
    {
        if (!player.contactGround.isEmpty())
        {
            int count = player.contactGround.size();
            for (Objects o : player.contactGround)
            {
                if (!player.contact(o))
                {
                    count--;
                }
            }
            if (count == 0)
            {
                player.setFallingDown(true);
                player.contactGround.clear();
            }
        }
    }

    public void checkPlayerContactRight()
    {
        if (!player.contactRight.isEmpty())
        {
            int count = player.contactRight.size();
            for (Objects o : player.contactRight)
            {
                if (!player.contact(o))
                {
                    count--;
                }
            }
            if (count == 0)
            {
                player.setHasBarrierInRight(false);
                player.contactRight.clear();
            }
        }
    }

    public void checkPlayerContactLeft()
    {
        if (!player.contactLeft.isEmpty())
        {
            int count = player.contactLeft.size();
            for (Objects o : player.contactLeft)
            {
                if (!player.contact(o))
                {
                    count--;
                }
            }
            if (count == 0)
            {
                player.setHasBarrierInLeft(false);
                player.contactLeft.clear();
            }
        }
    }

    public void checkContactWithBlocks()
    {
        for(Objects o : gameBack.getBlocks())
        {
            if ((o.getX() > player.getX() - 5 && o.getX() < player.getX() + player.getLength()) || (o.getX() < player.getX() - 5 && o.getX() + o.getLength() > player.getX()))
            {
                if (player.contact(o) && o.getY() <= player.getY() + player.getHeight() && o.getY() > player.getY() + player.getHeight() - 5)
                {
                    player.contactGround.add(o);
                    player.setFallingDown(false);
                } else if (player.contact(o) && player.isTournRight() && player.isHasBarrierInRight())
                {
                    player.contactRight.add(o);
                    gameCordinate.setDx(0);
                } else if (player.contact(o) && player.isTournLeft() && player.isHasBarrierInLeft())
                {
                    player.contactLeft.add(o);
                    gameCordinate.setDx(0);
                }
            }
        }
    }

    public void checkContactWithObjects()
    {
        for(Objects o : gameBack.getObjects())
        {
            if ((o.getX() > player.getX() - 5 && o.getX() < player.getX() + player.getLength()) || (o.getX() < player.getX() - 5 && o.getX() + o.getLength() > player.getX()))
            {
                if (player.contact(o) && o instanceof TeleportationPlus && player.getShadowDiametr() < 600)
                {
                    player.plusShadowDiametr(150);
                    player.setHasBarrierInLeft(false);
                    player.setHasBarrierInRight(false);
                    player.setHasBarrierOverHead(false);
                    objectMustBeDestroyed.add(o);
                }
            }
        }
        objectMustBeDestroyed();
    }



    public void checkContactWithCreatures ()
    {
        for(Creatures c : gameBack.getCreatures())
        {
            if ((c.getX() > player.getX() - 500 && c.getX() < player.getX() + 50) || (c.getX() < player.getX() + 500 && c.getX() > player.getX() - 50))
            {
                if (c.isFighter)
                {
                    if (player.contact(c) && !player.isNoPain())
                    {
                        player.setHp(player.getHp()-1);
                        player.setTimeOfHurt(System.currentTimeMillis());
                        player.setNoPain(true);
                    }
                }
                if (c.hasWeapon)
                {
                    for(CreatureWeapon cW : c.getWeapon())
                    {
                        if (player.contact(cW) && !player.isNoPain())
                        {
                            player.setHp(player.getHp()-1);
                            cWMustBeDestroyed.add(cW);
                            player.setTimeOfHurt(System.currentTimeMillis());
                            player.setNoPain(true);
                        }
                    }
                    cWMustBeDestroyed(c);
                }
            }
        }

    }

    public void cWMustBeDestroyed(Creatures creature)
    {
        if(!cWMustBeDestroyed.isEmpty())
        {
            for (CreatureWeapon cW : cWMustBeDestroyed)
            {
                creature.getWeapon().remove(cW);
            }
            cWMustBeDestroyed.clear();
        }
    }

    public void objectMustBeDestroyed()
    {
        if(!objectMustBeDestroyed.isEmpty())
        {
            for (Objects o : objectMustBeDestroyed)
            {
                gameBack.getObjects().remove(o);
            }
            objectMustBeDestroyed.clear();
        }
    }

    public boolean checkContactCWbackGround(CreatureWeapon cW , Creatures creature)
    {
        for (Objects o : gameBack.getBlocks())
        {
            if (o.getX() - 20 < cW.getX() && o.getX() + 50 > cW.getX())
            {
                if (cW.contact(o))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
