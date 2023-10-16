package chess;

public class Rook extends Pieces {
    public Rook(boolean isWhite) {
        super(isWhite);

        if (isWhite) {
            pieceType = PieceType.WR;
        } else {
            pieceType = PieceType.BR;
        }
    }

    @Override
    public boolean canMove(ReturnPlay board, String move) {
        // Determine the starting and ending squares
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(3, 5);
        
        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));
        if (startFile == endFile || startRank == endRank) {
            return true;
        }
        return false;
        }
    }


    /* private boolean canCastle(Board board, String move) {
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Check if the king is in check (need to add this in chess.java still)
        if (board.isKingInCheck(this.isWhite) {
            return false;
        }
        int fileStep = Integer.compare(endFile - startFile, 0); // Determine if castling queen or king side

        if (fileStep == -2) {
            // Queen side castling
            char currentFile = (char) (startFile - 1);
            while (currentFile != 'b') {
                if board.isOccupied(currentFile, startRank) {
                    return false;   // Can't castle if there are pieces in the way
                }
                currentFile = (char) (currentFile - 1);
            }
            // Check if queenside rook in initial position
            if (board.getPiece('a', startRank) instanceof Rook && board.getPiece('a', startRank).isWhite() == this.isWhite) {
                return true;    // Can castle queenside
            }
        }
        // King side castling
        if (fileStep == 2) {
            char currentFile = (char) (startFile + 1);
            while (currentFile != 'h') {
                if (board.isOccupied(currentFile, startRank)) {
                    return false;   // Can't castle if there are pieces in the way
                }
                currentFile = (char) (currentFile + 1);
            }
            // Check if kingside rook in initial position
            if (board.getPiece('h', startRank) instanceof Rook && board.getPiece('h', startRank).isWhite() == this.isWhite) {
                return true;
            }
        }
        return false;
    }
}   */