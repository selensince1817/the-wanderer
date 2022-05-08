/**
 * Contains most of the game's visual functionality
 */
package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class GameView extends UserView{
    /**
     * Declaration of images that will be used
     */
    private final Image background1;
    private final Image background2;
    private final Image background3;

    private final Image cave1;
    private final Image cave2;
    private final Image cave3;
    private final Image cave4;
    private final Image topCave;
    private final Image stalactiteCloser1;

    private final Image caveMelancholy1;
    private final Image caveMelancholy2;
    private final Image caveMelancholy3;
    private final Image caveMelancholy4;
    private final Image crystal1;
    private final Image crystal2;
    private final Image crystal3;



    private final Image writtenHP;
    private final Image fullHeart;
    private final Image emptyHeart;
    private final Image goodEmote;
    private final Image lamp;
    private final Image talkIcon;

    private final Image arrow;
//    private final Image dialogue;

    private CharacterController controller;
    private DialogueController dialogueController;
    private GameWorld world;
    private Game game;


    private int x = 0;
    private int y = 0;

    private Hero hero;

    public Graphics2D graphics;


    public int spriteCounter = 0;
    public int spriteNum = 0;

    private ArrayList<Image> arrows = new ArrayList<Image>();
    private Timer timer;

    private Font font;

    private boolean menuMode;


    public GameView(GameWorld world, int width, int height, Hero hero, CharacterController controller, DialogueController dialogueController, Game game){
        super(world, width, height);
        this.world = world;
        this.game = game;

        this.setZoom(25);
        this.controller = controller;
        this.dialogueController = dialogueController;

        this.hero = hero;


        Image imageIcon1 = new ImageIcon("data/background/background_layer_1.png").getImage();
        background1 = imageIcon1.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
        Image imageIcon2 = new ImageIcon("data/background/background_layer_2.png").getImage();
        background2 = imageIcon2.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
        Image imageIcon3 = new ImageIcon("data/background/background_layer_3.png").getImage();
        background3 = imageIcon3.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);


        Image cave1Image = new ImageIcon("data/cave1/008_background.png").getImage();
        cave1 = cave1Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
        Image cave2Image = new ImageIcon("data/cave1/007_background.png").getImage();
        cave2 = cave2Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
        Image cave3Image = new ImageIcon("data/cave1/006_background.png").getImage();
        cave3 = cave3Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);
        Image cave4Image = new ImageIcon("data/cave1/005_background.png").getImage();
        cave4 = cave4Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);

        Image topCaveImage = new ImageIcon("data/cave1/001_ceiling.png").getImage();
        topCave = topCaveImage.getScaledInstance(1000, 300, java.awt.Image.SCALE_SMOOTH);

        Image stalactite1 = new ImageIcon("data/cave1/002_stalactite_008.png").getImage();
        stalactiteCloser1 = stalactite1.getScaledInstance(160, 400, java.awt.Image.SCALE_SMOOTH);

        ////

        Image caveMelancholy1Image = new ImageIcon("data/cave2/012_background.png").getImage();
        caveMelancholy1 = caveMelancholy1Image.getScaledInstance(1000, 1800, java.awt.Image.SCALE_SMOOTH);

        Image caveMelancholy2Image = new ImageIcon("data/cave2/008_background.png").getImage();
        caveMelancholy2 = caveMelancholy2Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);

        Image caveMelancholy3Image = new ImageIcon("data/cave2/007_background.png").getImage();
        caveMelancholy3 = caveMelancholy3Image.getScaledInstance(1000, 1000, java.awt.Image.SCALE_SMOOTH);

        Image caveMelancholy4Image = new ImageIcon("data/cave2/003_ground.png").getImage();
        caveMelancholy4 = caveMelancholy4Image.getScaledInstance(640, 362, java.awt.Image.SCALE_SMOOTH);


        Image crystal1Image = new ImageIcon("data/cave2/003_crystal_005.png").getImage();
        crystal1 = crystal1Image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        Image crystal2Image = new ImageIcon("data/cave2/003_crystal_002.png").getImage();
        crystal2 = crystal2Image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
        Image crystal3Image = new ImageIcon("data/cave2/003_crystal_014.png.png").getImage();
        crystal3 = crystal3Image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);

        ////



        Image imageIcon4 = new ImageIcon("data/UI/HP.png").getImage();
        writtenHP = imageIcon4.getScaledInstance(imageIcon4.getWidth(this)*6, imageIcon4.getHeight(this)*6, java.awt.Image.SCALE_SMOOTH);

        Image imageIcon5 = new ImageIcon("data/UI/fullHeart.png").getImage();
        fullHeart = imageIcon5.getScaledInstance(imageIcon5.getWidth(this)*6, imageIcon5.getHeight(this)*6, java.awt.Image.SCALE_SMOOTH);

        Image imageIcon6 = new ImageIcon("data/UI/emptyHeart.png").getImage();
        emptyHeart = imageIcon6.getScaledInstance(imageIcon6.getWidth(this)*6, imageIcon6.getHeight(this)*6, Image.SCALE_SMOOTH);

        Image imageIcon7 = new ImageIcon("data/UI/emoteGood.png").getImage();
        goodEmote = imageIcon7.getScaledInstance(imageIcon7.getWidth(this)*4, imageIcon7.getHeight(this)*4, java.awt.Image.SCALE_SMOOTH);

        Image imageIcon8 = new ImageIcon("data/foreground/lamp.png").getImage();
        lamp = imageIcon8.getScaledInstance(imageIcon8.getWidth(this)*4, imageIcon8.getHeight(this)*4, java.awt.Image.SCALE_SMOOTH);

        Image imageIcon9 = new ImageIcon("data/UI/talkIcon.png").getImage();
        talkIcon = imageIcon9.getScaledInstance(imageIcon9.getWidth(this)*4, imageIcon9.getHeight(this)*4, java.awt.Image.SCALE_SMOOTH);

        Image imageIcon10 = new ImageIcon("data/UI/arrow.gif").getImage();
        arrow = imageIcon10.getScaledInstance(imageIcon10.getWidth(this)*1, imageIcon10.getHeight(this)*1, java.awt.Image.SCALE_SMOOTH);


        arrows.add(new ImageIcon("data/UI/owsList/frame_0_delay-0.1s.png").getImage());  // Sprites for the animated arrow
        arrows.add(new ImageIcon("data/UI/owsList/frame_1_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_2_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_3_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_4_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_5_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_6_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_7_delay-0.1s.png").getImage());
        arrows.add(new ImageIcon("data/UI/owsList/frame_8_delay-0.1s.png").getImage());


        InputStream is = getClass().getResourceAsStream("font/font.ttf");  // Loading font...
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Recalls different paint methods in response to current world
     */
    @Override
    protected void paintBackground(Graphics2D g){
        graphics = g;

        if(game.getWorld() instanceof SpringValley){
            paintSpringValleyBackground();
        } else if(game.getWorld() instanceof SavatiCave){
            paintSavatiCave1();
        } else if(game.getWorld() instanceof MelancholyHill){
            paintMelancholyHill();
        }






    }

    /**
     * Paints most of the UI and calls for methods which allow visual change in response to various game events
     *
     */
    protected void paintForeground(Graphics2D g){
        String s = String.valueOf(x); //xy coordinates
        String ss = String.valueOf(y);
        g.drawString(s, 40, 40);
        g.drawString(ss, 40, 80);

        if(game.getWorld() instanceof SpringValley){
            g.drawImage(lamp, (int) worldToView(new Vec2(290, -11)).x, (int) worldToView(new Vec2(290, -5.65f)).y, this);
        }

        if(game.getWorld() instanceof MelancholyHill){  // crystals for the second location
            for (int i = 0; i < 10; i++) {
                g.drawImage(crystal1, (int) worldToView(new Vec2(900+(80*i), 0)).x, (int) worldToView(new Vec2(290, 1.5f)).y, this);
            }
            for (int i = 0; i < 10; i++) {
                g.drawImage(crystal3, (int) worldToView(new Vec2(970+(140 *i), 0)).x, (int) worldToView(new Vec2(290, 1.5f)).y, this);
            }
            for (int i = 0; i < 10; i++) {
                g.drawImage(crystal2, (int) worldToView(new Vec2(940+(20*i), 0)).x, (int) worldToView(new Vec2(290, 1.5f)).y, this);
            }
        }

        dialogueManager(g);
        emoteManager();
        hpManager();

        menuManager(g);
        inventoryManager(g);


        if(game.getGUIController().getEndScreen()){  // end screen
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);  // 1600 x 1000
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(Font.PLAIN, 100f));
            g.setColor(Color.orange);


            g.drawString("This is it for now!", getWidth() * 0.345f, 300);

            g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
            g.drawString("Thank you for playing!", getWidth() * 0.345f, 600);
        }





//        System.out.println(hero + "      from view");





        if(SignTracker.nearSign){  // Draws an emote icon above the Hero if near one of the Signs
            graphics.drawImage(talkIcon, (int) (worldToView(hero.getPosition()).x - 28), (int) worldToView(hero.getPosition()).y-110, this);
        }

        if(world.getNPC().isDialogueFinished()){  // Calls a method to draw an arrow when dialogue is finished
            itemAcquired();
        }

       if(game.getWorld().isItemPicked()){ // Calls a method to draw an arrow when item is picked up
           itemAcquired();
       }


    }


    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }


    /**
     * Draws a emote icon if character is near NPC (boolean is switched in the DialogueController.class)
     */
    public void emoteManager(){
        if(DialogueController.canTalk){
            graphics.drawImage(talkIcon, (int) (worldToView(hero.getPosition()).x - 28), (int) worldToView(hero.getPosition()).y-110, this);
        }

    }

    /**
     *
     * Method co-working with DialogueController.
     * <p>
     *     DialogueController switched various booleans in response to key, step events whereas this method is responsible for getting these booleans from DialogueController and drawing different UI elements - dialogues, etc.
     *     Contains the vertical line spacer for dialogue windows, painter for the dialogue window.
     * </p>
     */
    public void dialogueManager(Graphics2D g){
        if(dialogueController.getDialogueMode()) {  // ENTERS THE DIALOGUE MODE
            int x = getWidth()/2;
            int y = getHeight()/4;
            int width = 600;
            int height = 260;

            int boxWidthX = x - (width/2);

            int lineSpacer = 10;


            graphics.setColor(Color.black);  // Draws a rounded box
            graphics.fillRoundRect(boxWidthX, y, width, height, 15, 15);

            graphics.setColor(Color.white);
            graphics.setStroke(new BasicStroke(2));
            graphics.drawRoundRect(boxWidthX+4, y+4, width-8, height-8, 10, 10); // Nice borders for our nice box





            g.setFont(font);
            g.setFont(g.getFont().deriveFont(Font.PLAIN, 40f));
            g.setColor(Color.white);


            for(String line : dialogueController.getDialogues().get(dialogueController.getDialogueCounter()).split("\n")) {  // Enables line breaks using .split() and regex
                g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40));
                lineSpacer += 40;
            }
//


            world.stop(); // Pauses because you are talking


        } else {  // QUITS DIALOGUE MODE
            world.start();
        }

    }

    /**
     * Draws hearts depending the amount of Hero's HP
     */
    public void hpManager(){  // Draws hearts depending on you HP
        graphics.drawImage(writtenHP, 40, 40, this);
        if(hero.getHP() <= 6 && hero.getHP() > 5){
            for (int i = 0; i < 6; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }

        }
        else if(hero.getHP() <= 5  && hero.getHP() > 4){
            for (int i = 0; i < 5; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }
            for (int i = 1; i < 2; i++) {
                graphics.drawImage(emptyHeart, 40+(fullHeart.getWidth(this)*(i+4)), 140, this);
            }
        }
        else if(hero.getHP() <= 4  && hero.getHP() > 3){
            for (int i = 0; i < 4; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }
            for (int i = 1; i < 3; i++) {
                graphics.drawImage(emptyHeart, 40+(fullHeart.getWidth(this)*(i+3)), 140, this);
            }

        }
        else if(hero.getHP() <= 3  && hero.getHP() > 2){
            for (int i = 0; i < 3; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }
            for (int i = 1; i < 4; i++) {
                graphics.drawImage(emptyHeart, 40+(fullHeart.getWidth(this)*(i+2)), 140, this);
            }
        }
        else if(hero.getHP() <= 2  && hero.getHP() > 1) {
            for (int i = 0; i < 2; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }
            for (int i = 1; i < 5; i++) {
                graphics.drawImage(emptyHeart, 40+(fullHeart.getWidth(this)*(i+1)), 140, this);
            }
        }
        else if(hero.getHP() <= 1  && hero.getHP() > 0) {
            for (int i = 0; i < 1; i++) {
                graphics.drawImage(fullHeart, 40+(fullHeart.getWidth(this)*i), 140, this);
            }
            for (int i = 1; i < 6; i++) {
                graphics.drawImage(emptyHeart, 40+(fullHeart.getWidth(this)*(i+0)), 140, this);
            }
        }

        else if(hero.getHP() < 0){  // Loads the last save in case Hero dies
            hero.setHP(6);
            try{
                game.setWorld(SaveLoad.loadGame(game, "data/save.txt"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }

    /**
     * method used to update the world when new level is set
     * @param world
     */
    public void updateWorld(GameWorld world){ this.world = world; }

    /**
     * method used to update the world when new level is set
     * @param controller
     */
    public void updateControllerView(CharacterController controller){ this.controller = controller; }

    /**
     * method used to update the world when new level is set
     * @param dialogueController
     */
    public void updateDialogueController(DialogueController dialogueController){ this.dialogueController = dialogueController; }

    /**
     * method used to update the world when new level is set
     * @param hero
     */
    public void updateHero(Hero hero){ this.hero = hero; }

    /**
     * Method enables the parallax effect for the multi-layered background of Spring Valley
     */
    public void paintSpringValleyBackground(){
        for (int i = 0; i < 10; i++) {
            graphics.drawImage(background1, (-200 + (1000*i)) + ( (int) (-1.2*Math.abs(x)) ) , y, this);
            graphics.drawImage(background2, (-200 + (1000*i)) + ( (int) (-4*Math.abs(x)) ), y, this);
            graphics.drawImage(background3, (-200 + (1000*i)) + ( (int) (-8*Math.abs(x)) ) , y, this);
        }
    }
    /**
     * Method enables the parallax effect for the multi-layered background of Savati Cave + some additional props
     */
    public void paintSavatiCave1(){
        for (int i = 0; i < 10; i++) {
            graphics.drawImage(cave1, (-200 + (1000*i)) + ( (int) (-1.2*Math.abs(x)) ) , y, this);    // Different background layers for Parallax Effect!
            graphics.drawImage(cave2, (-200 + (1000*i)) + ( (int) (-2*Math.abs(x)) ), y, this);
            graphics.drawImage(cave3, (-200 + (1000*i)) + ( (int) (-3*Math.abs(x)) ) , y, this);
            graphics.drawImage(cave4, (-200 + (1000*i)) + ( (int) (-6*Math.abs(x)) ) , y, this);
            graphics.drawImage(topCave, (-200 + (1000*i)) + ( (int) (-9*Math.abs(x)) ) , y, this);
            graphics.drawImage(stalactiteCloser1, (-500 + (1300*i)) + ( (int) (-11*Math.abs(x)) ) , y-20, this);



        }
    }

    /**
     * Method enables the parallax effect for the multi-layered background of Melancholy Hill
     */
    public void paintMelancholyHill(){
        for (int i = 0; i < 1000; i++) {
            graphics.drawImage(caveMelancholy1, (-200 + (1000*i)) + ( (int) (-1.2*Math.abs(x)) ) , y-500, this); // Different background layers for Parallax Effect!
            graphics.drawImage(caveMelancholy2, (-200 + (1000*i)) + ( (int) (-4*Math.abs(x)) ), y, this);
            graphics.drawImage(caveMelancholy3, (-200 + (1000*i)) + ( (int) (-8*Math.abs(x)) ) , y, this);
            graphics.drawImage(caveMelancholy4, (-200 + (640*i)) + ( (int) (-14 *Math.abs(x)) ) , y+400, this);
        }
    }


    /**
     * Draws an arrow using an array of images, little counter-switcher and timer which sets the amount of time the arrow is shown
     */
    public void itemAcquired(){  // Method to draw an arrow

            spriteCounter++;
            if(spriteCounter > 3){  // run switcher
                spriteNum = spriteNum%(arrows.size()-1) + 1;
                spriteCounter = 0;
            }

            graphics.drawImage(arrows.get(spriteNum),  getWidth() - 100, 100, this);

        Timer timer = new Timer(6000, new TimerHandlerArrowView(this, game.getWorld(), game)); // Timer to draw an arrow for 5 sec
        timer.setRepeats(false);
        timer.setInitialDelay(5000); //im so smart cant believe i did this finallyyy! :)
        timer.start();

    }


    /**
     * Enables the start screen
     * @param g
     */
    public void menuManager(Graphics2D g){
        if(menuMode){
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // Enables the start menu
            g.setFont(font);
            g.setFont(g.getFont().deriveFont(Font.PLAIN, 100f));
            g.setColor(Color.white);


            g.drawString("The Wanderer", getWidth() * 0.345f, 300);

            g.setFont(g.getFont().deriveFont(Font.PLAIN, 40));
            g.drawString("Press \'Enter\' to start wandering", getWidth() * 0.345f, 600);
        }
    }

    /**
     * Gets booleans from GUIController to enable visual response in the context of dialogues and so on.
     *<p>
     *     Contains the strings for all notes.
     *     Additional fields encapsulation is done to easily add new events
     *</p>
     * @param g
     */
    public void inventoryManager(Graphics2D g){  // Enables you to see the description of each item
        if(game.getGUIController().getDescription1Visible() || game.getGUIController().getDescription2Visible() || game.getGUIController().getDescription3Visible() || game.getGUIController().getDescription4Visible()){
            int x = getWidth()/2;
            int y = getHeight()/4;
            int width = 600;
            int height = 260;

            int boxWidthX = x - (width/2);

            int lineSpacer = 10;

            if((!game.getGUIController().getNote1Visible() && !game.getGUIController().getNote2Visible())) {

                graphics.setColor(Color.black);
                graphics.fillRoundRect(boxWidthX, y, width, height, 15, 15);

                graphics.setColor(Color.orange);
                graphics.setStroke(new BasicStroke(2));
                graphics.drawRoundRect(boxWidthX + 4, y + 4, width - 8, height - 8, 10, 10);
            }



            g.setFont(font);
            g.setFont(g.getFont().deriveFont(Font.PLAIN, 40f));
            g.setColor(Color.white);

            if(game.getGUIController().getDescription1Visible()) {  // Money description
                String line1 = "This is money, \nDon\'t know what to do with it YET.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40));
                    lineSpacer += 40;
                }
            } else if(game.getGUIController().getDescription2Visible()){ // Key description
                String line1 = "Umm... \nThis is pretty straight forward.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40));
                    lineSpacer += 40;
                }
            } else if(game.getGUIController().getNote1Visible()){  // Note 1 contents
                width = 450;
                height = 750;
                boxWidthX = x - (width/2);
                graphics.setColor(Color.black);
                graphics.fillRoundRect(boxWidthX, y-50, width, height, 15, 15);
                graphics.setColor(Color.pink);
                graphics.setStroke(new BasicStroke(4));
                graphics.drawRoundRect(boxWidthX+4, y+4-50, width-8, height-8, 10, 10);

                g.setColor(Color.white);

                String line1 = "This is enough.\nI am tired of making excuses \nand being the one who \nsuffers.\n\nOr am I going mad?\nThis feeling...\n\nNo, I am just\nfollowing the truth,\nright? \n\nYes,\nI need to face it. \n\nHe has to pay...\n\nPress \'Q\' to quit.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40 - 50));
                    lineSpacer += 40;
                }
            } else if(game.getGUIController().getDescription3Visible()){  // Note 1 description
                String line1 = "Mysterious note that one of the \ncucumber-looking skeletons has dropped. \n\nPress \'E\' to read.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40));
                    lineSpacer += 40;
                }
            } else if(game.getGUIController().getNote2Visible()){  // Note 2 contents
                width = 450;
                height = 750;
                boxWidthX = x - (width/2);
                graphics.setColor(Color.black);
                graphics.fillRoundRect(boxWidthX, y-50, width, height, 15, 15);
                graphics.setColor(Color.pink);
                graphics.setStroke(new BasicStroke(4));
                graphics.drawRoundRect(boxWidthX+4, y+4-50, width-8, height-8, 10, 10);

                g.setColor(Color.white);

                String line1 = "Those skeletons keep appearing \nout of nowhere. As if someone \nis doing this intentionally. \nI am not sure for \nhow long I\'ll \nbe able to keep up.\nBut I will make \nhim pay no matter what.\n\nPress \'Q\' to quit.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40 - 50));
                    lineSpacer += 40;
                }
            } else if(game.getGUIController().getDescription4Visible()){  // Note 2 description
                String line1 = "Second note that I also \ngot off one of the skeletons... \n\nPress \'E\' to read.";
                for(String line : line1.split("\n")) {
                    g.drawString(line, boxWidthX + 20, lineSpacer + (y + 40));
                    lineSpacer += 40;
                }
            }



        }
    }

    public boolean getMenuMode(){ return menuMode; }
    public void setMenuMode(boolean menuMode){ this.menuMode = menuMode;}


}
