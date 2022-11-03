public class Square {
    private int row;
    private int column;


    Square(int row, int column){

        this.row = row;
        this.column = column;

    }


    static boolean squareComparator(Square squareFrom,Square squareTo){

        boolean areEqual = false;

        if(squareFrom.getRow() == squareTo.getRow() && squareFrom.getColumn() == squareTo.getColumn())
            areEqual = true;

        return  areEqual;
    }

    static Square squareAddition(Square squareFrom,Square squareTo){

        return new Square(squareFrom.getRow()+ squareFrom.getRow(), squareFrom.getColumn() + squareTo.getColumn());

    }

    int getRow(){
        return row;
    }

    int getColumn(){
        return column;
    }

}
