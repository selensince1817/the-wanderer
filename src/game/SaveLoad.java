package game;

import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SaveLoad {
    private GameWorld world;

    public SaveLoad(GameWorld world){
        this.world = world;
    }

    public static void saveGame(GameWorld world, String fileName, Game game) throws IOException{
        Hero hero = world.getHero();

        FileWriter writer = null;
        try{
            writer = new FileWriter(fileName);
            writer.write(world.getWorldName() + ',' + hero.getHP() + ',' + (int)hero.getPosition().x + ',' + (int)hero.getPosition().y);
        } finally {
            writer.close();
        }
    }

    public static GameWorld loadGame(Game game, String fileName) throws IOException{
        FileReader fr = null;
        BufferedReader reader = null;
        try{
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");

            String world = tokens[0];
            int hp = Integer.parseInt(tokens[1].split("\\.")[0]);  // Had to use the split once more because int and not float is needed (5.0 --> 5)
            int posX = Integer.parseInt(tokens[2]);
            int posY = Integer.parseInt(tokens[3]);
            GameWorld gameWorld = null;

            if(world.equalsIgnoreCase("Spring Valley")){
                gameWorld = new SpringValley(game.getView(), game);
                gameWorld.getHero().setHP(hp);
                gameWorld.getHero().setPosition(new Vec2(posX, posY));
            } else if(world.equalsIgnoreCase("Savati Cave")){
                gameWorld = new SavatiCave(game.getView(), game);
                gameWorld.getHero().setHP(hp);
                gameWorld.getHero().setPosition(new Vec2(posX, posY));
            } else if(world.equalsIgnoreCase("Melancholy Hill")){
                gameWorld = new MelancholyHill(game.getView(), game);
                gameWorld.getHero().setHP(hp);
                gameWorld.getHero().setPosition(new Vec2(posX, posY));
            }
            return gameWorld;
        } finally {
            reader.close();
        }
    }
}
