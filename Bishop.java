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

    @Override 
    public boolean canMove(ReturnPlay board, String move){
        // Check if desired move is on valid diagonal
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // Check if the knight is trying to move to own square
        if (startFile == endFile && startRank == endRank) { // 
            return false;
        }

        // Check if absolute value of ranks and files are equal 
        int rankDifference = Math.abs(endRank - startRank);
        int fileDifference = Math.abs(endFile - startFile);
        
        int rankStep = 0;
        int fileStep = 0;
        if (rankDifference == fileDifference) {
            
            if (endRank > startRank) {
                rankStep = 1;
            } else {
                rankStep = -1;
            }
        }   
        
        if (endFile > startFile) {
                fileStep = 1;
        } else {
                fileStep = -1;
            }

            char currentFile = startFile;
            int currentRank = startRank;

            char nextSquareFile = (char)(startFile + fileStep);
            int nextSquareRank = startRank + rankStep;

            while (currentFile != endFile && currentRank != endRank) {
                String currentMove =""+currentFile+currentRank+" "+""+nextSquareFile+nextSquareRank;
                if (isOccupied(board, currentMove)) {
                    return false;  
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;
            }

            if(currentFile == endFile && currentRank == endRank){
                String currentMove =""+currentFile+currentRank+" "+""+nextSquareFile+nextSquareRank;
                if(isOccupied(board, currentMove)== true){
                    if(spacePiece(board, currentMove).isWhite == true && isWhite == true){
                        return false;
                    }else if(spacePiece(board, currentMove).isWhite == false && isWhite == false){
                        return false;
                    }else{
                        return true;
                    }
                }else{
                    return true;
                }
            }
            
        return false;
    } 

}
