package game;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.*;

public class Shop {
    private static StaticBody shopBody;
    private static final BodyImage shopImage = new BodyImage("data/shop/shopAnim.gif", 23);
    private static PolygonShape shopCoords = new PolygonShape(8.12f,-11.41f, 10.51f,-1.1f, 9.64f,3.77f, -9.54f,3.86f, -10.37f,-1.43f, -7.84f,-11.36f, 7.94f,-11.41f);
    private static SolidFixture shopSf;

    public Shop(World world){  // Exists to be there
        shopBody = new StaticBody(world);
        shopSf = new SolidFixture(shopBody, shopCoords);
        shopBody.addImage(shopImage);
    }

    public void setPosition(Vec2 position){
        shopBody.setPosition(position);
    }

}
