package gui;

import javax.swing.*;
import java.awt.*;

import static gui.GamePanel.tileSize;

public class Frame extends JFrame {
    public static EndGamePanel menuPanel = new EndGamePanel();
    public static GamePanel gamePanel = new GamePanel();

    public Frame() {
        this.setTitle("Snake 2.0");
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    public void addButton() {
        JButton restartBTN = new JButton("RESTART");
        restartBTN.setFont(GamePanel.getFont(tileSize / 2));
        restartBTN.setSize(new Dimension(tileSize * 2, tileSize));
        this.add(restartBTN, 1);
        restartBTN.setVisible(true);
        restartBTN.setBounds(this.getWidth() / 2, this.getHeight() / 2,
                tileSize * 3, tileSize);
        //restartBTN.setLocation(gWidth+20, gHeight+20);

        restartBTN.addActionListener(e -> {
            //gameOver = false;
            gamePanel = new GamePanel();
        });
    }

}
