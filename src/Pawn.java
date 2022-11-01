import java.util.ArrayList;
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

        switch (moveDirection) {
            case Up:


                for (int i = 1; i < range+1; i++) {
                    if(FreeMoveValidation(new Square(getPlaceAt().getRow()-1*i, getPlaceAt().getColumn())) || CaptureMoveValidation(new Square(getPlaceAt().getRow()-1*i, getPlaceAt().getColumn())))
                        continue;
                    else
                        freeMoves.add(new Square(getPlaceAt().getRow()-1*i, getPlaceAt().getColumn()));
                }


                break;

            case Down:

                for (int i = 1; i < range+1; i++) {
                    if(FreeMoveValidation(new Square(getPlaceAt().getRow()+1*i, getPlaceAt().getColumn())) || CaptureMoveValidation(new Square(getPlaceAt().getRow()+1*i, getPlaceAt().getColumn())))
                        continue;
                    else
                        freeMoves.add(new Square(getPlaceAt().getRow()+1*i, getPlaceAt().getColumn()));
                }
                break;
        }
        switch (moveDirection) {
            case Up:
                for (int i = 0; i < upDirections.length; i++) {
                    if((getPlaceAt().getRow() + upDirections[i][0])<0 ||(getPlaceAt().getRow() + upDirections[i][0]) >= 8 || (getPlaceAt().getColumn()+upDirections[i][1]) < 0 || (getPlaceAt().getColumn()+upDirections[i][1]) >=8)
                        continue;
                    else if (CaptureMoveValidation(new Square(getPlaceAt().getRow() + upDirections[i][0], getPlaceAt().getColumn()+upDirections[i][1])))
                        freeMoves.add(new Square(getPlaceAt().getRow() + upDirections[i][0], getPlaceAt().getColumn()+upDirections[i][1]));
                }

                break;

            case Down:
                for (int i = 0; i < downDirections.length; i++) {
                    if((getPlaceAt().getRow() + downDirections[i][0])<0 ||(getPlaceAt().getRow() + downDirections[i][0]) >= 8 || (getPlaceAt().getColumn()+downDirections[i][1]) < 0 || (getPlaceAt().getColumn()+downDirections[i][1]) >=8)
                        continue;
                    else if (CaptureMoveValidation(new Square(getPlaceAt().getRow() + downDirections[i][0], getPlaceAt().getColumn()+downDirections[i][1])))
                        freeMoves.add(new Square(getPlaceAt().getRow() + downDirections[i][0], getPlaceAt().getColumn()+downDirections[i][1]));
                }

                break;
        }


        Square[] freeMovesArray = new Square[freeMoves.size()];

        return freeMoves.toArray(freeMovesArray);
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
