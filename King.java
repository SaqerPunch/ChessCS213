package chess;

public class King extends Pieces{
    public boolean moved = false;

    public King(boolean white) {
        super(white);
    }

    public boolean canMove(Board board, String move) {
        return false;
    }
}
