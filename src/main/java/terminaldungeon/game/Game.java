package terminaldungeon.game;

/*
    Central coordinator of the game.
    Controls the main loop and game state transitions.
*/

public class Game {

    private GameState state;
    private boolean running;

    public Game () {
        this.state = GameState.START;
        this.running = true;
    }

    /*
        Starts the main game loop
    */

    public void start() {
        while (running) {
            switch (state) {
                case START -> handleStart();
                case EXIT -> running = false;
                default -> state = GameState.EXIT;
            }
        }
    }

    /*
        Handle the initial statup state.
    */

        private void handleStart() {
            System.out.println("Welcome to Terminal Dungeuon.");
            state = GameState.EXIT;
        }

}
