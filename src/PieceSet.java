import java.util.ArrayList;
import java.util.List;



public class PieceSet {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    List<Piece> pieces;
    private  PieceColor color;

    private PieceColor pieceOnTop;

    PieceSet(PieceColor color, PieceColor pieceOnTop){

        this.color = color;
        this.pieceOnTop = pieceOnTop;

        pieces = new ArrayList<Piece>();

        AddPawns();
        AddRooks();
        AddKnights();
        AddBishops();
        AddQueen();
        AddKing();

    }

    void AddPawns(){

        Piece[] pieceType = new Piece[8];
        String pieceSymbol = piecesSymbols[PieceType.Pawn.ordinal()];

        for (int i = 0; i < pieceType.length; i++) {
            pieceType[i] = new Pawn();
            pieceType[i].setPieceColor(color);
            pieceType[i].setPieceType(PieceType.Pawn);

            if(pieceOnTop == color)
                pieceType[i].setPlaceAt(new Square(1,i));
            else
                pieceType[i].setPlaceAt(new Square(6,i));
            if(color == PieceColor.Black)
                pieceSymbol = piecesSymbols[PieceType.Pawn.ordinal()+6];

            pieceType[i].setPieceSymbol(pieceSymbol);

            pieces.add(pieceType[i]);
        }
    }

    void AddRooks(){

        int row = 0;
        String pieceSymbol = piecesSymbols[PieceType.Rook.ordinal()];


        Piece[] pieceType = new Piece[2];


        pieceType[0] = new Rook();
        pieceType[1] = new Rook();

        pieceType[0].setPieceColor(color);
        pieceType[1].setPieceColor(color);

        if(pieceOnTop != color)
            row = 7;

        if(color == PieceColor.Black)
            pieceSymbol = piecesSymbols[PieceType.Rook.ordinal()+6];




        pieceType[0].setPlaceAt(new Square(row,0));
        pieceType[1].setPlaceAt(new Square(row,7));

        pieceType[0].setPieceType(PieceType.Rook);
        pieceType[1].setPieceType(PieceType.Rook);

        pieceType[0].setPieceSymbol(pieceSymbol);
        pieceType[1].setPieceSymbol(pieceSymbol);


        pieces.add(pieceType[0]);
        pieces.add(pieceType[1]);

    }

    void AddKnights(){

        int row = 0;
        String pieceSymbol = piecesSymbols[PieceType.Knight.ordinal()];

        Piece[] pieceType = new Piece[2];


        pieceType[0] = new Knight();
        pieceType[1] = new Knight();

        pieceType[0].setPieceColor(color);
        pieceType[1].setPieceColor(color);

        if(pieceOnTop != color)
            row = 7;
        if(color == PieceColor.Black)
            pieceSymbol = piecesSymbols[PieceType.Knight.ordinal()+6];

        pieceType[0].setPlaceAt(new Square(row,1));
        pieceType[1].setPlaceAt(new Square(row,6));

        pieceType[0].setPieceType(PieceType.Knight);
        pieceType[1].setPieceType(PieceType.Knight);

        pieceType[0].setPieceSymbol(pieceSymbol);
        pieceType[1].setPieceSymbol(pieceSymbol);

        pieces.add(pieceType[0]);
        pieces.add(pieceType[1]);

    }


    void AddBishops(){

        int row = 0;
        String pieceSymbol = piecesSymbols[PieceType.Bishop.ordinal()];

        Piece[] pieceType = new Piece[2];


        pieceType[0] = new Bishop();
        pieceType[1] = new Bishop();

        pieceType[0].setPieceColor(color);
        pieceType[1].setPieceColor(color);

        if(pieceOnTop != color)
            row = 7;
        if(color == PieceColor.Black)
            pieceSymbol = piecesSymbols[PieceType.Bishop.ordinal()+6];

        pieceType[0].setPlaceAt(new Square(row,2));
        pieceType[1].setPlaceAt(new Square(row,5));

        pieceType[0].setPieceType(PieceType.Bishop);
        pieceType[1].setPieceType(PieceType.Bishop);

        pieceType[0].setPieceSymbol(pieceSymbol);
        pieceType[1].setPieceSymbol(pieceSymbol);

        pieces.add(pieceType[0]);
        pieces.add(pieceType[1]);

    }


    void AddQueen() {

        int row = 0;
        String pieceSymbol = piecesSymbols[PieceType.Queen.ordinal()];

        Piece queen = new Queen();
        queen.setPieceColor(color);

        if (pieceOnTop != color)
            row = 7;
        if (color == PieceColor.Black)
            pieceSymbol = piecesSymbols[PieceType.Queen.ordinal() + 6];

        queen.setPlaceAt(new Square(row, 4));
        queen.setPieceType(PieceType.Queen);
        queen.setPieceSymbol(pieceSymbol);

        pieces.add(queen);

    }

    void AddKing(){

        int row = 0;
        String pieceSymbol = piecesSymbols[PieceType.King.ordinal()];

        Piece king = new King();
        king.setPieceColor(color);

        if(pieceOnTop != color)
            row = 7;
        if (color == PieceColor.Black)
            pieceSymbol = piecesSymbols[PieceType.King.ordinal() +6];

        king.setPlaceAt(new Square(row,3));
        king.setPieceType(PieceType.King);
        king.setPieceSymbol(pieceSymbol);

        pieces.add(king);

    }

    String[] piecesSymbols ={
            ANSI_BLUE +" ♚"+ ANSI_RESET, // White King      0
            ANSI_BLUE +" ♛"+ ANSI_RESET, // White Queen     1
            ANSI_BLUE +" ♜"+ ANSI_RESET, // White Rook      2
            ANSI_BLUE +" ♝"+ ANSI_RESET, // White Bishop    3
            ANSI_BLUE +" ♞"+ ANSI_RESET, // White Knight    4
            ANSI_BLUE +" ♙"+ ANSI_RESET, // White Pawn      5
            ANSI_RED  +" ♚"+ ANSI_RESET,  // Black King      6
            ANSI_RED  +" ♛"+ ANSI_RESET, // Black Queen     7
            ANSI_RED  +" ♜"+ ANSI_RESET, // Black Rook      8
            ANSI_RED  +" ♝"+ ANSI_RESET, // Black Bishop    9
            ANSI_RED  +" ♞"+ ANSI_RESET, // Black Knight    10
            ANSI_RED  +" ♙"+ ANSI_RESET, // Black Pawn      11

    };

}
