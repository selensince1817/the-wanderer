package game;

import city.cs.engine.SoundClip;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class MenuCharacterController implements KeyListener {
    private Game game;
    private GameView view;
    private SoundClip gameStart;

    public MenuCharacterController(Game Game, GameView view){
        this.game = game;
        this.view = view;
        try{
            gameStart = new SoundClip("data/sounds/start_game.wav");
            gameStart.setVolume(1.15);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_ENTER){
//            game.getWorld().start();  // not sure why but it all works :)
            view.setMenuMode(false);
            System.out.println("go!");
            gameStart.play();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
