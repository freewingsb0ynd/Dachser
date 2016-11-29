package Screens;

import CreateMapExtension.CreateMapManager;
import CreateMapExtension.LogicPoint;
import CreateMapExtension.MapCodeConst;
import GameObject.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static CreateMapExtension.MapCodeConst.*;
import static GameObject.ColorBox.*;
import static GameObject.ConveyorMoving.*;
import static GameObject.Direction.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    BufferedImage background;

    Vector<Conveyor> conveyorList;
    Conveyor conveyor1, conveyor2, conveyor3, conveyor4, conveyor5;

    int arrayIndex[][];

    Box box1;
    public GamePlayScreen() {
        loadBackground();

        arrayIndex = new int[36][36];
        arrayIndex[12][15] = source;
        for (int i = 13; i < 23 ; i++) {
           arrayIndex[i][15] = conveyorRight;
        }
        arrayIndex[19][15] = nonswitchDown;
        for (int i = 16; i < 23; i++) {
            arrayIndex[19][i] = conveyorDown;
        }


        conveyorList = new Vector<Conveyor>();

        for (int sum = 0; sum <= 60; sum++) {
            for (int i = 0; i <= sum; i++) {
                int j = sum - i;
                if ((i <= 35) && (j <= 35)) {
//                    System.out.println(" i:" + i + " j : " + j + "map : " + map[i][j]);
                    LogicPoint lp = new LogicPoint(i, j);
                    Point p = lp.convertToPoint();
                    if (arrayIndex[i][j] != 0) {
                        Point point = new LogicPoint(i,j).convertToPoint();
                        conveyorList.add(new ConveyorMoving(point.x,point.y).getConveyorByType(convertArrayIndex(arrayIndex[i][j])));
                    }
                }
            }
        }



//        for (int x = 10; x < 11; x = x + 4) {
//            for (int y = 14; y < 15; y++) {
//                conveyor1 = new ConveyorMoving(LogicPoint.baseX + 36 * (x - y), LogicPoint.baseY + 18 * (x + y)).getConveyorByType(ConveyorMoving.TYPE_Y_MID);
//                conveyorList.add(conveyor1);
//            }
//        }
//
//        box1 = new Box(new LogicPoint(18,18), WHITE);
//        for (int x = -15; x < 17 ; x = x + 1) {
//            //if (x % 4 == 0) continue;
//            // for (int y = - (17 - x) ; y < (17 - x) ; y++) {
//            conveyor1 = new ConveyorMoving( 571 + 36 * (x ), 312 + 18 * (x ) ).getConveyorByType(ConveyorMoving.TYPE_Y_MID);
//            conveyorList.add(conveyor1);
//            // }
//
//        }
//        Point
/***
 conveyor1 = new ConveyorMoving(417,251).getConveyorByType(ConveyorMoving.TYPE_X_END);

 conveyor2 = new ConveyorMoving(417 +36, 251 -18).getConveyorByType(ConveyorMoving.TYPE_X_MID);

 conveyor3 = new ConveyorMoving(92+38+38,90-18-18).getConveyorByType(ConveyorMoving.TYPE_X_MID);

 conveyor4 = new ConveyorMoving(92,90).getConveyorByType(ConveyorMoving.TYPE_Y_END);

 conveyor5 = new ConveyorMoving(92-38,90-18).getConveyorByType(ConveyorMoving.TYPE_Y_MID);


 //        conveyorList.add(conveyor3);
 conveyorList.add(conveyor2);
 conveyorList.add(conveyor5);
 conveyorList.add(conveyor4);
 conveyorList.add(conveyor1);


 *///

    }

    private int convertArrayIndex(int index) {
        switch (index){
            case conveyorUp:
            case conveyorDown:
                return TYPE_Y_MID;
            case conveyorRight:
            case conveyorLeft:
                return TYPE_X_MID;
            case nonswitchDown:
            default:return 0;
        }

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
        for (Conveyor conveyorItems : conveyorList) {
            conveyorItems.update();
        }
//        box1.movebyDirection(UP);
//        box1.movebyDirection(LEFT);
//        box1.movebyDirection(DOWN);
//        box1.movebyDirection(RIGHT);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 8, 31, null);
        for (Conveyor conveyorItems : conveyorList) {
            conveyorItems.draw(g);
        }
//        box1.draw(g);
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
