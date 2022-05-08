package game;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.interfaces.RSAKey;

public class CharacterController implements KeyListener, MouseListener {
    private int walking_speed = 15;
    private int jump_speed = 20;

    private Hero hero;
    private SkeletonController skeletonController;

    public CharacterController(Hero hero){
        this.hero = hero;
    }

    public CharacterController(Hero hero, SkeletonController skeletonController){
        this.skeletonController = skeletonController;
        this.hero = hero;
    }

    public int spriteCounter = 0;
    public int spriteNum = 0;



    public int spriteCounterJump = 0;
    public int spriteNumJump = 0;

    public boolean rightDown;
    public boolean leftDown;
    public boolean spaceDown;
    public boolean eDown;

    private boolean looksLeft;

    private Timer timer;

    private int soundCounter = 0; // Had to introduce the counter so sound doesn't play so frequently.
    private int soundNum = 0;







    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            leftDown = true;
           hero.updateImage(hero.getRunLeft().get(spriteNum));
           hero.startWalking(-walking_speed);


           looksLeft = true;

            soundCounter++;
            if(soundCounter > 1){
                hero.getStepSound().play();
                soundCounter = 0;
            }


        }
        else if(code == KeyEvent.VK_D){
            rightDown = true;
            hero.startWalking(walking_speed);
            hero.updateImage(hero.getRunRight().get(spriteNum));


            soundCounter++;
            if(soundCounter > 1){
                hero.getStepSound().play();
                soundCounter = 0;
            }

           looksLeft = false;


        }
        else if(code == KeyEvent.VK_SPACE){
            spaceDown = true;
            hero.updateImage(hero.getJumpRight().get(spriteNumJump));
            hero.jump(jump_speed);
        }

        else if(code == KeyEvent.VK_M){
            if(hero.getHP()< hero.getHpLimit()) {
                hero.setHP(hero.getHP() + 1);
            }
        }
        else if(code == KeyEvent.VK_N){
                hero.setHP(hero.getHP() - 1);

        }




        spriteCounter++;
        if(spriteCounter > 1){  // run switcher
            spriteNum = spriteNum%(hero.getRunRight().size()-1) + 1;
            spriteCounter = 0;
        }

        spriteCounterJump++;
        if(spriteCounterJump > 1){  // jump switcher
            spriteNumJump = spriteNumJump%(hero.getJumpRight().size()-1) + 1;
            spriteCounterJump = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_A){
            leftDown = false;
            hero.stopWalking();
            hero.updateImage(hero.getIdleLeft());

        }
        else if(code == KeyEvent.VK_D){
            rightDown = false;
            hero.stopWalking();
            hero.updateImage(hero.getIdleRight());
        }
        else if(code == KeyEvent.VK_SPACE){
            spaceDown = false;
            hero.updateImage(hero.getIdleRight());
        }

    }


    @Override
    public void keyTyped(KeyEvent e){
    }

    public void updateHero(Hero hero){ this.hero = hero; }

    public boolean getLooksLeft(){ return looksLeft; }

    public void setAttack1() {
        if (getLooksLeft()) {
            hero.setSwordAttack1Left();
        }
        if (!getLooksLeft()) {
            hero.setSwordAttack1Right();
        }
    }



    public void setIdle(){
        hero.updateImage(hero.getIdleRight());

    }


    ////////////////////////////////////////////////////////////////////////////////////



    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void updateSkeletonController(SkeletonController skeletonController){ this.skeletonController = skeletonController; }
}
