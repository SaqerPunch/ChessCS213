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

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */

		

		return null;
	}
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		/* FILL IN THIS METHOD */
		ReturnPlay beginning = new ReturnPlay();
		beginning.piecesOnBoard.clear();
		beginning.message = null;

		for(PieceFile x : PieceFile.values()){
			
			//adds white pawns to board
			ReturnPiece pawnWhite = new Pawn(true);
			pawnWhite.pieceFile = x;
			pawnWhite.pieceRank = 2;
			beginning.piecesOnBoard.add(pawnWhite);

			// adds black pawns to board
			ReturnPiece pawnBlack = new Pawn(false);
			pawnBlack.pieceFile = x;
			pawnBlack.pieceRank = 7;
			beginning.piecesOnBoard.add(pawnBlack);
		}
		
		//adds White Rook
		ReturnPiece whiteRook1 = new Rook(true);
		whiteRook1.pieceFile = PieceFile.a;
		whiteRook1.pieceRank = 1;
		beginning.piecesOnBoard.add(whiteRook1);

		ReturnPiece whiteRook2 = new Rook(true);
		whiteRook2.pieceFile = PieceFile.h;
		beginning.piecesOnBoard.add(whiteRook2);

		//adds Black Rook
		ReturnPiece blackRook1 = new Rook(false);
		blackRook1.pieceFile = PieceFile.a;
		blackRook1.pieceRank = 8;

		beginning.piecesOnBoard.add(blackRook1);

		ReturnPiece blackRook2 = new Rook(false);
		blackRook2.pieceFile = PieceFile.h;
		beginning.piecesOnBoard.add(blackRook2);


		//adds White Knight
		ReturnPiece whiteKnight1 = new Knight(true);
		whiteKnight1.pieceFile = PieceFile.b;
		whiteKnight1.pieceRank = 1;
		beginning.piecesOnBoard.add(whiteKnight1);

		ReturnPiece whiteKnight2 = new Knight(true);
		whiteKnight2.pieceFile = PieceFile.g;
		beginning.piecesOnBoard.add(whiteKnight2);

		//adds Black Knight
		ReturnPiece BlackKnight1 = new Knight(false);
		BlackKnight1.pieceFile = PieceFile.b;
		BlackKnight1.pieceRank = 8;
		beginning.piecesOnBoard.add(BlackKnight1);

		ReturnPiece BlackKnight2 = new Knight(false);
		BlackKnight2.pieceFile = PieceFile.g;
		beginning.piecesOnBoard.add(BlackKnight2);


		//adds White Bishop
		ReturnPiece WhiteBishop1 = new Bishop(true);
		WhiteBishop1.pieceFile = PieceFile.c;
		WhiteBishop1.pieceRank = 1;
		beginning.piecesOnBoard.add(WhiteBishop1);

		ReturnPiece WhiteBishop2 = new Bishop(true);
		WhiteBishop2.pieceFile = PieceFile.f;
		beginning.piecesOnBoard.add(WhiteBishop2);

		//adds Black Bishop
		ReturnPiece BlackBishop1 = new Bishop(false);
		BlackBishop1.pieceFile = PieceFile.c;
		BlackBishop1.pieceRank = 8;
		beginning.piecesOnBoard.add(BlackBishop1);

		ReturnPiece BlackBishop2 = new Bishop(false);
		BlackBishop2.pieceFile = PieceFile.f;
		beginning.piecesOnBoard.add(BlackBishop2);

		//adds White Queen
		ReturnPiece WhiteQueen = new Queen(true);
		WhiteQueen.pieceFile = PieceFile.d;
		WhiteQueen.pieceRank = 1;
		beginning.piecesOnBoard.add(WhiteQueen);

		//adds Black Queen
		ReturnPiece BlackQueen = new Queen(false);
		BlackQueen.pieceFile = PieceFile.d;
		BlackQueen.pieceRank = 8;
		beginning.piecesOnBoard.add(BlackQueen);

		//adds White King
		ReturnPiece WhiteKing = new King(true);
		WhiteKing.pieceFile = PieceFile.e;
		WhiteKing.pieceRank = 1;
		beginning.piecesOnBoard.add(WhiteKing);

		//adds Black King
		ReturnPiece BlacKKing = new King(false);
		BlacKKing.pieceFile = PieceFile.e;
		BlacKKing.pieceRank = 8;
		beginning.piecesOnBoard.add(BlacKKing);
		
	}
}
