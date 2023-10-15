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
    public boolean canMove(Board board, String move){
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

        // Check if the destination square is occupied
        if (board.isOccupied(endFile, endRank)) {
            Pieces targetPiece = board.getPiece(endFile, endRank);
            // Check color of target piece
            if (targetPiece.isWhite() != this.isWhite()) {
                return true;    // The knight can capture enemy piece
            } else {
                return false;  // Knight can not capture friendly piece or nothing
            }
        }

        // Ensure knight doesn't move off the board
        if (endFile < 'a' || endFile > 'h' || endRank < 1 || endRank > 8) {
            return false;
        }
        // Below we check for pins
        if (board.isOccupied(startFile, startRank)) {
            Pieces pinningPiece = board.getPiece(startFile, startRank);
            if (pinningPiece.isWhite() != this.isWhite()) {
                return true;    // The knight can capture the pinning piece
            } else {
                return false;   // Knight is pinned and can NOT move
            }
        }

        return true;    // Valid move
    }
}

