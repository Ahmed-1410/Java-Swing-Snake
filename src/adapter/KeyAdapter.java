package adapter;

import dataManagers.DJ;
import gui.GamePanel;
import objects.Body;
import objects.Direction;

import java.awt.event.KeyEvent;

public class KeyAdapter extends java.awt.event.KeyAdapter {
    public KeyAdapter() {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released");
        Direction headD = Body.bodies.get(0).getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                if (headD != Direction.DOWN)
                    headD = Direction.UP;
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                if (headD != Direction.UP)
                    headD = Direction.DOWN;
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                if (headD != Direction.LEFT)
                    headD = Direction.RIGHT;
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                if (headD != Direction.RIGHT)
                    headD = Direction.LEFT;
            }
            case KeyEvent.VK_ESCAPE, KeyEvent.VK_DELETE, KeyEvent.VK_BACK_SPACE -> {
                System.exit(0);
            }
            case KeyEvent.VK_SPACE -> {
                if(GamePanel.isRunning) {
                    GamePanel.isRunning = false;
                    DJ.stop();
                }
                else {
                    GamePanel.isRunning = true;
                    DJ.resume();
                }
            }
        }
        Body.bodies.get(0).setDirection(headD);
    }
}
