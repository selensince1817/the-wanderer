package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Sign {

    private static Shape signShape;
    private static StaticBody signBody;
    private static SolidFixture sign;

    private static final BodyImage signImage = new BodyImage("data/sign/sign.png", 4.7f);


    public Sign(World world){


        signShape = new BoxShape(1.5f, 2.7f);
        signBody = new StaticBody(world);
        sign = new SolidFixture(signBody, signShape);

        signBody.addImage(signImage);
        signBody.setAlwaysOutline(false);
    }

    public void setPosition(int x, int y){
        signBody.setPosition(new Vec2(x, y));
    }

    public Vec2 getPosition(){
        return signBody.getPosition();
    }
}
