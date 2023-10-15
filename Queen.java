package chess;

public class Queen extends Pieces{
    public Queen(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WQ;
        }else{
            pieceType = PieceType.BQ;
        }
        
    }

    public boolean canMove(ReturnPlay board, String move){
        // Determine starting and ending squares
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Check if valid along ranks or files
        int rankStep = Integer.compare(endRank, startRank);
        int fileStep = Integer.compare(endFile - 'a', startFile - 'a');

        if (rankStep == 0 && fileStep == 0) {
            // Check if queen actually even moves anywhere
            return false;
        }

        // Check for pins along queen's path
        char currentFile = (char) (startFile + fileStep);
        int currentRank = startRank + rankStep;

        while (currentFile != endFile || currentrank != endRank) {
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
        //Check if queen is capturing
        if(board.isOccupied(endFile, endRank)) {
            Pieces targetPiece = board.getPiece(endFile, endRank);
            if (targetPiece.isWhite() == this.isWhite()) {
                return false;
            }
        }
        if (endFile <'a' || endFile > 'h' || endRank < 1 || endRank > 8) {
            return false;
        }

        return true;
    }
}
