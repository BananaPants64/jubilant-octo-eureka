import java.util.Scanner;

public class App {

    

    static String[][] goBoard = new String[9][9];

    static int countLiberties(String[][] board, int xPos, int yPos){
        //TODO
    }

    static void getBoard(String[][] b){
        System.out.print(" ");
            for (int i = 0; i < b.length; i++){
                System.out.print(" " + (i + 1));
            }
            System.out.println();

            //prints Board
            for (int i = 0; i < b.length; i++){

                //Prints y coordinate possibilities
                System.out.print(i + 1 + " ");

                for (int j = 0; j < b[i].length; j++){
                    if (b[i][j] == null){
                        if (j==0){
                            System.out.print("|");
                        }
                        else{
                            System.out.print("-|");
                        }
                    }
                    else{
                        System.out.print(goBoard[i][j]);
                    }
                }
                System.out.println();
            }
    }

    public static void main(String[] args) throws Exception {
        /* 
        goBoard[1][1] = "-□";
        goBoard[2][2] = "-■";
        */
        Boolean blackTurn = true;

        while (true){

            

            Scanner scan = new Scanner(System.in);
            
            
                //Gets user x coord
                int xCoord = 0;
                System.out.println("Enter an x coordinate (1-" + goBoard.length + ")");
                while (!(xCoord >= 1 && xCoord <= goBoard.length)){
                
                    xCoord = scan.nextInt();
                    if (!(xCoord >= 1 && xCoord <= goBoard.length)){
                        System.out.println("Enter a number in range.");
                    }

                }

                //Gets user y coord
                int yCoord = 0;
                System.out.println("Enter an y coordinate (1-" + goBoard[0].length + ")");
                while (!(yCoord >= 1 && yCoord <= goBoard[0].length)){
                
                    yCoord = scan.nextInt();
                    if (!(yCoord >= 1 && yCoord <= goBoard[0].length)){
                        System.out.println("Enter a number in range.");
                    }

                }

                //Sets peice on game board
                if (xCoord != 1 && blackTurn){
                    goBoard[yCoord - 1][xCoord - 1] = "-0";
                }
                else if (blackTurn){
                    goBoard[yCoord - 1][xCoord - 1] = "0";
                }
                else if (xCoord != 1 && !blackTurn){
                    goBoard[yCoord - 1][xCoord - 1] = "-O";
                }
                else{
                    goBoard[yCoord - 1][xCoord - 1] = "O";
                }
                getBoard(goBoard);
            

            blackTurn = !blackTurn;
        }
    }
}
