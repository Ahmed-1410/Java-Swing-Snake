package objects;

import dataManagers.ImageManager;
import gui.GamePanel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Apple {
    private static final String ApplePath = "Images"+File.separator+"AppleImg.jpeg";
    private static BufferedImage image;
    public static boolean isEaten = true;
    public static int ApplesEaten;
    private static int x;
    private static int y;

    public static int generateX(){
        return x = new Random().nextInt(GamePanel.num_of_tiles);
    }
    public static int generateY() {
        return y = new Random().nextInt(GamePanel.num_of_tiles);
    }

    public static int getX() {
        return x;
    }
    public static int getY() {
        return y;
    }

    public static BufferedImage getImage(){
        return  image = ImageManager.loadImage(ApplePath);
    }
}
