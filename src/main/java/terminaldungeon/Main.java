package terminaldungeon;

import terminaldungeon.game.Game;

/*
    Entry point for the application.
    This class only exists to start the game.
    It should stay very small
*/

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}