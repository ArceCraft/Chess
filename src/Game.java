import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Move> playedMoves = new ArrayList<>();
    private Player[] players = new Player[2];
    private PieceColor turn;
    private Result result = Result.None;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    Game() {

        turn = PieceColor.White;
        Create();
        board.PrintBoard();

        while (true) {

            MoveProcess();
            if(result != Result.None)
                break;
            board.PrintBoard();

        }

        board.PrintFinalBoard(result);
        System.out.println("Juego terminado, resultado: " + result);

    }


    public void AddMove(Square startSquare, Square toSquare, Piece piece, Piece pieceCaptured) {

        try {
            playedMoves.add(new Move(startSquare,toSquare,piece,pieceCaptured));
            removePiece(pieceCaptured);
        }
        catch (Exception e){
            playedMoves.add(new Move(startSquare,toSquare,piece));
        }

        makeMove(piece,toSquare);

    }

    public void Create() {

        board = new Board();
        players[0] = new Player(PieceColor.Black);
        players[1] = new Player(PieceColor.White);

    }


    public boolean IsChecked() {

        Boolean enJaque = false;

        //Crea el turno de las piezas contrarias
        PieceColor turnoContrario = PieceColor.White;

        if(turn == PieceColor.White)
            turnoContrario = PieceColor.Black;

        //Toma la pieza "Rey" de las piezas del turno actual. Por defecto el rey se encuentra en la última posición de la lista, cambiarlo a la posición 0 sería mejor.
        Piece rey = board.pieceSets[turn.ordinal()].pieces.get(board.pieceSets[turn.ordinal()].pieces.size()-1);
        //Toma el set completo de piezas contrarias
        PieceSet pieceSetContrarias = board.pieceSets[turnoContrario.ordinal()];

        //Guarda la posición del rey para mejor manejo siguiente.
        Square reyPos = rey.getPlaceAt();


        /*Recorre todas las piezas del set de piezas contrarias y recorre los movimientos disponibles de cada pieza para saber si algún movimiento es igual a la
        /posición del rey.*/

        for(Piece piece : pieceSetContrarias.pieces){
            if(piece.getPieceType() != PieceType.Rey){
                Square[] movesOfThePiece = piece.Moves();
                for(Square moves : movesOfThePiece){
                    if(Square.squareComparator(moves,reyPos)){
                        enJaque = true;
                        break;
                    }
                }
            }

        }

        return enJaque;
    }



    void makeMove(Piece piece,Square square){

        piece.moveTo(square);

        if(!(piece.getMoved()))
            piece.setMoved();

    }

    void removePiece(Piece piece){

        PieceSet pieceSet =  board.pieceSets[piece.getPieceColor().ordinal()];

        pieceSet.pieces.remove(piece);

    }



    Piece getCapturedPieceIfCaptured(PieceColor pickedPieceColor, Square movement){

        PieceColor capturedPieceColor = PieceColor.White;
        Piece capturedPiece = null;

        if(pickedPieceColor == PieceColor.White)
            capturedPieceColor = PieceColor.Black;


        int pieceIndex = board.pieceSets[capturedPieceColor.ordinal()].getPieceIndexBySquareCoordinates(movement);

        if(pieceIndex !=  board.pieceSets[capturedPieceColor.ordinal()].pieces.size()){
            capturedPiece = board.pieceSets[capturedPieceColor.ordinal()].pieces.get(pieceIndex);
            System.out.println("Pieza capturada: " + capturedPiece.getPieceType());
        }

        return capturedPiece;

    }

    void MoveProcess(){

        PieceSet pickedPieceSet;
        Piece pickedPiece;
        Piece capturedPiece;
        Square moveSelected;
        List<Integer> movablePiecesList = new ArrayList<>();


        pickedPieceSet = board.pieceSets[turn.ordinal()];

        if(IsChecked())
            System.out.println("Jugador "+ turn +" estás en jaque.");

        for (int i = 0; i < pickedPieceSet.pieces.size(); i++) {
            if(IsChecked()){
                if(pickedPieceSet.pieces.get(i).MovesWhenInCheck().length != 0)
                    movablePiecesList.add(i);
            }
            else{
                if(pickedPieceSet.pieces.get(i).MovesAvoidingCheck().length != 0)
                    movablePiecesList.add(i);
            }
        }

        //Se imprimen las piezas válidas para mover
        if(movablePiecesList.size() != 0){
            System.out.println("Turno: " + turn);
            System.out.println("Piezas disponibles para mover: ");
            for (Integer integer : movablePiecesList) {
                System.out.print((integer + 1) + ".- " + pickedPieceSet.pieces.get(integer).getPieceType());
                System.out.println("[" + Rank.values()[pickedPieceSet.pieces.get(integer).getPlaceAt().getColumn()] + (8 - pickedPieceSet.pieces.get(integer).getPlaceAt().getRow()) + "]");
            }
        }


        if(movablePiecesList.size() == 0 && IsChecked()){
            System.out.println("JAQUE MATE.");
            if(turn == PieceColor.White)
                result = Result.BlackWin;
            else
                result = Result.WhiteWin;
        }
        else if(movablePiecesList.size() == 0 && !IsChecked()){
            System.out.println("EMPATE.");
            result = Result.Draw;
        }
        if(result != Result.None)
            return;

        Square[] movesOfThePiece;

        System.out.println("De las anteriores piezas listadas, seleccione la que desea mover");


        pickedPiece = players[turn.ordinal()].selectPiece(pickedPieceSet, movablePiecesList);
        System.out.println();
        System.out.println("Pieza Seleccionada: " + pickedPiece.getPieceType() + "[" + Rank.values()[pickedPiece.getPlaceAt().getColumn()] + (8 - pickedPiece.getPlaceAt().getRow()) + "]");
        System.out.println("Movimientos disponibles: ");

        if (IsChecked())
            movesOfThePiece = pickedPiece.MovesWhenInCheck();
        else
            movesOfThePiece = pickedPiece.MovesAvoidingCheck();

        board.printBoardWithPossiblesMovements(movesOfThePiece);


        System.out.println("Seleccione alguno: ");

        moveSelected = players[turn.ordinal()].selectMove(movesOfThePiece);
        capturedPiece = getCapturedPieceIfCaptured(pickedPiece.getPieceColor(),moveSelected);
        AddMove(pickedPiece.getPlaceAt(), moveSelected,pickedPiece,capturedPiece);
        setTurn(turn);


    }

    void setTurn(PieceColor actualTurn) {

        PieceColor turn = actualTurn;

        if (actualTurn == PieceColor.Black)
            turn = PieceColor.White;
        if (actualTurn == PieceColor.White)
            turn = PieceColor.Black;

        this.turn = turn;
    }



}