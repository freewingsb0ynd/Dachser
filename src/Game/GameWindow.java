package Game; /**
 * Created by Hoangelato on 01/11/2016.
 */

import GameObject.Conveyor;
import Helper.AnimationCreator;
import Screens.GameManager;
import Screens.GamePlayScreen;
import Screens.MenuScreen;
import Screens.Screen;
import javafx.animation.Animation;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameWindow extends Frame implements Runnable{
    BufferedImage bufferImage;
    BufferedImage background;
    BufferedImage mouseIcon;
//    Screen gamePlayingScreen = new GamePlayScreen();

    public GameWindow() throws IOException {
        initWindow();
        loadImage();

        MenuScreen menuScreen = new MenuScreen(this);
        this.addMouseListener(menuScreen);
        GameManager.getInstance().getStackScreen().push(menuScreen);
    }


    void loadImage() {
        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initWindow(){
        this.setTitle("Dachser");
        this.setSize(1280, 720);
        this.setVisible(true);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
//        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
//                cursorImg, new Point(0, 0), "blank cursor");
//        this.setCursor(blankCursor);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }
    void gameUpdate(){
//        gamePlayingScreen.update();
        //update
    }

    void gameLoop(){
        while (true) {
            try {
                gameUpdate();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
    @Override
    public void update(Graphics g) {
        if(bufferImage == null){
            bufferImage = new BufferedImage(1280, 720, 1);
        }

//        bufferGraphics.drawImage(background, 0, 0, null);
//        gamePlayingScreen.draw(bufferGraphics);

        Graphics bufferGraphics = bufferImage.getGraphics();
//        bufferGraphics.drawImage(background, 0, 0, null);
        GameManager.getInstance().getStackScreen().peek().draw(bufferGraphics);
        g.drawImage(bufferImage, 0, 0, null);

    }


    @Override
    public void run() {
        gameLoop();
    }
}

