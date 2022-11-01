public class Board {

    private Square[][] squares = new Square[8][8];
    PieceSet[] pieceSets = new PieceSet[2];
    private final PieceColor pieceSetOnTop = PieceColor.Negras;

    Board(){

        Create();

    }

    public void Create(){

        pieceSets[0] = new PieceSet(this, PieceColor.Negras, pieceSetOnTop);
        pieceSets[1] = new PieceSet(this, PieceColor.Blancas, pieceSetOnTop);


        UpdateSquares();


    }

    public void FillSquaresWithPieces(PieceSet pieceSet){

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {

                for (Piece piece : pieceSet.pieces) {
                    if ((piece.getPlaceAt().getRow() == i) && (piece.getPlaceAt().getColumn() == j)) {
                        squares[i][j].setPiece(piece);
                    }

                }

            }

        }


    }

    void UpdateSquares(){

        SquaresFiller();
        FillSquaresWithPieces(pieceSets[0]);
        FillSquaresWithPieces(pieceSets[1]);

    }

    void SquaresFiller(){
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                squares[i][j] = new Square(i,j);
            }
        }
    }

    Square getSquareFromSquares(int row, int colum){

        return squares[row][colum];

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
                if(getSquareFromSquares(i,j).getPiece() != null) {
                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
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
                if(getSquareFromSquares(i,j).getPiece() != null && isSelectable(moves,i,j)){

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_SELECT_ATTACK+ getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND_SELECT_ATTACK + getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                    continue;
                }

                else if(getSquareFromSquares(i,j).getPiece() != null && !isSelectable(moves,i,j)) {

                    if (cellPainting)
                        System.out.print(ConsoleColors.WHITE_BACKGROUND_BRIGHT + getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                    else
                        System.out.print(ConsoleColors.BLACK_BACKGROUND + getSquareFromSquares(i,j).getPiece().getPieceSymbol() + ConsoleColors.RESET);
                }

                else if(isSelectable(moves,i,j) && getSquareFromSquares(i,j).getPiece() == null ){
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

    boolean isSelectable(Square square[],int i, int j){
        boolean isSelectable = false;

        for (int k = 0; k < square.length; k++) {
            if(square[k].getColumn() == j && square[k].getRow() == i)
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

}


