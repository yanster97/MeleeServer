package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Bracket {
       public List<Round> rounds;
       
       /**
        * List of players is in descedning order of skill (0 Best -> Worst)
        * @param players 
        */
       public Bracket(List<Player> players) {
           int numPlayers = players.size();
           int nearestPower = nearestPower2(numPlayers);
           this.rounds = new ArrayList();
           //adding last round
           rounds.add(Round.getLastRound());
           
           Round prevRound = rounds.get(0);
           //adding middle rounds
           while(shouldMakeNewRound(prevRound, nearestPower)){
               //make round from prev round
               Round currentRound = Round.fromRound(prevRound);
               //add round to list
               rounds.add(currentRound);
               prevRound = currentRound;
               
           }
           
           //adding first round
           rounds.add(Round.getFirstRound(numPlayers, prevRound));
           
       }
       
       private boolean shouldMakeNewRound(Round prevRound, int nearestPower){
           return (prevRound.getNumPlayers() * 2) <= nearestPower;
       }
       
       public int nearestPower2(int n) {
           int power2 = 1;
           while(n > power2) {
               power2 *= 2;
           }
           return power2/2;
       }

}
