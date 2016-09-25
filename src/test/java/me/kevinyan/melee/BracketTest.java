/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author kevin
 */
public class BracketTest {

    public BracketTest() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBracket() {
        List<Player> players = new ArrayList();
        for(int i = 0; i < 5; i++){
            players.add(new Player());
        }
        Bracket testBracket = new Bracket(players);
        assertTrue(testBracket.rounds.size() == 3);
    }
}
