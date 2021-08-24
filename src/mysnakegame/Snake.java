package mysnakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class Snake extends JLabel {
    public Box bHead = new Box();
    public Timer mTimer = null;
    public Food mFood = new Food();
    public Random random = null;
    public ArrayList<Box> List = new ArrayList<Box>();
//    private boolean GameOver = false;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        // cercive xxetti cekende qoyuldugu yerden sag ve sola atr bu sebebden diger getwidth de stroke qeder cixmaliyiq
        Rectangle2D rect = new Rectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(10));// xettin qalinligi
        g2.draw(rect);

    }

    Snake() {
        random = new Random(System.currentTimeMillis());//herdefe ferqli reqem olsun deye
        //Jlabel da layout olmadigi  ucun

        addKeyListener(new KeypadControl());
        setFocusable(true);
        mTimer = new Timer(100, new TimerControl());
        mTimer.start();
        List.add(bHead);
        for (int i = 1; i < 10; i++) {
            AddBoxes();
        }

        add(mFood);
        add(bHead);

    }

    public void AddBoxes() {
        Box B = List.get(List.size() - 1).CreateBox();
        List.add(B);
        add(B);
    }

    public void AddFood() {
        int Width = getWidth() - 40 - mFood.fWidth;
        int Height = getHeight() - 40 - mFood.fWidth;

        int PosX = 20 + Math.abs(random.nextInt()) % Width;//abs menfi olmamasi ucun
        int PosY = 20 + Math.abs(random.nextInt()) % Height;

        PosX -= PosX % 20;
        PosY -= PosY % 20;

        for (int i = 0; i < List.size(); i++) {
            if ((PosX == List.get(i).getX()) && (PosY == List.get(i).getY())) {
                AddFood();
                return;
            }
        }

        mFood.setPosition(PosX, PosY);
    }

    public void MoveBoxes() {
        for (int i = List.size() - 1; i > 0; i--) {
            Box Before = List.get(i - 1);
            Box After = List.get(i);
            List.get(i).Move();
            After.mDirection = Before.mDirection;
        }
        bHead.Move();
    }

    public boolean Collision() {
        int FrameWidth = 10;
        int width = getWidth();
        int height = getHeight();

        if (bHead.getX() <= 10) {
            return true;
        }
        if (bHead.getX() + bHead.bWidth > width - FrameWidth) {
            return true;
        }
        if (bHead.getY() <= 10) {
            return true;
        }
        if (bHead.getY() + bHead.bWidth > height - FrameWidth) {
            return true;
        }
        for (int i = 1; i < List.size(); i++) {
            int X = List.get(i).getX();
            int Y = List.get(i).getY();

            if ((X == bHead.getX()) && (Y == bHead.getY())) {
                return true;
            }
        }

        if ((mFood.getX() == bHead.getX()) && (mFood.getY() == bHead.getY())) {
            AddBoxes();
            AddFood();
        }
        return false;
    }

    // listener ne?
    class KeypadControl implements KeyListener {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (bHead.mDirection != Direction.RIGHT) {
                    bHead.mDirection = Direction.LEFT;

                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (bHead.mDirection != Direction.LEFT) {
                    bHead.mDirection = Direction.RIGHT;

                }

            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (bHead.mDirection != Direction.DOWN) {
                    bHead.mDirection = Direction.UP;

                }

            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (bHead.mDirection != Direction.UP) {
                    bHead.mDirection = Direction.DOWN;
                }

            }
        }

        public void keyReleased(KeyEvent e) {

        }

    }


    class TimerControl implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            MoveBoxes();
            if (Collision())
                mTimer.stop();
//            GameOver = true;

        }
    }


}
