package me.kevinyan.melee;

import java.util.List;

/**
 *
 * @author kevin
 */
public class Match {

    public int topBranchID, bottomBranchID;
    public Player topPlayer, bottomPlayer;

    public Match(int branchID, int numPlayers) {
        this.topBranchID = branchID;
        this.bottomBranchID = numPlayers - branchID + 1;
    }
    
    private Match(){
        
    }
    
    public static Match getMatchFromID(int topID, int bottomID){
        Match toReturn = new Match();
        toReturn.topBranchID = topID;
        toReturn.bottomBranchID = bottomID;
        return toReturn;
    }
    
    public static Match getLastMatch(){
        return new Match(1,2);
    }

    public void addPlayers(List<Player> players, int cutoff) {
        if(topBranchID <= cutoff) {
            topPlayer = players.get(topBranchID);
        }
        if(bottomBranchID <= cutoff) {
            bottomPlayer = players.get(bottomBranchID);
        }
    }

}
