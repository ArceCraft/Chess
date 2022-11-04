import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece{
    Bishop(Board board, PieceColor pieceColor) {
        super(board, pieceColor);
        setPieceType(PieceType.Alfil);

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
    public Square[] MovesWithOutCheckedMoves() {
        ArrayList<Square> movesOfThePiece = new ArrayList<>(Arrays.asList(this.Moves()));
        ArrayList<Square> movesOfThePieceWithPutCheckedMoves = new ArrayList<>();

        movesOfThePieceWithPutCheckedMoves.addAll(movesOfThePiece);

        for(Piece piece : board.pieceSets[(this.getPieceColor().ordinal()-1)*(-1)].pieces){
            if(nonCheckMoveValidation(piece)){
                for(Square moveOfThePiece : movesOfThePiece){
                    Square[] movesOfTheContraryPiece = piece.Moves();
                    for(Square moveOfContraryPiece : movesOfTheContraryPiece){
                        if(Square.squareComparator(moveOfThePiece,moveOfContraryPiece))
                            movesOfThePieceWithPutCheckedMoves.remove(moveOfThePiece);

                    }
                }
            }


        }

        Square[] movesOfThePieceArray = new Square[movesOfThePieceWithPutCheckedMoves.size()];

        return movesOfThePieceWithPutCheckedMoves.toArray(movesOfThePieceArray);
    }

    @Override
    public Square[] MovesWhenInCheck( ) {
        return new Square[0];
    }

    @Override
    public Square[] PathOfAttacks() {
        return new Square[0];
    }

    private int[][] moveDirections ={

        {1,1},
        {1,-1},
        {-1,1},
        {-1,-1}
    };
}
