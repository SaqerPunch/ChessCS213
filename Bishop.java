package chess;

public class Bishop extends Pieces{
    public Bishop(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WB;
        }else{
            pieceType = PieceType.BB;
        }
        
    }

    public boolean canMove(ReturnPlay board, String move){
        return true;
    }
}
