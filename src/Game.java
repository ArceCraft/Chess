import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Move> playedMoves = new ArrayList<>();
    private Player[] players = new Player[2];
    private PieceColor turn;
    private Result result;
    private CheckStatus checkStatus;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    Game() {

        turn = PieceColor.Blancas;

        Create();

        while (true){

            board.PrintBoard();
            MoveRequest();
        }
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
        players[0] = new Player(PieceColor.Negras, new PlayerEngine());
        players[1] = new Player(PieceColor.Blancas, new PlayerEngine());

    }

    public void IsEnded() {

    }

    public boolean IsChecked() {

        Boolean enJaque = false;

        //Crea el turno de las piezas contrarias
        PieceColor turnoContrario = PieceColor.Blancas;

        if(turn == PieceColor.Blancas)
            turnoContrario = PieceColor.Negras;

        //Toma la pieza "Rey" de las piezas del turno actual
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
                        System.out.println("Rey "+ rey.getPieceColor() +" en jaque");
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



    Piece getCapturedPieceIfCaptured(PieceColor pickedPieceColor, Square square){

        PieceColor capturedPieceColor = PieceColor.Blancas;
        Piece capturedPiece = null;

        if(pickedPieceColor == PieceColor.Blancas)
            capturedPieceColor = PieceColor.Negras;


        int pieceIndex = board.pieceSets[capturedPieceColor.ordinal()].getPieceIndexBySquareCoordinates(square);

        if(pieceIndex !=  board.pieceSets[capturedPieceColor.ordinal()].pieces.size()){
            capturedPiece = board.pieceSets[capturedPieceColor.ordinal()].pieces.get(pieceIndex);
            System.out.println("Pieza capturada: " + capturedPiece.getPieceType());
        }
        else
            System.out.println("Ninguna pieza capturada");



        return capturedPiece;

    }

    void MoveRequest(){

        PieceSet pickedPieceSet;
        Piece pickedPiece;
        Piece capturedPiece;
        Square moveSelected;
        List<Integer> movablePiecesList = new ArrayList<>();
        Piece lastMovedPiece = null;

        if(playedMoves.size()!=0)
            lastMovedPiece = playedMoves.get(playedMoves.size()-1).getMovedPiece();

        int pieceIndex;


        System.out.println("Turno de las " + turn);

        pickedPieceSet = board.pieceSets[turn.ordinal()];

        System.out.println("Piezas disponibles para mover: ");


        if(!IsChecked()){

            //Bloque que se ejecuta cuando no se está en check

            for (int i = 0; i < pickedPieceSet.pieces.size(); i++) {
                if(pickedPieceSet.pieces.get(i).getPieceType() != PieceType.Rey){
                    //Solo para las piezas que no sean el rey
                    if(pickedPieceSet.pieces.get(i).MovesWithOutCheckedMoves().length != 0){
                        movablePiecesList.add(i);
                        System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + " |");
                        System.out.println(" " + Rank.values()[pickedPieceSet.pieces.get(i).getPlaceAt().getColumn()] + (8-pickedPieceSet.pieces.get(i).getPlaceAt().getRow()));
                    }
                }
                else{
                    //Solo para el rey
                    if(pickedPieceSet.pieces.get(i).MovesWithOutCheckedMoves().length != 0){
                        movablePiecesList.add(i);
                        System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + " |");
                        System.out.println(" " + Rank.values()[pickedPieceSet.pieces.get(i).getPlaceAt().getColumn()] + (8-pickedPieceSet.pieces.get(i).getPlaceAt().getRow()));
                    }

                }

            }

        }
        else{

            for (int i = 0; i < pickedPieceSet.pieces.size(); i++) {

                if(pickedPieceSet.pieces.get(i).getPieceType() != PieceType.Rey){
                    //Solo para las piezas que no sean el rey
                    if(pickedPieceSet.pieces.get(i).MovesWhenInCheck().length != 0){
                        movablePiecesList.add(i);
                        System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + " |");
                        System.out.println(" " + Rank.values()[pickedPieceSet.pieces.get(i).getPlaceAt().getColumn()] + (8-pickedPieceSet.pieces.get(i).getPlaceAt().getRow()));
                    }
                }

                else{
                    //Solo para el rey
                    if(pickedPieceSet.pieces.get(i).MovesWithOutCheckedMoves().length != 0){
                        movablePiecesList.add(i);
                        System.out.print((i+1)+".- "+pickedPieceSet.pieces.get(i).getPieceType() + " |");
                        System.out.println(" " + Rank.values()[pickedPieceSet.pieces.get(i).getPlaceAt().getColumn()] + (8-pickedPieceSet.pieces.get(i).getPlaceAt().getRow()));
                    }

                }

            }

        }




        while (true){
            System.out.println("De las anteriores piezas listadas, seleccione la que desea mover");
            try {
                pieceIndex = new Scanner(System.in).nextInt()-1;
                break;
            }
            catch (Exception e){
                System.out.println("Valor introducido no valido. ");
            }
        }

        pickedPiece = pickedPieceSet.pieces.get(pieceIndex);


        System.out.println();
        System.out.println("Pieza Seleccionada: " + pickedPiece.getPieceType() + "|" +Rank.values()[pickedPiece.getPlaceAt().getColumn()] + (8-pickedPiece.getPlaceAt().getRow()));

        System.out.println("Movimientos disponibles: ");

        board.printBoardWithPossiblesMovements(pickedPiece.MovesWithOutCheckedMoves());

        System.out.println("Seleccione alguno: ");
        moveSelected = pickedPiece.Moves()[sc.nextInt()-1];
        capturedPiece = getCapturedPieceIfCaptured(pickedPiece.getPieceColor(),moveSelected);
        AddMove(pickedPiece.getPlaceAt(), moveSelected,pickedPiece,capturedPiece);

        setTurn(turn);



    }

    void setTurn(PieceColor actualTurn) {

        PieceColor turn = actualTurn;

        if (actualTurn == PieceColor.Negras)
            turn = PieceColor.Blancas;
        if (actualTurn == PieceColor.Blancas)
            turn = PieceColor.Negras;

        this.turn = turn;
    }



}