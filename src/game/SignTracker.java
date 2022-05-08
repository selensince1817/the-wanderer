package game;

import city.cs.engine.SoundClip;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SignTracker implements StepListener, KeyListener {
    public static boolean nearSign;

    private Hero hero;
    private Sign sign;
    private Game game;

    private SoundClip levelSound;

    public SignTracker(Hero hero, Sign sign, Game game){
        this.hero = hero;
        this.sign = sign;
        this.game = game;
        try{
            levelSound = new SoundClip("data/sounds/interaction.wav");
            levelSound.setVolume(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        if(Math.abs(game.getWorld().getHero().getPosition().x - game.getWorld().getSign().getPosition().x) < 5){  // Check if hero is near sign
            nearSign = true;
        } else {
            nearSign = false;
        }
    }

    public boolean getNearSign(){
        return nearSign;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E && nearSign) {
            game.goToNextLevel();
            levelSound.play();

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
        }
    }

    public void updateHero(Hero hero){ this.hero = hero; }
    public void updateSign(Sign sign){ this.sign = sign;}
}
