package game;

import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandler2Idle implements ActionListener {
    private SkeletonController controller;
    public TimerHandler2Idle(SkeletonController controller){
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.setIdle();
        controller.getHero().setAttackAnim(false);
        if (controller.getCanDamage()) {
            controller.getSkeleton().hpAction();
        }

//        controller.setSkeletonCanWalk(true);

    }
}
