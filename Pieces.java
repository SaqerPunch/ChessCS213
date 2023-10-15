package chess;
import java.util.*;

public abstract class Pieces {
    public boolean isWhite;
    public boolean captured;
    
    public int pieceRank;
    public ReturnPiece.PieceType pieceType;
    public ReturnPiece.PieceFile pieceFile;


    /**
     * Commented out so it doesnt break stuff
     * 
     * public Pieces(ReturnPiece.PieceType pieceType, ReturnPiece.PieceFile pieceFile, int pieceRank) { 
     *      this.pieceType = pieceType;
     *      this.pieceFile = pieceFile;
     *      this.pieceRank = pieceRank;
     * }
     * 
    */ 

    public getPieceRank() {
        return pieceRank;
    }

    public void setPieceRank(int pieceRank) {
        this.pieceRank = pieceRank;
    }

    public getPieceFile() {
        return pieceFile;
    }

    public setPieceFile(ReturnPiece.PieceFile pieceFile) {
        this.pieceFile = pieceFile;
    }

    public ReturnPiece.PieceType getPieceType() {
        return pieceType;
    }
    
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
    
    public abstract PieceType getType(); // Each piece class must implement this method

    public static Piece getPiece(Board board, char file, int rank) {
        return board.getPiece(file, rank);
    }

    public abstract boolean canMove(Board board, String move);
    
}
