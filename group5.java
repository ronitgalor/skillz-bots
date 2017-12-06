package bots;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import pirates.*;

/**
 * This is an example for a bot.
 */
public class MyBot implements PirateBot {
    /**
     * Makes the bot run a single turn.
     *
     * @param game - The current game state.
     */
     
    
        
    @Override
    public void doTurn(PirateGame game) {
        Pirate pirate;
        Mothership mothership = game.getMyMothership();
        Capsule capsule = game.getMyCapsule();
        Location[] locations = new Location[5];
        locations[0] = new Location(0, -250);
        locations[1] = new Location(500, 0);
        locations[2] = new Location(0, 0);
        locations[3] = new Location(250,-250);
        locations[4] = new Location(0, -2500);
        Location pushM = new Location(1000, 1000);
        int i;
        Pirate pirateC = game.getAllMyPirates()[5];
        Pirate pirateC1 = game.getAllMyPirates()[6];
        Pirate pirateEC = game.getAllMyPirates()[4];
        Pirate boris = game.getAllMyPirates()[7];
        
        if(game.getAllPlayers()[1].equals(game.getMyself()))
        {
            locations[1].multiply(-1);
            locations[2].multiply(-1);
            locations[3].multiply(-1);
            locations[4].multiply(-1);
            pushM.multiply(-1);
        }
        
        
        if (pirateC.capsule == null)
            pirateC.sail(capsule);
        else
            pirateC.sail(mothership);
        
        if (pirateC1.capsule == null)
            pirateC1.sail(capsule);
        else
            pirateC1.sail(mothership);
    
        if(pirateC.capsule != null && !tryPush(boris, game))
              boris.sail(pirateC);
        else if (pirateC1.capsule != null && !tryPush(boris, game))
              boris.sail(pirateC1);
        else if(!tryPush(boris, game))
              boris.sail(pirateC);
              
              
        for(i= 0; i < 4; i++ )
        {
            pirate = game.getAllMyPirates()[i];
            if(!tryPushM(pirate, game, pushM))
                pirate.sail(game.getEnemyMothership().getLocation().add(locations[i]));
                
        }
        
        if(!tryPushEC(pirateEC, game, mothership.getLocation()))
            pirateEC.sail(game.getEnemyCapsule().getLocation());
    }
    
    /**
     * Makes the pirate try to push an enemy pirate. Returns True if it did.
     *
     * @param pirate - The pushing pirate.
     * @param game - The current game state.
     * @return - true if the pirate pushed.
     */
    private boolean tryPush(Pirate pirate, PirateGame game) {
        Mothership mothership = game.getMyMothership();
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            if (pirate.canPush(enemy)) {
                pirate.push(enemy, mothership.getLocation());
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                return true;
            }
        }
        return false;
    }
    
     private boolean tryPushM(Pirate pirate, PirateGame game, Location push) {
        Capsule capsule = game.getMyCapsule();
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            if (pirate.canPush(enemy) && enemy.capsule != null) {
                pirate.push(enemy, enemy.getLocation().add(push));
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                return true;
            }
            if(pirate.inRange(enemy, 500) && enemy.capsule != null)
            {
                pirate.sail(enemy);
                return true;
            }
        }
        return false;
    }
    
    private boolean tryPushEC(Pirate pirate, PirateGame game, Location push) {
        Capsule capsule = game.getMyCapsule();
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            if (pirate.canPush(enemy)) {
                pirate.push(enemy, enemy.getLocation().add(push));
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);
                return true;
            }
            if(pirate.inRange(enemy, 500) && pirate.inRange(capsule,500))
            {
                pirate.sail(enemy);
                return true;
            }
        }
        return false;
    }
}