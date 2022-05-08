package game;

import city.cs.engine.*;

public class NPCollider implements CollisionListener {

    private Hero hero;

    public NPCollider(Hero hero){
        this.hero = hero;
    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof NPC){
            if(hero.getHP() < hero.getHpLimit()) {
                hero.setHP(hero.getHP() + 0);
            }
        }
    }
}
