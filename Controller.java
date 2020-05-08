package game;

import javafx.application.Application;

import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Controller extends Thread {
    private List<Enemy> enemies;
    private List<Enemy2> enemies2;
    public static boolean collision;
    private Character character;

    public Controller(List<Enemy> enemies, Character charac, List<Enemy2> enemies2) {
        this.enemies = enemies;
        this.enemies2 = enemies2;
        this.character=charac;
    }


    public void run() {
        Random rand = new Random();

        //Set enemies speed (start)
        enemies.forEach(e -> {
           e.speed= 10+ rand.nextInt(30);
        });

       
        while (!collision) {
            enemies.forEach(e -> {
                e.setLocation(e.getX(), e.getY()+e.speed);

                //Reuse enemies + change speed
                if(e.getY()>=Utils.WIN_SIZE) { e.setLocation(rand.nextInt(Utils.WIN_SIZE-Utils.CHARACTER_SIZE), 0);
                e.speed= 10+ rand.nextInt(40);
                }

                //Check collision
                if (e.getX()< character.getX()+Utils.CHARACTER_SIZE &&
                    e.getX() + Utils.CHARACTER_SIZE > character.getX() &&
                    e.getY()< character.getY()+Utils.CHARACTER_SIZE &&
                    e.getY() + Utils.CHARACTER_SIZE > character.getY())
                {
                    //Shut this thread
                    collision = true;
                    
                    //Shut the other thread too
                    Controller2.collision2=true;
                    //Stop the timer
                    MainWindow.end= System.currentTimeMillis();
                    new Final();
                }
            });

            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }


        }
    }



}