public abstract  class Piece {

    protected PieceColor pieceColor;
    protected Square placeAt;
    private Boolean moved = false;

    private PieceType pieceType;
    private String pieceSymbol;

    Board board;



    Piece(Board board, PieceColor pieceColor){

        this.pieceColor = pieceColor;
        this.board = board;


    }

    public abstract Square[] Moves();


    public Boolean FreeMoveValidation(Square square){

        boolean exist = false;

        if(board.getSquareFromSquares(square.getRow(),square.getColumn()).getPiece() != null && board.getSquareFromSquares(square.getRow(),square.getColumn()).getPiece().getPieceColor() == pieceColor )
            exist=true;

        return exist;

    }

    public Boolean CaptureMoveValidation(Square square){

        boolean exist = false;

        if(board.getSquareFromSquares(square.getRow(),square.getColumn()).getPiece() != null && board.getSquareFromSquares(square.getRow(),square.getColumn()).getPiece().getPieceColor() != pieceColor )
            exist=true;

        return exist;

    }


    ///////////////////SETS///////////////////////

    void setPieceColor(PieceColor color){
        pieceColor = color;
    }
    void setMoved(){
        moved = true;
    }

    void setPieceType(PieceType pieceType){

        this.pieceType = pieceType;

    }

    void setPieceSymbol(String pieceSymbol){
        this.pieceSymbol = pieceSymbol;
    }


    //////////////////GETS/////////////////////


    Square getPlaceAt(){
        return placeAt;
    }

    PieceColor getPieceColor(){
        return  pieceColor;
    }

    Boolean getMoved(){
        return moved;
    }

    PieceType getPieceType(){

        return  pieceType;
    }

    String getPieceSymbol(){
        return  pieceSymbol;
    }


    public void setPlaceAt(Square square) {
        placeAt = square;
    }

    public void moveTo(Square square){
        placeAt = square;
    }
}
