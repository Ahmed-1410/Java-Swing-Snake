package objects;

import dataManagers.ImageManager;

import java.awt.image.BufferedImage;

public class Tiles {
    private static final String Tile$1 = "Images\\Tiles\\PlainTile#1.jpeg";
    private static final String Tile$2 = "Images\\Tiles\\PlainTile#2.jpeg";
    private static final String Tile$3 = "Images\\Tiles\\PlainTile#3.jpeg";

    public static final BufferedImage image$1 = ImageManager.loadImage(Tile$1, Direction.RIGHT);
    public static final BufferedImage image$2 = ImageManager.loadImage(Tile$2, Direction.RIGHT);
    public static final BufferedImage image$3 = ImageManager.loadImage(Tile$3, Direction.RIGHT);

}
