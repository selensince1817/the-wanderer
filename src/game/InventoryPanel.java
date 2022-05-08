package game;

import city.cs.engine.BodyImage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryPanel {
    public JPanel mainPanel;
    private JPanel ItemsPanel;
    private Game game;
    private ArrayList<Integer> items = new ArrayList<Integer>(10);
    private int itemsCounter;

    public InventoryPanel(Game game){
        this.game = game;
        itemsCounter = 0;
        addItemCoins();

        mainPanel.setPreferredSize(new Dimension(200, 900));

    }

    public void addItemNPC(){  // Method to add an item to an int array

        ItemsPanel.add(game.getWorld().getNPC().getItem());
        items.add(itemsCounter, game.getWorld().getNPC().getItem().getItemNumber());
        itemsCounter++;

    }

    public void addItemCoins(){  // Initially gets you some money

        ItemsPanel.add(new InventoryItem("data/items/loot04coins.png", "money", "1. Money", 225));
        items.add(itemsCounter, 225);
        itemsCounter++;
    }

    public void addItem(InventoryItem item, int i){
        ItemsPanel.add(item);
        items.add(itemsCounter, i);
        itemsCounter++;

    }

    public ArrayList<Integer> getItems(){ return items;}

}
