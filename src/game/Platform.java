package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform {

    private static Shape groundShape;
    private static StaticBody groundBody;
    private static SolidFixture ground;

    private static final BodyImage goldenGrassImage = new BodyImage("data/groundForestGold.png", 3.5f);

    private static float halfWidth;
    private static float halfHeight;


    public Platform(World world){
        halfHeight = 1.8f;
        halfWidth = 5.45f;

        groundShape = new BoxShape(halfWidth, halfHeight);
        groundBody = new StaticBody(world);
        ground = new SolidFixture(groundBody, groundShape);
        groundBody.setAlwaysOutline(false);

        groundBody.addImage(goldenGrassImage);
    }



    public static void setPosition(float x, float y){
        groundBody.setPosition(new Vec2(x, y));
    }
}
