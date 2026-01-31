package terminaldungeon.game;

/*
    Representes the current phase of the game.
    The Game class switches behaviors based on this.
*/

public enum GameState {
    START,
    EXPLORING, 
    COMBAT,
    EVENT, 
    GAME_OVER,
    EXIT
}