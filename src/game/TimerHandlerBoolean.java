
package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Used to switch different booleans. Usually activated after some delay to achieve a wished effect for a specified amount of time.
 */
public class TimerHandlerBoolean implements ActionListener {
    private SkeletonController controller;

    /**
     * Two different constructors one of which requires an additional argument to allow you to specify which boolean do you want switched
     */
    private int arg;

    public TimerHandlerBoolean(SkeletonController controller){
      this.controller = controller;
      this.arg = 0;

    }
    public TimerHandlerBoolean(SkeletonController controller, int arg){
        this.controller = controller;
        this.arg = arg;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(arg == 0) {
            controller.setSkeletonCanWalk(true);
        }
        if(arg == 1) {
            controller.setCanBeHurt(true);
        }

    }
}
