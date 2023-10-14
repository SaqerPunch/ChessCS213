package chess;

public class Bishop extends Pieces{
    public boolean moved = false;

    public Bishop(boolean white) {
        super(white);
    }
    public boolean canMove(Board board, String move) {
        return false;
    }
}
