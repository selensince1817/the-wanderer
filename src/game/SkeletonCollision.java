package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

import javax.swing.*;

/**
 * For future development.
 */
public class SkeletonCollision implements CollisionListener {
    private Hero hero;
    private Timer timer;
    public SkeletonCollision(Hero hero){
        this.hero = hero;
    }

    @Override
    public void collide(CollisionEvent e) {  // For future mechanics
        if(e.getOtherBody() instanceof Skeleton){


        }
    }
}
