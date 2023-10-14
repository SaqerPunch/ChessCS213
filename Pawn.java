package chess;

public class Pawn extends Pieces{

    public Pawn(boolean isWhite){
        super(isWhite);
    }

    public boolean canMove(Board board, String move){
        //move is b2 c3
        Coordinates moveInfo = new Coordinates(move);
        if(captured == true){
            return false;
        }else if(board.isOccupied(moveInfo) == true){
            if(board.takenBoard[][0].charAt(0) = 'b'){

            }
            return false; // placeholder. Occupied Spaces can be attacked
        }else if(isWhite == true){

            //first possible move (without attacking)
            if(moveInfo.startFile == moveInfo.endFile){//checks if moving in a straight line
                if(moveInfo.endRank == (moveInfo.startRank+1)){
                    return true;
                }else if(moveInfo.startRank ==2 && moveInfo.endRank == 4){
                    return true;
                }
            }
            
        }else if(isWhite == false){//its black
           if(moveInfo.startFile == moveInfo.endFile){//checks if moving in a straight line
                if(moveInfo.endRank == (moveInfo.startRank-1)){
                    return true;
                }else if(moveInfo.startRank ==7 && moveInfo.endRank == 5){
                    return true;
                }
            }
        }
        
        return false;
    }
}
