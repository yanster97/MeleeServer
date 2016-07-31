package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Admin {
    
    
    public String name;
    public List<Tournament> tournaments;
    
    public Admin(String name) {
        this.name = name;
        tournaments = new ArrayList();
    }
    
    public void addTournament(Tournament t) {
        tournaments.add(t);
    }
    
}
