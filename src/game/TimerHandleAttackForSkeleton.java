package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandleAttackForSkeleton implements ActionListener {

    private SkeletonController controller;
    private Hero hero;

    private Timer timer;


    public TimerHandleAttackForSkeleton(SkeletonController controller, Hero hero){
        this.hero = hero;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.setAttackAnimSkeleton();
        controller.getSkeleton().setAttackAnim(true);

        controller.setSkeletonCanWalk(false);

        timer = new Timer(1, new TimerHandlerHeroPush(controller, hero)); // pushes the skeleton in the middle of the hero attack animation to add realism
        timer.setRepeats(false);
        timer.setInitialDelay(500);
        timer.start();

        timer = new Timer(1, new TimerHandlerBoolean(controller, 1)); // Ensures the Hero is not constantly damage during the time he is being pushed by the skeleton attack, additional argument added to specify which boolean is to be switched
        timer.setRepeats(false);
        timer.setInitialDelay(1500);
        timer.start();



        timer = new Timer(1, new TimerHandlerBoolean(controller)); // Ensures that the skeleton won't walk when he attacks
        timer.setRepeats(false);
        timer.setInitialDelay(500);
        timer.start();

    }
}
