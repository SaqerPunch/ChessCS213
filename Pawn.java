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
        
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(3, 5);
        
        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));
        if(captured == true){
            return false;
        }else if(isOccupied(board, move) == true){
            if(startRank+1 == endRank && (char)(startFile+1) == endFile && isWhite == true){
                return true;
            }else if(startRank-1 == endRank && (char)(startFile-1) == endFile && isWhite == false){
                return true;
            }else{
                return false;
            }
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
