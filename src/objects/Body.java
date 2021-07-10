package objects;

import dataManagers.ImageManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Body {
    public static ArrayList<Body> bodies = new ArrayList<>();
    private int x, y;

    public Body(Type type) {
        setType(type);
    }
    public Body(Type type, Direction direction) {
        this(type);
        setDirection(direction);
        image = ImageManager.loadImage(BODYPATH, direction);
    }
    public Body(Type type, Direction direction, int x, int y) {
        this(type, direction);
        this.x = x;
        this.y = y;
    }
    private Type type = Type.BODY;

    private static final String HEADPATH = "Images\\BodyParts\\HeadImg.jpeg";
    private static final String BODYPATH = "Images\\BodyParts\\BodyImg.jpeg";
    private static final String TAILPATH = "Images\\BodyParts\\TailImg.jpeg";

    private Direction direction = Direction.RIGHT;
    private BufferedImage image;


    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
        refreshImage();
    }

    public Direction getDirection() {
        return direction;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
        refreshImage();
    } // Auto refresh Images

    private void refreshImage(){
        switch (type){
            case HEAD ->{
                image = ImageManager.loadImage(HEADPATH, direction);
            }
            case TAIL -> {
                image = ImageManager.loadImage(TAILPATH, direction);
            }
            default -> {
                image = ImageManager.loadImage(BODYPATH, direction);
            }
        }
    }
    public BufferedImage getImage() {
        return image;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public static void addBabySnake(){
        Body head = new Body(Type.HEAD);
        head.setX(2); head.setY(0);

        Body body = new Body(Type.BODY);
        body.setX(1); body.setY(0);

        Body tail = new Body(Type.TAIL);
        tail.setX(0); tail.setY(0);

        bodies.add(0,head);
        bodies.add(1,body);
        bodies.add(2,tail);
    }
}
