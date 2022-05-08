package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Swamp extends InteractiveStaticBody{
    BodyImage image = new BodyImage("data/cave2/006_crystal_05.png", 6);
    PolygonShape bodyCoords = new BoxShape(1, 1.4f);

    private Hero hero;
    private World world;

    private StaticBody body;

    public Swamp(Hero hero, World world) { // Idk why I called it swamp
        super(hero, world);
        this.hero = hero;
        this.world = world;

        body = new StaticBody(world);
        SolidFixture bodySf = new SolidFixture(body, bodyCoords);
        body.addImage(image);
        body.setAlwaysOutline(false);

        Sensor sensor = new Sensor(body, bodyCoords);
        sensor.addSensorListener(this);
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {

    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        damageHero();
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    public void setPosition(Vec2 pos){
        body.setPosition(pos);
    }


}
