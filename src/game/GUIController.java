package game;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Used to switch various booleans and play sounds in response to different events
 */
public class GUIController implements KeyListener {
    private Game game;
    private boolean description1Visible;
    private boolean description2Visible;
    private boolean description3Visible;
    private boolean description4Visible;

    private boolean note1Visible;
    private boolean note2Visible;

    private SoundClip quitSound;
    private SoundClip interactSound;

    private SoundClip backpackSound;
    private SoundClip nextSlide;


    private boolean endScreen;

// This class is made to switch booleans and play sounds


    private int counter;

    public GUIController(Game game){
        this.game = game;
        description1Visible = false;
        description2Visible = false;
        description3Visible = false;
        note1Visible = false;
        description4Visible = false;
        note2Visible = false;
        counter = 0;
        endScreen = false;

        try{
            backpackSound = new SoundClip("data/sounds/backpack.wav");
            backpackSound.setVolume(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        try{
            nextSlide = new SoundClip("data/sounds/dialogue_change.wav");
            nextSlide.setVolume(1.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }



        try{
            quitSound = new SoundClip("data/sounds/quit.wav");
            quitSound.setVolume(1.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
        try{
            interactSound = new SoundClip("data/sounds/interaction.wav");
            interactSound.setVolume(1.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            game.toggleEscMenu();
        } else if (e.getKeyCode() == KeyEvent.VK_I){
            game.toggleInventoryMenu();
            backpackSound.play();
        }

        if(game.getInventoryMenuVisible()){
            if(e.getKeyCode() == KeyEvent.VK_1 && game.getInventoryPanel().getItems().contains(225)){  // money
                description1Visible = true;
                counter++;
                if(counter < 2) {
                    nextSlide.play();
                }
            } else if(e.getKeyCode() == KeyEvent.VK_2 && game.getInventoryPanel().getItems().contains(1)){  // savati key
                description2Visible = true;
                counter++;
                if(counter < 2) {
                    nextSlide.play();
                }

            } else if(e.getKeyCode() == KeyEvent.VK_3  && game.getInventoryPanel().getItems().contains(142)){  //note  // && game.getInventoryPanel().getItems().contains(143)
                description3Visible = true; // note
                counter++;
                if(counter < 2) {
                    nextSlide.play();
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_4 ){  //note  //  && game.getInventoryPanel().getItems().contains(143)
                description4Visible = true; // note 2
                counter++;
                if(counter < 2) {
                    nextSlide.play();
                }
            }
        }
        if(description4Visible){
            if(e.getKeyCode() == KeyEvent.VK_E){
                note2Visible = true;

                nextSlide.play();
            }
        }

        if(note2Visible){  // Enables end screen when the second note is read
            if(e.getKeyCode() == KeyEvent.VK_Q){
                note2Visible = false;
                quitSound.play();
                endScreen = true;
                game.getWorld().stop();
            }
        }

        if(description3Visible){
            if(e.getKeyCode() == KeyEvent.VK_E){
                note1Visible = true;
                nextSlide.play();
            }
        }

        if(note1Visible){
            if(e.getKeyCode() == KeyEvent.VK_Q){
                note1Visible = false;
                quitSound.play();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(description1Visible) {
            if (e.getKeyCode() == KeyEvent.VK_1) {
                description1Visible = false;
                counter = 0;
            }

        }
        if(description2Visible) {
            if (e.getKeyCode() == KeyEvent.VK_2) {
                description2Visible = false;
                counter = 0;
            }
        }
        if(description3Visible) {
             if (e.getKeyCode() == KeyEvent.VK_3) {
                description3Visible = false;
                 counter = 0;
            }
        }
        if(description4Visible) {
            if (e.getKeyCode() == KeyEvent.VK_4) {
                description4Visible = false;
                counter = 0;

            }
        }



        System.out.println("desc2 visible is:  " + description2Visible);

    }

    public boolean getDescription1Visible(){ return description1Visible; }
    public boolean getDescription2Visible(){ return description2Visible; }
    public boolean getDescription3Visible(){ return description3Visible; }
    public boolean getNote1Visible(){ return note1Visible; }

    public boolean getDescription4Visible(){ return description4Visible; }
    public boolean getNote2Visible(){ return note2Visible; }

    public boolean getEndScreen(){ return endScreen; }
}
