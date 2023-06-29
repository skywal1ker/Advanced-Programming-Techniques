package TictacToeWithComputer;
import java.util.Scanner;
// Define an enumeration for the possible values of each cell in the tic-tac-toe board.
enum Marker {
    X, // Marker for player X
    O, // Marker for player O
    EMPTY // Marker for an empty cell
}

// Define a class for the tic-tac-toe game.
class TicTacToe {
    // Create a 2D array to represent the game board.
    private Marker[][] board;

    // Constructor for the TicTacToe class.
    TicTacToe() {
        // Initialize the game board with empty cells.
        board = new Marker[3][3]; // Create a 3x3 array.
        for (int i = 0; i < 3; i++) { // Loop through each row.
            for (int j = 0; j < 3; j++) { // Loop through each column in the row.
                board[i][j] = Marker.EMPTY; // Set the cell to the EMPTY marker.
            }
        }
    }


    public void playHumanVsHuman() {
        // Create a new scanner object to read user input from the console
        Scanner sc = new Scanner(System.in);

        // Ask the first human player which marker they want to use (X or O)
        System.out.println("Human 1, do you want to be X or O?");
        String input = sc.nextLine();

        // Set the markers for each player based on the user's choice
        Marker human1 = Marker.X;
        Marker human2 = Marker.O;
        if (input.equalsIgnoreCase("O")) {
            human1 = Marker.O;
            human2 = Marker.X;
        }

        // Initialize the move count to 0
        int moveCount = 0;

        // Loop until a player wins or the game ends in a draw
        while (true) {
            // Determine which player's turn it is based on the move count
            if (moveCount % 2 == 0) {
                // If it's player 1's turn, ask them to make a move
                System.out.println("Human 1, make your move (choose a number between 1-9):");
                int choice = sc.nextInt();

                // Calculate the row and column of the chosen cell
                int row = (choice - 1) / 3;
                int col = (choice - 1) % 3;

                // Check if the chosen cell is empty, and if so, mark it with the player's marker
                if (board[row][col] == Marker.EMPTY) {
                    board[row][col] = human1;
                    moveCount++;
                } else {
                    // If the chosen cell is not empty, inform the user and continue the loop
                    System.out.println("This cell is already filled. Choose another cell.");
                    continue;
                }
            } else {
                // If it's player 2's turn, ask them to make a move
                System.out.println("Human 2, make your move (choose a number between 1-9):");
                int choice = sc.nextInt();

                // Calculate the row and column of the chosen cell
                int row = (choice - 1) / 3;
                int col = (choice - 1) % 3;

                // Check if the chosen cell is empty, and if so, mark it with the player's marker
                if (board[row][col] == Marker.EMPTY) {
                    board[row][col] = human2;
                    moveCount++;
                } else {
                    // If the chosen cell is not empty, inform the user and continue the loop
                    System.out.println("This cell is already filled. Choose another cell.");
                    continue;
                }
            }

            // Display the updated board after each move
            displayBoard();

            // Check if either player has won the game
            if (isWon(human1)) {
                System.out.println("Human 1 wins!");
                break;
            }
            if (isWon(human2)) {
                System.out.println("Human 2 wins!");
                break;
            }

            // Check if the game has ended in a draw
            if (isDraw()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }


    public void playHumanVsComputer() {
        Scanner sc = new Scanner(System.in);

        // Prompt the user to choose their marker
        System.out.println("Human, do you want to be X or O?");
        String input = sc.nextLine();
        Marker human = Marker.X;
        Marker computer = Marker.O;
        if (input.equalsIgnoreCase("O")) {
            human = Marker.O;
            computer = Marker.X;
        }

        // Ask the user if they want to go first
        System.out.println("Do you want to go first? (choose 1 for yes, choose 2 for no)");
        int choice = sc.nextInt();
        boolean humanGoesFirst = true;
        if (choice == 2) {
            humanGoesFirst = false;
        }

        int moveCount = 0;
        while (true) {
            if (moveCount % 2 == 0 && humanGoesFirst || moveCount % 2 == 1 && !humanGoesFirst) {
                // It is the human's turn
                System.out.println("Human, make your move (choose a cell number from 1-9):");
                int cellNumber = sc.nextInt();
                int row = (cellNumber - 1) / 3;
                int col = (cellNumber - 1) % 3;
                if (board[row][col] == Marker.EMPTY) {
                    // Update the board with the human's move
                    board[row][col] = human;
                    moveCount++;
                } else {
                    // The cell is already filled, prompt the user to choose another cell
                    System.out.println("This cell is already filled. Choose another cell.");
                    continue;
                }
            } else {
                // It is the computer's turn
                System.out.println("Computer's turn:");
                int row = (int) (Math.random() * 3);
                int col = (int) (Math.random() * 3);
                if (board[row][col] == Marker.EMPTY) {
                    // Update the board with the computer's move
                    board[row][col] = computer;
                    moveCount++;
                } else {
                    // The randomly selected cell is already filled, try again
                    continue;
                }
            }
            // Display the current state of the board
            displayBoard();
            // Check if the game is over
            if (isWon(human)) {
                System.out.println("Human wins!");
                break;
            }
            if (isWon(computer)) {
                System.out.println("Computer wins!");
                break;
            }
            if (isDraw()) {
                System.out.println("It's a draw!");
                break;
            }
        }
    }


    public void playComputerVsComputer() {
        int moveCount = 0; // keep track of the number of moves made
        while (true) { // loop until the game is over
            if (moveCount % 2 == 0) { // if the move count is even, it's Computer 1's turn
                System.out.println("Computer 1's turn:");
                int row = (int) (Math.random() * 3); // choose a random row
                int col = (int) (Math.random() * 3); // choose a random column
                if (board[row][col] == Marker.EMPTY) { // if the chosen cell is empty
                    board[row][col] = Marker.X; // mark the cell with an X
                    moveCount++; // increment the move count
                } else { // if the chosen cell is not empty
                    continue; // skip this iteration of the loop and try again
                }
            } else { // if the move count is odd, it's Computer 2's turn
                System.out.println("Computer 2's turn:");
                int row = (int) (Math.random() * 3); // choose a random row
                int col = (int) (Math.random() * 3); // choose a random column
                if (board[row][col] == Marker.EMPTY) { // if the chosen cell is empty
                    board[row][col] = Marker.O; // mark the cell with an O
                    moveCount++; // increment the move count
                } else { // if the chosen cell is not empty
                    continue; // skip this iteration of the loop and try again
                }
            }
            displayBoard(); // display the current state of the board
            try {
                Thread.sleep(2000); // wait for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace(); // print the stack trace if an exception is caught
            }
            if (isWon(Marker.X)) { // if Computer 1 has won
                System.out.println("Computer 1 wins!");
                break; // exit the loop
            }
            if (isWon(Marker.O)) { // if Computer 2 has won
                System.out.println("Computer 2 wins!");
                break; // exit the loop
            }
            if (isDraw()) { // if the game is a draw
                System.out.println("It's a draw!");
                break; // exit the loop
            }
        }
    }


    // Define the private method to display the current state of the board
    private void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Print each board element separated by a space
                System.out.print(board[i][j] + " ");
            }
            // Move to a new line after printing the row
            System.out.println();
        }
        // Print an empty line for spacing
        System.out.println();
    }

    // Define the private method to check if the given marker has won
    private boolean isWon(Marker m) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            boolean won = true;
            for (int j = 0; j < 3; j++) {
                // If the element in the row does not match the marker, this row cannot be won by the marker
                if (board[i][j] != m) {
                    won = false;
                    break;
                }
            }
            // If all elements in this row match the marker, the marker has won
            if (won) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            boolean won = true;
            for (int i = 0; i < 3; i++) {
                // If the element in the column does not match the marker, this column cannot be won by the marker
                if (board[i][j] != m) {
                    won = false;
                    break;
                }
            }
            // If all elements in this column match the marker, the marker has won
            if (won) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == m && board[1][1] == m && board[2][2] == m) {
            return true;
        }
        if (board[0][2] == m && board[1][1] == m && board[2][0] == m) {
            return true;
        }
        // If the marker does not win by any of the above conditions, the marker has not won
        return false;
    }

    // Define the private method to check if the game is a draw
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // If there is an empty space on the board, the game is not a draw
                if (board[i][j] == Marker.EMPTY) {
                    return false;
                }
            }
        }
        // If all spaces on the board are filled and there is no winner, the game is a draw
        return true;
    }


}
