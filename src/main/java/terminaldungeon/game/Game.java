package terminaldungeon.game;

import java.util.Scanner;

import terminaldungeon.map.Room;
import terminaldungeon.map.RoomGenerator;
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
        currentRoom = RoomGenerator.generateRoom(50, 10); // randomly generate room
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

    // Capitalize first letter, lowercase the rest

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    /*
        Handle the initial statup state.
    */

    private void handleStart() {

        System.out.println("\nWelcome to Terminal Dungeon.\n");

        // Ask player for name
        System.out.print("Enter your character name: ");
        String name = capitalize(scanner.nextLine().trim());
    
        // Chose class
        String characterClass = "";
        while (!characterClass.equals("warrior") && !characterClass.equals("mage") && !characterClass.equals("rogue")){
            System.out.print("Choose your class (Warrior / Mage / Rogue): ");
            characterClass = scanner.nextLine().trim().toLowerCase();
        }
        characterClass = capitalize(characterClass);

        // Create player object
        player = new Player(name, characterClass);

        System.out.println("\nWelcome " + player.getName() + " the " + player.getCharacterClass() + "!");
        System.out.println("Starting items:");
        for (Item i : player.getInventory()) {
            System.out.println( "- " + i);
        }

        System.out.println("\nPress ENTER to start exploring... ");
        scanner.nextLine();
        state = GameState.EXPLORING;
    }

    /*
        Exploration loop
    */

    private void handleExploration() {
        currentRoom.printRoom(playerX, playerY, false);

        while (state == GameState.EXPLORING) {
            System.out.println("Move with W/A/S/D (you can type multiple letters.");
            System.out.println("Type 'help' for commands (stats, inventory, exit).");
            System.out.print("> ");

            String input = scanner.nextLine().trim().toLowerCase();        

            switch (input) {
                case "help" -> {
                    System.out.println("\nAvailable commands:");
                    System.out.println("- W/A/S/D: Move up/left/down/right (you can type multiple letters)");
                    System.out.println("- stats: View your character stats");
                    System.out.println("- inventory: View your inventory");
                    System.out.println("- map: Redraw the current room map");
                    System.out.println("- exit: Quit the game\\n");
                }
                case "stats" -> {
                    System.out.println("\nCharacter Stats:");
                    System.out.println("- Name: " + player.getName());
                    System.out.println("- Class: " + player.getCharacterClass());
                    // TODO: Add more stats here in the future
                    System.out.println();
                }
                case "inventory" -> {
                    System.out.println("\nInventory:");
                        for (Item item : player.getInventory()) {
                            System.out.println("- " + item);
                        }
                    System.out.println();
                }
                case "map" -> {
                    currentRoom.printRoom(playerX, playerY, false);
                }
                case "exit" -> state = GameState.EXIT;
                default -> { //Assume movement string
                    int movesMade = 0;
                    for (char c : input.toCharArray()) {
                        boolean moved = switch (c) {
                            case 'w' -> tryMove(0, -1); // up
                            case 's' -> tryMove(0, 1); // down
                            case 'a' -> tryMove(-1, 0); // left
                            case 'd' -> tryMove(1, 0); // right
                            default -> { 
                                System.out.println("Unknown command: " + c);
                                yield false;
                            }
                        };
                        if (moved) movesMade++;
                        else break;
                    }
                    if (movesMade > 0) {
                        System.out.println("\nYou moved " + movesMade + " step" + (movesMade > 1 ? "s" : "") + "!\n");
                        currentRoom.printRoom(playerX, playerY, false);
                    }
                }
            }
        }
    }

    /*
        Move the player if the target tile is not a wall
    */
    private boolean tryMove(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;
        char [][] tiles = currentRoom.getTiles();

        if (tiles[newY][newX] == '#') {
            // Temporarily highlight the wall with red
            System.out.println("\033[31mThud! That wall isn't going anywhere.\033[0m");

            // Temporarily change the wall color to red
            tiles[newY][newX] = 'R'; // placeholder for red wall

            // Print the room with the highlighted wall
            currentRoom.printRoom(playerX, playerY, true); // Pass an addition flad for printing

            // Delay a moment to show the highlight
            try {
                Thread.sleep(500); // half second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Restore the wall symbil back to a regular "#"
            tiles[newY][newX] = '#';

            // Print the room again with normal walls
            currentRoom.printRoom(playerX, playerY, false);
            return false;
        }

        playerX = newX;
        playerY = newY;
        return true; // successful move
        
    }
}
