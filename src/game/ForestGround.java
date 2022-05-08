package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.*;
public class ForestGround {

    private static Shape groundShape;
    private static StaticBody groundBody;
    private static SolidFixture ground;

    private static Shape earthShape;
    private static StaticBody earthBody;
    private static SolidFixture earth;
    private static Shape earthShape1;
    private static StaticBody earthBody1;
    private static SolidFixture earth1;



    private static float halfWidth;
    private static float halfHeight;


    private static final BodyImage goldenGrassImage = new BodyImage("data/groundForestGold.png", 3.5f);    // o-width = 1,5714285714; o-height = 0,5142857143
    private static final BodyImage earthBrown = new BodyImage("data/earthBrown.png", 3.65f);


    public ForestGround(World world){
        halfHeight = 1.8f;
        halfWidth = 5.45f;

        earthShape = new BoxShape(halfWidth-2f,halfHeight-2f);
        earthBody = new StaticBody(world);
        earth = new SolidFixture(earthBody, earthShape);

        earthShape1 = new BoxShape(halfWidth-2f,halfHeight-2f);
        earthBody1 = new StaticBody(world);
        earth1 = new SolidFixture(earthBody1, earthShape1);


        earthBody.addImage(earthBrown);
        earthBody1.addImage(earthBrown);



        groundShape = new BoxShape(halfWidth, halfHeight);
        groundBody = new StaticBody(world);
        ground = new SolidFixture(groundBody, groundShape);
        groundBody.setAlwaysOutline(false);

        groundBody.addImage(goldenGrassImage);




    }

    public static Shape getGroundShape(){ return groundShape; }
    public static StaticBody getGroundBody(){ return groundBody; }
    public static SolidFixture getGround(){ return ground; }

    public float getHalfWidth(){ return this.halfWidth; }
    public float getHalfHeight(){ return this.halfHeight; }

    public static void setPosition(float x, float y){
        groundBody.setPosition(new Vec2(x, y));
        earthBody.setPosition(new Vec2(x, y- 2* (halfHeight-0.05f)));
        earthBody1.setPosition(new Vec2(x, y- 4* (halfHeight-0.05f)));
    }

}
