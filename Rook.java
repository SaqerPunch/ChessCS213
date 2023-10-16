package chess;

public class Rook extends Pieces {
    private boolean hasMoved;
    public Rook(boolean isWhite) {
        super(isWhite);

        if (isWhite) {
            pieceType = PieceType.WR;
        } else {
            pieceType = PieceType.BR;
        }
        hasMoved = false;
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

        // Check if the knight is trying to move to own square
        if (startFile == endFile && startRank == endRank) { // 
            return false;
        }

        if (startFile == endFile || startRank == endRank) {
            int rankStep = 0;
            int fileStep = 0;
            
            
                
            if (endRank > startRank) {
                rankStep = 1;
            }else if(endRank == startRank){
                rankStep = 0;
            }else {
                rankStep = -1;
            }

            if (endFile > startFile) {
                fileStep = 1;
            }else if(endFile == startFile){
                fileStep = 0;
            }else {
                fileStep = -1;
            }

            char currentFile = startFile;
            int currentRank = startRank;

            char nextSquareFile = (char)(startFile + fileStep);
            int nextSquareRank = startRank + rankStep;

            while (currentFile != endFile || currentRank != endRank) {
                String currentMove =""+currentFile+currentRank+" "+""+nextSquareFile+nextSquareRank;
                if (isOccupied(board, currentMove)) {
                    return false;  
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;

                nextSquareFile = (char)(currentFile + fileStep);
                nextSquareRank = currentRank + rankStep;

            }
                
            if(currentFile == endFile && currentRank == endRank){
                String currentMove =""+currentFile+currentRank+" "+""+currentFile+currentRank;
                if(isOccupied(board, currentMove)== true){
                    if(spacePiece(board, currentMove).isWhite == true && isWhite == true){
                        return false;
                    }else if(spacePiece(board, currentMove).isWhite == false && isWhite == false){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return true;
                }
            }
        }
           
            return true;
        }
        
        public boolean hasMoved() {
            return hasMoved;
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