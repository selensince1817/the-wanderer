package game;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class DialogueController implements KeyListener, StepListener {
    private GameWorld world;
    private Hero hero;
    private NPC someoneToTalkTo;

    boolean ePressed;
    boolean dialogueMode;

    public static boolean canTalk = false;

    private int dialogueCounter;

    private InventoryPanel inventoryPanel;

    private boolean dialogueDoneChepchya;
    private GameView view;

    private KeyListener keyListener;

    private static SoundClip sound1;

    private SoundClip nextSlide;
    private SoundClip quitSound;
    private SoundClip interactSound;


   public DialogueController(GameWorld world,  Hero hero, NPC someoneToTalkTo, InventoryPanel inventoryPanel){
       this.world = world;
       this.hero = hero;
       this.someoneToTalkTo = someoneToTalkTo;

       this.view = view;

       dialogueDoneChepchya = false;

       this.inventoryPanel = inventoryPanel;

       ePressed = false;
       dialogueMode = false;
       dialogueCounter = 0;


       try{
           sound1 = new SoundClip("data/sounds/sound1.wav");
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
        if(e.getKeyCode() == KeyEvent.VK_E && canTalk){
            dialogueMode = true;
            if(dialogueMode) {
                interactSound.play();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_Q || !canTalk){
            dialogueCounter = 0;
            if(dialogueMode) {
                quitSound.play();
            }
            dialogueMode = false;

        }


        if(e.getKeyCode() == KeyEvent.VK_RIGHT && canTalk){
            if(dialogueCounter < someoneToTalkTo.getDialoguesSize()-1 && dialogueCounter !=9){  // increments the dialogue counter -- String switch occurs in GameVIew
                dialogueCounter++;
                nextSlide.play();

            }


            if(dialogueCounter >= someoneToTalkTo.getDialoguesSize()-1){  // decrements
                    world.getNPC().dialogueDone();  // Adds an inventory item after the dialogue is finished.
                    sound1.play();
                }
            }


        if(e.getKeyCode() == KeyEvent.VK_LEFT && canTalk){
            if(dialogueCounter > 0){
                dialogueCounter--;
                nextSlide.play();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_1 && dialogueCounter == 9){
            dialogueCounter++;
            nextSlide.play();
        }
        if(e.getKeyCode() == KeyEvent.VK_2 && dialogueCounter == 9){
            dialogueMode = false;
            dialogueCounter = 0;
        }

    }





    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_E){
            ePressed = false;
        }

    }



    public int getDialogueCounter(){
       return dialogueCounter;
    }
    public ArrayList<String> getDialogues(){
       return someoneToTalkTo.getDialogues();
    }





    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {


        if(Math.abs(hero.getPosition().x - someoneToTalkTo.getPosition().x) < 6){
            canTalk = true;

        } else {
            canTalk = false;
        }

    }

    public boolean getDialogueDoneChepchya(){ return dialogueDoneChepchya; }

    public KeyListener getKeyListener(){ return keyListener; }


    public void updateHero(Hero hero){ this.hero = hero; }
    public void updateSomeoneToTalkTo(NPC someoneToTalkTo){ this.someoneToTalkTo = someoneToTalkTo; }
    public void updateWorld(GameWorld world){ this.world = world; }

    public boolean getDialogueMode(){ return dialogueMode; }


}
