package mysnakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Food extends JLabel {
    public int fWidth = 20;

    Food() {
        setPosition(60,60);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D ellipse = new Ellipse2D.Double(1, 1, getWidth() - 2, getHeight() - 2);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));// xettin qalinligi
        g2.draw(ellipse);
        g2.setColor(Color.red);
        g2.fill(ellipse);

    }

    public void setPosition(int PosX, int PosY) {
        setBounds(PosX, PosY, fWidth, fWidth);

    }


}
