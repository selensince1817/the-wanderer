package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Earth {

    private static Shape earthShape;
    private static StaticBody earthBody;
    private static SolidFixture earth;

    private static float halfWidth;
    private static float halfHeight;

    private static final BodyImage earthBrown = new BodyImage("data/earthBrown.png", 3.65f);


    public Earth(World world){

        halfHeight = 1.8f;
        halfWidth = 5.45f;

        earthShape = new BoxShape(halfWidth,halfHeight);
        earthBody = new StaticBody(world);
        earth = new SolidFixture(earthBody, earthShape);
        earthBody.setAlwaysOutline(false);

        earthBody.addImage(earthBrown);

    }

    public static void setPosition(float x, float y){
        earthBody.setPosition(new Vec2(x, y- 2* (halfHeight-0.01f)));
    }


    public float getHalfWidth(){ return this.halfWidth; }
    public float getHalfHeight(){ return this.halfHeight; }
}
