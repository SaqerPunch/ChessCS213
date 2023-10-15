package chess;

public class Knight extends Pieces{
    public Knight(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WN;
        }else{
            pieceType = PieceType.BN;
        }
        
    }

    public boolean canMove(Board board, String move){
        return true;
    }
}
