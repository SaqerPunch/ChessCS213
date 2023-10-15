package chess;

public class Bishop extends Pieces{
    public Bishop(boolean isWhite){
        super(isWhite); 

        if(isWhite == true){
            pieceType = PieceType.WB;
        }else{
            pieceType = PieceType.BB;
        }
        
    }
    @Override 
    public boolean canMove(Board board, String move){
        // Check if desired move is on valid diagonal
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Check if absolute value of ranks and files are equal 
        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);

        if (rankDifference == fileDifference) {
            int rankStep;
            if (endRank > startRank) {
                rankStep = 1;
            } else {
                rankStep = -1;
            }
        }   int fileStep;
            if (endFile > startFile) {
                fileStep = 1;
            } else {
                fileStep = -1;
            }

            char currentFile = (char) (startFile + fileStep);
            int currentRank = startRank + rankStep;

            while (currentFile != endFile && currentRank != endRank) {
                if (board.isOccupied(currentFile, currentRank)) {
                    // Below we're checking for pins
                    if (board.isOccupied(currentFile, currentRank)) {
                        Pieces pinningPiece = board.getPiece(currentFile, currentRank);
                        if (pinningPiece.isWhite() != this.isWhite()) {
                            return true;    // The bishop can capture the pinning piece
                        } else {
                            return false;  // The bishop is pinned and can NOT move
                        }
                    }
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;
            }
            
            if (board.isOccupied(endFile, endRank)) {
                if (board.getPiece(endFile, endRank).isWhite() == this.isWhite()) {
                    return false;
                }
            }
            return true;
    }
    return false;
}
