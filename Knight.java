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

        // Check if the knight is trying to move to its own square
        if (startFile == endFile && startRank == endRank) {
            return false;
        }
        
        // Check if move is L-shaped (2 squuares one way, 1 the other)
        if (!((rankDifference == 2 && fileDifference == 1) || (rankDifference == 1 && fileDifference == 2))) {
            return false;
        }

        // Check the direction of the move
        int rankStep;
        if (endRank > startRank) {
            rankStep = 1;
        } else {
            rankStep = -1;
        }
        int fileStep;
        if(endFile > startFile){
            fileStep = 1;
        }else{
            fileStep = -1;
        }

        char currentFile = (char) (startFile + fileStep);
        int currentRank = startRank + rankStep;

        // Check the move finishes on destination square
        if (currentFile == endFile && currentRank == endRank) {
            return true;    // Valid move
        }

        // Check if the destination square is occupied by a piece of the same color
        if (board.isOccupied(endFile, endRank) && board.getPiece(endFile, endRank).isWhite() == this.isWhite()) {
            return false;
        }

        // Ensure knight doesn't move off the board
        if (currentFile < 'a' || currentFile > 'h' || currentRank < 1 || currentRank > 8) {
            return false;
        }

        return true;    // Valid move
    }
}

