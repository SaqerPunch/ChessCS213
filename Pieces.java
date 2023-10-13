package chess;

public abstract class Pieces {
    private boolean white;
    private boolean captured;

    public Pieces(boolean white) {
        this.setWhite(white);
}

    public boolean isWhite() {
        return this.white();
    }
    
    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isCaptured() {
        return this.captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public abstract boolean canMove(Board board, Coordinates start, Coordinates end);
}
