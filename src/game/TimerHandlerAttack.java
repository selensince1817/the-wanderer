






package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandlerAttack implements ActionListener {
    private SkeletonController controller;
    private Timer timer;
    private Hero hero;





    /**
     * Enables the correct and realistic mechanics of the combat.
     * <p>
     *     Class containing multiple timers that have different delay times depending on the animation length, wished outcome and etc.
     * </p>
     */

    public TimerHandlerAttack(SkeletonController controller, Hero hero){
        this.controller = controller;
        this.hero = hero;
    }

    /**
     * Calls a method to set hero's animation to attack, to play attack sound
     * Creates 3 timers:
     *      1. Pushes the skeleton according to the hero's animation
     *      2. Sets the hero's animation to idle after some delay
     *      3. Makes sure the skeleton won't walk when he is being pushed, otherwise will not work
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) { // COMMENTS OTHERWISE YOU WILL FORGET!!!



        controller.setAttack1(); // calls a function in Skeleton Controller which checks for hero's pointing direction
                                // which in turn sets an animation on hero
        controller.getHero().setAttackAnim(true);  // isAttackAnimation to TRUE

        hero.getAttackSound().play();

        timer = new Timer(1, new TimerHandlerSkeletonPush(controller)); // pushes the skeleton in the middle of the hero attack animation to add realism
        timer.setRepeats(false);
        timer.setInitialDelay(150);
        timer.start();


        timer = new Timer(0, new TimerHandler2Idle(getController())); // Starts a timer with a delay of 100ms to stop the hero's attack anim and set it to idle
        timer.setRepeats(false); // the timer would set the Hero's anim to idle and decrease skeleton hp
        timer.setInitialDelay(470); // lasts for 470ms
        timer.start();



        timer = new Timer(0, new TimerHandlerBoolean(controller)); // Makes sure skeleton does not walk when he is being attacked and starts walking when he is finished with being pushed by the hero's attack
        timer.setRepeats(false);
        timer.setInitialDelay(1000);
        timer.start();



    }

    public SkeletonController getController(){ return controller; }
}
