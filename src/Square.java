public class Square {
    private int row;
    private int column;

    private Piece piece;

    Square(int row, int column){

        this.row = row;
        this.column = column;

    }

    int getRow(){
        return row;
    }

    int getColumn(){
        return column;
    }

    Piece getPiece(){
        return  piece;
    }

    void setPiece(Piece piece){
        this.piece = piece;
    }
}
