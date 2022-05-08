package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SidePanel {
    private JButton restartGame;
    public JPanel mainPanel;
    private JButton quitButton;
    private JButton settingsButton;
    private JButton saveButton;
    private JButton loadButton;
    private Game game;

    public SidePanel(Game game){
        this.game = game;

        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restart();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.exit();
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    SaveLoad.saveGame(game.getWorld(), "data/save.txt", game);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    game.setWorld(SaveLoad.loadGame(game, "data/save.txt"));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });


    }
}
