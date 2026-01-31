package terminaldungeon.map;

/*
    Represents a single room in the dungeon.
    Uses a 2D char array as a simple map.

*/
public class Room {

    private char[][] tiles;
    private int width;
    private int height;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new char[height][width];
        // generateEmptyRoom(); // not needed with RoomGenerator.java
    }

    /*
        Fill the room with walls on edges and floor inside
        NOT NEEDED WITH RoomGenerator.java
    */

    // private void generateEmptyRoom() {
    //     for (int y = 0; y < height; y++) {
    //         for (int x = 0; x < width; x++) {
    //             if (y == 0 || y == height - 1 || x == 0 || x == width - 1) {
    //                 tiles[y][x] = '#'; // Wall
    //             } else {
    //                 tiles[y][x] = '.'; // Floor
    //             }
    //         }
    //     }
    //     // Place a door for testing
    //     tiles[height/2][width-1] = 'D';
    // }

    public char[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    /*
        Render the room to the console, marking the player's position.
    */

    public void printRoom(int playerX, int playerY, boolean highlight) {
        char[][] tiles = this.tiles;
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles[y].length; x++) {
                char tile = tiles[y][x];

                // Highlight the player and walls with different colors
                if (x == playerX && y == playerY) {
                    // Print the player in green
                    System.out.print("\033[32mP\033[0m");  // Green for the player
                } else if (tile == '#') {
                    // Normal wall
                    System.out.print("\033[37m#\033[0m");  // Default color for the wall
                } else if (highlight && tile == 'R') {
                    // Highlighted wall (red when bumped)
                    System.out.print("\033[31m#\033[0m");  // Red for the wall on bump
                } else {
                    // Default floor or other tiles
                    System.out.print("\033[0m" + tile);
                }
            }
            System.out.println();
        }
    }
    
}
