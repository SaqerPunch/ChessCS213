package chess;

public class Rook extends Pieces{
    public Rook(boolean isWhite){
        super(isWhite);

        if(isWhite == true){
            pieceType = PieceType.WR;
        }else{
            pieceType = PieceType.BR;
        }
        
    }
    @Override
    public boolean canMove(Board board, String move){
        // 
    }
}
