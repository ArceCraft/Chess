import java.util.ArrayList;
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

        switch (moveDirection) {
            case Up:


                for (int i = 1; i < range+1; i++) {
                    if(FreeMoveValidation(new Square(getPlaceAt().getRow()-1*i, getPlaceAt().getColumn())))
                        continue;
                    else
                        freeMoves.add(new Square(getPlaceAt().getRow()-1*i, getPlaceAt().getColumn()));
                }


                break;

            case Down:

                for (int i = 1; i < range+1; i++) {
                    if(FreeMoveValidation(new Square(getPlaceAt().getRow()+1*i, getPlaceAt().getColumn())))
                        continue;
                    else
                        freeMoves.add(new Square(getPlaceAt().getRow()+1*i, getPlaceAt().getColumn()));
                }
                break;
        }
        switch (moveDirection) {
            case Up:

                    if (CaptureMoveValidation(new Square(getPlaceAt().getRow() - 1, getPlaceAt().getColumn()+1)))
                        freeMoves.add(new Square(getPlaceAt().getRow() - 1, getPlaceAt().getColumn()+1));
                    if (CaptureMoveValidation(new Square(getPlaceAt().getRow() - 1, getPlaceAt().getColumn()-1)))
                        freeMoves.add(new Square(getPlaceAt().getRow() - 1, getPlaceAt().getColumn()-1));

                break;

            case Down:

                if (CaptureMoveValidation(new Square(getPlaceAt().getRow() + 1, getPlaceAt().getColumn()+1)))
                    freeMoves.add(new Square(getPlaceAt().getRow() + 1, getPlaceAt().getColumn()+1));
                if (CaptureMoveValidation(new Square(getPlaceAt().getRow() + 1, getPlaceAt().getColumn()-1)))
                    freeMoves.add(new Square(getPlaceAt().getRow() + 1, getPlaceAt().getColumn()-1));

                break;
        }


        Square[] freeMovesArray = new Square[freeMoves.size()];

        return freeMoves.toArray(freeMovesArray);
    }



}
