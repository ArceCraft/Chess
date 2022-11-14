import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece{

    Queen(Board board,PieceColor pieceColor) {
        super(board, pieceColor);
        setPieceType(PieceType.Reina);
    }


    @Override
    public Square[] Moves() {
        List<Square> freeMoves = new ArrayList<>();


        int pivotRow = getPlaceAt().getRow();
        int pivotColum = getPlaceAt().getColumn();

        for (int[] moveDirection : moveDirections) {

            for (int i = 1; i < 8; i++) {
                if(pivotRow + moveDirection[0]*i < 0 || pivotRow + moveDirection[0]*i >= 8 || pivotColum + moveDirection[1]*i < 0 || pivotColum + moveDirection[1]*i >= 8)
                    break;
                else if(FreeMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i)))
                    break;
                else if(CaptureMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))){
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    break;
                }
                else
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
            }

        }


        Square[] freeMovesArray = new Square[freeMoves.size()];

        return freeMoves.toArray(freeMovesArray);
    }

    @Override
    public Square[] ProtectingMoves() {
        List<Square> freeMoves = new ArrayList<>();
        int pivotRow = getPlaceAt().getRow();
        int pivotColum = getPlaceAt().getColumn();

        for (int[] moveDirection : moveDirections) {

            for (int i = 1; i < 8; i++) {
                if(pivotRow + moveDirection[0]*i < 0 || pivotRow + moveDirection[0]*i >= 8 || pivotColum + moveDirection[1]*i < 0 || pivotColum + moveDirection[1]*i >= 8)
                    break;
                else if (KingValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))) {
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i+moveDirection[0], pivotColum + moveDirection[1]*i+moveDirection[1]*i));
                    break;
                }
                else if(CaptureMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i)) && !KingValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i)))
                    break;
                else if(FreeMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i)) && !KingValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))){
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    break;
                }
                else
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
            }

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


        int pivotRow = getPlaceAt().getRow();
        int pivotColum = getPlaceAt().getColumn();
        boolean kingExist = false;

        for (int[] moveDirection : moveDirections) {

            for (int i = 1; i < 8; i++) {
                if(pivotRow + moveDirection[0]*i < 0 || pivotRow + moveDirection[0]*i >= 8 || pivotColum + moveDirection[1]*i < 0 || pivotColum + moveDirection[1]*i >= 8)
                    break;
                else if(KingValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))){
                    movesToKing.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    kingExist = true;
                    break;
                }
                else
                    movesToKing.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
            }

            if (!kingExist){
                movesToKing.clear();
            }

            else
               break;

        }

        movesToKing.add(this.getPlaceAt());

        Square[] movesToKingArray = new Square[movesToKing.size()];

        return movesToKing.toArray(movesToKingArray);
    }


    int[][] moveDirections ={
            {1,0},
            {0,1},
            {0,-1},
            {-1,0},
            {1,1},
            {1,-1},
            {-1,1},
            {-1,-1}
    };
}
