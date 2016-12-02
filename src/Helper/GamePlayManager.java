package Helper;

/**
 * Created by admin on 12/2/2016.
 */

import CreateMap.MapCodeConst;
import GameObject.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Queue;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

import static CreateMap.MapCodeConst.*;
import static CreateMap.MapCodeConst.CONVEYOR_LEFT;
import static GameObject.ColorBox.*;
import static GameObject.ConveyorMoving.*;

/**
 * Created by Hoangelato on 01/12/2016.
 */
public class GamePlayManager {
    public Vector<Conveyor> conveyorList;
    public Vector<ConveyorSwitch> conveyorSwitchList;

    public int map[][];

    public Queue<Box> boxWaitingList;
    public Queue<Box> boxOnMapList;

    public int score;
    LogicPoint source;

    public Box box1;

    public GamePlayManager(File mapFile) {
        loadMap(mapFile);
//        getImagesFromMap();


    }

    public int levelTime = 180;

    private void startBoxManager() {
        boxWaitingList = new LinkedList<Box>();

        box1 = new Box(source,PINK);
        boxWaitingList.add(new Box(source,PINK));
        boxWaitingList.add(new Box(source,GREEN));
        boxWaitingList.add(new Box(source,BLUE));
        boxWaitingList.add(new Box(source,RED));
        boxWaitingList.add(new Box(source,PINK));
        boxWaitingList.add(new Box(source,GREEN));
        boxWaitingList.add(new Box(source,BLUE));
        boxWaitingList.add(new Box(source,RED));

        boxOnMapList = new LinkedList<Box>();
//        for (int i=0;i<6;++i){
//            boxOnMapList.add(boxWaitingList.poll());
//        }

        System.out.println(boxWaitingList);
        System.out.println(boxOnMapList);

        updateDirectionForBoxes();


    }


    public void update() {
    }


    private void loadMap(File mapFile) {
        map = new int[36][36];
        try {

            FileReader f = new FileReader(mapFile);
            BufferedReader reader = new BufferedReader(f);
            String line = reader.readLine();
            line = reader.readLine();
            line = reader.readLine();
            int j = 0;
            while (line != null) {
                String[] lineSplit = line.split(",");
                for (int i = 0; i < lineSplit.length; i++) {
                    map[j][i] = Integer.parseInt(lineSplit[i]);
                }
                line = reader.readLine();
                j++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        //        map = new int[36][36];
        //        map[12][15] = SOURCE;
        //        for (int i = 13; i < 23; i++) {
        //            map[i][15] = CONVEYOR_RIGHT;
        //        }
        //        map[23][15] = NONSWITCH_UP;
        //        map[19][15] = NONSWITCH_RIGHT;
        //        for (int i = 16; i < 23; i++) {
        //            map[19][i] = CONVEYOR_DOWN;
        //        }
        //        map[19][19] = SWITCH_LEFT;
        //        for (int i = 18; i > 14; i--) {
        //            map[i][19] = CONVEYOR_LEFT;
        //        }
        //        map[21][21] = CONVEYOR_DOWN;



        conveyorList = new Vector<Conveyor>();
        conveyorSwitchList = new Vector<ConveyorSwitch>();


        for (int sum = 15; sum <= 60; sum++) {
            for (int i = 0; i <= sum; i++) {
                int j = sum - i;
                if ((i <= 35) && (j <= 35)) {
                    //                    System.out.println(" i:" + i + " j : " + j + "map : " + map[i][j]);
                    LogicPoint lp = new LogicPoint(i, j);
                    Point point = lp.convertToPoint();
                    if (map[i][j] != 0 && map[i][j] != 100) {
                        //                        if(isSwitch())
                        //                            addConveyorFromCode(map[i][j], point.x, point.y);
                        Conveyor conveyorToAdd = getConveyorFromMapCode(map[i][j], point.x, point.y);
                        if (isSwitch(conveyorToAdd)) conveyorSwitchList.add((ConveyorSwitch) conveyorToAdd);
                        conveyorList.add(conveyorToAdd);

                        //conveyorList.add(getConveyorFromMapCode(map[i][j], point.x, point.y));
                    }
                    if (map[i][j] == SOURCE) source = new LogicPoint(lp.getLogicX()+1, lp.getLogicY());


                }
            }
        }

        getProbableDirectionForAllSwitches();

        startBoxManager();

//        box1 = new Box(new LogicPoint(11, 15), PINK);
//        updateDirectionForBoxes();

//        System.out.println(getDirectionFromMapCode(map[11][15]));
    }

    int nextBoxTime=2000;
    int countTime = 0;



    public void makeBox(){
        countTime += 17;

//        int makeBoxTime = rand.nextInt()*2000 + 3000;
//        while (countTime <= makeBoxTime){

//        }
        System.out.println(countTime + "     " + nextBoxTime);

        if(!boxWaitingList.isEmpty()&&boxOnMapList.size()<7&&countTime>nextBoxTime) {
            boxOnMapList.add(boxWaitingList.poll());

            countTime = 0;
            nextBoxTime = (int) (Math.round(Math.random() * 2000) + 3000);
        }
    }

    public void updateDirectionForBoxes(){

        //System.out.println(box1.isAllowToChange);
        System.out.println(boxOnMapList);
        for (Box b: boxOnMapList){
            if (b.isAllowToChange) {
                b.setDirection(getDirectionFromMapCode(map[b.getLogicPoint().getLogicX()][b.getLogicPoint().getLogicY()]));
            }
            b.checkToChangDirection();
        }
        //box1.checkToChangDirection();
    }

    public void getProbableDirectionForAllSwitches() {
        for (ConveyorSwitch conveyorSwitch : conveyorSwitchList) {
            getProbableDirection(conveyorSwitch);
        }
    }

    void getProbableDirection(ConveyorSwitch conveyorSwitch) {
        ArrayList<Direction> returnList = new ArrayList<Direction>();

        int logicX = conveyorSwitch.getLogicPoint().getLogicX();
        int logicY = conveyorSwitch.getLogicPoint().getLogicY();

        boolean[] isValidDirection = new boolean[4];

        int[] neighborsMapCode = new int[4];
        neighborsMapCode[0] = map[logicX][logicY - 1];
        neighborsMapCode[1] = map[logicX + 1][logicY];
        neighborsMapCode[2] = map[logicX][logicY + 1];
        neighborsMapCode[3] = map[logicX - 1][logicY];

        for (int i = 0; i < 4; ++i) {
            if (getDirectionFromMapCode(neighborsMapCode[i]) == Direction.NONE) {
                isValidDirection[i] = false;
            } else if (getDirectionFromMapCode(neighborsMapCode[i]) == Conveyor.convertIndexToDirection((i + 2) % 4)) {
                isValidDirection[i] = false;
            } else isValidDirection[i] = true;
        }

        for (int i = 0; i < 4; i++) {
            if (isValidDirection[i]) {
                returnList.add(Conveyor.convertIndexToDirection(i));
            }
        }

        conveyorSwitch.probableDirections = returnList;
        //System.out.println(returnList);
    }

    private boolean isSwitch(Conveyor c) {
        if (c instanceof ConveyorSwitch) return true;
        return false;
    }


    private Conveyor getConveyorFromMapCode(int mapCode, int posX, int posY) {
        switch (mapCode) {
            case CONVEYOR_UP:
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
            case CONVEYOR_DOWN:
                return new ConveyorMoving(posX, posY).getConveyorByType(getConveyorTypeFromMapcode(mapCode));
            case NONSWITCH_DOWN:
            case NONSWITCH_LEFT:
            case NONSWITCH_RIGHT:
            case NONSWITCH_UP:
                return new ConveyorNonSwitch(posX, posY).getConveyorNonSwitchByDirection(getDirectionFromMapCode(mapCode));
            case SWITCH_DOWN:
            case SWITCH_LEFT:
            case SWITCH_UP:
            case SWITCH_RIGHT:
                return new ConveyorSwitch(posX, posY, getDirectionFromMapCode(mapCode));
            case END_DOWN:
            case END_UP:
                return new ConveyorYEnd(posX,posY);
            case END_LEFT:
            case END_RIGHT:
                return new ConveyorXEnd(posX,posY);
        }
        return new Conveyor(posX, posY);
    }

//    public void getImagesFromMap() {
//        for (int i = 0; i < 36; i++) {
//            for (int j = 0; j < 36; j++) {
//
//                try {
//                    switch (map[i][j]) {
//                        case MapCodeConst.TRUCK:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
//                        case MapCodeConst.PLANE:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_plane.png"));
//                        case MapCodeConst.SHIP:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_ship.png"));
//                        case MapCodeConst.TREE:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_tree.png"));
//                        case MapCodeConst.WATER:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_water.png"));
//                        case MapCodeConst.ROAD:
//                            imageMap[i][j] = ImageIO.read(new File("resource/Create map button/Map_road.png"));
//                        default:
//                            continue;
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//
//                }
//            }
//        }
//    }


    public Direction getDirectionFromMapCode(int mapCode) {
        switch (mapCode) {
            case NONSWITCH_DOWN:
            case SWITCH_DOWN:
            case CONVEYOR_DOWN:
            case END_DOWN:
                return Direction.DOWN;
            case NONSWITCH_LEFT:
            case SWITCH_LEFT:
            case CONVEYOR_LEFT:
            case END_LEFT:
                return Direction.LEFT;
            case NONSWITCH_RIGHT:
            case SWITCH_RIGHT:
            case CONVEYOR_RIGHT:
            case END_RIGHT:
                return Direction.RIGHT;
            case NONSWITCH_UP:
            case SWITCH_UP:
            case CONVEYOR_UP:
            case END_UP:
                return Direction.UP;
            default:
                return Direction.NONE;
        }

    }


    private int getConveyorTypeFromMapcode(int mapCode) {
        switch (mapCode) {
            case CONVEYOR_UP:
            case CONVEYOR_DOWN:
                return TYPE_Y_MID;
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
                return TYPE_X_MID;
            default:
                return 0;
        }
    }


}
