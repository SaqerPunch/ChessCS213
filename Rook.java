package chess;

public class Rook extends Pieces {
    public Rook(boolean isWhite) {
        super(isWhite);

        if (isWhite) {
            pieceType = PieceType.WR;
        } else {
            pieceType = PieceType.BR;
        }
    }

    @Override
    public boolean canMove(ReturnPlay board, String move) {
        // Determine the starting and ending squares
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(3, 5);
        
        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));

        // Check if the knight is trying to move to own square
        if (startFile == endFile && startRank == endRank) { // 
            return false;
        }

        if (startFile == endFile || startRank == endRank) {
            int rankStep = 0;
            int fileStep = 0;
            
            
                
            if (endRank > startRank) {
                rankStep = 1;
            }else if(endRank == startRank){
                rankStep = 0;
            }else {
                rankStep = -1;
            }

            if (endFile > startFile) {
                fileStep = 1;
            }else if(endFile == startFile){
                fileStep = 0;
            }else {
                fileStep = -1;
            }
            
            System.out.println("Rank Step is " + rankStep);
            System.out.println("File Step is " + fileStep);

            char currentFile = startFile;
            int currentRank = startRank;

            char nextSquareFile = (char)(startFile + fileStep);
            int nextSquareRank = startRank + rankStep;

            while (currentFile != endFile || currentRank != endRank) {
                String currentMove =""+currentFile+currentRank+" "+""+nextSquareFile+nextSquareRank;
                //System.out.println(currentMove);
                //System.out.println(isOccupied(board, currentMove));
                if (isOccupied(board, currentMove)) {
                    return false;  
                }
                currentFile = (char) (currentFile + fileStep);
                currentRank += rankStep;

                nextSquareFile = (char)(currentFile + fileStep);
                nextSquareRank = currentRank + rankStep;

            }
                
            if(currentFile == endFile && currentRank == endRank){
                System.out.println("REACHES HERE");
                String currentMove =""+currentFile+currentRank+" "+""+currentFile+currentRank;
                System.out.println(currentMove);
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
        }
           
            return true;
        }    
    }

