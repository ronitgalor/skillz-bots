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
        Pirate[] defenseP = new Pirate[4];
        for(int i = 0; i < 4; i++)
        {
            if(game.getMyLivingPirates()[i] != null)
            defenseP[i] = game.getMyLivingPirates()[i];
        }
        deffendersAct(defenseP, game);
        
        attack(game);
    }
        
        //takeCapsulesAndDeliverToMothership(game);
		//guardEnemyMothershipAndMine(game);
		
	public void deffendersAct(Pirate[] defendersP, PirateGame game)
    {
        boolean hasActed = false;
        
        for(Pirate dP : defendersP)
        {
            hasActed = false;
            for(Pirate enemy : game.getEnemyLivingPirates())
            {
                if(!hasActed && dP.canPush(enemy) && enemy.hasCapsule())
                {
                    System.out.println("Tried to push. Pusher: " + dP.id + " tried to push " + enemy.id);
                    dP.push(enemy, enemy.getLocation().towards(game.getEnemyMothership().getLocation(), -10));
                    hasActed = true;
                }
            }
            
        }
        int i = 0;
        Pirate dP;
        for(i = 0; i <defendersP.length/2; i++)
        {
            dP = defendersP[i];
            Location goTo;
            if(game.getMyself().id == 1) //dP.owner.id == 1)
            {
                //System.out.println("owner 1");
                goTo = new Location((game.getEnemyMothership().getLocation().row + 300), (game.getEnemyMothership().getLocation().col - 600));
                //dP.sail(();
            }
            else
            {
                //System.out.println("owner not 1");
                goTo = new Location((game.getEnemyMothership().getLocation().row - 300), (game.getEnemyMothership().getLocation().col + 600));
                //dP.sail(((game.getEnemyMothership().getLocation().col + 300), (game.getEnemyMothership().getLocation().row - 300)));
            }
            //System.out.println("go to: " + goTo);
            //System.out.println("Enemy ship: " + game.getEnemyMothership().getLocation());
            if(!hasActed)
            {
                dP.sail(goTo);
            }
        }
        for(;i < defendersP.length; i++)
        {
            dP = defendersP[i];
            Location goTo;
            if(dP.owner.id == 1)
            {
                //System.out.println("owner 1");
                goTo = new Location((game.getEnemyMothership().getLocation().row + 600), (game.getEnemyMothership().getLocation().col - 300));
                //dP.sail(();
            }
            else
            {
                //System.out.println("owner not 1");
                goTo = new Location((game.getEnemyMothership().getLocation().row - 600), (game.getEnemyMothership().getLocation().col + 300));
                //dP.sail(((game.getEnemyMothership().getLocation().col + 300), (game.getEnemyMothership().getLocation().row - 300)));
            }
            //System.out.println("go to: " + goTo);
            //System.out.println("Enemy ship: " + game.getEnemyMothership().getLocation());
            if(!hasActed)
            {
                dP.sail(goTo);
            }
        }
    }
    
    
	
	/*public void takeCapsulesAndDeliverToMothership(PirateGame game) {
		Pirate[] availablePirates = game.getMyLivingPirates();
		
		for (int i = availablePirates.length / 2; i < availablePirates.length; i++) {
			Pirate currentPirate = availablePirates[i];
			
			if (currentPirate.capsule != null) {
				Mothership mothership = game.getMyMothership();
				
				if (mothership.inUnloadRange(currentPirate)) { // TODO: Only move necessary distance to be in the unloadign range of the mothership.
					Capsule capsuleToDeliver = game.getMyCapsule();
					approachPirateToTarget(currentPirate, capsuleToDeliver);
				} else {
					approachPirateToTarget(currentPirate, mothership);
				}
			} else {
				Capsule capsuleToDeliver = game.getMyCapsule();
				approachPirateToTarget(currentPirate, capsuleToDeliver);
			}
		}
	}
	
	public void guardEnemyMothershipAndMine(PirateGame game) {
		Pirate[] availablePirates = game.getMyLivingPirates();
		int availablePiratesLength = availablePirates.length / 2;
		
		Mothership enemyMothership = game.getEnemyMothership();
		Location enemyMine = new Location(5400, 3200);
		
		for (int i = 0; i < availablePiratesLength / 2; i++) {
			// Guard the enemy's mothership
			Pirate currentPirate = availablePirates[i];			
			
			if (currentPirate.getLocation().equals(enemyMothership.getLocation())) {
				Pirate[] enemyPirates = game.getEnemyLivingPirates();
				
				for (Pirate enemyPirate: enemyPirates) {
					if (currentPirate.inPushRange(enemyPirate) && currentPirate.canPush(enemyPirate)) {
						Location approachBorder = enemyPirate.getLocation().towards(new Location(enemyMothership.getLocation().col, enemyMothership.getLocation().row + currentPirate.pushDistance), currentPirate.pushDistance);

						currentPirate.push(enemyPirate, approachBorder);
					}
				}
			} else {
				approachPirateToTarget(currentPirate, enemyMothership.getLocation().towards(enemyMine, 500));
			}
		}
		
		for (int i = availablePiratesLength / 2; i < availablePiratesLength; i++) {
			// Guard the enemy's capsule mine
			// Pirate currentPirate = availablePirates[i];
		}
	}
	
	public void approachPirateToTarget(Pirate pirate, MapObject target) {
		Location approachingLocation = pirate.getLocation().towards(target, 200);
		pirate.sail(approachingLocation);*/
	
	
	// public guardPirate(Private deliveryPirate, Pirate guardingPirate) {
	// 	Location approachingLocation = guardingPirate.getLocation().towards(deliveryPirate, 200);
	// 	guardingPirate.sail(approachingLocation);
    // 
	// 	// TODO: Push any enemy pirate in the close range of the pirates.
	// }
	
	private void attack(PirateGame game) {
        Mothership enemyMothership = game.getEnemyMothership();
        
		Pirate[] pirates = game.getAllMyPirates();
		
		for (int i = 4; i < Math.min(7, pirates.length); i++) {
            // for (int i = 5; i <= 7; i++)
            // {
               Capsule capsule = game.getMyCapsule();
               if (game.getAllMyPirates()[i].isAlive())
                {
                    Pirate pirate = game.getAllMyPirates()[i];
                    if (!getPirateShip(game))
                    {
                        if (pirate.capsule == null && !pirate.hasCapsule() && !tryPush(pirate, game)) 
                            {
                                pirate.sail(capsule);
                            }
                            if (pirate.getLocation() == capsule.getLocation())
                            {
                                pirate.sail(game.getMyMothership());
                            }
                    }
                    else if (pirate.getLocation().row == 3200)
                    {
                        pirate.sail(game.getMyMothership());
                    }
                    else
                    {
                        Location location5 = new Location(3200,3200);
                        pirate.sail(location5);
                    }
                        
                }
            // }
		}
	}

    /**
     * Makes the pirate try to push an enemy pirate. Returns True if it did.
     *
     * @param pirate - The pushing pirate.
     * @param game - The current game state.
     * @return - true if the pirate pushed.
     */
    private boolean tryPush(Pirate pirate, PirateGame game) {
        // Go over all enemies.
        for (Pirate enemy : game.getEnemyLivingPirates()) {
            // Check if the pirate can push the enemy.
            if (pirate.canPush(enemy)) {
                // Push enemy!
                pirate.push(enemy, enemy.initialLocation);

                // Print a message.
                System.out.println("pirate " + pirate + " pushes " + enemy + " towards " + enemy.initialLocation);

                // Did push.
                return true;
            }
        }

        // Didn't push.
        return false;
    }
    
    public static boolean getPirateShip(PirateGame game)
    {
        boolean id = false;
        for (int i = 0; i <= Math.min(7, game.getAllMyPirates().length); i++)
        {
            Pirate pirate = game.getMyLivingPirates()[i];
            if (pirate.hasCapsule())
            {
                id = true;
            }
        }
        return id;
    }
}