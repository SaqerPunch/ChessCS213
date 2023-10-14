package chess;

public class Board {
    String takenBoard[][];
    
    public Board(String board[][]){
        String takenBoard[][] = board;
    }

    public boolean isOccupied(Coordinates move){
        if (takenBoard[move.letterConverter(move.endFile)][move.endRank] != " " || takenBoard[move.letterConverter(move.endFile)][move.endRank] != "##"){
            return true;
        }else{
            return false;
        }   
    }

}
