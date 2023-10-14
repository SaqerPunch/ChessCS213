package chess;

import java.util.ArrayList;

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
		ReturnPlay result = new ReturnPlay();		// Make new ReturnPlay object storing result

		Coordinates coordinates = new Coordinates(move);	// Make new Coordinates object parsing move

		char startFile = coordinates.letterConverter(coordinates.getStartFile());
		int startRank = coordinates.getStartRank();
		char endFile = coordinates.letterConverter(coordinates.getEndFile());
		int endRank = coordinates.getEndRank();

		// Check if the source square is empty
		Piece sourcePiece = board.getPiece(startFile, startRank);
		if (sourcePiece == null) {
			result.message = ReturnPlay.Message.ILLEGAL_MOVE;
			return result;
		}

		PieceType pieceType = sourcePiece.getType();
		Piece piece = null;

		switch (piecetype) {
			case PAWN:
			piece = new Pawn(board, startFile, startRank);
			break;
			case KNIGHT:
			piece = new Knight(board, startFile, startRank);
			break;
			case BISHOP:
			piece = new Bishop(board, startFile, startRank);
			break;
			case ROOK:
			piece = new Rook(board, startFile, startRank);
			break;
			case QUEEN:
			piece = new Queen(board, startFile, startRank);
			break;
			case KING:
			piece = new King(board, startFile, startRank);
			break;
		}

		/* FILL IN THIS METHOD */			// Use our logic from individual piece classes to check legal moves
		
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
			ReturnPiece pawnWhite = new ReturnPiece();
			pawnWhite.pieceFile = x;
			pawnWhite.pieceRank = 2;
			pawnWhite.pieceType = PieceType.WP;
			beginning.piecesOnBoard.add(pawnWhite);

			// adds black pawns to board
			ReturnPiece pawnBlack = new ReturnPiece();
			pawnBlack.pieceFile = x;
			pawnBlack.pieceRank = 7;
			pawnBlack.pieceType = PieceType.BP;
			beginning.piecesOnBoard.add(pawnBlack);
		}
		
		//adds White Rook
		ReturnPiece whiteRook = new ReturnPiece();
		whiteRook.pieceFile = PieceFile.a;
		whiteRook.pieceRank = 1;
		whiteRook.pieceType = PieceType.WR;
		beginning.piecesOnBoard.add(whiteRook);

		whiteRook.pieceFile = PieceFile.h;
		beginning.piecesOnBoard.add(whiteRook);

		//adds Black Rook
		ReturnPiece blackRook = new ReturnPiece();
		blackRook.pieceFile = PieceFile.a;
		blackRook.pieceRank = 8;
		blackRook.pieceType = PieceType.BR;
		beginning.piecesOnBoard.add(blackRook);

		blackRook.pieceFile = PieceFile.h;
		beginning.piecesOnBoard.add(blackRook);


		//adds White Knight
		ReturnPiece whiteKnight = new ReturnPiece();
		whiteKnight.pieceFile = PieceFile.b;
		whiteKnight.pieceRank = 1;
		whiteKnight.pieceType = PieceType.WK;
		beginning.piecesOnBoard.add(whiteKnight);

		whiteKnight.pieceFile = PieceFile.g;
		beginning.piecesOnBoard.add(whiteKnight);

		//adds Black Knight
		ReturnPiece BlackKnight = new ReturnPiece();
		BlackKnight.pieceFile = PieceFile.b;
		BlackKnight.pieceRank = 8;
		BlackKnight.pieceType = PieceType.BK;
		beginning.piecesOnBoard.add(BlackKnight);

		BlackKnight.pieceFile = PieceFile.g;
		beginning.piecesOnBoard.add(BlackKnight);


		//adds White Bishop
		ReturnPiece WhiteBishop = new ReturnPiece();
		WhiteBishop.pieceFile = PieceFile.c;
		WhiteBishop.pieceRank = 1;
		WhiteBishop.pieceType = PieceType.WB;
		beginning.piecesOnBoard.add(WhiteBishop);

		WhiteBishop.pieceFile = PieceFile.f;
		beginning.piecesOnBoard.add(WhiteBishop);

		//adds Black Bishop
		ReturnPiece BlackBishop = new ReturnPiece();
		BlackBishop.pieceFile = PieceFile.c;
		BlackBishop.pieceRank = 8;
		BlackBishop.pieceType = PieceType.BB;
		beginning.piecesOnBoard.add(BlackBishop);

		BlackBishop.pieceFile = PieceFile.f;
		beginning.piecesOnBoard.add(BlackBishop);

		//adds White Queen
		ReturnPiece WhiteQueen = new ReturnPiece();
		WhiteQueen.pieceFile = PieceFile.d;
		WhiteQueen.pieceRank = 1;
		WhiteQueen.pieceType = PieceType.WQ;
		beginning.piecesOnBoard.add(WhiteQueen);

		//adds Black Queen
		ReturnPiece BlackQueen = new ReturnPiece();
		BlackQueen.pieceFile = PieceFile.d;
		BlackQueen.pieceRank = 8;
		BlackQueen.pieceType = PieceType.BQ;
		beginning.piecesOnBoard.add(BlackQueen);

		//adds White King
		ReturnPiece WhiteKing = new ReturnPiece();
		WhiteKing.pieceFile = PieceFile.e;
		WhiteKing.pieceRank = 1;
		WhiteKing.pieceType = PieceType.WK;
		beginning.piecesOnBoard.add(WhiteKing);

		//adds Black King
		ReturnPiece BlacKKing = new ReturnPiece();
		BlacKKing.pieceFile = PieceFile.e;
		BlacKKing.pieceRank = 8;
		BlacKKing.pieceType = PieceType.BK;
		beginning.piecesOnBoard.add(BlacKKing);
		
	}
}
