package chess;

public class King extends Pieces{
    public King(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WK;
        }else{
            pieceType = PieceType.BK;
        }
        
    }

    public boolean canMove(ReturnPlay board, String move) {
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(3, 5);

        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);
        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));

        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);

        if (startFile == endFile && startRank == endRank) {
            return false; // The king cannot move to the same position.
        }

        if (rankDifference <= 1 && fileDifference <= 1) {
            // Check if kings would be adjacent after the move.
            if (willKingsBeAdjacent(board, move)) {
                return false;
            }
            return true;
        }

        return false; // The move is invalid.
    }

    private boolean willKingsBeAdjacent(ReturnPlay board, String move) {
        // Extract the target positions from the move string.
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));
    
        // Initialize variables to store the positions of the kings.
        char whiteKingFile = ' '; // Assuming space is not a valid file
        int whiteKingRank = -1; // Assuming -1 is not a valid rank
        char blackKingFile = ' ';
        int blackKingRank = -1;
    
        // Find the positions of the kings.
        for (ReturnPiece piece : board.piecesOnBoard) {
            if (piece instanceof King) {
                String piecePosition = String.valueOf(piece.pieceFile) + piece.pieceRank;
                if (piece.isWhite()) {
                    whiteKingFile = piece.pieceFile;
                    whiteKingRank = piece.pieceRank;
                } else {
                    blackKingFile = piece.pieceFile;
                    blackKingRank = piece.pieceRank;
                }
            }
        }
    
        // Calculate the potential new positions of the kings after the move.
        char newWhiteKingFile = ' ';
        int newWhiteKingRank = -1;
        char newBlackKingFile = ' ';
        int newBlackKingRank = -1;
    
        if (whiteKingFile != ' ') {
            // Calculate new positions for the white king.
            newWhiteKingFile = calculateNewFile(startFile, endFile, whiteKingFile);
            newWhiteKingRank = calculateNewRank(startRank, endRank, whiteKingRank);
        }
    
        if (blackKingFile != ' ') {
            // Calculate new positions for the black king.
            newBlackKingFile = calculateNewFile(startFile, endFile, blackKingFile);
            newBlackKingRank = calculateNewRank(startRank, endRank, blackKingRank);
        }
    
        // Check if the kings would be adjacent after the move.
        if (areKingsAdjacent(newWhiteKingFile, newWhiteKingRank, newBlackKingFile, newBlackKingRank)) {
            return false;
        }
    
        return true;
    }
    private char calculateNewFile(char startFile, char endFile, char kingFile) {
        char newFile = kingFile;
    
        // Check if the king is moving horizontally (same rank).
        if (Character.getNumericValue(startFile) != Character.getNumericValue(endFile)) {
            // Determine the direction of the king's movement.
            if (Character.getNumericValue(endFile) > Character.getNumericValue(startFile)) {
                newFile++;
            } else {
                newFile--;
            }
        }
    
        return newFile;
    }

    private int calculateNewRank(int startRank, int endRank, int kingRank) {
        int newRank = kingRank;
    
        // Check if the king is moving vertically (same file).
        if (startRank != endRank) {
            // Determine the direction of the king's movement.
            if (endRank > startRank) {
                newRank++;
            } else {
                newRank--;
            }
        }
    
        return newRank;
    }

    private boolean areKingsAdjacent(char whiteKingFile, int whiteKingRank, char blackKingFile, int blackKingRank) {
        // Check if the kings are in adjacent squares either horizontally or vertically.
        boolean horizontallyAdjacent = Math.abs(whiteKingFile - blackKingFile) <= 1 && whiteKingRank == blackKingRank;
        boolean verticallyAdjacent = Math.abs(whiteKingRank - blackKingRank) <= 1 && whiteKingFile == blackKingFile;
    
        return horizontallyAdjacent || verticallyAdjacent;
    }

}




