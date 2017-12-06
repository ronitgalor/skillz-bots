package bots;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import pirates.*;

public class MyBot implements PirateBot {
    public static boolean getPirateShip(PirateGame game)
    {
        boolean id = false;
        for (int i = 0; i <= 7; i++)
        {
            Pirate pirate = game.getMyLivingPirates()[i];
            if (pirate.hasCapsule())
            {
                id = true;
            }
        }
        return id;
    }
        //Don't look!
    public static boolean isGreen(PirateGame game)                                                                                                                                                                                                                                                                                      
    {
        Mothership mothership2 = game.getMyMothership();
        if(mothership2.getLocation().col<3200)
            return true;
        else
            return false;
    }
    
    public static int fixNumber(PirateGame game, int num)
    {
        if (isGreen(game))
            return num;
        else
            return (-num);
    }
        //Don't look!
    @Override
    public void doTurn(PirateGame game) {
        Mothership mothership = game.getEnemyMothership();
        Mothership mothership2 = game.getMyMothership();
        boolean flag = true;
        //DEFFENSE
        if (game.getMyLivingPirates()[3].getLocation() != mothership.getLocation())
        {
            for (int i = 0; i <= 2; i++)
            {
            Pirate pirate = game.getAllMyPirates()[i];
            if (pirate.isAlive())
                {
                    if (i == 1)
                    {
                        Location location = new Location(mothership.getLocation().row + fixNumber(game, 200), mothership.getLocation().col);
                        if (!tryPush(pirate, game)) {
                            pirate.sail(location);
                        }
                    }
                    else
                    {
                       Location location = new Location(mothership.getLocation().row, mothership.getLocation().col - fixNumber(game, 200));
                        if (!tryPush(pirate, game)) {
                            pirate.sail(location);
                        } 
                    }
                }
            }
        }
            //Don't look!
        Pirate p1 = game.getAllMyPirates()[3];
             if (p1.isAlive())
                {
                Capsule capsule1 = game.getEnemyCapsule();
                if (!tryPush(p1, game)) {
                    p1.sail(capsule1);
                    }
                }
         Pirate p2 = game.getAllMyPirates()[4];
         if (p2.isAlive())
            {
            Capsule capsule2 = game.getEnemyCapsule();
            if (!tryPush(p2, game)) {
                p2.sail(capsule2);
                }
            }
            
            
            
            
            
        //ATTACK
        for (int i = 5; i <= 7; i++)
        {
           Capsule capsule = game.getMyCapsule();
           if (game.getAllMyPirates()[i].isAlive())
            {
                Pirate pirate = game.getAllMyPirates()[i];
                Location location = pirate.getLocation();
                if (!getPirateShip(game))
                {
                    if (pirate.capsule == null && !pirate.hasCapsule() && !tryPush(pirate, game)) 
                        {
                            pirate.sail(capsule);
                        }
                        if (pirate.getLocation() == capsule.getLocation())
                        {
                            pirate.sail(mothership2);
                        }
                }
                else if (pirate.getLocation().row == 3200)
                {
                    pirate.sail(mothership2);
                }
                else
                {
                    Location location5 = new Location(3200,3200);
                    pirate.sail(location5);
                }
                //for (Pirate enemy : game.getAllEnemyPirates())
                //{
                //    if(pirate.canPush(enemy))
                //    {
                //        Location whereToPush = new Location(location.row, -1);
                //        pirate.push(enemy, whereToPush);
                //    }
                //}
            }
        }
        
    }


    //Don't look!
    private boolean tryPush(Pirate pirate, PirateGame game) {
        Mothership mothership = game.getMyMothership();
        Mothership mothership2 = game.getEnemyMothership();
        Capsule capsule = game.getEnemyCapsule();
        Capsule capsule5 = game.getMyCapsule();
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            if (pirate.canPush(enemy) && pirate.getLocation() != capsule.getLocation() && enemy.hasCapsule()) {
                Location location = new Location(0, 6405);
                pirate.push(enemy, mothership);
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                return true;
            }
            else if (pirate.canPush(enemy) && enemy.hasCapsule())
            {
                pirate.push(enemy, mothership);
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                return true;
                
            }
            else if (pirate.canPush(enemy) && pirate.getLocation() == mothership.getLocation())
            {
                pirate.push(enemy, mothership2);
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                System.out.println("Dack");
                return true;
            }
        }
        return false;
    }
}