package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Boolean switcher, enables the display of arrow when item is acquired
 */
public class TimerHandlerArrowView implements ActionListener {

    private GameView view;
    private GameWorld world;
    private Game game;


    public TimerHandlerArrowView(GameView view, GameWorld world, Game game){
        this.view = view;
        this.world = world;
        this.game = game;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(world instanceof SpringValley) {

            world.getNPC().setDialogueFinished(false);
        } else {
            game.getWorld().itemPicked(false);
        }
    }
}
