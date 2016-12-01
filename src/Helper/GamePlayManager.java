package Helper;

import GameObject.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import static CreateMap.MapCodeConst.*;
import static GameObject.ConveyorMoving.TYPE_X_MID;
import static GameObject.ConveyorMoving.TYPE_Y_MID;
import static GameObject.Direction.UP;

/**
 * Created by Hoangelato on 01/12/2016.
 */
public class GamePlayManager {
    public Vector<Conveyor> conveyorList;
    public Vector<ConveyorSwitch> conveyorSwitchList;
    public int map[][];

    public GamePlayManager() {
        loadMap();

    }

    public void update(){

    }


    private void loadMap() {
        map = new int[36][36];
        try {

            FileReader f = new FileReader("resource/Map/map4.pam");
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
                        Conveyor conveyorToAdd = getConveyorFromCode(map[i][j], point.x, point.y);
                        if(isSwitch(conveyorToAdd)) conveyorSwitchList.add((ConveyorSwitch) conveyorToAdd);
                        conveyorList.add(conveyorToAdd);

                        //conveyorList.add(getConveyorFromCode(map[i][j], point.x, point.y));
                    }
                }
            }
        }
    }

    private boolean isSwitch(Conveyor c){
        if (c instanceof ConveyorSwitch) return true;
        return false;
    }


    private Conveyor getConveyorFromCode(int mapCode, int posX, int posY) {
        switch (mapCode) {
            case CONVEYOR_UP:
            case CONVEYOR_RIGHT:
            case CONVEYOR_LEFT:
            case CONVEYOR_DOWN:
                return new ConveyorMoving(posX, posY).getConveyorByType(convertArrayIndex(mapCode));
            case NONSWITCH_DOWN:
            case NONSWITCH_LEFT:
            case NONSWITCH_RIGHT:
            case NONSWITCH_UP:
                return new ConveyorNonSwitch(posX, posY).getConveyorNonSwitchByDirection(getDirectionFromMapCode(mapCode));
            case SWITCH_DOWN:
            case SWITCH_LEFT:
            case SWITCH_UP:
            case SWITCH_RIGHT:
                return new ConveyorSwitch(posX,posY,getDirectionFromMapCode(mapCode));
        }
        return new Conveyor(posX,posY);
    }

    private Direction getDirectionFromMapCode(int mapCode){
        switch (mapCode){
            case NONSWITCH_DOWN:
            case SWITCH_DOWN:
            case CONVEYOR_DOWN:
                return Direction.DOWN;
            case NONSWITCH_LEFT:
            case SWITCH_LEFT:
            case CONVEYOR_LEFT:
                return Direction.LEFT;
            case NONSWITCH_RIGHT:
            case SWITCH_RIGHT:
            case CONVEYOR_RIGHT:
                return Direction.RIGHT;
            case NONSWITCH_UP:
            case SWITCH_UP:
            case CONVEYOR_UP:
                return UP;
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



}
