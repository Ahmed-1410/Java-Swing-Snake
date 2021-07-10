package adapter;

import gui.Frame;
import gui.GamePanel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PanelAdapter extends ComponentAdapter {
    public PanelAdapter() {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int newHeight = 0,newWidth = 0;
        final float HFraction = (e.getComponent().getHeight() % GamePanel.tileSize),
                WFraction = (e.getComponent().getWidth() % GamePanel.tileSize);
        System.out.println("e.getComponent().getHeight(): " + e.getComponent().getHeight());

        if(e.getComponent().getHeight() % GamePanel.tileSize < 10 && e.getComponent().getHeight() % GamePanel.tileSize !=0)
            newHeight = (int) (Frame.gamePanel.getHeight() - GamePanel.tileSize);
        else
            newHeight = (int) (Frame.gamePanel.getHeight() + GamePanel.tileSize);

        if(e.getComponent().getWidth() % GamePanel.tileSize < 10)
            newWidth = (int) (Frame.gamePanel.getWidth() - GamePanel.tileSize);
        else
            newWidth = (int) (Frame.gamePanel.getWidth() + GamePanel.tileSize);
        System.out.println("newHeight: " + newHeight);
        Frame.gamePanel.setSize(new Dimension(newWidth, newHeight));
    }
}
