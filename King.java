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

    public boolean canMove(Board board, String move){
        return true;
    }
}
