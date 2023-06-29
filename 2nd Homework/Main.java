package TictacToeWithComputer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a new scanner object to read user input
        Scanner sc = new Scanner(System.in);

        // Create a new TicTacToe object for the game
        TicTacToe game = new TicTacToe();

        // Display game mode options to the user
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Computer");
        System.out.print("Choose game mode (1, 2, or 3): ");

        // Read the user's choice of game mode
        int mode = sc.nextInt();

        // Play the selected game mode
        switch (mode) {
            case 1:
                game.playHumanVsHuman();
                break;
            case 2:
                game.playHumanVsComputer();
                break;
            case 3:
                game.playComputerVsComputer();
                break;
            default:
                System.out.println("Invalid game mode choice.");
                break;
        }
    }
}
