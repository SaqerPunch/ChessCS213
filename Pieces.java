package chess;

public abstract class Pieces extends ReturnPiece {
    public boolean isWhite;
    public boolean captured;

    public Pieces(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.isWhite();
    }
    
    public void setWhite(boolean white) {
        this.isWhite = white;
    }

    public boolean isCaptured() {
        return this.captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public abstract boolean canMove(ReturnPlay board, String move);

    public boolean isOccupied(ReturnPlay board, String move){

        for(ReturnPiece x: board.piecesOnBoard){
            String spaceFile = ""+x.pieceFile;
            int spaceRank = x.pieceRank;
            int endMoveRank = Character.getNumericValue(move.charAt(4));
            if(move.charAt(3) == spaceFile.charAt(0) && endMoveRank == spaceRank){
                return true;
            }

        }

        return false;
    }

    public Pieces spacePiece(ReturnPlay board, String move){
        
        for(ReturnPiece x: board.piecesOnBoard){
            String spaceFile = ""+x.pieceFile;
            int spaceRank = x.pieceRank;
            int endMoveRank = Character.getNumericValue(move.charAt(4));
            if(move.charAt(3) == spaceFile.charAt(0) && endMoveRank == spaceRank){
                return (Pieces)x;
            }

        }
        return null;
    }
    
}
