package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;


public class Hero extends Walker {
    private static final Shape heroShape = new PolygonShape(-0.41f,1.57f, -1.2f,-1.09f, -0.65f,-2.41f, 0.99f,-2.36f, 1.18f,0.22f, 0.32f, 1.68f, -0.28f,1.62f);



    private ArrayList<BodyImage> runAnimRight = new ArrayList<BodyImage>();
    private ArrayList<BodyImage> runAnimLeft = new ArrayList<BodyImage>();
    private ArrayList<BodyImage> jumpAnimRight = new ArrayList<BodyImage>();

    private ArrayList<BodyImage> attackAnimArray = new ArrayList<BodyImage>();


    private final BodyImage idleAnimGifRight = new BodyImage("data/hero/idleAnimRight.gif", 5f);
    private final BodyImage idleAnimGifLeft = new BodyImage("data/hero/idleAnimLeft.gif", 5f);


    private static SoundClip attackSound;
    private static SoundClip stepSound;

    private float HP;
    private int MP;

    private float hpLimit = 7;

    private boolean attackAnim;


//    private int spriteCounterAttack = 0;
//    private int spriteNumAttack = 0;


    public Hero(World world){
        super(world);
        SolidFixture sf = new SolidFixture(this, heroShape);
        sf.setFriction(100);
        setGravityScale(4);
        addImage(idleAnimGifRight);
        setAlwaysOutline(false);

        HP = 5;
        MP = 3;

        attackAnim = false;


        // I decided to do some animations using <BodyImage> Array instead of .gif
        // ListArray with Running - right sprites
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-00.png", 5f));
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-01.png", 5f));
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-02.png", 5f));
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-03.png", 5f));
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-04.png", 5f));
        runAnimRight.add(new BodyImage("data/hero/adventurer-run-05.png", 5f));
        // ListArray with Running - left sprites
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-00 copy.png", 5f));
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-01 copy.png", 5f));
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-02 copy.png", 5f));
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-03 copy.png", 5f));
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-04 copy.png", 5f));
        runAnimLeft.add(new BodyImage("data/hero/adventurer-run-05 copy.png", 5f));
        // ListArray with Jumping - right sprites
        jumpAnimRight.add(new BodyImage("data/hero/adventurer-jump-00.png", 5f));
        jumpAnimRight.add(new BodyImage("data/hero/adventurer-jump-01.png", 5f));
        jumpAnimRight.add(new BodyImage("data/hero/adventurer-jump-02.png", 5f));
        jumpAnimRight.add(new BodyImage("data/hero/adventurer-jump-03.png", 5f));


        // ListArray with Attack
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-00.png", 5f));
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-01.png", 5f));
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-02.png", 5f));
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-03.png", 5f));
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-04.png", 5f));
        attackAnimArray.add(new BodyImage("data/hero/attack_animations/adventurer-attack3-05.png", 5f));






        try{
            attackSound = new SoundClip("data/sounds/attack.wav");
            attackSound.setVolume(0.75);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

        try{
            stepSound = new SoundClip("data/sounds/step2.wav");
            stepSound.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }

    }





    public void updateImage(BodyImage image){
        this.removeAllImages();
        this.addImage(image);
    }

    public ArrayList<BodyImage> getRunRight(){
        return runAnimRight;
    }
    public ArrayList<BodyImage> getRunLeft(){
        return runAnimLeft;
    }


    public ArrayList<BodyImage> getJumpRight(){ return jumpAnimRight; }


    public ArrayList<BodyImage> getAttackAnimArray(){ return attackAnimArray; }




    public BodyImage getIdleRight(){
        return idleAnimGifRight;
    }
    public BodyImage getIdleLeft(){
        return idleAnimGifLeft;
    }


    public float getHP(){ return HP; }
    public int getMP(){ return MP; }
    public float getHpLimit(){ return hpLimit; }


    public void setHP(float HP){ this.HP = HP; }
    public void setMP(int MP){ this.MP = MP; }
    public void setHpLimit(int limit){ this.hpLimit = limit; }



    public void setSwordAttack1Left(){
        removeAllImages();
        this.addImage(new BodyImage("data/hero/attack3Left.gif", 5f));


    }
    public void setSwordAttack1Right(){
        removeAllImages();
        this.addImage(new BodyImage("data/hero/attack3.gif", 5f));
//
    }

    public void setAttackAnim(boolean attackAnim){ this.attackAnim = attackAnim; }
    public boolean getAttackAnim(){ return attackAnim; }

    public SoundClip getAttackSound(){ return attackSound; }
    public SoundClip getStepSound(){ return stepSound; }


}
