package bots;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import pirates.*;


public class newClass0 implements PirateBot {
    
   
    public void doTurn(PirateGame game) {
        
        Pirate pirate;  
        Capsule capsule = game.getMyCapsule();
        //int myLivingPirate = game.getMyLivingPirates().size();
         
            pirate = game.getMyLivingPirates()[0];
            pirate.sail(capsule);
            pirate = game.getMyLivingPirates()[1];
            pirate.sail(capsule);
            pirate = game.getMyLivingPirates()[2];
            pirate.sail(capsule);
            pirate = game.getMyLivingPirates()[3];
            pirate.sail(capsule);
            
         
    }
}