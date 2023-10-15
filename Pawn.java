package chess;

public class Pawn extends Pieces{

    public Pawn(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WP;
        }else{
            pieceType = PieceType.BP;
        }

    }

    public boolean canMove(ReturnPlay board, String move){
        //move is b2 c3
        
        String startPosition = move.substring(0, 1);
        String endPosition = move.substring(3, 4);

        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = startPosition.charAt(1);
        int endRank = endPosition.charAt(1);

        if(captured == true){
            return false;
        }else if(isOccupied(board, move) == true){
            return false; // placeholder. Occupied Spaces can be attacked
        }else if(isWhite == true){

            //first possible move (without attacking)
            if( startFile == endFile){//checks if moving in a straight line
                if(startRank+1 == endRank){
                    return true;
                }else if(startRank == 2 &&  endRank == 4){
                    return true;
                }
            }
            
        }else if(isWhite == false){//its black
           if( startFile == endFile){//checks if moving in a straight line
                if(startRank-1 == endRank){
                    return true;
                }else if(startRank == 7 &&  endRank == 5){
                    return true;
                }
            }
        }

        return false;
    }
}
