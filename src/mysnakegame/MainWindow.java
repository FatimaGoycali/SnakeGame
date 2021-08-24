package mysnakegame;

import javax.swing.JFrame;
import java.awt.*;

public class MainWindow extends JFrame {
    private int mHeight = 600;
    private int mWidth = 600;
    // pencere ucun referans edirik ki her defe givewindow edende yeni pencere yaranmasin
    private static MainWindow mWindow = null;

    private MainWindow() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // pencere baglananda program dayansın
        SetDimension(mWidth, mHeight);
        setResizable(false);
        // pencerenin olcusun deyismek olmasin
        Snake S = new Snake();
        add(S);
        // javada compuntlari yerlesdirerken Layout mexanizmi ile isleyir ve deyiskenleri kotrol edir.
    }

    // statici cagirmaq ucun objecte sinife ehtiyac yoxdur bu sebebden MainWindow private edirkse kosturuktorun islemesi ucun static lazmdi
    public static MainWindow GiveWindow() {
        if (mWindow == null)
            mWindow = new MainWindow();
        return mWindow;
    }

    public void SetDimension(int Width, int Height) {
        Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
        int PosX = 0;
        int PosY = 0;

        if (mWidth + 100 > Dim.width)
            mWidth = Dim.width - 100;
        if (mWidth + 100 > Dim.width)
            mWidth = Dim.width - 100;

        PosX = (Dim.width - mWidth) / 2;
        PosY = (Dim.height - mHeight) / 2;
        setBounds(PosX, PosY, mWidth, mHeight);


    }

}
