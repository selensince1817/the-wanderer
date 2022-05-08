package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class InventoryItem extends JPanel {
    private Image icon;
    private String type;
    private String itemName;
    private int itemNumber;

    private Font font;


    /**
     * Used for visual display of items in the inventory
     * @param imagePath
     * @param type
     * @param itemName
     * @param itemNumber
     */
    public InventoryItem(String imagePath, String type, String itemName, int itemNumber){
        this.type = type;
        this.itemName = itemName;

        this.itemNumber = itemNumber;

        icon = new ImageIcon(imagePath).getImage();
        this.setPreferredSize(new Dimension(200, 200));
        this.setBorder(BorderFactory.createLineBorder(Color.white));
        this.setBackground(Color.BLACK);
        InputStream is = getClass().getResourceAsStream("font/font.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(icon, 70, 50, 60, 60, null);
        g.setFont(font);
        g.setFont(g.getFont().deriveFont(Font.PLAIN, 26f));
        g.setColor(Color.white);

        g.drawString(itemName, 40, 130);
    }

    public int getItemNumber(){ return itemNumber; }
}
