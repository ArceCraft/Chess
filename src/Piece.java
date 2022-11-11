import java.util.ArrayList;

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
    public abstract Square[] MovesAvoidingCheck(); //Moves to avoid Check

    //Movimientos que la pieza puede realizar para sacar de jaque al Rey.
    public abstract Square[] MovesWhenInCheck();

    public abstract Square[] PathOfAttacks();

    public  abstract Square[] PathToKing();


    public Boolean FreeMoveValidation(Square square){

        boolean exist = false;

        if(board.getPieceOnBoardSquare(square) != null && board.getPieceOnBoardSquare(square).getPieceColor() == pieceColor )
            exist=true;

        return exist;

    }

    public Piece[] CheckinPieces(){

        ArrayList<Piece> checkinPieces = new ArrayList<>();

        PieceColor turnoContrario = PieceColor.White;


        if(this.pieceColor == PieceColor.White)
            turnoContrario = PieceColor.Black;

        PieceSet pieceSetContrarias = board.pieceSets[turnoContrario.ordinal()];

        Piece rey = board.pieceSets[this.pieceColor.ordinal()].pieces.get(board.pieceSets[this.pieceColor.ordinal()].pieces.size()-1);

        for(Piece contraryPiece : pieceSetContrarias.pieces){
            for(Square movesOfTheContrarypiece : contraryPiece.Moves()){
                if(Square.squareComparator(rey.getPlaceAt(),movesOfTheContrarypiece))
                    checkinPieces.add(contraryPiece);
            }
        }

        Piece[] checkinPiecesArray = new Piece[checkinPieces.size()];

        return checkinPieces.toArray(checkinPiecesArray);

    }

    public Boolean CaptureMoveValidation(Square square){

        boolean exist = false;

        if(board.getPieceOnBoardSquare(square) != null && board.getPieceOnBoardSquare(square).getPieceColor() != pieceColor )
            exist=true;

        return exist;

    }

    public Boolean KingValidation(Square square){

        boolean exist = false;
        if(board.getPieceOnBoardSquare(square) != null && board.getPieceOnBoardSquare(square).getPieceType() == PieceType.Rey && board.getPieceOnBoardSquare(square).getPieceColor() != pieceColor)
            exist = true;

        return exist;
    }


    //Metodo para validar si la pieza está entre el rey y una los movimientos de una pieza contraria que se pasa por el parametro afecten al rey.
    public  Boolean isBetween(Piece piece){

        boolean isBetween = false;
        boolean comprobation1 = false;
        boolean comprobation2 = false;


        //Tomamos al rey del turno actual
        Piece rey = board.pieceSets[this.pieceColor.ordinal()].pieces.get(board.pieceSets[this.pieceColor.ordinal()].pieces.size()-1);


        //Tomamos los movimientos de la pieza contraria
        for(Square moves : piece.Moves()){
            if(Square.squareComparator(this.getPlaceAt(),moves))
                comprobation1 = true;
        }

        //Tomamos el area de vulnerabilidad del rey
        for(Square moves : rey.PathOfAttacks()){
            if(Square.squareComparator(this.getPlaceAt(),moves))
                comprobation2 = true;
        }

        //Si las dos anteriores coinciden entonces la pieza que ejecuta este metodo está entre el rey y la pieza que lo puede poner en jaque.
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
