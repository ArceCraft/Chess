public class Board {

    private Square[][] squares = new Square[8][8];
    PieceSet[] pieceSets = new PieceSet[2];
    private final PieceColor pieceSetOnTop = PieceColor.Black;

    Board(){

        Create();

    }

    public void Create(){

        pieceSets[0] = new PieceSet(this, PieceColor.Black, pieceSetOnTop);
        pieceSets[1] = new PieceSet(this, PieceColor.White, pieceSetOnTop);


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

}


