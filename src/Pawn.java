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

        if(pieceColor == PieceColor.Blancas)
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

        switch (moveDirection) {
            case Up:


                for (int i = 1; i < range+1; i++) {

                    if(row - i < 0)
                        break;
                    if(FreeMoveValidation(new Square(row - i, column)) || CaptureMoveValidation(new Square(row - i, column)))
                        break;
                    else
                        freeMoves.add(new Square(row - i, column));
                }


                break;

            case Down:

                for (int i = 1; i < range+1; i++) {
                    if(row + i >= 8)
                        break;
                    if(FreeMoveValidation(new Square(row + i, column)) || CaptureMoveValidation(new Square(row + i, column)))
                        break;
                    else
                        freeMoves.add(new Square(row + i, column));
                }
                break;
        }

        switch (moveDirection) {
            case Up:
                for (int i = 0; i < upDirections.length; i++) {
                    if((row + upDirections[i][0])<0 ||(row + upDirections[i][0]) >= 8 || (column +upDirections[i][1]) < 0 || (column +upDirections[i][1]) >=8)
                        continue;
                    else if (CaptureMoveValidation(new Square(row + upDirections[i][0], column +upDirections[i][1])))
                        freeMoves.add(new Square(row + upDirections[i][0], column +upDirections[i][1]));
                }

                break;

            case Down:
                for (int i = 0; i < downDirections.length; i++) {
                    if((row + downDirections[i][0])<0 ||(row + downDirections[i][0]) >= 8 || (column +downDirections[i][1]) < 0 || (column +downDirections[i][1]) >=8)
                        continue;
                    else if (CaptureMoveValidation(new Square(row + downDirections[i][0], column +downDirections[i][1])))
                        freeMoves.add(new Square(row + downDirections[i][0], column +downDirections[i][1]));
                }

                break;
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
                Square[] movesOfTheContraryPiece = piece.Moves();
                for(Square moveOfContraryPiece : movesOfTheContraryPiece){
                    for(Square moveOfThePiece : movesOfThePiece){
                        if(!Square.squareComparator(moveOfThePiece,moveOfContraryPiece))
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

    int[][] downDirections = {
            {1,1 },
            {1,-1}
    };

    int[][] upDirections = {
            {-1,1 },
            {-1,-1}
    };

}
