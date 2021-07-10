package dataManagers;

import gui.GamePanel;
import objects.Apple;
import objects.Body;

import java.io.*;
import java.util.Scanner;


public abstract class FoodManager {
    public static boolean checkEaten(){
        if(Body.bodies.get(0).getX() == Apple.getX() && Body.bodies.get(0).getY() == Apple.getY()) {
            Apple.isEaten = true;
            Apple.ApplesEaten++;
            DJ.Play(DJ.BitePath);
        }
        return Apple.isEaten;
    }

    public static void generateApple(){
        int tileSize = GamePanel.tileSize;
        if(Apple.isEaten) {
            Apple.generateX();
            Apple.generateY();
            for (int i = 0; i < Body.bodies.size(); i++)
                if(Body.bodies.get(i).getX() == Apple.getX() && Body.bodies.get(i).getX() == Apple.getX())
                    generateApple();
            Apple.isEaten = false;
        }
    }

    public static int readHighScore(){
        FileInputStream fIn;
        Scanner s;
        //String Name;
        int highScore = 0;
        try {
            fIn = new FileInputStream("HighScore.txt");
            s = new Scanner(fIn);
            if (s.hasNext())
                highScore = Integer.parseInt(s.nextLine());
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }

    public static void possibleHighScore(int newHighScore){
       // FileOutputStream fout = null;
        try {
            //fout = new FileOutputStream("HighScore.txt");
            FileWriter fw = new FileWriter(new File("HighScore.txt"));
            fw.write(newHighScore + "");
            fw.close();
            //fout.write((Body.bodies.size() - 3) * 150);
            //fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isHighScore(){
        return ((Body.bodies.size() - 3) * 150) >= readHighScore();
    }
}
