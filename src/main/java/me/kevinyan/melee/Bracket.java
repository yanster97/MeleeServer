package me.kevinyan.melee;

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
           int numMatchesR1 = numPlayers - nearestPower;
           int playersR1 = numMatchesR1 * 2;
       }
       
       public int nearestPower2(int n) {
           int power2 = 1;
           while(n > power2) {
               power2 *= 2;
           }
           return power2/2;
       }

}
