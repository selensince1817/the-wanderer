package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.*;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public abstract class GameWorld extends World{
    private static Hero hero;
    private static GameView view; //

    private SignTracker signTracker;
    private Sign caveSign;


    public Hashtable<String, BodyImage> skinsDict;
    public Hashtable<String, PolygonShape> coordsDict;
    private Game game;

    private DialogueController dialogueController;
    private NPC chepchya;



    public GameWorld(GameView view, Game game){ //
        super();

        this.game = game;
        this.view = view; //

        skinsDict = skinsDict();
        coordsDict = coordsBoxDict();




        hero = new Hero(this);

        if(this instanceof SpringValley) {  // to ensure no NullPointerExceptions occur
            NPCollider npCollider = new NPCollider(hero);
            hero.addCollisionListener(npCollider);
        }

        if(this instanceof  SpringValley) {
            chepchya = new NPC(this, 2, skinsDict.get("chepchya_idleLeft"), skinsDict.get("chepchya_idleRight"), coordsDict.get("chepchya_idleLeft"), 3, new InventoryItem("data/items/loot05key.png", "Key", "2. The Savati Key", 1), game.getInventoryPanel());
        }

        caveSign = new Sign(this);

        signTracker = new SignTracker(getHero(), getSign(), game);

        if(this instanceof SpringValley) {
            dialogueController = new DialogueController(this, getHero(), getNPC(), getGame().getInventoryPanel());
        }




        if(this instanceof SpringValley) {
            addStepListener(dialogueController);
        }

        addStepListener(signTracker);





    }

    public Hero getHero() {
        return hero;
    }



    public void buildGround(int length, float x, float y){  // Creates the Earth and so
        for (int i = 0; i < length; i++) {
            ForestGround ground = new ForestGround(this);
            ForestGround.setPosition(x + (2*i*ground.getHalfWidth()), y);
        }
    }

    public void buildPlatform(float x, float y){
        Platform platform1 = new Platform(this);
        platform1.setPosition(x, y);
    }

    public void buildEarth(float x, float y, int depth){
        for (int i = 0; i < depth; i++) {
            Earth earth1 = new Earth(this);
            earth1.setPosition(x, y - (2 * i * earth1.getHalfHeight()));
        }

    }







    public Hashtable<String, BodyImage> skinsDict(){
        Hashtable<String, BodyImage> skins = new Hashtable<String, BodyImage>();
        skins.put("chepchya_idleLeft", new BodyImage("data/chepchya/idleLeft.gif", 3.7f));
        skins.put("chepchya_idleRight", new BodyImage("data/chepchya/chepchya idle copy.gif", 3.7f));
        return skins;
    }
    public Hashtable<String, PolygonShape> coordsBoxDict(){  // I decided to use a dict for future NPC
        Hashtable<String, PolygonShape> coords = new Hashtable<String, PolygonShape>();
        coords.put("chepchya_idleLeft", new PolygonShape(0.52f,1.76f, 1.19f,0.96f, 1.03f,-1.04f, 0.61f,-1.74f, -0.86f,-1.76f, -1.19f,-1.04f, -1.14f,1.47f, -0.35f,1.83f));
        coords.put("chepchya_idleRight", new PolygonShape(0.52f,1.76f, 1.19f,0.96f, 1.03f,-1.04f, 0.61f,-1.74f, -0.86f,-1.76f, -1.19f,-1.04f, -1.14f,1.47f, -0.35f,1.83f));
        return coords;
    }

    public abstract boolean isComplete();
    public NPC getNPC(){
        return chepchya;
    }

    public abstract void itemPicked(boolean itemPicked);
    public abstract boolean isItemPicked();


    public GameView getView(){
        return view;
    }
    public Game getGame(){
        return game;
    }

    public SignTracker getSignTracker(){ return signTracker; }

    public Sign getSign(){
        return caveSign;
    }

    public abstract String getWorldName();

    public DialogueController getDialogueController() {
        return dialogueController;
    }
}
