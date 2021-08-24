package mysnakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Box extends JLabel {
    public int bWidth = 20;

    public int mDirection = Direction.RIGHT; // baslangic istiqamet

    Box() {
        setBounds(100, 100, bWidth, bWidth);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // cercive xxetti cekende qoyuldugu yerden sag ve sola atr bu sebebden diger getwidth de stroke qeder cixmaliyiq

        Rectangle2D rect = new Rectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));// xettin qalinligi
        g2.draw(rect);
        g2.setColor(Color.green);
        g2.fill(rect);

    }

    public void GoLeft() {
        int PosX = getX();
        int PosY = getY();
        PosX -= bWidth;
        setBounds(PosX, PosY, bWidth, bWidth);
    }

    public void GoRight() {
        int PosX = getX();
        int PosY = getY();
        PosX += bWidth;
        setBounds(PosX, PosY, bWidth, bWidth);
    }

    public void GoUp() {
        int PosX = getX();
        int PosY = getY();
        PosY -= bWidth;
        setBounds(PosX, PosY, bWidth, bWidth);
    }

    public void GoDown() {
        int PosX = getX();
        int PosY = getY();
        PosY += bWidth;
        setBounds(PosX, PosY, bWidth, bWidth);
    }

    public Box CreateBox() {
        Box B = new Box();
        int X = getX();
        int Y = getY();
        B.setBounds(X, Y, bWidth, bWidth);
        B.mDirection = -mDirection;// ters teref ucun menfi cunki yeni qutu quyruga elave olunmalidi
        B.Move();
        B.mDirection = mDirection;
        return B;
    }

    public void Move() {
        if (mDirection == Direction.LEFT) {
            GoLeft();
        }
        if (mDirection == Direction.RIGHT) {
            GoRight();
        }
        if (mDirection == Direction.UP) {
            GoUp();
        }
        if (mDirection == Direction.DOWN) {
            GoDown();
        }
    }
}
