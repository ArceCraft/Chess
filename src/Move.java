public class Move {

    private Square startSquare;
    private Square endSquare;
    private Piece piece;
    private Piece capturedPiece;

    public Move(Square startSquare, Square endSquare, Piece piece, Piece capturedPiece){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.piece = piece;
        this.capturedPiece = capturedPiece;
    }

    public Move(Square startSquare, Square endSquare, Piece piece){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.piece = piece;
        this.capturedPiece = null;
    }






}
