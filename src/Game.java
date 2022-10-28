import java.util.List;
import java.util.Scanner;

public class Game {

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

        MoveRequest();


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

        System.out.print(FrameGeneration());

        for (int i = 0; i < 8; i++) {
            System.out.print(FrameGeneration(String.valueOf(Rank.values()[i])));
        }

        for (int i = 0; i < 8; i++) {
            System.out.println();
            System.out.print(FrameGeneration(String.valueOf(8-i)));
            cellPainting = !cellPainting;
            for (int j = 0; j < 8; j++) {
                cellPainting = !cellPainting;
                if(board.getSquareFromSquares(i,j).getPiece() != null) {
                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + board.getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + board.getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
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

    Square makeMove(int row, int column){

        Square squareTo = new Square(row,column);

     return squareTo;
    }

    void MoveRequest(){


        System.out.println("Piezas disponibles para mover: ");

        for (int i = 0; i < board.pieceSets[0].pieces.size(); i++) {
            if(board.pieceSets[0].pieces.get(i).getPieceType() == PieceType.Rook || board.pieceSets[0].pieces.get(i).getPieceType() == PieceType.Pawn || board.pieceSets[0].pieces.get(i).getPieceType() == PieceType.King)
                System.out.print((i+1)+".- "+board.pieceSets[0].pieces.get(i).getPieceType() + "   |");
            else if(board.pieceSets[0].pieces.get(i).getPieceType() == PieceType.Queen)
                System.out.print((i+1)+".- "+board.pieceSets[0].pieces.get(i).getPieceType() + "  |");
            else
                System.out.print((i+1)+".- "+board.pieceSets[0].pieces.get(i).getPieceType() + " |");
            System.out.println(" " + Rank.values()[board.pieceSets[0].pieces.get(i).getPlaceAt().getColumn()] + (8-board.pieceSets[0].pieces.get(i).getPlaceAt().getRow()));
        }

        System.out.println("De las anteriores piezas listadas, seleccione la que desea mover");
        int pieceIndex = sc.nextInt()-1;

        System.out.println();
        System.out.println("Pieza Seleccionada: " + board.pieceSets[1].pieces.get(pieceIndex).getPieceType() + "|" +Rank.values()[board.pieceSets[0].pieces.get(pieceIndex).getPlaceAt().getColumn()] + (8-board.pieceSets[0].pieces.get(pieceIndex).getPlaceAt().getRow()));



        System.out.println("¿A qué coordenada desea moverla?");


        System.out.println();

    }




    ////////////////////Recursos para imprimir//////////////////
    String[] boardSymbol = {
            ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + " ♚ " + ConsoleColors.RESET,
            ConsoleColors.BLACK_BACKGROUND + ConsoleColors.BLACK_BOLD + " ♚ " + ConsoleColors.RESET,
    };

    String FrameGeneration(String value){

        String string = ConsoleColors.BROWN_BACKGROUND + ConsoleColors.BLACK_BRIGHT + " " + value+ ConsoleColors.BROWN_BRIGHT+ "♚"  + ConsoleColors.RESET;

        return string;

    }

    String FrameGeneration(){

        String string = ConsoleColors.BROWN_BACKGROUND + ConsoleColors.BROWN_BRIGHT +" "+ "A" + "♚"  + ConsoleColors.RESET;

        return string;

    }


}