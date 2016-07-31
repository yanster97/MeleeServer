package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Tournament {
      
    public String name;
    public List<Player> players;
    public Bracket bracket;
    
    public Tournament(String name) {
        this.name = name;
        players = new ArrayList();
    }
    
    public void addPlayer(Player p) {
        players.add(p);
    }
    
    public void generateBracket() {
        bracket = new Bracket(players);
    }
}
