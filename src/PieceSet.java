import java.util.ArrayList;
import java.util.List;



public class PieceSet {

    List<Piece> pieces;
    private  PieceColor color;

    private PieceColor pieceOnTop;

    private Board board;

    PieceSet(Board board,PieceColor color, PieceColor pieceOnTop){
        this.board = board;
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


    int getPiecesBySquareCoordinates(Square square){
        int pieceIndex = 0;
        for(Piece piece : pieces){
            if(square.getRow() == piece.getPlaceAt().getRow() && square.getColumn() == piece.getPlaceAt().getColumn())
                break;
            pieceIndex++;
        }

        return pieceIndex;

    }

    void AddPawns(){

        Piece[] pieceType = new Piece[8];
        String pieceSymbol = piecesSymbols[PieceType.Pawn.ordinal()];

        for (int i = 0; i < pieceType.length; i++) {
            pieceType[i] = new Pawn(board);
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

            ConsoleColors.BLUE_BRIGHT +" ♚ "+ ConsoleColors.RESET, // White King      0
            ConsoleColors.BLUE_BRIGHT +" ♛ "+ ConsoleColors.RESET, // White Queen     1
            ConsoleColors.BLUE_BRIGHT +" ♜ "+ ConsoleColors.RESET, // White Rook      2
            ConsoleColors.BLUE_BRIGHT +" ♝ "+ ConsoleColors.RESET, // White Bishop    3
            ConsoleColors.BLUE_BRIGHT +" ♞ "+ ConsoleColors.RESET, // White Knight    4
            ConsoleColors.BLUE_BRIGHT +" ♙ "+ ConsoleColors.RESET, // White Pawn      5
            ConsoleColors.RED_BRIGHT  +" ♚ "+ ConsoleColors.RESET,  // Black King      6
            ConsoleColors.RED_BRIGHT  +" ♛ "+ ConsoleColors.RESET, // Black Queen     7
            ConsoleColors.RED_BRIGHT  +" ♜ "+ ConsoleColors.RESET, // Black Rook      8
            ConsoleColors.RED_BRIGHT  +" ♝ "+ ConsoleColors.RESET, // Black Bishop    9
            ConsoleColors.RED_BRIGHT  +" ♞ "+ ConsoleColors.RESET, // Black Knight    10
            ConsoleColors.RED_BRIGHT  +" ♙ "+ ConsoleColors.RESET, // Black Pawn      11

    };

}
