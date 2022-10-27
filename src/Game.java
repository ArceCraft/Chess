import java.util.List;
import java.util.Scanner;

public class Game {

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[45m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[45m";

    private List<Move> playedMoves;
    private Player[] players = new Player[2];
    private PieceColor turn;
    private Result result;
    private CheckStatus checkStatus;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    Game() {

        Create();
        PrintBoard();

        System.out.println(board.pieceSets[0].pieces.get(8).getPieceType());
        System.out.println(board.pieceSets[0].pieces.get(8).getPlaceAt().getRow()+  ", "+board.pieceSets[0].pieces.get(0).getPlaceAt().getColumn()  );
        board.pieceSets[0].pieces.get(8).moveTo(makeMove());

        board.UpdateSquares();

        PrintBoard();


    }


    public void AddMove(Square startSquare, Square toSquare, Piece piece, Piece pieceCaptured) {

        playedMoves.add(new Move(startSquare,toSquare,piece,pieceCaptured));
    }

    public void Create() {

        board = new Board();
        players[0] = new Player(PieceColor.Black, new PlayerEngine());
        players[1] = new Player(PieceColor.White, new PlayerEngine());

    }

    public void IsEnded() {

    }

    public void IsChecked() {

    }

    public void Operation() {

    }

    public void IsCheckMated() {

    }

    void PrintBoard() {

        boolean cellPainting = true;

        for (int i = 0; i < 8; i++) {
            System.out.println();
            cellPainting = !cellPainting;
            for (int j = 0; j < 8; j++) {
                cellPainting = !cellPainting;
                if(board.getSquareFromSquares(i,j).getPiece() != null) {
                    if (cellPainting)
                        System.out.print(ANSI_WHITE_BACKGROUND + board.getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ANSI_RESET);
                    else
                        System.out.print(ANSI_BLACK_BACKGROUND + board.getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ANSI_RESET);
                }

                else{
                    if(cellPainting)
                        System.out.print(boardSymbol[0]);
                    else
                        System.out.print(boardSymbol[1]);
                }

            }
        }
        System.out.println();
    }

    Square makeMove(){

        int row = sc.nextInt();
        int column = sc.nextInt();
        Square squareTo = new Square(row,column);

     return squareTo;
    }

    String[] boardSymbol = {
            ANSI_WHITE_BACKGROUND + ANSI_WHITE + " ♚" + ANSI_RESET,
            ANSI_BLACK_BACKGROUND + ANSI_BLACK + " ♚" + ANSI_RESET
    };


}