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

    public void printRoom(int playerX, int playerY) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print('P');
                } else {
                    System.out.print(tiles[y][x]);
                }
            }
            System.out.println();
        }
    } 
    
}
