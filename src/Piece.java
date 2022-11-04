public abstract  class Piece {

    private PieceColor pieceColor;
    private Square placeAt;
    private Boolean moved = false;

    private PieceType pieceType;
    private String pieceSymbol;

    Board board;



    Piece(Board board, PieceColor pieceColor){

        this.pieceColor = pieceColor;
        this.board = board;


    }

    public abstract Square[] Moves();

    //Movimientos que la pieza pueda hacer sin entrar en Jaque
    public abstract Square[] MovesWithOutCheckedMoves();

    //Movimientos que la pieza puede realizar para sacar de jaque al Rey.
    public abstract Square[] MovesWhenInCheck();

    public abstract Square[] PathOfAttacks();


    public Boolean FreeMoveValidation(Square square){

        boolean exist = false;

        if(board.getPieceOnBoardSquare(square) != null && board.getPieceOnBoardSquare(square).getPieceColor() == pieceColor )
            exist=true;

        return exist;

    }

    public Boolean CaptureMoveValidation(Square square){

        boolean exist = false;

        if(board.getPieceOnBoardSquare(square) != null && board.getPieceOnBoardSquare(square).getPieceColor() != pieceColor )
            exist=true;

        return exist;

    }

    public  Boolean nonCheckMoveValidation(Piece piece){

        boolean isBetween = false;
        boolean comprobation1 = false;
        boolean comprobation2 = false;

        Piece rey = board.pieceSets[pieceColor.ordinal()].pieces.get(board.pieceSets[pieceColor.ordinal()].pieces.size()-1);

        for(Square moves : piece.Moves()){
            if(Square.squareComparator(this.getPlaceAt(),moves))
                comprobation1 = true;
        }

        for(Square moves : rey.PathOfAttacks()){
            if(Square.squareComparator(this.getPlaceAt(),moves))
                comprobation2 = true;
        }

        if(comprobation1 && comprobation2)
            isBetween = true;

        return isBetween;

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
