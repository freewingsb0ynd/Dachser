package Game; /**
 * Created by Hoangelato on 01/11/2016.
 */

import GameObject.Conveyor;
import Helper.AnimationCreator;
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

    public GameWindow() throws IOException {
        initWindow();
        loadImage();
    }

    Conveyor conveyor1End= new Conveyor(100,100);
    AnimationCreator conveyor1EndAni = new AnimationCreator("resource/conveyor/1_end/1_end(", 199, 4);
    AnimationCreator conveyor1MidAni = new AnimationCreator("resource/conveyor/1_mid1/1_mid1(", 199, 4);


    void loadImage() {
        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        conveyor1End.loadImage();
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
        conveyor1EndAni.update();
        conveyor1MidAni.update();

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
        Graphics bufferGraphics = bufferImage.getGraphics();
        bufferGraphics.drawImage(background, 0, 0, null);

        //conveyor1End.draw(bufferGraphics);
        conveyor1MidAni.draw(bufferGraphics, 100+38, 100-18);
        conveyor1EndAni.draw(bufferGraphics, 100, 100);


        g.drawImage(bufferImage, 0, 0, null);
        //System.out.println("Paint");


    }


    @Override
    public void run() {
        gameLoop();
    }
}

