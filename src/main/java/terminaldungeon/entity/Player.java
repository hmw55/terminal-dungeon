package terminaldungeon.entity;

import java.util.ArrayList;
import terminaldungeon.item.Item;

public class Player {

    private String name;
    private String characterClass;
    private int health;
    private ArrayList<Item> inventory;

    public Player(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        this.inventory = new ArrayList<>();

        // Initialize stats and starting inventory
        switch (characterClass.toLowerCase()) {
            case "warrior":
                health = 10;
                inventory.add(new Item("Dagger", "A basic dagger", 5));
                break;
            case "mage":
                health = 10;
                inventory.add(new Item("Magic Potion", "Restores health", 10));
                break;
            case "rogue":
                health = 12;
                inventory.add(new Item("Short Sword", "A nimble sword", 4));
                break;
            default:
                health = 10;
        }
    }

    // Getters and setters
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getHealth() { return health; }
    public ArrayList<Item> getInventory() { return inventory; }
}
