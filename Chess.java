package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile; //Letter Part of Coordinate
	int pieceRank;  // 1..8. Number Part of Coordinate
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}

public class Chess {
	
	enum Player { white, black }
	public static ReturnPlay current;
	public static Player turn;
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {
		//e1 e2
		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		if(move.equals("resign") && turn == Player.white){
			current.message = ReturnPlay.Message.RESIGN_BLACK_WINS;
			return current;
		}else if(move.equals("resign") && turn == Player.black){
			current.message = ReturnPlay.Message.RESIGN_WHITE_WINS;
			return current;
		}else if(move.equals("reset")){
			start(); 
			return current;
		}else if(move.equals("quit")){
			return current = null;
		}
		
		Pieces p = null;

		for(ReturnPiece x: current.piecesOnBoard){
            String spaceFile = ""+x.pieceFile;
            int spaceRank = x.pieceRank;
			int moveRank = Character.getNumericValue(move.charAt(1));

            if(move.charAt(0) == spaceFile.charAt(0) && moveRank == spaceRank){
				p = (Pieces)x;
            }

        }
		if(p == null){
			current.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return current;
		}else if(turn == Player.white && p.isWhite != true){
			current.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return current;
		}else if(turn == Player.black && p.isWhite == true){
			current.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return current;
		}
		
		int endRank = Character.getNumericValue(move.charAt(4));
		char endFile = move.charAt(3); 
		
		//Promotes White Pawn
		if(p.pieceType == PieceType.WP && endRank == 8){
			if(p instanceof Pawn){
				Pieces j =((Pawn) p).promote(current, move);
				current = removePiece(current, move, j.spacePiece(current, move));
				current.piecesOnBoard.add(j);
				return current;
			}
		}

		//Promotes Black Pawn
		if(p.pieceType == PieceType.BP && endRank == 1){
			if(p instanceof Pawn){
				Pieces j =((Pawn) p).promote(current, move);
				current = removePiece(current, move, j.spacePiece(current, move));
				current.piecesOnBoard.add(j);
				return current;
			}
		}

		if (p.pieceType == PieceType.BK) {
			// Check if the move would put the black king next to the white king
			for (ReturnPiece piece : current.piecesOnBoard) {
				if (piece.pieceType == PieceType.WK) {
					String wKingPos = "" + piece.pieceFile + piece.pieceRank;
					String bKingPos = "" + move.charAt(3) + move.charAt(4);
					if (areKingsAdjacent(bKingPos, wKingPos)) {
						current.message = ReturnPlay.Message.ILLEGAL_MOVE;
						return current;
					}
				}
			}
		}
		
		if (p.pieceType == PieceType.WK) {
			// Check if the move would put the white king next to the black king
			for (ReturnPiece piece : current.piecesOnBoard) {
				if (piece.pieceType == PieceType.BK) {
					String bKingPos = "" + piece.pieceFile + piece.pieceRank;
					String wKingPos = "" + move.charAt(3) + move.charAt(4);
					if (areKingsAdjacent(wKingPos, bKingPos)) {
						current.message = ReturnPlay.Message.ILLEGAL_MOVE;
						return current;
					}
				}
			}
		}
		
	
		if(p.canMove(current, move) == true){
			
			if(p.isOccupied(current, move) == true){
				current = removePiece(current, move, p.spacePiece(current, move));
				p.pieceFile = converter(move.charAt(3));
				p.pieceRank = Character.getNumericValue(move.charAt(4));
				current.piecesOnBoard.add(p);
			}else{
				p.pieceFile = converter(move.charAt(3));
				p.pieceRank = Character.getNumericValue(move.charAt(4));
				current.piecesOnBoard.add(p);
			}

			if(turn == Player.white){
				turn = Player.black;
			}else if(turn == Player.black){
				turn = Player.white;
			}
			current.message = null;
			return current;
		}else if(p.pieceType == PieceType.WP && p.pieceRank == 5){

			ReturnPiece adjacent = null;
			for(ReturnPiece y: current.piecesOnBoard){
				String spaceFile = ""+y.pieceFile;
				int spaceRank = y.pieceRank;
				int moveRank = Character.getNumericValue(move.charAt(4))-1;//Checks piece under it

				if(move.charAt(3) == spaceFile.charAt(0) && moveRank == spaceRank){
					if(y instanceof Pawn){
						adjacent = (Pawn) y;
					}else{
						adjacent = y;
					}
				} 

        	}

			if(adjacent instanceof Pawn){
				if(((Pawn)adjacent).enpassantable == true){
					p.pieceFile = converter(move.charAt(3));
					p.pieceRank = Character.getNumericValue(move.charAt(4));
					current.piecesOnBoard.add(p);
					
					
					for(int i = 0; i < current.piecesOnBoard.size(); i++){
						if(current.piecesOnBoard.get(i).equals(adjacent)){
							current.piecesOnBoard.remove(current.piecesOnBoard.get(i));
						}
        			}
					if(turn == Player.white){
						turn = Player.black;
					}else if(turn == Player.black){
						turn = Player.white;
					}
					current.message = null;
					return current;
				}else{
					current.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
		}else if(p.pieceType == PieceType.BP && p.pieceRank == 4){
			ReturnPiece adjacent = null;
			for(ReturnPiece y: current.piecesOnBoard){
				String spaceFile = ""+y.pieceFile;
				int spaceRank = y.pieceRank;
				int moveRank = Character.getNumericValue(move.charAt(4))+1;//Checks piece under it

				if(move.charAt(3) == spaceFile.charAt(0) && moveRank == spaceRank){
					if(y instanceof Pawn){
						adjacent = (Pawn) y;
					}else{
						adjacent = y;
					}
				} 

        	}

			if(adjacent instanceof Pawn){
				if(((Pawn)adjacent).enpassantable == true){
					p.pieceFile = converter(move.charAt(3));
					p.pieceRank = Character.getNumericValue(move.charAt(4));
					current.piecesOnBoard.add(p);
					
					
					for(int i = 0; i < current.piecesOnBoard.size(); i++){
						if(current.piecesOnBoard.get(i).equals(adjacent)){
							current.piecesOnBoard.remove(current.piecesOnBoard.get(i));
						}
        			}
					if(turn == Player.white){
						turn = Player.black;
					}else if(turn == Player.black){
						turn = Player.white;
					}
					current.message = null;
					return current;
				}else{
					current.message = ReturnPlay.Message.ILLEGAL_MOVE;
				}
			}
		}else{
			current.message = ReturnPlay.Message.ILLEGAL_MOVE;
		}

		return current;
	}

	private static boolean areKingsAdjacent(String position1, String position2) {
		char file1 = position1.charAt(0);
		char rank1 = position1.charAt(1);
		char file2 = position2.charAt(0);
		char rank2 = position2.charAt(1);
	
		return Math.abs(file1 - file2) <= 1 && Math.abs(rank1 - rank2) <= 1;
	}

	public static PieceFile converter(char x){

		if(x == 'a'){
			return PieceFile.a;
		}else if(x == 'b'){
			return PieceFile.b;
		}else if(x == 'c'){
			return PieceFile.c;
		}else if(x == 'd'){
			return PieceFile.d;
		}else if(x == 'e'){
			return PieceFile.e;
		}else if(x == 'f'){
			return PieceFile.f;
		}else if(x == 'g'){
			return PieceFile.g;
		}else if(x == 'h'){
			return PieceFile.h;
		}else {
			return null;
		}

	}
	
	public static ReturnPlay removePiece(ReturnPlay board, String move, ReturnPiece x){
		ReturnPlay temp = board;
		for(int i = 0; i < board.piecesOnBoard.size(); i++){
			if(current.piecesOnBoard.get(i).equals(x)){
				current.piecesOnBoard.remove(board.piecesOnBoard.get(i));
			}
		}
		return temp;
	}

	public static Pieces findPiece(ReturnPlay board, String move){

		for(ReturnPiece x: board.piecesOnBoard){
            String spaceFile = ""+x.pieceFile;
            int spaceRank = x.pieceRank;

            if(move.charAt(0) == spaceFile.charAt(0) && move.charAt(1) == spaceRank){
               return (Pieces)x;
            }

        }
		return null;

	}

	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		current = new ReturnPlay();
		current.piecesOnBoard = new ArrayList<ReturnPiece>();
		current.message = null;
		turn = Player.white;

		for(PieceFile x : PieceFile.values()){
			//adds white pawns to board
			ReturnPiece pawnWhite = new Pawn(true);
			pawnWhite.pieceFile = x;
			pawnWhite.pieceRank = 2;
			current.piecesOnBoard.add(pawnWhite);

			// adds black pawns to board
			ReturnPiece pawnBlack = new Pawn(false);
			pawnBlack.pieceFile = x;
			pawnBlack.pieceRank = 7;
			current.piecesOnBoard.add(pawnBlack);
		}
		
		//adds White Rook
		ReturnPiece whiteRook1 = new Rook(true);
		whiteRook1.pieceFile = PieceFile.a;
		whiteRook1.pieceRank = 1;
		current.piecesOnBoard.add(whiteRook1);

		ReturnPiece whiteRook2 = new Rook(true);
		whiteRook2.pieceFile = PieceFile.h;
		whiteRook2.pieceRank = 1;
		current.piecesOnBoard.add(whiteRook2);

		//adds Black Rook
		ReturnPiece blackRook1 = new Rook(false);
		blackRook1.pieceFile = PieceFile.a;
		blackRook1.pieceRank = 8;

		current.piecesOnBoard.add(blackRook1);

		ReturnPiece blackRook2 = new Rook(false);
		blackRook2.pieceFile = PieceFile.h;
		blackRook2.pieceRank = 8;
		current.piecesOnBoard.add(blackRook2);


		//adds White Knight
		ReturnPiece whiteKnight1 = new Knight(true);
		whiteKnight1.pieceFile = PieceFile.b;
		whiteKnight1.pieceRank = 1;
		current.piecesOnBoard.add(whiteKnight1);

		ReturnPiece whiteKnight2 = new Knight(true);
		whiteKnight2.pieceFile = PieceFile.g;
		whiteKnight2.pieceRank = 1;
		current.piecesOnBoard.add(whiteKnight2);

		//adds Black Knight
		ReturnPiece BlackKnight1 = new Knight(false);
		BlackKnight1.pieceFile = PieceFile.b;
		BlackKnight1.pieceRank = 8;
		current.piecesOnBoard.add(BlackKnight1);

		ReturnPiece BlackKnight2 = new Knight(false);
		BlackKnight2.pieceFile = PieceFile.g;
		BlackKnight2.pieceRank = 8;
		current.piecesOnBoard.add(BlackKnight2);


		//adds White Bishop
		ReturnPiece WhiteBishop1 = new Bishop(true);
		WhiteBishop1.pieceFile = PieceFile.c;
		WhiteBishop1.pieceRank = 1;
		current.piecesOnBoard.add(WhiteBishop1);

		ReturnPiece WhiteBishop2 = new Bishop(true);
		WhiteBishop2.pieceFile = PieceFile.f;
		WhiteBishop2.pieceRank = 1;
		current.piecesOnBoard.add(WhiteBishop2);

		//adds Black Bishop
		ReturnPiece BlackBishop1 = new Bishop(false);
		BlackBishop1.pieceFile = PieceFile.c;
		BlackBishop1.pieceRank = 8;
		current.piecesOnBoard.add(BlackBishop1);

		ReturnPiece BlackBishop2 = new Bishop(false);
		BlackBishop2.pieceFile = PieceFile.f;
		BlackBishop2.pieceRank = 8;
		current.piecesOnBoard.add(BlackBishop2);

		//adds White Queen
		ReturnPiece WhiteQueen = new Queen(true);
		WhiteQueen.pieceFile = PieceFile.d;
		WhiteQueen.pieceRank = 1;
		current.piecesOnBoard.add(WhiteQueen);

		//adds Black Queen
		ReturnPiece BlackQueen = new Queen(false);
		BlackQueen.pieceFile = PieceFile.d;
		BlackQueen.pieceRank = 8;
		current.piecesOnBoard.add(BlackQueen);

		//adds White King
		ReturnPiece WhiteKing = new King(true);
		WhiteKing.pieceFile = PieceFile.e;
		WhiteKing.pieceRank = 1;
		current.piecesOnBoard.add(WhiteKing);

		//adds Black King
		ReturnPiece BlacKKing = new King(false);
		BlacKKing.pieceFile = PieceFile.e;
		BlacKKing.pieceRank = 8;
		current.piecesOnBoard.add(BlacKKing);
		
	}
}
