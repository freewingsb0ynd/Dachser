package Screens;

import CreateMap.MapCodeConst;
import GameObject.Box;
import GameObject.Conveyor;
import GameObject.ConveyorSwitch;
import Helper.GamePlayManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    BufferedImage background;

    GamePlayManager gamePlayManager;

    Vector<Conveyor> conveyorList;
    public int score = 0;
    private int timeLeft;
    private static final int fps = 60;
    int thisFPS=0;

    public GamePlayScreen(File mapFile) {
        loadBackground();
        gamePlayManager = new GamePlayManager(mapFile);
        this.conveyorList = gamePlayManager.conveyorList;
        this.timeLeft = gamePlayManager.levelTime;
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

        thisFPS +=1;
        if (thisFPS>=fps) {
            thisFPS =0;
            timeLeft--;
        }

//        for (ConveyorSwitch conveyorSwitch : gamePlayManager.conveyorSwitchList) {
//            conveyorSwitch.update();
//        }

        System.out.println(gamePlayManager.box1.getDirection());

        gamePlayManager.updateDirectionForBoxes();
//        gamePlayManager.box1.movebyDirection();
        gamePlayManager.makeBox();
        if(!gamePlayManager.boxOnMapList.isEmpty()){
            for (Box b: gamePlayManager.boxOnMapList) {
                b.movebyDirection();
            }
        }

    }




    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 8, 31, null);
        for (Conveyor conveyorItems : conveyorList) {
            conveyorItems.draw(g);
        }

        if(!gamePlayManager.boxOnMapList.isEmpty()){
            for (Box b: gamePlayManager.boxOnMapList) {
                b.draw(g);
            }
        }
        System.out.println(gamePlayManager.boxOnMapList);
        g.setColor(Color.BLACK);
        g.drawString("Score: "+score+"\t Time:"+timeLeft+"\t High Score: "+score, 40, 40);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point pointClicked = new Point(e.getX(),e.getY());
//
//        System.out.println("p =" + e.getX() + "," + e.getY());
//        LogicPoint f = LogicPoint.convertPointToLogicPoint(e.getPoint());
//        System.out.println("logic p =" + f.getLogicX() + "," + f.getLogicY());
//


        for (ConveyorSwitch conveyorSwitch: gamePlayManager.conveyorSwitchList){
            if (conveyorSwitch.clickArea.contains(pointClicked)) {
                gamePlayManager.getProbableDirectionForAllSwitches();
                conveyorSwitch.changeDirection();
                int x = conveyorSwitch.getLogicPoint().getLogicX();
                int y = conveyorSwitch.getLogicPoint().getLogicY();
                switch (conveyorSwitch.getDirection()) {
                    case UP:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_UP;
                        break;
                    case RIGHT:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_RIGHT;
                        break;
                    case DOWN:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_DOWN;
                        break;
                    case LEFT:
                        gamePlayManager.map[x][y] = MapCodeConst.SWITCH_LEFT;
                        break;


                }
            }

        }
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
