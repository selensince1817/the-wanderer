package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import city.cs.engine.BodyImage;
import city.cs.engine.Sensor;
import org.jbox2d.common.Vec2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Time;

public class SavatiCave extends GameWorld{

    private Sign caveSign;
    private DialogueController dialogueControllerChepchya;
    private SignTracker signTracker;
    private Game game;
    private GameView view;
    private PickableItem noteBody;

    private ItemPickup notePickup;

    private Timer timer;
    private boolean itemPicked;

    private InventoryItem note = new InventoryItem("data/items/magic07scroll.png", "note", "3. First note", 142);
    public SavatiCave(GameView view, Game game) {



        super(view, game);
        this.game = game;
        this.view = view;

        itemPicked = false;

        getHero().setPosition(new Vec2(260, -11));

        buildGround(40, 150, -16.5f);

        Skeleton skeleton1 = new Skeleton(this, 1);
        Skeleton skeleton2 = new Skeleton(this, 1);
        Skeleton skeleton3 = new Skeleton(this, 1);
        Skeleton skeleton4 = new Skeleton(this, 1);
        Skeleton skeleton5 = new Skeleton(this, 2, true);


        SkeletonController skeletonController = new SkeletonController(getHero(), skeleton1, game.getController());
        SkeletonCollision skeletonCollision = new SkeletonCollision(getHero());
        getHero().addCollisionListener(skeletonCollision);
        view.addMouseListener(skeletonController);

        SkeletonController skeletonController2 = new SkeletonController(getHero(), skeleton2, game.getController());
        SkeletonCollision skeletonCollision2 = new SkeletonCollision(getHero());
        getHero().addCollisionListener(skeletonCollision2);
        view.addMouseListener(skeletonController2);

        SkeletonController skeletonController3 = new SkeletonController(getHero(), skeleton3, game.getController());
        SkeletonCollision skeletonCollision3 = new SkeletonCollision(getHero());
        getHero().addCollisionListener(skeletonCollision3);
        view.addMouseListener(skeletonController3);

        SkeletonController skeletonController4 = new SkeletonController(getHero(), skeleton4, game.getController());
        SkeletonCollision skeletonCollision4 = new SkeletonCollision(getHero());
        getHero().addCollisionListener(skeletonCollision4);
        view.addMouseListener(skeletonController4);

        SkeletonController skeletonController5 = new SkeletonController(getHero(), skeleton5, game.getController());
        SkeletonCollision skeletonCollision5 = new SkeletonCollision(getHero());
        getHero().addCollisionListener(skeletonCollision5);
        view.addMouseListener(skeletonController5);

        HealingCrystal crystal1 = new HealingCrystal(getHero(), this);
        crystal1.setPosition(new Vec2(300,-14));





        getSign().setPosition(420, -14);



        noteBody = new PickableItem(this);
        noteBody.setPositionF(new Vec2(600, -5));
        noteBody.setGravityScale(0);
        notePickup = new ItemPickup(game.getInventoryPanel(), note, noteBody, this);
        getHero().addCollisionListener(notePickup);



        getSignTracker().updateHero(getHero());
        getSignTracker().updateSign(getSign());

        addStepListener(skeletonController);
        addStepListener(skeletonController2);
        addStepListener(skeletonController3);
        addStepListener(skeletonController4);
        addStepListener(skeletonController5);



        skeleton1.setPosition(new Vec2(280, -11));
        skeleton2.setPosition(new Vec2(340, -11));
        skeleton3.setPosition(new Vec2(362, -11));
        skeleton4.setPosition(new Vec2(380, -11));
        skeleton5.setPosition(new Vec2(400, -11));

        Rock rock1 = new Rock(this);
        rock1.setPosition(new Vec2(310, -14));
        Rock rock2 = new Rock(this);
        rock2.setPosition(new Vec2(360, -13));


        skeleton5.setWalkingLeft(new BodyImage("data/skeleton/walkleft.gif", 5f));
        skeleton5.setHitLeft(new BodyImage("data/skeleton/hitleft.gif", 5f));
        skeleton5.setIdleLeft(new BodyImage("data/skeleton/idleleft.gif", 5f));

        skeleton5.setIdleSkeletonRight(new BodyImage("data/skeleton/idleright.gif", 5f));
        skeleton5.setHitRight(new BodyImage("data/skeleton/hitright.gif", 5f));
        skeleton5.setWalkingRight(new BodyImage("data/skeleton/walkright.gif", 5f));


    }


    @Override
    public boolean isComplete() {
        if(game.getInventoryPanel().getItems().contains(142)){  // Check if you have the note in the array of InventoryPanel
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NPC getNPC() {
        return null;
    }

    @Override
    public void itemPicked(boolean itemPicked) {
        this.itemPicked = itemPicked;
    }

    @Override
    public boolean isItemPicked() {
        return itemPicked;
    }

    @Override
    public String getWorldName() {
        return "Savati Cave";
    }

    public PickableItem getNoteBody(){ return noteBody; }

    public ItemPickup getNotePickup() {
        return notePickup;
    }
}
