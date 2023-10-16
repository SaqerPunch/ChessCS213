package chess;

public class King extends Pieces {
    public King(boolean isWhite) {
        super(isWhite);

        if (isWhite) {
            pieceType = PieceType.WK;
        } else {
            pieceType = PieceType.BK;
        }
    }

    public boolean canMove(ReturnPlay board, String move) {
        // Extract the source and destination positions from the move string.
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Calculate the absolute differences in ranks and files.
        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);

        // The king can move one square in any direction.
        if (rankDifference <= 1 && fileDifference <= 1) {
            return true;
        }

        return false;
    }
}