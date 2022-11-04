import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
    King(Board board, PieceColor pieceColor) {

        super(board, pieceColor);
        setPieceType(PieceType.Rey);

    }



    @Override
    public Square[] Moves() {

        //Creamos una lista con los movimientos sin tomar en cuenta si esa casilla puede poner en jaque al Rey
        List<Square> freeMoves = new ArrayList<>();

        //Copia de la anterior lista pero eliminando los movimientos propios que pongan en jaque al rey
        List<Square> freeMovesWithCheckedMoves = new ArrayList<>();

        PieceSet pieceSet = board.pieceSets[(this.getPieceColor().ordinal()-1)*(-1)];

        int pivotRow = getPlaceAt().getRow();
        int pivotColum = getPlaceAt().getColumn();

        for (int[] moveDirection : moveDirections) {

            for (int i = 1; i < 2; i++) {

                if(pivotRow + moveDirection[0]*i < 0 || pivotRow + moveDirection[0]*i >= 8 || pivotColum + moveDirection[1]*i < 0 || pivotColum + moveDirection[1]*i >= 8)
                    break;
                else if(FreeMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))){
                    break;
                }

                else if(CaptureMoveValidation(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i))){
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    //freeMovesWithCheckedMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    break;
                }
                else{
                    freeMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                    //freeMovesWithCheckedMoves.add(new Square(pivotRow + moveDirection[0]*i, pivotColum + moveDirection[1]*i));
                }

            }

        }

       freeMovesWithCheckedMoves.addAll(freeMoves);


        for(Square moves : freeMoves){
            for(Piece piece : pieceSet.pieces){
                if(piece.getPieceType() != PieceType.Rey){
                    Square[] movesOfPiece = piece.Moves();
                    for(Square possibleMoves : movesOfPiece){
                        if(Square.squareComparator(possibleMoves,moves))
                            freeMovesWithCheckedMoves.remove(moves);
                    }
                }
            }
        }


        Square[] freeMovesArray = new Square[freeMovesWithCheckedMoves.size()];

        return freeMovesWithCheckedMoves.toArray(freeMovesArray);
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
