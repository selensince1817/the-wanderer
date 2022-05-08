package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class Parallax implements StepListener {
    private GameView view;
    private Hero hero;



    public Parallax(GameView view, Hero hero){
        this.view = view;
        this.hero = hero;


    }


    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
     view.setX((int) hero.getPosition().x);
     view.setY((int) hero.getPosition().y);
     view.paintBackground(view.graphics);

    }

    public void updateHero(Hero hero){
        this.hero = hero;
    }
}
