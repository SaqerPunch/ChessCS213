package chess;

public class Knight extends Pieces{
    public Knight(boolean isWhite){
        super(isWhite);
        // Set piece type based on color
        if(isWhite == true){
            pieceType = PieceType.WN;
        }else{
            pieceType = PieceType.BN;
        }
        
    }
    @Override
    public boolean canMove(ReturnPlay board, String move){
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);

        // Check if the knight is trying to move to own square
        if (startFile == endFile && startRank == endRank) {
            return false;
        }

        // Check if move is L-shaped(2 squares one way, 1 square another)
        if (!((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2))) {
            return false;
        }


        // Ensure knight doesn't move off the board
        if (endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8) {
            return false;
        }

        if(isOccupied(board, move)== true){
            if(spacePiece(board, move).isWhite == true && isWhite == true){
                return false;
            }else if(spacePiece(board, move).isWhite == false && isWhite == false){
                return false;
            }else{
                return true;
            }
        }
        
        return true;    // Valid move
    }
}