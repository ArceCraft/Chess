import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece{
    private Boolean promoted;
    private Piece promotedTo;
    private MoveDirection moveDirection;

    Pawn(Board board, PieceColor pieceColor) {
        super(board, pieceColor);
        setPieceType(PieceType.Peon);

        if(pieceColor == PieceColor.White)
            moveDirection = MoveDirection.Up;
        else
            moveDirection = MoveDirection.Down;


    }


    @Override
    public Square[] Moves() {

        List<Square> freeMoves = new ArrayList<>();

        byte range=1;
        if(!this.getMoved())
            range = 2;

        int row = getPlaceAt().getRow();
        int column = getPlaceAt().getColumn();
        byte direction = 1;

        if(moveDirection == MoveDirection.Up)
            direction = - 1;


        for (int i = 1; i < range+1; i++) {

            if(row + direction*i < 0)
                break;
            if(FreeMoveValidation(new Square(row + direction*i, column)) || CaptureMoveValidation(new Square(row + direction*i, column)))
                break;
            else
                freeMoves.add(new Square(row + direction*i, column));
        }

        for (int i = 0; i < moveDirections.length; i++) {
            if((row + moveDirections[i][0]*direction)<0 ||(row + moveDirections[i][0]*direction) >= 8 || (column +moveDirections[i][1]) < 0 || (column +moveDirections[i][1]) >=8)
                continue;
            else if (CaptureMoveValidation(new Square(row + moveDirections[i][0]*direction, column +moveDirections[i][1])))
                freeMoves.add(new Square(row + moveDirections[i][0]*direction, column +moveDirections[i][1]));
        }

        Square[] freeMovesArray = new Square[freeMoves.size()];

        return freeMoves.toArray(freeMovesArray);
    }

    @Override
    public Square[] ProtectingMoves() {
        List<Square> freeMoves = new ArrayList<>();

        byte range=1;
        if(!this.getMoved())
            range = 2;

        int row = getPlaceAt().getRow();
        int column = getPlaceAt().getColumn();
        byte direction = 1;

        if(moveDirection == MoveDirection.Up)
            direction = - 1;


        for (int i = 1; i < range+1; i++) {

            if(row + direction*i < 0)
                break;
            if(FreeMoveValidation(new Square(row + direction*i, column)) || CaptureMoveValidation(new Square(row + direction*i, column)))
                break;
            if(CaptureMoveValidation(new Square(row + direction*i, column)) || CaptureMoveValidation(new Square(row + direction*i, column)))
                break;
            else
                freeMoves.add(new Square(row + direction*i, column));
        }

        for (int i = 0; i < moveDirections.length; i++) {
            if((row + moveDirections[i][0]*direction)<0 ||(row + moveDirections[i][0]*direction) >= 8 || (column +moveDirections[i][1]) < 0 || (column +moveDirections[i][1]) >=8)
                continue;
            else if (FreeMoveValidation(new Square(row + moveDirections[i][0]*direction, column +moveDirections[i][1])))
                freeMoves.add(new Square(row + moveDirections[i][0]*direction, column +moveDirections[i][1]));
        }

        Square[] freeMovesArray = new Square[freeMoves.size()];

        return freeMoves.toArray(freeMovesArray);
    }

    @Override
    public Square[] MovesAvoidingCheck() {

        ArrayList<Square> movesOfThePiece = new ArrayList<>(Arrays.asList(this.Moves()));
        ArrayList<Square> movesOfThePieceWithPutCheckedMoves = new ArrayList<>();

        movesOfThePieceWithPutCheckedMoves.addAll(movesOfThePiece);

        //Para cada pieza del set contrario
        for(Piece contraryPiece : board.pieceSets[(this.getPieceColor().ordinal()-1)*(-1)].pieces){
            //Comprobar si la pieza esta está entre el rey y los movimientos de pieza contraria.
            if(isBetween(contraryPiece)){
                //Toma los movientos posibles de la pieza.
                for(Square moveOfThePiece : movesOfThePiece){
                    //Guarda los movimientos de la pieza contraria.
                    Square[] movesOfTheContraryPiece = contraryPiece.PathToKing();
                    boolean movementExistence = false;
                    for(Square moveOfContraryPiece : movesOfTheContraryPiece){
                        if((Square.squareComparator(moveOfThePiece,moveOfContraryPiece) || Square.squareComparator(moveOfThePiece,contraryPiece.getPlaceAt())))
                            movementExistence = true;
                    }
                    if(!movementExistence)
                        movesOfThePieceWithPutCheckedMoves.remove(moveOfThePiece);

                }
            }
        }

        Square[] movesOfThePieceArray = new Square[movesOfThePieceWithPutCheckedMoves.size()];

        return movesOfThePieceWithPutCheckedMoves.toArray(movesOfThePieceArray);
    }


    @Override
    public Square[] MovesWhenInCheck( ) {

        ArrayList<Square> movesOfThePiece = new ArrayList<>(Arrays.asList(this.Moves()));
        ArrayList<Square> movesOfThePieceWithPutCheckedMoves = new ArrayList<>();

        movesOfThePieceWithPutCheckedMoves.addAll(movesOfThePiece);
        Piece[] setOfCheckinPieces = CheckinPieces();

        if(setOfCheckinPieces.length == 1){
            //Para cada pieza del set contrario
            for(Piece contraryPiece : setOfCheckinPieces){
                //Toma los movimientos posibles de la pieza actual
                for(Square moveOfThePiece : movesOfThePiece){
                    //Guarda los movimientos que se dirigen al rey de la pieza contraria.
                    Square[] movesOfTheContraryPiece = contraryPiece.PathToKing();
                    boolean movementExistence = false;
                    for(Square moveOfContraryPiece : movesOfTheContraryPiece){
                        if((Square.squareComparator(moveOfThePiece,moveOfContraryPiece) || Square.squareComparator(moveOfThePiece,contraryPiece.getPlaceAt())))
                            movementExistence = true;
                    }

                    if(!movementExistence)
                        movesOfThePieceWithPutCheckedMoves.remove(moveOfThePiece);

                }
            }
        }
        else
            movesOfThePieceWithPutCheckedMoves.clear();


        Square[] movesOfThePieceArray = new Square[movesOfThePieceWithPutCheckedMoves.size()];

        return movesOfThePieceWithPutCheckedMoves.toArray(movesOfThePieceArray);
    }

    @Override
    public Square[] PathOfAttacks() {
        return new Square[0];
    }

    @Override
    public Square[] PathToKing() {
        List<Square> movesToKing = new ArrayList<>();
        movesToKing.add(this.getPlaceAt());

        Square[] movesToKingArray = new Square[movesToKing.size()];
        return movesToKing.toArray(movesToKingArray);
    }

    int[][] moveDirections = {
            {1, 1},
            {1,-1}
    };

}
