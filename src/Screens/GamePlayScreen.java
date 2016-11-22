package Screens;

import GameObject.Conveyor;
import GameObject.ConveyorMoving;
import Helper.AnimationHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static GameObject.Conveyor.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    BufferedImage background;

    Vector<Conveyor> conveyorList;
    Conveyor conveyor1, conveyor2, conveyor3, conveyor4,conveyor5;

    int arrayIndex[][];


    public GamePlayScreen() {
        loadBackground();

        arrayIndex = new int[17][18];

        

        conveyorList = new Vector<Conveyor>();

        conveyor1 = new ConveyorMoving(92+36*28,90+18*28).getConveyorByType(ConveyorMoving.TYPE_X_END);

        conveyor2 = new ConveyorMoving(92+38,90-18).getConveyorByType(ConveyorMoving.TYPE_X_MID);

        conveyor3 = new ConveyorMoving(92+38+38,90-18-18).getConveyorByType(ConveyorMoving.TYPE_X_MID);

        conveyor4 = new ConveyorMoving(92,90).getConveyorByType(ConveyorMoving.TYPE_Y_END);

        conveyor5 = new ConveyorMoving(92-38,90-18).getConveyorByType(ConveyorMoving.TYPE_Y_MID);


//        conveyorList.add(conveyor3);
//        conveyorList.add(conveyor2);
        conveyorList.add(conveyor5);
        conveyorList.add(conveyor4);
        conveyorList.add(conveyor1);




    }

    void loadBackground() {
        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update() {
        for (Conveyor conveyorItems: conveyorList) {
            conveyorItems.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        for (Conveyor conveyorItems: conveyorList){
            conveyorItems.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
