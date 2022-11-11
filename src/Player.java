import java.util.List;
import java.util.Scanner;

public class Player {

    private PieceColor pieceColor;

    Player(PieceColor pieceColor){

        this.pieceColor = pieceColor;

    }


    Piece selectPiece(PieceSet pieceSet, List<Integer> moves){

        Piece pieceSelected = null;
        int pieceIndex;
        while (true){
            while (true){
                try {
                    pieceIndex = new Scanner(System.in).nextInt()-1;
                    break;
                } catch (Exception e){
                    System.out.println("Valor introducido no valido. ");
                }
            }

            if(moves.contains(pieceIndex)){
                pieceSelected = pieceSet.pieces.get(pieceIndex);
                break;
            }
            else{
                System.out.println("No se seleccionó una opción válida, por favor vuelva a seleccionar la pieza que desea mover: ");
            }

        }


        return pieceSelected;
    }

    Square selectMove(Square[] movesOfThePiece){

        Square selectedMove;
        int moveIndex;

        while (true){
            while (true){
                try {
                    moveIndex = new Scanner(System.in).nextInt()-1;
                    break;
                } catch (Exception e){
                    System.out.println("Valor introducido no valido. ");
                }
            }

            if(!(moveIndex<0 || moveIndex>=movesOfThePiece.length)){
                selectedMove = movesOfThePiece[moveIndex];
                break;
            }
            else{
                System.out.println("No se seleccionó una opción válida, por favor vuelva a seleccionar la pieza que desea mover: ");
            }

        }

        return selectedMove;
    }

    boolean makeChoice(){
        boolean choice;
        int choiceInt;

        System.out.println("1.- Sí");
        System.out.println("2.- No");

        while (true){
            while (true){
                try {
                    choiceInt = new Scanner(System.in).nextInt();
                    break;
                } catch (Exception e){
                    System.out.println("Valor introducido no valido. ");
                }
            }

            if(choiceInt == 1 || choiceInt == 2){
                if(choiceInt == 1)
                    choice =true;
                else
                    choice = false;
                break;
            }
            else{
                System.out.println("No se seleccionó una opción válida, por favor, introduzca una opción válida");
            }

        }

        return choice;
    }
}
