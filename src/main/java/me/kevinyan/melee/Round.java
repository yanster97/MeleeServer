package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Round {
 
    public List<Match> matches;
    public Round(List<Player> players) {
        matches = new ArrayList();
        for(int i = 0; i < players.size()/2; i++){
            matches.add(new Match(players.get(i), players.get(players.size()-1-i)));
        }
    }
    
    
}
