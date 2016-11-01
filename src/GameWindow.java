/**
 * Created by Hoangelato on 01/11/2016.
 */

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

    public GameWindow() throws IOException {
        initWindow();
        loadImage();
    }


    void initGame(){
        this.setTitle("Demo Game 1945");
        this.setSize(1366, 800);
        this.setVisible(true);
//        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
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

    void loadImage() {
        try {

            background = ImageIO.read(new File("resource/Image/image 96.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initWindow(){
        this.setTitle("Dachser");
        this.setSize(760, 570);
        this.setVisible(true);
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        this.setCursor(blankCursor);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });



    }
    void gameUpdate(){
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
            bufferImage = new BufferedImage(760, 570, 1);
        }
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(background, 0, 0, null);
        g.drawImage(bufferImage, 0, 0, null);
        //System.out.println("Paint");
    }


    @Override
    public void run() {
        gameLoop();
    }
}

