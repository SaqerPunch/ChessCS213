package chess;

public class Board {

    Coordinates[][] spaces;

    public Board(){
        newBlankBoard();
        setStarting();
    }

    public void newBlankBoard(){
        char letters[] = {'h','g','f','e' ,'d','c','b','a'};
        this.spaces = new Coordinates[8][8];

        for(int x = 0; x < letters.length; x++){
            for(int y = 0; y < spaces.length; y++){
                spaces[x][y] = new Coordinates(letters[x], y, null);
            }
        }

    }

    public void setStarting(){

        for(int i = 0; i < 8; i++){
            spaces[1][i].spotPiece = new Pawn(false); // set black pawns
            spaces[6][i].spotPiece = new Pawn(true); // set white pawns
        }

        //Set Rooks
        spaces[0][0].spotPiece = new Rook(false);
        spaces[0][7].spotPiece = new Rook(false);
        spaces[7][0].spotPiece = new Rook(true);
        spaces[7][7].spotPiece = new Rook(true);

        // Set Knights
        spaces[0][1].spotPiece = new Knight(false);
        spaces[0][6].spotPiece = new Knight(false);
        spaces[7][1].spotPiece = new Knight(true);
        spaces[7][6].spotPiece = new Knight(true);

        //Set Bishops
        spaces[0][2].spotPiece = new Bishop(false);
        spaces[0][5].spotPiece = new Bishop(false);
        spaces[7][2].spotPiece = new Bishop(true);
        spaces[7][5].spotPiece = new Bishop(true);

        //Set Queens
        spaces[0][3].spotPiece = new Queen(false);
        spaces[7][3].spotPiece = new Queen(true);

        //Set Kings
        spaces[0][4].spotPiece = new King(false);
        spaces[7][4].spotPiece = new King(true);

    }

    /*
    String takenBoard[][];
    
    public Board(String board[][]){
        String takenBoard[][] = board;
    }

    public boolean isOccupied(Coordinates move){
        if (takenBoard[move.letterConverter(move.endFile)][move.endRank] != " " || takenBoard[move.letterConverter(move.endFile)][move.endRank] != "##"){
            return true;
        }else{
            return false;
        }   
    }
    */

}
