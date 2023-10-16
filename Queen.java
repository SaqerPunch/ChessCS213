package chess;

public class Queen extends Pieces{
    public Queen(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WQ;
        }else{
            pieceType = PieceType.BQ;
        }
        
    }

    public boolean canMove(ReturnPlay board, String move){
        return true;
    }
}
