import java.util.*;

public class Minigame{
    char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' ',}, {' ',' ',' ',}};
    char player = 'X';
    public void printBoard(){
        System.out.println("Board: ");
        for(int row = 0; row < board.length; row++){
            if(row != 0){
                System.out.println("------");
            }
            for(int column = 0; column < board[row].length; column++){
                if(column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }
    public void turn(){
        Scanner input = new Scanner(System.in);
        int row;
        int column;
        do{
            System.out.println("Player: " + player + " Enter row and column 0-2");
            while(!input.hasNextInt()){
            System.out.println("Player: " + player + " Enter row and column 0-2");
            input.next();
            }
            row = input.nextInt();
            column = input.nextInt();
            while (row > 2 || column > 2 || row < 0 || column < 0) {
                System.out.println("Please Enter Specified Choices");
                System.out.println("Player: " + player + " Enter row and column 0-2");
                row = input.nextInt();
                column = input.nextInt();
            }
            break;
        }while(true);
        

        if(board[row][column] == ' '){
            board[row][column] = player;
        }else{
            System.out.println("Entered Column and row already has value in it, please enter different row and column value");
            turn();
        }
    }
    public void switchPlayer(){
        player = (player == 'X') ? 'O' : 'X';
    }
    public boolean checkRowCol(char c1, char c2, char c3){
        return (c1 != ' ' && c1 == c2 && c2 == c3);
    }
    public boolean gameState(){
        for(int i = 0; i < 3; i++){
                if(checkRowCol(board[i][0], board[i][1], board[i][2]) || checkRowCol(board[0][i], board[1][i], board[2][i]))
                return true;
        }
        return(checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
        
    }
    public boolean isBoardFull(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if(board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Minigame obj = new Minigame();
        
        obj.printBoard();
        while(!obj.gameState() || !obj.isBoardFull()){
            obj.turn();
            if(obj.gameState()){
                System.out.println("Player: " + obj.player + " won");
                obj.printBoard();
                System.exit(0);
            }else if(obj.isBoardFull()){
                System.out.println("Maximum Turns has been reached. TIE");
                obj.printBoard();
                System.exit(0);
            }
            obj.switchPlayer();
            obj.printBoard();
        }
        
    }
}