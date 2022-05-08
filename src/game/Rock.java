package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Rock {
    private StaticBody rockBody;
    private final BodyImage rockImage = new BodyImage("data/cave1/002_rock_007.png", 6.5f);
    private PolygonShape rockCoords = new PolygonShape(-4.84f,-2.67f, -3.55f,1.69f, 0.5f,3.48f, 1.99f,3.54f, 4.59f,0.03f, 4.98f,-2.69f, -4.72f,-2.79f);
    private SolidFixture rockSf;

    public Rock(World world){
        rockBody = new StaticBody(world);
        rockSf = new SolidFixture(rockBody, rockCoords);
        rockBody.addImage(rockImage);
        rockBody.setAlwaysOutline(false);
    }

    public void setPosition(Vec2 pos){
        rockBody.setPosition(pos);
    }
}
