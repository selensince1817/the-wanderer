package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.*;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SpringValley extends GameWorld{
    private DialogueController dialogueControllerChepchya;
    private Game game;
//    private NPC chepchya;

    private boolean keyAcquired;



    public SpringValley(GameView view, Game game){
        super(view, game);
        this.game = game;


//        chepchya = new NPC(this, 2, skinsDict.get("chepchya_idleLeft"), skinsDict.get("chepchya_idleRight"), coordsDict.get("chepchya_idleLeft"), 3, new InventoryItem("data/items/loot05key.png", "Key", "The Savati Key", 1), game.getInventoryPanel());

        keyAcquired = false;

        getHero().setPosition(new Vec2(300, -11));
        getChepchya().setPosition(new Vec2(280, -11));

        Shop shop = new Shop(this);
        shop.setPosition(new Vec2(320, -3.5f));

        getSign().setPosition(222, -5);

        getDialogueController().updateHero(getHero());
        getDialogueController().updateSomeoneToTalkTo(getNPC());
        getDialogueController().updateWorld(this);




        // LISTENERS





//        dialogueControllerChepchya = new DialogueController(this, getHero(), getChepchya(), game.getInventoryPanel()); // add this to level1 bro



//        addStepListener(dialogueControllerChepchya);

        addStepListener(new ChepchyaController(getChepchya(), 281, 285));



        //

        buildGround(40, 254, -16.5f);

        buildPlatform(260, -12);
        buildEarth(260, -10, 5);

        buildPlatform(260 - 10.9f, -12);
        buildEarth(260 - 10.9f, -10, 5);

        buildPlatform(260 - 10.9f, -9);
        buildEarth(260 - 10.9f, -8, 5);

        buildPlatform(260 - 10.9f*2, -9);
        buildEarth(260 - 10.9f*2, -8, 5);

        buildPlatform(260 - 10.9f*3, -9);
        buildEarth(260 - 10.9f*3, -8, 5);


        buildPlatform(260 - 10.9f*4, -9);
        buildEarth(260 - 10.9f*4, -8, 5);

        buildPlatform(260 - 10.9f*5, -9);
        buildEarth(260 - 10.9f*5, -8, 5);

        buildPlatform(260 - 10.9f*6, -9);
        buildEarth(260 - 10.9f*6, -8, 5);


    }

    @Override
    public boolean isComplete() {
        if(game.getInventoryPanel().getItems().contains(1)){ // Can go to Savati cave if you have the key
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void itemPicked(boolean itemPicked) {

    }

    @Override
    public boolean isItemPicked() {
        return false;
    }

    @Override
    public String getWorldName() {
        return "Spring Valley";
    }


    public NPC getChepchya(){
        return getNPC();
    }




}




