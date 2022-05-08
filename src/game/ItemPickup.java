package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class ItemPickup implements CollisionListener {

    private InventoryItem item;
    private InventoryPanel inventoryPanel;
    private PickableItem itemPickable;
    private Hero hero;

    private SoundClip pickupSound;

    private GameWorld world;
    public ItemPickup(InventoryPanel inventoryPanel, InventoryItem item, PickableItem itemPickable, GameWorld world){
        this.item = item;
        this.inventoryPanel = inventoryPanel;
        this.itemPickable = itemPickable;
        this.world = world;

        try{
            pickupSound = new SoundClip("data/sounds/sound1.wav");
            pickupSound.setVolume(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof PickableItem){
            inventoryPanel.addItem(item, item.getItemNumber());
            e.getOtherBody().destroy();
            world.itemPicked(true);

            pickupSound.play();
        }
    }


}
