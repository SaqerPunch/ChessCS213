package chess;

import java.util.ArrayList;

public class King extends Pieces {
    private boolean canCastle;
    private boolean hasMoved;

    public King(boolean isWhite) {
        super(isWhite);

        if (isWhite) {
            pieceType = PieceType.WK;
        } else {
            pieceType = PieceType.BK;
        }
        canCastle = true;
        hasMoved = false;
    }

    public boolean canMove(ReturnPlay board, String move) {
        // Check if it's a castling move
        if (isCastlingMove(board, move)) {
            if (canCastle && isCastlingValid(board, move)) {
                performCastling(board, move);
                return true;
            } else {
                return false;
            }
        }
    
        // Add a check for regular moves (non-castling)
        if (isRegularMove(move)) {
            // Extract the source and destination positions from the move string.
            char startFile = move.charAt(0);
            int startRank = Character.getNumericValue(move.charAt(1));
            char endFile = move.charAt(3);
            int endRank = Character.getNumericValue(move.charAt(4));
    
            // Calculate the absolute differences in ranks and files.
            int rankDifference = Math.abs(endRank - startRank);
            int fileDifference = Math.abs(endFile - startFile);
    
            // The king can move one square in any direction.
            if (rankDifference <= 1 && fileDifference <= 1) {
                if (isOccupied(board, move)) {
                    if (spacePiece(board, move).isWhite == isWhite) {
                        return false;
                    }
                    return true;
                }
                
                // Check if the move places the king adjacent to the opponent's king
                String kingPos = isWhite ? "WK" : "BK";
                String opponentKingPos = isWhite ? "BK" : "WK";
                if (move.contains(kingPos) && move.contains(opponentKingPos)) {
                    return false;
                }
                
                hasMoved = true;
                return true;
            }
        }
    
        return false;
    }
    
    private boolean isRegularMove(String move) {
        // Regular moves have the format "a1 b2," check if the move string fits this format.
        return move.matches("^[a-h][1-8] [a-h][1-8]$");
    }

    private boolean isCastlingMove(ReturnPlay board, String move) {
    // Castling moves for white:
    // "e1 g1" for kingside castling
    // "e1 c1" for queenside castling
    if (isWhite && (move.equals("e1 g1") || move.equals("e1 c1"))) {
        // Check if king has moved
        if (!hasMoved) {
            if (move.equals("e1 g1")) {
                // Kingside castle
                // Check that f1 and g1 are empty
                String fSquare = "f1";
                String gSquare = "g1";
                if (!isOccupied(board, fSquare) && !isOccupied(board, gSquare)) {
                    // TODO: Check for checks making sure the king is not in check
                    return true;
                }
            } else if (move.equals("e1 c1")) {
                // Queenside castle
                // Check that b1, c1, and d1 are empty
                String bSquare = "b1";
                String cSquare = "c1";
                String dSquare = "d1";
                if (!isOccupied(board, bSquare) && !isOccupied(board, cSquare) && !isOccupied(board, dSquare)) {
                    // TODO: Check for checks making sure the king is not in check
                    return true;
                }
            }
        }
    }
    return false;
}

    private boolean isCastlingValid(ReturnPlay board, String move) {
        if (move.equals("e1 g1") || move.equals("e8 g8")) {
            // Kingside castling
            Pieces king = spacePiece(board, move.substring(0, 2)); // Get the king
            Pieces rook = spacePiece(board, isWhite ? "h1" : "h8"); // Get the rook (for white)
            String fSquare = isWhite ? "f1" : "f8";
            String gSquare = isWhite ? "g1" : "g8";

            // Check if there is no piece between the king and the rook, and they haven't moved
            if (rook != null && king != null && !king.hasMoved() && !rook.hasMoved()) {
                if (!isOccupied(board, fSquare) && !isOccupied(board, gSquare)) {
                    // TODO: Check for checks or attacks on the squares
                    if (!isSquareUnderAttack(board, move.substring(0, 2)) &&
                        !isSquareUnderAttack(board, fSquare) && !isSquareUnderAttack(board, gSquare)) {
                        return true;
                    }
                }
            }
        } else if (move.equals("e1 c1") || move.equals("e8 c8")) {
            // Queenside castling
            Pieces king = spacePiece(board, move.substring(0, 2)); // Get the king
            Pieces rook = spacePiece(board, isWhite ? "a1" : "a8"); // Get the rook (for white)
            String bSquare = isWhite ? "b1" : "b8";
            String cSquare = isWhite ? "c1" : "c8";

            // Check if there is no piece between the king and the rook, and they haven't moved
            if (rook != null && king != null && !king.hasMoved() && !rook.hasMoved()) {
                if (!isOccupied(board, bSquare) && !isOccupied(board, cSquare)) {
                    // TODO: Check for checks or attacks on the squares
                    if (!isSquareUnderAttack(board, move.substring(0, 2)) &&
                        !isSquareUnderAttack(board, bSquare) && !isSquareUnderAttack(board, cSquare)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isSquareUnderAttack(ReturnPlay board, String square) {
        for (ReturnPiece piece : board.piecesOnBoard) {
            String piecePosition = "" + piece.pieceFile + piece.pieceRank;
            if (((Pieces)piece).canMove(board, (piecePosition + " " + square)) == true) {
                // The piece can move to the specified square, so it's under attack.
                return true;
            }
        }
        return false;
    }

    private void performCastling(ReturnPlay board, String move) {
        if (move.equals("e1 g1") || move.equals("e8 g8")) {
            // Kingside castling
            String rookFromSquare = isWhite ? "h1" : "h8";
            String rookToSquare = isWhite ? "f1" : "f8";
            String kingFromSquare = isWhite ? "e1" : "e8";
            String kingToSquare = isWhite ? "g1" : "g8";

            // Find the king and rook in the board's pieces
            Pieces king = spacePiece(board, kingFromSquare);
            Pieces rook = spacePiece(board, rookFromSquare);

            // Update the piece positions
            king.pieceFile = PieceFile.valueOf(kingToSquare.substring(0, 1));
            king.pieceRank = Integer.parseInt(kingToSquare.substring(1));
            rook.pieceFile = PieceFile.valueOf(rookToSquare.substring(0, 1));
            rook.pieceRank = Integer.parseInt(rookToSquare.substring(1));

            // Update the board's piecesOnBoard ArrayList
            board.piecesOnBoard.remove(king);
            board.piecesOnBoard.remove(rook);
            board.piecesOnBoard.add(king);
            board.piecesOnBoard.add(rook);
        } else if (move.equals("e1 c1") || move.equals("e8 c8")) {
            // Queenside castling
            String rookFromSquare = isWhite ? "a1" : "a8";
            String rookToSquare = isWhite ? "d1" : "d8";
            String kingFromSquare = isWhite ? "e1" : "e8";
            String kingToSquare = isWhite ? "c1" : "c8";

            // Find the king and rook in the board's pieces
            Pieces king = spacePiece(board, kingFromSquare);
            Pieces rook = spacePiece(board, rookFromSquare);

            // Update the piece positions
            king.pieceFile = PieceFile.valueOf(kingToSquare.substring(0, 1));
            king.pieceRank = Integer.parseInt(kingToSquare.substring(1));
            rook.pieceFile = PieceFile.valueOf(rookToSquare.substring(0, 1));
            rook.pieceRank = Integer.parseInt(rookToSquare.substring(1));

            // Update the board's piecesOnBoard ArrayList
            board.piecesOnBoard.remove(king);
            board.piecesOnBoard.remove(rook);
            board.piecesOnBoard.add(king);
            board.piecesOnBoard.add(rook);
        }
    }
}