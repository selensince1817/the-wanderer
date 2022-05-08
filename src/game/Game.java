package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.border.Border;

/**
 * Your main game entry point
 */
public class Game {

    GameView view;
    GameWorld world;

    CharacterController controller;
    MovementTracking setCenterTracker;
    Parallax parallaxTracker;


    private JFrame frame;

    private SidePanel sidePanel;
    private boolean escMenuVisible;

    private InventoryPanel inventoryPanel;
    private boolean inventoryMenuVisible;

    private SignTracker signTracker;
    private SoundClip gameMusic;

    private MenuCharacterController menuController;

    private GUIController guiController;


    /** Initialise a new Game. */
    public Game() {

        inventoryPanel = new InventoryPanel(this);
        inventoryMenuVisible = false;


        //1. make an empty game world
        //World world = new World();
        world = new SpringValley(getView(), this);


        controller = new CharacterController(world.getHero());


        view = new GameView(world, 1200, 1000, world.getHero(), controller, ((SpringValley) world).getDialogueController(), this);

        view.setMenuMode(true);


        view.addMouseListener(new GiveFocus(view));


        view.addKeyListener(controller); // character movement
        view.addMouseListener(controller);

        view.addKeyListener(((SpringValley) world).getSignTracker());

        view.addKeyListener(((SpringValley) world).getDialogueController()); // dialogues


        setCenterTracker = new MovementTracking(getView(), world.getHero());
        world.addStepListener(setCenterTracker); //view set center


        parallaxTracker = new Parallax(view, world.getHero());
        world.addStepListener(parallaxTracker); // parallax


        try{
            gameMusic = new SoundClip("data/sounds/within.wav");
            gameMusic.setVolume(0.38);
            gameMusic.loop();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }







        frame = new JFrame("The Wanderer");
        frame.add(view);


        sidePanel = new SidePanel(this); // esc menu
        escMenuVisible = false;



        guiController = new GUIController(this); // key listener to control menu
        view.addKeyListener(guiController);


        menuController = new MenuCharacterController(this, view);
        view.addKeyListener(menuController);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        frame.setResizable(false);

        frame.pack();

        frame.setVisible(true);



//        goToNextLevel();   debugging
//        goToNextLevel();   debugging

//        world.start();     you don't have to uncomment this
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }

    public void setWorld(GameWorld world1){

        world.removeStepListener(world.getDialogueController());
        world.stop();
        this.world = world1;



        controller.updateHero(world.getHero());


        getView().setWorld(world);
        getView().updateHero(world.getHero());
        if(world instanceof SpringValley){
//            view.updateDialogueController(getWorld().getDialogueController()); debugging

        }


        setCenterTracker.updateHero(world.getHero());
        parallaxTracker.updateHero(world.getHero());
        view.setZoom(25);
        view.updateControllerView(controller);
        view.updateHero(world.getHero());

        if(world instanceof SpringValley) {
            getWorld().getDialogueController().updateWorld(world);
            getWorld().getDialogueController().updateSomeoneToTalkTo(world.getNPC());
            getWorld().getDialogueController().updateHero(world.getHero());
        }



        world.start();

    }

    public void goToNextLevel(){
        if(world instanceof SpringValley && getWorld().isComplete()){ //isComplete add this bro
            world.stop();



            world = new SavatiCave(getView(), this);


            getView().setWorld(world);

            controller.updateHero(world.getHero());
            setCenterTracker.updateHero(world.getHero());
            parallaxTracker.updateHero(world.getHero());
            view.setZoom(25);
            view.updateControllerView(controller);
            view.updateHero(world.getHero());

//            gameMusic.stop();     // music of level 2

//            try{
//                gameMusic = new SoundClip("data/sounds/cave.wav");
//                gameMusic.loop();
//            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e){
//                System.out.println(e);
//            }


            world.start();

        } else if(world instanceof SavatiCave && getWorld().isComplete()){
            world.stop();

            world = new MelancholyHill(getView(), this);


            getView().setWorld(world);

            controller.updateHero(world.getHero());
            setCenterTracker.updateHero(world.getHero());
            parallaxTracker.updateHero(world.getHero());
            view.setZoom(25);
            view.updateControllerView(controller);
            view.updateHero(world.getHero());


            world.start();
        }
    }

    public void toggleEscMenu(){
        if(escMenuVisible) {
            escMenuVisible = false;
            frame.remove(sidePanel.mainPanel);
            frame.pack();
            world.start();

        } else {
            escMenuVisible = true;
            frame.add(sidePanel.mainPanel, BorderLayout.WEST);
            frame.pack();
            world.stop();

        }
    }

    public void toggleInventoryMenu(){
        if(inventoryMenuVisible){
            inventoryMenuVisible = false;
            frame.remove(inventoryPanel.mainPanel);
            frame.pack();
        } else {
            inventoryMenuVisible = true;
            frame.add(inventoryPanel.mainPanel, BorderLayout.EAST);
            frame.pack();
        }
    }

    public GameView getView(){
        return view;
    }
    public GameWorld getWorld(){ return world; }

    public void restart(){
        new Game();

    }
    public void exit(){
        System.exit(1);
    }

    public InventoryPanel getInventoryPanel(){
        return inventoryPanel;
    }

    public CharacterController getController(){ return controller; }

    public void setMusicLevel(float level){ gameMusic.setVolume(level); }

    public boolean getInventoryMenuVisible(){ return inventoryMenuVisible; }

    public GUIController getGUIController(){ return guiController; }

}


