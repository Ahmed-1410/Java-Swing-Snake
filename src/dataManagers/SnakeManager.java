package dataManagers;

import gui.GamePanel;
import objects.Body;
import objects.Direction;
import objects.Type;

public abstract class SnakeManager {
    public static boolean checkCollision(){
        int headX = Body.bodies.get(0).getX();
        int headY = Body.bodies.get(0).getY();

        for (int i = 1; i < Body.bodies.size(); i++)
            if(Body.bodies.get(i).getX() == headX && Body.bodies.get(i).getY() == headY) {
                DJ.Play(DJ.BonkPath);
                DJ.stop();
                DJ.Play(DJ.GameOverPath);
                return true;
            }
        return false;
    }
    public static void moveHead() {
        Body snakeHead = Body.bodies.get(0);
        int hX = snakeHead.getX();
        int hY = snakeHead.getY();
        Direction hD = snakeHead.getDirection();

        switch (hD) {
            case UP -> {
                if (hY <= 0)
                    Body.bodies.get(0).setY(GamePanel.num_of_tiles - 1);
                else
                    Body.bodies.get(0).setY(hY - 1);
            }
            case DOWN -> {
                if (hY >= (GamePanel.num_of_tiles - 1))
                    Body.bodies.get(0).setY(0);
                else
                    Body.bodies.get(0).setY(hY + 1);
            }
            case LEFT -> {
                if (hX <= 0)
                    Body.bodies.get(0).setX(GamePanel.num_of_tiles - 1);
                else
                    Body.bodies.get(0).setX(hX - 1);
            }
            case RIGHT -> {
                if (hX >= (GamePanel.num_of_tiles - 1))
                    Body.bodies.get(0).setX(0);
                else
                    Body.bodies.get(0).setX(hX + 1);
            }
        }
        //Has to be Under Switch
        if (FoodManager.checkEaten()) addBody(hX, hY, hD);
        else followAhead(hX, hY, hD);
    }

    private static void followAhead(int headPreX, int headPreY, Direction hPreDirection) {
        for (int i = Body.bodies.size() - 1; i > 0; i--) {
            if (i == 1) {
                Body.bodies.get(i).setDirection(hPreDirection);
                Body.bodies.get(i).setX(headPreX);
                Body.bodies.get(i).setY(headPreY);
                break;
            }
            Body.bodies.get(i).setDirection(Body.bodies.get(i - 1).getDirection());
            Body.bodies.get(i).setX(Body.bodies.get(i - 1).getX());
            Body.bodies.get(i).setY(Body.bodies.get(i - 1).getY());
        }
    }
    // Adds a Body before Head
    public static void addBody(int preHeadX, int preHeadY, Direction preHeadD) {
        Body newBody = new Body(Type.BODY, preHeadD, preHeadX, preHeadY);
        Body.bodies.add(1, newBody);
    }

    private static int getPreBodyGridX(int x, Direction D) {
        switch (D) {
            case UP, DOWN -> {
                return x;
            }
            case LEFT -> {
                return x + 1;
            }
            case RIGHT -> {
                return x - 1;
            }
            default -> {
                return 0;
            }
        }
    }

    private static int getPreBodyGridY(int y, Direction D) {
        switch (D) {
            case UP -> {
                return y + 1;
            }
            case DOWN -> {
                return y - 1;
            }
            case LEFT, RIGHT -> {
                return y;
            }
            default -> {
                return 0;
            }
        }
    }

    private static Direction getPreBodyDirection(int x, int y, Direction d) {
        int previousX = getPreBodyGridX(x, d), previousY = getPreBodyGridY(y, d);

        if (isXOutOfBounds(previousX))
            previousX = 0;
        if (isYOutOfBounds(previousY))
            previousY = 0;

        return GamePanel.gridCO[previousX][previousY].getDirection();
    }

    private static Body getPreviousBody(int previousX, int previousY) {
        if (isXOutOfBounds(previousX) || isYOutOfBounds(previousY))
            throw new IndexOutOfBoundsException("Indexes are off the grid");
        return GamePanel.gridCO[previousX][previousY];
    }

    private static int snakeLength() {
        return Body.bodies.size();
    }

    public static int getXIndexOf(Type bp) {
        if (bp == Type.BODY)
            throw new IllegalArgumentException("Cant get index of a body");

        for (int i = 0; i < GamePanel.gridCO.length; i++)
            for (int j = 0; j < GamePanel.gridCO[0].length; j++)
                if (GamePanel.gridCO[i][j] != null)
                    if (GamePanel.gridCO[i][j].getType().equals(bp)) //check if toString needed
                        return i;
        return 0;
    }

    public static int getXIndexOf(Type bp, int headY) {
        if (bp == Type.BODY)
            throw new IllegalArgumentException("Cant get index of a body");

        for (int i = 0; i < GamePanel.gridCO.length; i++)
            if (GamePanel.gridCO[i][headY].getType().equals(bp)) //check if toString needed
                return i;
        return 0;
    }

    public static int getYIndexOf(Type bp) {
        if (bp == Type.BODY)
            throw new IllegalArgumentException("Cant get index of a body");

        for (int i = 0; i < GamePanel.gridCO.length; i++)
            for (int j = 0; j < GamePanel.gridCO[0].length; j++)
                if (GamePanel.gridCO[i][j] != null)
                    if (GamePanel.gridCO[i][j].getType().equals(bp)) //check if toString needed
                        return j;
        return 0;
    }

    public static int getYIndexOf(Type bp, int headX) {
        if (bp == Type.BODY)
            throw new IllegalArgumentException("Cant get index of a body");

        for (int j = 0; j < GamePanel.gridCO[headX].length; j++)
            if (GamePanel.gridCO[headX][j] != null)
                if (GamePanel.gridCO[headX][j].getType().equals(bp)) //check if toString needed
                    return j;
        return 0;
    }

    private static boolean isXOutOfBounds(int x) {
        return x < 0 || x > GamePanel.gWidth;
    }

    private static boolean isYOutOfBounds(int y) {
        return y < 0 || y > GamePanel.gHeight;
    }
}
