public class Pawn extends Piece{
    private Boolean promoted;
    private Piece promotedTo;
    private MoveDirection moveDirection;

    Pawn(Board board) {
        super(board);
    }
}
