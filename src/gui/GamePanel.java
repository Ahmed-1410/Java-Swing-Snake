package gui;

import adapter.KeyAdapter;
import dataManagers.DJ;
import dataManagers.FoodManager;
import dataManagers.ImageManager;
import dataManagers.SnakeManager;
import objects.Apple;
import objects.Body;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener {

    public Timer timer = new Timer(200, this); //Capped at 80
    public static boolean isRunning = true, gameOver;
    public static int score, highScore;
    public static int gWidth = 600, gHeight = 600, tileSize = 20, num_of_tiles = gWidth / tileSize;
    public static Body[][] gridCO = new Body[num_of_tiles][num_of_tiles];

    public GamePanel() {
       this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyAdapter());
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(gWidth, gHeight));
        Body.addBabySnake(); // Adds Head(3,0), body(2,0), and tail(1,0)
        FoodManager.generateApple();
        DJ.Play(DJ.AmongPath);
        highScore = FoodManager.readHighScore();
        timer.start();
    }


    public void paintGameOver(Graphics g) {
        if(FoodManager.isHighScore()) {
            DJ.Play(DJ.WOWPath);
            FoodManager.possibleHighScore(score);
        }


        g.drawImage(ImageManager.loadImage("Images"+File.separator+"Tiles"+File.separator+"ForestImg.jpg"),0,0,gWidth,gHeight,this);

        String message = "Game Over";
        g.setFont(getFont((int)(tileSize*2.5)));
        FontMetrics fM = getFontMetrics(g.getFont());
        g.setColor(new Color(255, 255, 255, 255));
        g.drawString(message,(gWidth - fM.stringWidth(message)) / 2,gHeight/4);

        String points = "Score: " + score;
        g.setFont(getFont((int)(tileSize*1.5)));
        fM = getFontMetrics(g.getFont());
        g.drawString(points,((gWidth - fM.stringWidth(points))/2), (int) (gHeight*0.32));

        String highScore = "HighScore: " + FoodManager.readHighScore();
        g.setFont(getFont((tileSize*0.6f)));
        fM = getFontMetrics(g.getFont());
        g.drawString(highScore,((gWidth - fM.stringWidth(highScore))/2), (int) (gHeight*0.36));

//        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//        JButton restartBTN = new JButton("RESTART");
//        frame.add(restartBTN);
//        restartBTN.setFont(getFont(tileSize/2));
//        restartBTN.setSize(new Dimension(5,5));
//        restartBTN.setVisible(true);
//        restartBTN.setLocation((gWidth - fM.stringWidth("Restart"))/2, gHeight/2);
//
//        //restartBTN.setBounds((gWidth - fM.stringWidth("Restart"))/2, gHeight/2, tileSize*3, tileSize);
//        restartBTN.addActionListener(e -> {
//            new GamePanel();
//        });
    }

    public void paintGrid(Graphics g) {
        for (int i = 0; i < this.getWidth(); i += tileSize)// Vertical Lines
            g.drawLine(i, 0, i, this.getHeight());
        for (int j = 0; j < this.getHeight(); j += tileSize) // Horizontal Lines
            g.drawLine(0, j, this.getWidth(), j);
    }

    public void paintSnake(Graphics g) {
        Body body;
        for (int i = Body.bodies.size() - 1; i >= 0; i--) {
            body = Body.bodies.get(i);
            g.drawImage(body.getImage(),
                    body.getX() * (tileSize),
                    body.getY() * (tileSize),
                    tileSize, tileSize, this);
        }
    }

    public void paintApple(Graphics g) {
        if (FoodManager.checkEaten()) {
            int decrement = 0;
            if (timer.getDelay() > 150)
                decrement = 10;
            if (timer.getDelay() > 110)
                decrement = 5;
            else if (timer.getDelay() > 60)
                decrement = 2;
            timer.setDelay(timer.getDelay() - decrement);
        }
        FoodManager.generateApple();
        g.drawImage(Apple.getImage(), Apple.getX() * tileSize, Apple.getY() * tileSize, tileSize, tileSize, this);
    }

    public static void paintScore(Graphics g) {
        g.setColor(new Color(193, 193, 196));
        g.drawString("Score: " + score, 0, gHeight-1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score = (Body.bodies.size() - 3) * 150;
        if (isRunning) {
            if (DJ.clipPlaying.getMicrosecondPosition() == DJ.clipPlaying.getMicrosecondLength()) {
                DJ.Play(DJ.WiiPath);
            }
            System.out.println(DJ.clipPlaying.getMicrosecondPosition() / 1000000);

            SnakeManager.moveHead();

            System.out.printf("%d- (%d, %d) -%S%n",
                    timer.getDelay(), Body.bodies.get(0).getX(), Body.bodies.get(0).getY(), Body.bodies.get(0).getDirection());

            if(SnakeManager.checkCollision()) {
                gameOver = true;
                timer.stop();
            }
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(getFont(10));
        paintGrid(g);
        paintApple(g);
        paintSnake(g);
        paintScore(g);
        if (!isRunning) {
            getPauseScreen(g);
        }
        if (gameOver) {
            clearAll();
            paintGameOver(g);
            //frame.removeAll();
        }
    }

    public void getPauseScreen(Graphics g) {
        String message = "Paused...";
        g.setColor(new Color(1, 1, 1, 92));
        g.fill3DRect(0, 0, getWidth(), getHeight(), true);
        g.setFont(getFont(20));
        FontMetrics fM = getFontMetrics(g.getFont());
        g.setColor(new Color(255, 255, 255, 255));
        g.drawString(message, (gWidth - fM.stringWidth(message.toString())) / 2, gHeight / 2 - 20);
    }

    public static Font getFont(float size) {
        Font myFont = null;
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts"+File.separator+"GoblinFOnt.ttf")).deriveFont(Font.BOLD, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return myFont;
    }

    public void clearAll(){
        //gameOver = false;
        timer.stop();
        //this.removeAll();
        Body.bodies.clear();
        Body.addBabySnake();

    }
}

