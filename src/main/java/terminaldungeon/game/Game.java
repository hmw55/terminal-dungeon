package terminaldungeon.game;

import java.util.Scanner;

/*
    Central coordinator of the game.
    Controls the main loop and game state transitions.
*/

public class Game {

    private GameState state;
    private boolean running;
    private Scanner scanner;

    public Game () {
        this.state = GameState.START;
        this.running = true;
        this.scanner = new Scanner(System.in);
    }

    /*
        Starts the main game loop
    */

    public void start() {
        while (running) {
            switch (state) {
                case START -> handleStart();
                case EXPLORING -> handleExploration();
                case EXIT -> running = false;
                default -> state = GameState.EXIT;
            }
        }
        scanner.close();
    }

    /*
        Handle the initial statup state.
    */

    private void handleStart() {
        System.out.println("Welcome to Terminal Dungeuon.");
        System.out.println("Press ENTER to start exploring...");
        scanner.nextLine();
        state = GameState.EXPLORING;
    }

    /*
        Exploration placeholder. 
        Right now it waits for simple commands.
    */

    private void handleExploration() {
        System.out.println("You are in a dark room. Type 'exit' to quit or 'look' to look around:");
        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "exit" -> state = GameState.EXIT;
            case "look" -> System.out.println("It's very dark. You see nothing.");
            default -> System.out.println("Unknown command: " + input);
        }

    }

}
