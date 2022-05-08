package game;

import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Pushes the Hero and damages him.
 */
public class TimerHandlerHeroPush implements ActionListener {
    private SkeletonController controller;
    private Hero hero;


    public TimerHandlerHeroPush(SkeletonController controller, Hero hero){
        this.controller = controller;
        this.hero = hero;

    }

    /**
     * Entity will be pushed for as long as the boolean is true
     * Entity will be damaged as well
     * You can control the damage amount in this method
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(controller.getCanDamage()) {

            if(controller.getLooksLeft()) {
                controller.getHero().setLinearVelocity(new Vec2(-22, 0));
            } else {
                controller.getHero().setLinearVelocity(new Vec2(22, 0));
            }

            if(controller.getCanBeHurt()) {

                hero.setHP(hero.getHP() - 1f); // // //

            }
        }

        controller.setCanBeHurt(false);



    }
}
