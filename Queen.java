package chess;

public class Queen extends Pieces{
    public boolean moved = false;

    public Queen(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, String move) {
        return false;
    }
}
