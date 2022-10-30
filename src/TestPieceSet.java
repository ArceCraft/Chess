import java.util.ArrayList;
import java.util.List;

public class TestPieceSet {

    List<Piece> pieces;
    private  PieceColor color;

    private PieceColor pieceOnTop;

    private Board board;

    TestPieceSet(Board board,PieceColor color, PieceColor pieceOnTop, Square square){
        this.board = board;
        this.color = color;
        this.pieceOnTop = pieceOnTop;

        pieces = new ArrayList<Piece>();

        Piece blackPawn = new Pawn(board,color);
        blackPawn.setPlaceAt(square);

        pieces.add(new Pawn(board,color));
    }
}
