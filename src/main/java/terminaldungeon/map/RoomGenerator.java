package terminaldungeon.map;

import java.util.Random;

public class RoomGenerator {

    private  static final Random rand = new Random();

    // Generates a room of given width and height
    public static Room generateRoom(int width, int height) {
        Room room = new Room(width, height);
        char[][] tiles = room.getTiles();

        // Step 1: Fill edges with walls, rest with floor
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y == 0 || y == height -1 || x == 0 || x == width -1) {
                    tiles[y][x] = '#'; // border wall
                } else {
                    tiles[y][x] = '.'; // floor
                }
            }
        } 

        // Step 2: Add interior walls
        int interiorWalls = (width * height) / 10; // rougly 10% of room
        for (int i = 0; i < interiorWalls; i++) {
            int x, y;
            do {
                x = rand.nextInt(width - 2) + 1; // avoid edges
                y = rand.nextInt(height - 2) + 1; 
            } while (tiles[y][x] != '.'); // only place on empty floor
            tiles[y][x] = '#';
        }

        // Step 3: Add a door
        if (rand.nextBoolean()) {
            tiles[height / 2][width - 1] = 'D'; // right edge
        } else {
            tiles[height - 1][width / 2] = 'D'; // bottom edge
        }

        return room;
    }
    
}
