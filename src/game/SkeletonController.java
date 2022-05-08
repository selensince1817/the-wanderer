
package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Used to control the whole skeleton behaviour.
 * <p>
 *     Most of functionality is contained in timers created specifically for this class.
 *     Functionality:
 *      1. Skeleton will look towards the Hero
 *      2. Skeleton will move towards you if you are close enough
 *      3. Skeleton will attack you
 *      4. Skeleton won't move when attacks
 *      5. Skeleton will damage hero according to his animation and not on collision
 *      6. Skeleton will push you
 *      7. Skeleton can drop an item which can be picked up
 * </p>
 */
public class SkeletonController implements StepListener, MouseListener {  // This one is big

    private Skeleton skeleton;
    private Hero hero;
    private boolean attacks;

    private Timer timer;

    private CharacterController controller;

    /**
     * Those two booleans get switched in timers to ensure correct game mechanics
     */
    private boolean skeletonCanWalk;
    private boolean canBeHurt;


    public SkeletonController(Hero hero, Skeleton skeleton, CharacterController controller){
        this.hero = hero;
        this.skeleton = skeleton;
        this.controller = controller;

        skeletonCanWalk = true;
        canBeHurt = true;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    /**
     * StepListener is used to constantly check for player's position and call corresponding Skeleton methods - move, attack, etc.
     *
     */
    @Override
    public void postStep(StepEvent stepEvent) {
        if(Math.abs(hero.getPosition().x - skeleton.getPosition().x) < 20 && !attacks){
            moveToHero();  // Skeleton will go towards the hero of you are close enough
        } else {
            lookToHero(); // Skeleton will always look towards you
        }
        if(Math.abs(hero.getPosition().x - skeleton.getPosition().x) < 3){
            attacks = true;
            hitHero();  // Triggers an anim and several timers
            attacks = false;
        }


    }

    public void moveToHero(){
        if(skeletonCanWalk) {
            if (getLooksLeft()) {
                skeleton.setLinearVelocity(new Vec2(-4.5f, 0));
                skeleton.setImageWalkingLeft();
            } else if (!getLooksLeft()) {
                skeleton.setImageWalkingRight();
                skeleton.setLinearVelocity(new Vec2(4.5f, 0));
            }
        }
    }

    public void lookToHero(){
        if(getLooksLeft()){
            skeleton.setImageIdleLeft();

        } else if(!getLooksLeft()){
            skeleton.setImageIdleRight();

        }
    }

    public void hitHero(){


        timer = new Timer(200, new TimerHandleAttackForSkeleton(this, getHero())); // Timer containing other timers: 1. Sets animation 2. Pushes hero 3. Damages Hero, etc...
        timer.setRepeats(false);
        timer.setInitialDelay(0);
        timer.start();

    }

    public void setAttackAnimSkeleton(){
        if(getLooksLeft()){
            skeleton.setImageHitLeft();
        } else {
            skeleton.setImageHitRight();
        }
    }

    public boolean getLooksLeft(){  // checks the skeleton view direction
        if(hero.getPosition().x - skeleton.getPosition().x < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getCanDamage(){
        if(Math.abs(hero.getPosition().x - skeleton.getPosition().x) < 14){
            return true;
        } else {
            return false;
        }
    }
    public Skeleton getSkeleton(){ return skeleton; }
    public Hero getHero(){ return hero; }



    public void setIdle(){
        hero.updateImage(hero.getIdleRight());

    }

    public void setAttack1() {
        if(!controller.getLooksLeft()) {
            hero.setSwordAttack1Right();
        } else {
            hero.setSwordAttack1Left();
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void mouseClicked(MouseEvent e) {

        timer = new Timer(200, new TimerHandlerAttack(this, getHero()));
        timer.setRepeats(false);
        timer.setInitialDelay(0);
        timer.start();

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setSkeletonCanWalk(boolean bool){ this.skeletonCanWalk = bool; }
    public boolean getSkeletonCanWalk(){ return this.skeletonCanWalk; }

    public void setCanBeHurt(boolean canBeHurt){ this.canBeHurt = canBeHurt; }
    public boolean getCanBeHurt(){ return canBeHurt; }
}
