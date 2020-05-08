package game;


import javax.swing.*;
import java.awt.*;

public class Enemy extends JComponent {
    public int speed;
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, Utils.CHARACTER_SIZE, Utils.CHARACTER_SIZE);
    }
}
