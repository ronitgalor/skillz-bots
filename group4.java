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
    private Pirate [] pirates;
    private boolean isOccured = false; 
    private boolean myColor;
    private Location defenceLocation1;
    private Location defenceLocation2;
    private Location defenceEnemyBaseLocation;
    @Override
    public void doTurn(PirateGame game) {
        // Get one of my pirates.
         if(game.turn == 1)
            createAllMyPirates(game);
            
       bringCapsulesWithTwoBots(game,0,1);
       
       defence(game);
      
    }
            
           
    
    //gaining capsules    
    public void bringCapsulesWithTwoBots( PirateGame game, int id1, int id2)
    {
        
        if(!isOccured && (pirates[id1].getLocation().equals(game.getMyCapsule().getLocation()) || pirates[id2].getLocation().equals(game.getMyMothership().getLocation())))
        {
            moveTo(game.getMyCapsule(), id1);
            moveTo(game.getMyMothership(), id2);
        }
        else
        {
            isOccured = true;
            bringCapsules(id1, game);
            bringCapsules(id2, game);
        }
      
    }
    
    public void bringCapsules(int id, PirateGame game)
    {
        if (!tryPush(pirates[id], game)) {
            // If the pirate doesn't have a capsule, go and get it!
            if (pirates[id].capsule == null) {
                 moveTo(game.getMyCapsule(), id);
            }
            // Else, go to my mothership.
            else {
                // Go towards the mothership.
                moveTo(game.getMyMothership(), id);
            }
        }
    }
    
    //defence 
    public void defence(PirateGame game)
    {
         //sest the defence according to our color 
       myColor = getMyMothershipLocation(game);
       if(myColor)
       {
           defenceLocation1 = new Location(3600, 5150);
            defenceLocation2 = new Location(3200, 5150);
            defenceEnemyBaseLocation =  new Location(5400, 3200);
           
       }
          
       else
       {
            defenceLocation1 = new Location(2800, 1000);
            defenceLocation2 = new Location(3200, 1000);
            defenceEnemyBaseLocation =  new Location(1000, 3200);
       }
       
       
       
      
       
       
       
      
      if(!pirates[2].getLocation().equals(defenceLocation1) && !tryPush(pirates[2], game))
        moveTo(defenceLocation1, 2);
      else
        tryPush(pirates[2], game);
      if(!pirates[3].getLocation().equals(defenceLocation1) && !tryPush(pirates[3], game))
            moveTo(defenceLocation1, 3);
       else
        tryPush(pirates[3], game);   
        
         
      if(!pirates[4].getLocation().equals(defenceLocation2) && !tryPush(pirates[4], game))
        moveTo(defenceLocation2, 4);
      else
        tryPush(pirates[4], game);
      if(!pirates[5].getLocation().equals(defenceLocation2) && !tryPush(pirates[5], game))
            moveTo(defenceLocation2, 5);
       else
        tryPush(pirates[5], game);   
        
            
      if(!pirates[6].getLocation().equals(defenceEnemyBaseLocation) && !tryPush(pirates[6], game))
        moveTo(defenceEnemyBaseLocation, 6);
        else
        tryPush(pirates[6], game); 
    
      if(!pirates[7].getLocation().equals(defenceEnemyBaseLocation) && !tryPush(pirates[7], game))
            moveTo(defenceEnemyBaseLocation, 7);
            else
        tryPush(pirates[7], game); 
      
        
    }
    
    public boolean getMyMothershipLocation(PirateGame game)
    {
        //purple ship - true, green ship - false
        if(game.getEnemyMothership().getLocation().getLocation().equals(new Location(3200, 5900)))
          return true;
        return false;
    }
    
    
    
    //crates the living pirates
    private void createAllMyPirates(PirateGame game)
    {
        pirates = new Pirate[8];
        for(int i = 0; i < 8; i ++)
        {
               pirates[i] = game.getMyLivingPirates()[i];
        }
    }
    
    //Motion actions 
    private void moveTo(Location destination, int id)
    {
        // Sail towards the destination
        pirates[id].sail(destination);
    }
    
     private void moveTo(GameObject obj, int id)
    {
        // Sail towards specific Object
        pirates[id].sail(obj);
    }
    
    
    
    /**
     * Makes the pirate try to push an enemy pirate. Returns True if it did.
     *
     * @param pirate - The pushing pirate.
     * @param game - The current game state.
     * @return - true if the pirate pushed.
     */
    private boolean tryPush(Pirate pirate, PirateGame game) {
        if(pushPirateCapsule(pirate, game))
            return true;
        else
        {
            // Go over all enemies.
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            // Check if the pirate can push the enemy.
            if (pirate.canPush(enemy)) {
               
                 if(myColor)
                 {
                     if(pirate.location.inRange(defenceEnemyBaseLocation, 300))
                        pirate.push(enemy, enemy.location.add(new Location(600, 0)));
                    else
                        pirate.push(enemy, enemy.location.add(new Location(600, 600)));
                 }
                else
                {
                       if(pirate.location.inRange(defenceEnemyBaseLocation, 300))
                        pirate.push(enemy, enemy.location.add(new Location(-600, 0)));
                    else
                        pirate.push(enemy, enemy.location.add(new Location(-600, -600)));
                }
                // Print a message.
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);

              
                // Did push.
                return true;    
                
                }
               
                        
          
            }
        }
       
          // Didn't push.
        return false;
        }
        
        
        private boolean pushPirateCapsule(Pirate pirate, PirateGame game) {
        // Go over all enemies.
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            // Check if the pirate can push the enemy.
            if (pirate.canPush(enemy)) {
                
                 if(myColor)
                 {
                     if(pirate.location.inRange(defenceEnemyBaseLocation, 700))
                        pirate.push(enemy, enemy.location.add(new Location(600, 0)));
                    else
                        pirate.push(enemy, enemy.location.add(new Location(600, 600)));
                 }
                else
                {
                     if(pirate.location.inRange(defenceEnemyBaseLocation, 700))
                        pirate.push(enemy, enemy.location.add(new Location(-600, 0)));
                    else
                        pirate.push(enemy, enemy.location.add(new Location(-600, -600)));
                }
                    
               
                // Print a message.
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);

              
                // Did push.
                return true;    
                
                }
               
                        
          
            }
          // Didn't push.
        return false;
        }
        
        

       
    }
    