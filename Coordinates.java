package chess;

public class Coordinates {
        public Pieces spotPiece;
        char currentx;
        int currenty;
        
        public Coordinates(char x, int y, Pieces piece){
            this.currentx = x;
            this.currenty = y;
            this.spotPiece = piece;
        }
        
        /* 
        String start;
        String end;
        char startFile;
        int startRank;
        char endFile;
        int endRank;
    public Coordinates(String move){
        setValues(move);
    }

    private void setValues(String move){
        //Move Format "e2 e3"
        this.start = move.substring(0, 1);
        this.end = move.substring(2, 3);

        this.startFile = start.charAt(0);
        this.endFile = end.charAt(0);

        this.startRank = start.charAt(1);
        this.endRank = end.charAt(1);
    }
    */
    public int letterConverter(char x){

        if(x == 'a'){
            return 0;
        }else if(x == 'b'){
            return 1;
        }else if(x == 'c'){
            return 2;
        }else if(x == 'd'){
            return 3;
        }else if(x == 'e'){
            return 4;
        }else if(x == 'f'){
            return 5;
        }else if(x == 'g'){
            return 6;
        }else if(x == 'h'){
            return 7;
        }else{
            return -1;
        }

    }
}
