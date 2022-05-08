package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class MovementTracking implements StepListener {
    private GameView view;
    private Hero hero;


    public static boolean speechEmote = false;
    public static boolean canTalk = false;


    public MovementTracking(GameView view, Hero hero){
        this.view = view;
        this.hero = hero;

    }



    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        view.setCentre(new Vec2(hero.getPosition().x, hero.getPosition().y + 8));

    }

    public void updateHero(Hero hero){ this.hero = hero; }
}
