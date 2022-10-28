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


        while (true){
            board.UpdateSquares();
            PrintBoard();
            MoveRequest();
        }



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

    void printBoardWithPossiblesMovements(Square[] moves){

        boolean cellPainting = true;


        for (int i = 0; i < moves.length; i++) {
            System.out.println((i+1)+".- "+ Rank.values()[moves[i].getColumn()] + "" + (8-moves[i].getRow()));
        }


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

                else if(isSelectable(moves,i,j)){
                            if (cellPainting)
                                System.out.print(boardSymbol[2]);
                            else
                                System.out.print(boardSymbol[3]);

                        }
                else {
                    if (cellPainting)
                        System.out.print(boardSymbol[0]);
                    else
                        System.out.print(boardSymbol[1]);
                }

            }
        }
        System.out.println();
    }

    void makeMove(Piece piece,Square square){

        piece.moveTo(square);

    }

    void MoveRequest(){

        PieceSet pickedPieceSet;
        Piece pickedPiece;

        System.out.println("Seleccione color: ");
        System.out.println("1.- " + PieceColor.Black);
        System.out.println("2.- " + PieceColor.White);


        pickedPieceSet = board.pieceSets[sc.nextByte()-1];

        System.out.println("Piezas disponibles para mover: ");

        for (int i = 0; i < pickedPieceSet.pieces.size(); i++) {
            if(pickedPieceSet.pieces.get(i).getPieceType() == PieceType.Torre || pickedPieceSet.pieces.get(i).getPieceType() == PieceType.Peon || pickedPieceSet.pieces.get(i).getPieceType() == PieceType.Rey)
                System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + "   |");
            else if(pickedPieceSet.pieces.get(i).getPieceType() == PieceType.Reina)
                System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + "  |");
            else
                System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + " |");
            System.out.println(" " + Rank.values()[pickedPieceSet.pieces.get(i).getPlaceAt().getColumn()] + (8-pickedPieceSet.pieces.get(i).getPlaceAt().getRow()));
        }

        System.out.println("De las anteriores piezas listadas, seleccione la que desea mover");
        int pieceIndex = sc.nextInt()-1;

        pickedPiece = pickedPieceSet.pieces.get(pieceIndex);

        System.out.println();
        System.out.println("Pieza Seleccionada: " + pickedPiece.getPieceType() + "|" +Rank.values()[pickedPiece.getPlaceAt().getColumn()] + (8-pickedPiece.getPlaceAt().getRow()));

        System.out.println("Movimientos disponibles: ");
        printBoardWithPossiblesMovements(pickedPiece.Moves());
        System.out.println("Seleccione alguno: ");

        makeMove(pickedPiece,pickedPiece.Moves()[sc.nextInt()-1]);




    }




    ////////////////////Recursos para imprimir//////////////////
    String[] boardSymbol = {
            ConsoleColors.WHITE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD_BRIGHT + " \u2001 " + ConsoleColors.RESET,
            ConsoleColors.BLACK_BACKGROUND + ConsoleColors.BLACK_BOLD + " \u2001 " + ConsoleColors.RESET,
            ConsoleColors.WHITE_BACKGROUND_SELECT+ ConsoleColors.WHITE_BOLD_BRIGHT + " \u2001 " + ConsoleColors.RESET,
            ConsoleColors.BLACK_BACKGROUND_SELECT + ConsoleColors.BLACK_BOLD + " \u2001 " + ConsoleColors.RESET,

    };

    String FrameGeneration(String value){

        String string = ConsoleColors.BROWN_BACKGROUND + ConsoleColors.BLACK_BRIGHT + " " + value+ ConsoleColors.BROWN_BRIGHT+ "\u2001"  + ConsoleColors.RESET;

        return string;

    }

    String FrameGeneration(){

        String string = ConsoleColors.BROWN_BACKGROUND + ConsoleColors.BROWN_BRIGHT +" "+ " " + "\u2001"  + ConsoleColors.RESET;

        return string;

    }

    boolean isSelectable(Square square[],int i, int j){
        boolean isSelectable = false;

        for (int k = 0; k < square.length; k++) {
            if(square[k].getColumn() == j && square[k].getRow() == i)
                isSelectable = true;

        }

        return  isSelectable;
    }


}