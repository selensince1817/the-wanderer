package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class NPC extends Walker {

    private ArrayList<String> dialogues = new ArrayList<>();

    private final BodyImage idleRightImage;
    private final BodyImage idleLeftImage;
    private final int walking_speed;

    private boolean dialogueFinished;
    private int dialogueSize;
    private InventoryItem item;

    private InventoryPanel inventoryPanel;


    public NPC(World world, int walking_speed, BodyImage idleLeft, BodyImage idleRight, PolygonShape idleShapeCoords, int dialogueSize, InventoryItem item, InventoryPanel inventoryPanel){
        super(world);

        this.inventoryPanel = inventoryPanel;

        this.item = item;

        this.dialogueFinished = false;

        this.dialogueSize = dialogueSize;

        this.walking_speed = walking_speed;
        this.idleRightImage = idleRight;
        this.idleLeftImage = idleLeft;


        SolidFixture sf = new SolidFixture(this, idleShapeCoords);
        sf.setFriction(100);

        addImage(idleRightImage);


        dialogues.add("Hey, you! \n\n\n\n --> to continue");
        dialogues.add("What's your name, boy?");
        dialogues.add(". . . ");
        dialogues.add("Can't you talk?");
        dialogues.add(". . .");
        dialogues.add(". . . . .");
        dialogues.add("Don't tell me I have been living on this \nplanet for one thousand years just for \nsomeone to come up and \ndisrespect me like that!");
        dialogues.add(". . . . . . .");
        dialogues.add("Ok just do a little nod if you want to talk");
        dialogues.add("You want to talk? \n \nPress:  \n\"1\" if Yes \n\"2\" if No");
        dialogues.add("Nice, \nI knew we could find a common language.");
        dialogues.add("Listen, \nI do not look like a typical super-powered \ncharacter from an RPG game, \nbut you know what?");
        dialogues.add(". . . ?");
        dialogues.add("You do!");
        dialogues.add("0 _ 0");
        dialogues.add("Ok, it goes something like this: \nMy parents got lost in that cave \nto the West of our shop \nI wish I could go there but \nI\'ve told you already");
        dialogues.add("I really miss them, \nI can\'t live a day without my Mama's \npancakes with strawberry jam. \nI swear there won\'t be any \nmonsters by the way.");
        dialogues.add(". . .");
        dialogues.add("Never mind about the monsters,\nI just want to say that \nyou should not be \nscared of typical RPG skeletons\nthat you can slash like vegetables.\nNot at all...");
        dialogues.add(" :/ ");
        dialogues.add("Goood, I like the excitement! \nNow take the key from the cave entrance.");
        dialogues.add(". . .");
        dialogues.add("Off you go, wanderer! \n\nPress: \n\'Q\' to exit \n\'I\' to open the inventory");
    }


    public void updateImage(BodyImage image){
        this.removeAllImages();
        this.addImage(image);
    }

    public void dialogueDone(){
        inventoryPanel.addItemNPC();
        dialogueFinished = true;
        //inventory panel add npc get item
    }

    public int getWalking_speed(){ return walking_speed; }
    public BodyImage getIdleRightImage(){
        return idleRightImage;
    }
    public BodyImage getIdleLeftImage(){
        return idleLeftImage;
    }

    public ArrayList getDialogues(){
        return dialogues;
    }
    public int getDialoguesSize(){
        return dialogues.size();
    }

    public InventoryItem getItem(){ return item; }


    public boolean isDialogueFinished(){
        return dialogueFinished;
    }

    public void setDialogueFinished(boolean dialogueFinished){ this.dialogueFinished = dialogueFinished; }




}
