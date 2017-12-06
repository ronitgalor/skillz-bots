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
        //    0

        game.getMyLivingPirates()[0].sail(new Location(3200,5000));
        game.getMyLivingPirates()[1].sail(new Location(3400,5400));
        game.getMyLivingPirates()[2].sail(new Location(3800,5900));
        game.getMyLivingPirates()[3].sail(new Location(3700,5700));
        
         game.getMyLivingPirates()[4].sail(new Location(5400,3200));
         game.getMyLivingPirates()[5].sail(new Location(5400,3200));
         
         for(int i=0;i<8;i++)
         {
              for (Pirate enemy: game.getEnemyLivingPirates()) 
              {
                if (game.getMyLivingPirates()[i].canPush(enemy)) 
                {
                    game.getMyLivingPirates()[i].push(enemy, enemy.initialLocation);
}}}

         
         //game.getMyLivingPirates()][7].sail(2529,1920);
        

    /**
     * Makes the pirate try to push an enemy pirate. Returns True if it did.
     *
     * @param pirate - The pushing pirate.
     * @param game - The current game state.
     * @return - true if the pirate pushed.
     */
     
}

}