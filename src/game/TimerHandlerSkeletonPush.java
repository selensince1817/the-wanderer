package game;

import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandlerSkeletonPush implements ActionListener {

    private SkeletonController controller;


    public TimerHandlerSkeletonPush(SkeletonController controller){
        this.controller = controller;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.setSkeletonCanWalk(false);
        if(controller.getCanDamage()) {
            if(controller.getLooksLeft()) {
                controller.getSkeleton().setLinearVelocity(new Vec2(28, 10));
            } else {
                controller.getSkeleton().setLinearVelocity(new Vec2(-28, 10));
            }
        }
    }
}
