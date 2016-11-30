package Screens;

import CreateMap.LogicPoint;
import GameObject.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static CreateMap.MapCodeConst.*;
import static GameObject.ConveyorMoving.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    BufferedImage background;

    Vector<Conveyor> conveyorList;
    int arrayIndex[][];

    Box box1;
    public GamePlayScreen() {
        loadBackground();

        arrayIndex = new int[36][36];
        arrayIndex[12][15] = SOURCE;
        for (int i = 13; i < 23; i++) {
            arrayIndex[i][15] = CONVEYOR_RIGHT;
        }
        arrayIndex[23][15] = NONSWITCH_UP;
        arrayIndex[19][15] = NONSWITCH_RIGHT;
        for (int i = 16; i < 23; i++) {
            arrayIndex[19][i] = CONVEYOR_DOWN;
        }
        arrayIndex[19][19] = SWITCH_LEFT;
        for (int i = 18; i > 14; i--) {
            arrayIndex[i][19] = CONVEYOR_LEFT;
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
                        Point point = new LogicPoint(i, j).convertToPoint();
                        conveyorList.add(getConveyorFromCode(arrayIndex[i][j], point.x, point.y));
                    }
                }
            }
        }

    }


    private Conveyor getConveyorFromCode(int index, int posX, int posY){
        switch (index) {
            case CONVEYOR_UP:
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
            case CONVEYOR_DOWN:
                return new ConveyorMoving(posX, posY).getConveyorByType(convertArrayIndex(index));
            case NONSWITCH_DOWN:
            case NONSWITCH_LEFT:
            case NONSWITCH_RIGHT:
            case NONSWITCH_UP:
                return new ConveyorFixedSwitch(posX,posY).getConveyorNonSwitchByDirection(convertFromMapCode(index));


            default:
                return new Conveyor(posX, posY);
            }

    };
    private Direction convertFromMapCode(int mapCode){
        switch (mapCode){
            case NONSWITCH_DOWN:
                return Direction.DOWN;
            case NONSWITCH_LEFT:
                return Direction.LEFT;
            case NONSWITCH_RIGHT:
                return Direction.RIGHT;
            case NONSWITCH_UP:
                return Direction.UP;
            default: return Direction.NONE;
        }

    }

    private int convertArrayIndex(int index) {
        switch (index){
            case CONVEYOR_UP:
            case CONVEYOR_DOWN:
                return TYPE_Y_MID;
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
                return TYPE_X_MID;
            case NONSWITCH_DOWN:
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
