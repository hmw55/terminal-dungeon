package terminaldungeon.game;

import java.util.Scanner;

import terminaldungeon.map.Room;
import terminaldungeon.entity.Player;
import terminaldungeon.item.Item;

/*
    Central coordinator of the game.
    Controls the main loop and game state transitions.
*/

public class Game {

    private GameState state;
    private boolean running;
    private Scanner scanner;
    private Player player;
    private Room currentRoom;
    private int playerX, playerY;

    public Game () {
        this.state = GameState.START;
        this.running = true;
        this.scanner = new Scanner(System.in);
        currentRoom = new Room (10, 6); // width=10, height=6
        playerX = 1; // starting X position
        playerY = 1; // starying Y position
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

        // Ask player for name
        System.out.print("Enter your character name: ");
        String name = scanner.nextLine().trim();

        // Chose class
        String characterClass = "";
        while (!characterClass.equals("warrior") && !characterClass.equals("mage") && !characterClass.equals("rogue")){
            System.out.print("Choose your class (Warrior / Mage / Rogue): ");
            characterClass = scanner.nextLine().trim().toLowerCase();
        }

        // Create player object
        player = new Player(name, characterClass);

        System.out.println("Welcome " + player.getName() + " the " + player.getCharacterClass() + "!");
        System.out.println("Starting items:");
        for (Item i : player.getInventory()) {
            System.out.println( "- " + i);
        }

        System.out.println("Press ENTER to start exploring... ");
        scanner.nextLine();
        state = GameState.EXPLORING;
    }

    /*
        Exploration placeholder. 
        Right now it waits for simple commands.
    */

    private void handleExploration() {
        
        currentRoom.printRoom(playerX, playerY);
        System.out.println("Move with W/A/S/D, type 'exit' to quit:");

        String input = scanner.nextLine().trim().toLowerCase();

        switch (input) {
            case "w" -> tryMove(0, -1); // up
            case "s" -> tryMove(0, 1); // down
            case "a" -> tryMove(-1, 0); // left
            case "d" -> tryMove(1, 0); // right
            case "exit" -> state = GameState.EXIT;
            default -> System.out.println("Unknown command: " + input);
        }
    }

    /*
        Move the player if the target tile is not a wall
    */
   private void tryMove(int dx, int dy) {
    int newX = playerX + dx;
    int newY = playerY + dy;
    char [][] tiles = currentRoom.getTiles();

    if (tiles[newY][newX] != '#') {
        playerX = newX;
        playerY = newY;
    } else {
        System.out.println("You bumb into a wall.");
    }
   }

}
