package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyListener;

/**
 * Used to create note items
 */
public class PickableItem extends DynamicBody {

    private final BodyImage image = new BodyImage("data/items/magic07scroll.gif", 3);  // This class is used for notes you can pickup!
    private StaticBody body;
    private static final BoxShape boxShape = new BoxShape(1, 1);

    private World world;



    public PickableItem(World world){
        super(world, boxShape);
        this.world = world;

        addImage(image);
        setAlwaysOutline(false);

    }



    public void setPositionF(Vec2 pos){
        setPosition(pos);
    }





}
