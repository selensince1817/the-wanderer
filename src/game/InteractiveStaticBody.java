package game;

import city.cs.engine.*;

public abstract class InteractiveStaticBody implements CollisionListener, SensorListener {

    private BodyImage image;
    private PolygonShape bodyCoords;

    private Hero hero;
    private World world;

    public InteractiveStaticBody(Hero hero, World world){
        this.hero = hero;
        this.world = world;


    }

    public void healHero(){
        if(hero.getHP() < hero.getHpLimit()-1){
            hero.setHP(hero.getHP()+1);
        }
    }
    public void damageHero(){
        if(hero.getHP() > 2){
            hero.setHP(hero.getHP()-1);
        }
    }
}
