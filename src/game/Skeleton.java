package game;


import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.sql.Array;
import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all assets for two types of skeletons + various methods to vary behaviour.
 */
public class Skeleton extends Walker {

    private static Shape skeletonShape = new PolygonShape(-0.22f,1.33f, -0.32f,-2.49f, 1.79f,-2.52f, 1.57f,1.27f, -0.18f,1.34f);


    private boolean special;

    private BodyImage idleSkeletonRight = new BodyImage("data/skeleton/Skeleton Idle.gif", 5f);
    private BodyImage walkingRight = new BodyImage("data/skeleton/Skeleton Walk.gif", 5f);
    private BodyImage walkingLeft = new BodyImage("data/skeleton/Skeleton Walk Left.gif", 5f);
    private BodyImage idleLeft = new BodyImage("data/skeleton/Skeleton Idle Left.gif", 5f);
    private BodyImage hitLeft = new BodyImage("data/skeleton/Skeleton Hit Left.gif", 5f);
    private BodyImage hitRight = new BodyImage("data/skeleton/Skeleton Hit.gif", 5f);



    private GameWorld world;

    private MelancholyHill world2;

    private boolean attackAnim;

    private int hp;


    public Skeleton(GameWorld world, int hp){
        super(world);
        this.world = world;
        this.hp = hp;

        SolidFixture sf = new SolidFixture(this, skeletonShape);
        addImage(idleSkeletonRight);
        sf.setFriction(1);
        setGravityScale(14);

        this.setAlwaysOutline(false);

        this.special = false;

        attackAnim = false;

    }

    public Skeleton(SavatiCave world, int hp, boolean special){ // Different constructor if you want to make your guy special (strong skeleton) who will drop something when dies
        super(world);
        this.world = world;
        this.hp = hp;
        this.special = special;

        SolidFixture sf = new SolidFixture(this, skeletonShape);
        addImage(idleSkeletonRight);
        sf.setFriction(1);
        setGravityScale(14);

        this.setAlwaysOutline(false);

        attackAnim = false;

    }

    public Skeleton(MelancholyHill world, int hp, boolean special){
        super(world);
        this.world = world;
        this.hp = hp;
        this.special = special;

        SolidFixture sf = new SolidFixture(this, skeletonShape);
        addImage(idleSkeletonRight);
        sf.setFriction(1);
        setGravityScale(14);

        this.setAlwaysOutline(false);

        attackAnim = false;
    }



    public Skeleton(MelancholyHill world, int hp){
        super(world);
        this.world = world;
        this.hp = hp;
        SolidFixture sf = new SolidFixture(this, skeletonShape);
        addImage(idleSkeletonRight);

        sf.setFriction(1);
        setGravityScale(14);

        this.setAlwaysOutline(false);

        attackAnim = false;
    }




    public void setImageWalkingRight(){
        removeAllImages();
        addImage(walkingRight);
    }

    public void setImageIdleRight(){
        removeAllImages();
        addImage(idleSkeletonRight);
    }

    public void setImageWalkingLeft(){
        removeAllImages();
        addImage(walkingLeft);
    }

    public void setImageIdleLeft(){
        removeAllImages();
        addImage(idleLeft);
    }

    public void setImageHitLeft(){
        removeAllImages();
        addImage(hitLeft);
    }
    public void setImageHitRight(){
        removeAllImages();
        addImage(hitRight);
    }


    public void hpAction(){
        if(hp <= 0){
            if(getSpecial()){  // Ensures the item is dropped of the special skeleton - the cucumber looking one
                System.out.println("killed the one");
                if(world instanceof SavatiCave) {
                    ((SavatiCave) world).getNoteBody().setPositionF(this.getPosition());
                    ((SavatiCave) world).getNoteBody().setGravityScale(4);
                }
                if(world instanceof MelancholyHill){
                    System.out.println("Yess !");
                    ((MelancholyHill) world).getNoteBody().setPositionF(this.getPosition());
                    ((MelancholyHill) world).getNoteBody().setGravityScale(4);
                }
            }
            this.destroy();
            this.setPosition(new Vec2(1800, -500)); // to avoid the bug with AttackHandler activating after skeleton's death
        } else {
            this.hp--;
        }


    }

    public void setIdleSkeletonRight(BodyImage idleSkeletonRight) {
        this.idleSkeletonRight = idleSkeletonRight;
    }

    public void setWalkingRight(BodyImage walkingRight) {
        this.walkingRight = walkingRight;
    }

    public void setWalkingLeft(BodyImage walkingLeft) {
        this.walkingLeft = walkingLeft;
    }

    public void setIdleLeft(BodyImage idleLeft) {
        this.idleLeft = idleLeft;
    }

    public void setHitLeft(BodyImage hitLeft) {
        this.hitLeft = hitLeft;
    }

    public void setHitRight(BodyImage hitRight) {
        this.hitRight = hitRight;
    }

    public boolean getSpecial(){ return special; }


    public boolean isAttackAnim() {
        return attackAnim;
    }

    public void setAttackAnim(boolean attackAnim) {
        this.attackAnim = attackAnim;
    }

}
