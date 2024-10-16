import java.util.Scanner;

public class App {

    

    static String[][] goBoard = new String[9][9];

    static boolean hasLiberties(String[][] board, int xPos, int yPos, int lastDirection){
        /* 
         * lastDirection denotes which direction the search came from
         * this prevents double backing on itself
         * the initial call to this function always inputs 0 denoting no initial direction
         * 1 = up
         * 2 = left
         * 3 = down
         * 4 = right
         */
        
        if (board[xPos][yPos] != null){
            //check 4 orthogonal sides
            if (xPos > 0 && board[xPos - 1][yPos] == null){
                return true;
            }
            if (xPos < board.length - 1 && board[xPos + 1][yPos] == null){
                return true;
            }
            if (yPos > 0 && board[xPos][yPos - 1] == null){
                return true;
            }
            if (yPos < board[xPos].length - 1 && board[xPos][yPos + 1] == null){
                return true;
            }

            //check adjacent pieces of same type
            //up
            if (yPos > 0 && board[xPos][yPos].equals(board[xPos][yPos - 1]) && lastDirection != 3){
                if (hasLiberties(board, xPos, yPos - 1, 1)){
                    return true;
                }
            }
            //left
            if (xPos > 0 && board[xPos][yPos].equals(board[xPos - 1][yPos]) && lastDirection != 4){
                if (hasLiberties(board, xPos - 1, yPos, 2)){
                    return true;
                }
            }
            //down
            if (yPos < board[xPos].length - 1 && board[xPos][yPos].equals(board[xPos][yPos + 1]) && lastDirection != 1){
                if (hasLiberties(board, xPos, yPos + 1, 3)){
                    return true;
                }
            }
            //right
            if (xPos < board.length - 1 && board[xPos][yPos].equals(board[xPos + 1][yPos]) && lastDirection != 2){
                if (hasLiberties(board, xPos + 1, yPos, 4)){
                    return true;
                }
            }

            return false;
        }
        //dont want to remove pieces that are empty
        return true;
    }

    static int removeGroup (String[][] board, int xPos, int yPos, int piecesRemoved){
        String pieceType = board[xPos][yPos];

        board[xPos][yPos] = null;
        piecesRemoved ++;
        if (yPos > 0 && pieceType.equals(board[xPos][yPos - 1])){
            removeGroup(board, xPos, yPos - 1, piecesRemoved);
        }

        if (xPos > 0 && pieceType.equals(board[xPos - 1][yPos])){
            removeGroup(board, xPos - 1, yPos, piecesRemoved);
        }

        if (yPos < board[xPos].length - 1 && pieceType.equals(board[xPos][yPos + 1])){
            removeGroup(board, xPos, yPos + 1, piecesRemoved);
        }

        if (xPos < board.length - 1 && pieceType.equals(board[xPos + 1][yPos])){
            removeGroup(board, xPos + 1, yPos, piecesRemoved);
        }

        return piecesRemoved;
    }

    static void getBoard(String[][] b){
        System.out.print(" ");

            //prints x coordinate possibilities
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
                        System.out.print("|");
                    }
                    else{
                        System.out.print(goBoard[i][j]);
                    }
                    if (j != b[i].length - 1){
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
    }

    public static void main(String[] args) {
        
        Boolean blackTurn = true;

        int whiteScore = 0;
        int blackScore = 0;

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
                if (blackTurn){
                    goBoard[yCoord - 1][xCoord - 1] = "0";
                }
                else{
                    goBoard[yCoord - 1][xCoord - 1] = "O";
                }

                
                

                //check for captures
                for (int i = 0; i < goBoard.length; i++){
                    for (int j = 0; j < goBoard[i].length; j++){
                        if (!hasLiberties(goBoard, i, j, 0)){
                            if (goBoard[i][j].equals("0")){
                                blackScore += removeGroup(goBoard, i, j, 0);
                            }
                            else{
                                whiteScore += removeGroup(goBoard, i, j, 0);
                            }
                        }
                    }
                }
            
                //print board and score
                getBoard(goBoard);

                System.out.println("White Score: " + whiteScore);
                System.out.println("Black Score: " + blackScore + "\n");
                

            blackTurn = !blackTurn;
        }
    }
}
