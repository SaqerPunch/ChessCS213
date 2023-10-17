package chess;

public class Pawn extends Pieces{
    public boolean enpassantable;
    public Pawn(boolean isWhite){
        super(isWhite);
        enpassantable = false;
        if(isWhite == true){
            pieceType = PieceType.WP;
        }else{
            pieceType = PieceType.BP;
        }


    }

    public boolean canMove(ReturnPlay board, String move){
        //move is b2 c3
        
        String startPosition = move.substring(0, 2);
        String endPosition = move.substring(3, 5);
        
        char startFile = startPosition.charAt(0);
        char endFile = endPosition.charAt(0);

        int startRank = Character.getNumericValue(startPosition.charAt(1));
        int endRank = Character.getNumericValue(endPosition.charAt(1));
        if(captured == true){
            return false;
        }else if(isOccupied(board, move) == true){
            if(startRank+1 == endRank && ((char)(startFile+1) == endFile || (char)(startFile-1) == endFile) && isWhite == true){
                if(spacePiece(board, move).isWhite == true){
                    return false;
                }else{
                    enpassantable = false;
                    return true;
                }
            }else if(startRank-1 == endRank && ((char)(startFile+1) == endFile || (char)(startFile-1) == endFile) && isWhite == false){
                if(spacePiece(board, move).isWhite == false){
                    return false;
                }else{
                    enpassantable = false;
                    return true;
                }
            }else{
                return false;
            }
        }else if(isWhite == true){

            //first possible move (without attacking)
            if( startFile == endFile){//checks if moving in a straight line
                if(startRank+1 == endRank){
                    enpassantable = false;
                    return true;
                }else if(startRank == 2 &&  endRank == 4){
                    enpassantable = true;
                    return true;
                }
            }
            
        }else if(isWhite == false){//its black
           if( startFile == endFile){//checks if moving in a straight line
                if(startRank-1 == endRank){
                    enpassantable = false;
                    return true;
                }else if(startRank == 7 &&  endRank == 5){
                    enpassantable = true;
                    return true;
                }
            }
        }

        return false;
    }

    public Pieces promote(ReturnPlay board, String move){
        Pieces p = null;
        if(isWhite == true){
            //String startPosition = move.substring(0, 2);
            String endPosition = move.substring(3, 5);
            
            //char startFile = startPosition.charAt(0);
            char endFile = endPosition.charAt(0);

           // int startRank = Character.getNumericValue(startPosition.charAt(1));
            int endRank = Character.getNumericValue(endPosition.charAt(1));
            if(canMove(board, move) == true){
                if(endRank == 8){
                    if(move.length() == 5){
                        p = new Queen(isWhite);
                        p.pieceFile = converter(endFile);
                        p.pieceRank = endRank;
                        p.pieceType = PieceType.WQ;
                        return p;
                    }else if(move.length() > 5){
                        if(move.charAt(6) == 'Q'){
                            p = new Queen(isWhite);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.WQ;
                            
                        }else if(move.charAt(6) == 'N'){
                            p = new Knight(isWhite);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.WN;
                            return p;
                        }else if(move.charAt(6) == 'R'){
                            p = new Rook(isWhite);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.WR;
                            return p;
                        }else if(move.charAt(6) == 'B'){
                            p = new Bishop(isWhite);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.WB;
                            return p;
                        }
                    }
                
                }
            }
            
        }else if(isWhite != true){
            //String startPosition = move.substring(0, 2);
            String endPosition = move.substring(3, 5);
            
            //char startFile = startPosition.charAt(0);
            char endFile = endPosition.charAt(0);

           // int startRank = Character.getNumericValue(startPosition.charAt(1));
            int endRank = Character.getNumericValue(endPosition.charAt(1));
            if(canMove(board, move) == true){
                if(endRank == 1){
                    if(move.length() == 5){
                        p = new Queen(isWhite = false);
                        p.pieceFile = converter(endFile);
                        p.pieceRank = endRank;
                        p.pieceType = PieceType.BQ;
                       return p;
                    }else if(move.length() > 5){
                        if(move.charAt(6) == 'Q'){
                            p = new Queen(isWhite = false);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.BQ;
                            return p;
                        }else if(move.charAt(6) == 'N'){
                            p = new Knight(isWhite = false);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.BN;
                           return p;
                        }else if(move.charAt(6) == 'R'){
                            p = new Rook(isWhite = false);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.BR;
                            return p;
                        }else if(move.charAt(6) == 'B'){
                            p = new Bishop(isWhite = false);
                            p.pieceFile = converter(endFile);
                            p.pieceRank = endRank;
                            p.pieceType = PieceType.BB;
                            return p;
                        }
                    }
                
                }
            }
        }
        
        return p;
    }
}
