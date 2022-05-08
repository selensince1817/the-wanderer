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


public class MelancholyHill extends GameWorld{

    private Game game;
    private GameView view;



    private Timer timer;

    private PickableItem noteBody;
    private ItemPickup notePickup;
    private InventoryItem note = new InventoryItem("data/items/magic07scroll.png", "note2", "4. Second Note", 143);

    private boolean itemPicked;


    public MelancholyHill(GameView view, Game game){
        super(view, game);
        this.view = view;
        this.game = game;

        itemPicked = false;


        getHero().setPosition(new Vec2(900, 0));
        buildGround(40, 900,-5);


        noteBody = new PickableItem(this);
        noteBody.setPositionF(new Vec2(200, 200));
        noteBody.setGravityScale(0);
        notePickup = new ItemPickup(game.getInventoryPanel(), note, noteBody, this);
        getHero().addCollisionListener(notePickup);



        Swamp swamp = new Swamp(getHero(), this);
        swamp.setPosition(new Vec2(934, -1));

        Swamp swamp1 = new Swamp(getHero(), this);
        swamp.setPosition(new Vec2(950, -1));

        Swamp swamp2 = new Swamp(getHero(), this);
        swamp.setPosition(new Vec2(960, -1));

        Skeleton skeleton1 = new Skeleton(this, 2);
        Skeleton skeleton2 = new Skeleton(this, 3);
        Skeleton skeleton3 = new Skeleton(this, 4);
        Skeleton skeleton4 = new Skeleton(this, 1);
        Skeleton skeleton5 = new Skeleton(this, 8, true);


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



        addStepListener(skeletonController);
        addStepListener(skeletonController2);
        addStepListener(skeletonController3);
        addStepListener(skeletonController4);
        addStepListener(skeletonController5);

        skeleton1.setPosition(new Vec2(940, 0));
        skeleton2.setPosition(new Vec2(980, 0));
        skeleton3.setPosition(new Vec2(1040, 0));
        skeleton4.setPosition(new Vec2(1070, 0));
        skeleton5.setPosition(new Vec2(1100, 0));



        skeleton5.setWalkingLeft(new BodyImage("data/skeleton/walkleft.gif", 5f));
        skeleton5.setHitLeft(new BodyImage("data/skeleton/hitleft.gif", 5f));
        skeleton5.setIdleLeft(new BodyImage("data/skeleton/idleleft.gif", 5f));

        skeleton5.setIdleSkeletonRight(new BodyImage("data/skeleton/idleright.gif", 5f));
        skeleton5.setHitRight(new BodyImage("data/skeleton/hitright.gif", 5f));
        skeleton5.setWalkingRight(new BodyImage("data/skeleton/walkright.gif", 5f));


    }













    @Override
    public boolean isComplete() {
        if(game.getInventoryPanel().getItems().contains(143)){
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
        return "Melancholy Hill";
    }

    public PickableItem getNoteBody(){ return noteBody; }

}
