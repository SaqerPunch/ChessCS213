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

    public boolean canMove(ReturnPlay board, String move){

        String startPosition = move.substring(0,2);
        String endPosition = move.substring(3,5);

        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));

        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);

        if (startFile == endFile && startRank == endRank) {
            return false;
        }

        if (rankDifference <= 1 && fileDifference <= 1) {
            return true;
        }
        if (isAdjacentToOpposingKing(board, move)) {
            return false;
        }
        return false;
    }
    private boolean isAdjacentToOpposingKing(ReturnPlay board, String move) {
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        boolean isWhiteKing = this.isWhite;

        for (ReturnPiece piece : board.piecesOnBoard) {
            if (piece instanceof King && piece.isWhite() != isWhiteKing) {
                int opposingKingFile = piece.getFile();
                int opposingKingRank = piece.getRank();

                int rankDifference = Math.abs(opposingKingRank - startRank);
                int fileDifference = Math.abs(opposingKingFile - startFile);

                if (rankDifference <= 1 && fileDifference <=1) {
                    return false;
                }
            }
        }

    return true;
    }
}
