package chess;

public class Knight extends Pieces{
    public boolean moved = false;

    public Knight(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, String move) {
        return false;
    }
}
