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
    public boolean canMove(Board board, String move) {
        // Determine the starting and ending squares
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Check if the move is for castling (king moves two squares and rook jumps over)
        if (Math.abs(endRank - startRank) == 2 && startFile == endFile) {
            // Implement castling logic here
            return canCastle(board, move);
        } else if (startFile == endFile || startRank == endRank) {
            // Check if it's a valid rank or file move
            int rankStep = Integer.compare(endRank, startRank);
            int fileStep = Integer.compare(endFile - 'a', startFile - 'a');

            char currentFile = (char) (startFile + fileStep);
            int currentRank = startRank + rankStep;

            while (currentFile != endFile || currentRank != endRank) {
                // Check for pins along rooks past
                if (board.isOccupied(currentFile, currentRank)) {
                    Pieces pinningPiece = board.getPiece(currentFile, currentRank);
                    if (pinningPiece.isWhite() != this.isWhite()) {
                        return true;
                    } else {
                        return false;
                    }
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;
            }
            if (board.isOccupied(endFile, endRank)) {
                Pieces targetPiece = board.getPiece(endFile, endRank);
                if (targetPiece.isWhite() == this.isWhite()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private boolean canCastle(Board board, String move) {
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
}   