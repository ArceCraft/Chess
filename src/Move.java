public class Move {

    private Square startSquare;
    private Square endSquare;
    private Piece movedPiece;
    private Piece capturedPiece;

    public Move(Square startSquare, Square endSquare, Piece movedPiece, Piece capturedPiece){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    public Move(Square startSquare, Square endSquare, Piece movedPiece){
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.movedPiece = movedPiece;
        this.capturedPiece = null;
    }

    public Piece getMovedPiece(){
        return movedPiece;
    }







}
