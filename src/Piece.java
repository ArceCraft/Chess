import java.util.ArrayList;
import java.util.List;

public class Piece {

    private PieceColor pieceColor;
    private Square placeAt;
    private Boolean moved = false;

    private PieceType pieceType;
    private String pieceSymbol;

    Piece(){

    }

    public Square[] ValidMoves(){

        List<Square> validMovesList= new ArrayList<Square>();

        Square[] validMoves = new Square[validMovesList.size()];

        return validMoves;
    }

    public void moveTo(Square squareDestination){

        placeAt = squareDestination;

    }

    public Square[] AttackSquares(){
        Square[] attackSquares = new Square[0];

        return  attackSquares;
    }

    public Square[] CaptureFreeMoves(){
        Square[] captureFreeMoves = new Square[0];

        return captureFreeMoves;
    }

    public Boolean ToBeCaptured(){
        Boolean toBeCaptured = false;
        return toBeCaptured;
    }


    ///////////////////SETS///////////////////////

    void setPlaceAt(Square square){

        placeAt =  square;

    }
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


}
