package chess;
import java.util.*;

public abstract class Pieces {
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
    
    public abstract PieceType getType(); // Each piece class must implement this method

    public static Piece getPiece(Board board, char file, int rank) {
        return board.getPiece(file, rank);
    }

    public abstract boolean canMove(Board board, String move);
    
}
