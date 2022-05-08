package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HealingCrystal extends InteractiveStaticBody{
    BodyImage image = new BodyImage("data/cave1/003_crystal_004.png", 6.5f);
    PolygonShape bodyCoords = new PolygonShape(-2.36f,-2.92f, -2.76f,0.42f, -0.15f,2.64f, 0.67f,3.29f, 2.84f,1.03f, 2.16f,-3.12f, -2.23f,-3.03f);

    private Hero hero;
    private World world;

    private StaticBody body;

    private SoundClip heal;

    public HealingCrystal(Hero hero, World world) {
        super(hero, world);
        this.hero = hero;
        this.world = world;

        body = new StaticBody(world);
        SolidFixture bodySf = new SolidFixture(body, bodyCoords);
        body.addImage(image);
        body.setAlwaysOutline(false);

        Sensor sensor = new Sensor(body, getBodyCoords());
        sensor.addSensorListener(this);

        try{
            heal = new SoundClip("data/sounds/hpUp.wav");
            heal.setVolume(1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {

    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        healHero();
        heal.play();
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }

    public void setPosition(Vec2 pos){
        body.setPosition(pos);
    }

    public PolygonShape getBodyCoords(){ return bodyCoords; }
}
