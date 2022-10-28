import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Board board, PieceColor pieceColor) {
        super(board, pieceColor);
        setPieceType(PieceType.Rey);
    }



    @Override
    public Square[] Moves() {

        List<Square> freeMoves = new ArrayList<>();
        List<Square> captureMoves = new ArrayList<>();


        int pivotRow = getPlaceAt().getRow();
        int pivotColum = getPlaceAt().getColumn();

        for (int[] moveDirection : moveDirections) {

            for (int i = 1; i < 2; i++) {

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
