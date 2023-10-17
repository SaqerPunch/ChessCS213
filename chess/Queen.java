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
        char startFile = move.charAt(0);
        int startRank = Character.getNumericValue(move.charAt(1));
        char endFile = move.charAt(3);
        int endRank = Character.getNumericValue(move.charAt(4));

        // CHeck if queen is trying to move to own square
        if (startFile == endFile && startRank == endRank) {
            return false;
        }

        int rankDifference = Math.abs(endRank - startRank); 
        int fileDifference = Math.abs(endFile - startFile);

        if (rankDifference == fileDifference) {
            int rankStep = (endRank > startRank) ? 1 : -1;
            int fileStep = (endFile > startFile) ? 1 : -1;

            char currentFile = startFile;
            int currentRank = startRank;

            char nextSquareFile = (char) (startFile + fileStep);
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
        if (startFile == endFile || startRank == endRank) {
            int fileStep = (startFile == endFile) ? 0 : (startFile < endFile) ? 1 : -1;
            int rankStep = (startRank == endRank) ? 0 : (startRank < endRank) ? 1 : -1;

            char currentFile = (char) (startFile + fileStep);
            int currentRank = startRank + rankStep;

            char nextSquareFile = (char)(startFile + fileStep);
            int nextSquareRank = startRank + rankStep;

            while (currentFile != endFile || currentRank != endRank) {
                String currentMove =""+currentFile+currentRank+" "+""+nextSquareFile+nextSquareRank;
                if (isOccupied(board, currentMove)) {
                    return false;  
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;

                nextSquareFile = (char)(currentFile + fileStep);
                nextSquareRank = currentRank + rankStep;

            }
                
            if(currentFile == endFile && currentRank == endRank){
                String currentMove =""+currentFile+currentRank+" "+""+currentFile+currentRank;
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

        // If the move is neither a bishop or a rook, invalid
        return false;
    }
        

    }