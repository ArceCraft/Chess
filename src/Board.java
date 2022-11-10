public class Board {

    private Square[][] squares = new Square[8][8];
    PieceSet[] pieceSets = new PieceSet[2];
    private final PieceColor pieceSetOnTop = PieceColor.Negras;

    Board(){

        Create();

    }

    public void Create(){

        SquaresFiller();
        pieceSets[0] = new PieceSet(this, PieceColor.Negras, pieceSetOnTop);
        pieceSets[1] = new PieceSet(this, PieceColor.Blancas, pieceSetOnTop);

    }


    void SquaresFiller(){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new Square(i,j);
            }
        }
    }

    Piece getPieceOnBoardSquare(Square square){

        Piece pieceAtSquare = null;

        for(PieceSet pieceSet : pieceSets){
            for(Piece piece : pieceSet.pieces){
                if(Square.squareComparator(piece.getPlaceAt(),square))
                    pieceAtSquare = piece;
            }
        }
        return  pieceAtSquare;
    }


    void PrintBoard() {

        boolean cellPainting = true;

        System.out.print(FrameGeneration("X"));

        for (int i = 0; i < 8; i++) {
            System.out.print(FrameGeneration(String.valueOf(Rank.values()[i])));
        }
        System.out.print(FrameGeneration("-"));



        for (int i = 0; i < squares.length ; i++) {

            System.out.println();
            System.out.print(FrameGeneration(String.valueOf(rowValues[i])));
            cellPainting = !cellPainting;

            for (Square square : squares[i]) {
                cellPainting = !cellPainting;

                if(getPieceOnBoardSquare(square) != null) {

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                }

                else{

                    if(cellPainting)
                        System.out.print(boardSymbol[0]);
                    else
                        System.out.print(boardSymbol[1]);
                }

            }
            System.out.print(FrameGeneration(" "));

        }
        System.out.println();
        for (int i = 0; i < 10; i++) {

            System.out.print(FrameGeneration("-"));

        }

        System.out.println();
    }

    void printBoardWithPossiblesMovements(Square[] moves){

        boolean cellPainting = true;


        for (int i = 0; i < moves.length; i++) {
            System.out.println((i+1)+".- "+ Rank.values()[moves[i].getColumn()] + "" + (8-moves[i].getRow()));
        }


        System.out.print(FrameGeneration(" "));

        for (int i = 0; i < 8; i++) {
            System.out.print(FrameGeneration(String.valueOf(Rank.values()[i])));
        }
        System.out.print(FrameGeneration("-"));

        for (int i = 0; i < squares.length ; i++) {

            System.out.println();
            System.out.print(FrameGeneration(String.valueOf(rowValues[i])));
            cellPainting = !cellPainting;

            for (Square square : squares[i]) {
                cellPainting = !cellPainting;

                if(getPieceOnBoardSquare(square) != null && isSelectable(moves,square)){

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_SELECT_ATTACK+ getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND_SELECT_ATTACK + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                    continue;
                }

                else if(getPieceOnBoardSquare(square) != null && !isSelectable(moves,square)) {

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                }

                else if(isSelectable(moves,square) && getPieceOnBoardSquare(square) == null ){
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
            System.out.print(FrameGeneration(" "));

        }
        System.out.println();
        for (int i = 0; i < 10; i++) {

            System.out.print(FrameGeneration("-"));

        }

        System.out.println();


    }

    void PrintFinalBoard(Result result) {

        boolean cellPainting = true;

        System.out.print(FrameGeneration("X"));

        for (int i = 0; i < 8; i++) {
            System.out.print(FrameGeneration(String.valueOf(Rank.values()[i])));
        }
        System.out.print(FrameGeneration(" "));

        for (int i = 0; i < squares.length ; i++) {

            System.out.println();
            System.out.print(FrameGeneration(String.valueOf(rowValues[i])));
            cellPainting = !cellPainting;

            for (Square square : squares[i]) {
                cellPainting = !cellPainting;

                if(getPieceOnBoardSquare(square) != null) {

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + getPieceOnBoardSquare(square).getPieceSymbol() + ConsoleColors.RESET);
                }

                else{

                    if(cellPainting)
                        System.out.print(boardSymbol[0]);
                    else
                        System.out.print(boardSymbol[1]);
                }

            }
            System.out.print(FrameGeneration(" "));
        }
        System.out.println();

        int j = 0;
        for (int i = 0; i < 10; i++) {

            if(i == 0)
                System.out.print(FrameGeneration("-"));
            else if (i<=String.valueOf(result).length()) {
                System.out.print(FrameGeneration(String.valueOf(String.valueOf(result).charAt(j))));
                j++;
            }
            else
                System.out.print(FrameGeneration("-"));


        }

        System.out.println();
    }


    boolean isSelectable(Square squares[], Square square){
        boolean isSelectable = false;

        for (int k = 0; k < squares.length; k++) {
            if(Square.squareComparator(squares[k],square))
                isSelectable = true;

        }

        return  isSelectable;
    }


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

    int[] rowValues = {
      8,7,6,5,4,3,2,1
    };


}


