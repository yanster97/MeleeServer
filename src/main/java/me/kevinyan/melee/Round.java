package me.kevinyan.melee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class Round {
 
    public List<Match> matches;
    public Round(List<Match> matches) {
        this.matches = matches;
    }
    
    public static Round fromRound(Round prevRound) {
        List<Match> prevMatches = prevRound.matches;
        List<Match> currentMatches = new ArrayList();
        
        //to find total number of players in previous round
        int prevNumPlayers = prevMatches.size() * 2;
        for(Match m : prevMatches) {
            //multiply by 2 to find number of players in current round
            currentMatches.add(new Match(m.topBranchID, prevNumPlayers * 2));
            currentMatches.add(new Match(m.bottomBranchID, prevNumPlayers * 2));
        }
        return new Round(currentMatches);
    }
    
    public static Round getLastRound(){
        List<Match> lastMatch = new ArrayList();
        lastMatch.add(Match.getLastMatch());
        return new Round(lastMatch);
    }
    
    public static Round getFirstRound(int numPlayers, Round secondRound){
        List<Match> firstMatch = new ArrayList();
        int leftOver = numPlayers - secondRound.getNumPlayers();
        int cutOff = numPlayers - (leftOver * 2) + 1;
        for(Match m : secondRound.matches){
            if(m.topBranchID >= cutOff)
            {
                int bottomID = numPlayers + cutOff - m.topBranchID;
                firstMatch.add(Match.getMatchFromID(m.topBranchID, bottomID));
            }
            if(m.bottomBranchID >= cutOff)
            {
                int bottomID = numPlayers + cutOff - m.bottomBranchID;
                firstMatch.add(Match.getMatchFromID(m.bottomBranchID, bottomID));
            }
        }
        Round firstRound = new Round(firstMatch);
        return firstRound;
    }
    
    public int getNumPlayers(){
        return matches.size() * 2;
    }
            
}
    
    

