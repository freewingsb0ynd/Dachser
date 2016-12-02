package Screens;

import CreateMap.MapCodeConst;
import GameObject.Conveyor;
import GameObject.ConveyorSwitch;
import GameObject.GameObject;
import Helper.GamePlayManager;
import Helper.LogicPoint;

import javax.imageio.ImageIO;
import javax.swing.*;
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

    Timer timer = new Timer(30000, null);
    int score, time;

    public GamePlayScreen() {
        loadBackground();
        gamePlayManager = new GamePlayManager();
        this.conveyorList = gamePlayManager.conveyorList;
        Point p = new Point();
        LogicPoint lp = new LogicPoint();
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

//        for (ConveyorSwitch conveyorSwitch : gamePlayManager.conveyorSwitchList) {
//            conveyorSwitch.update();
//        }
        System.out.println(gamePlayManager.box1.getDirection());
        gamePlayManager.updateDirectionForBoxes();
        gamePlayManager.box1.movebyDirection();

    }




    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 8, 31, null);
//        drawThings(g);
        for(int i = 0; i < 36; i++){
            for(int j = 0; j < 36; j++){
                LogicPoint lp = new LogicPoint(i, j);
                Point p = lp.convertToPoint();
                try {
                    if (gamePlayManager.map[i][j] == MapCodeConst.PLANE) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_plane.png")),
                                p.x, p.y, null);
                    }if (gamePlayManager.map[i][j] == MapCodeConst.SHIP) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_ship.png")),
                                p.x, p.y, null);
                    }if (gamePlayManager.map[i][j] == MapCodeConst.TRUCK) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_truck.png")),
                                p.x, p.y, null);
                    }if (gamePlayManager.map[i][j] == MapCodeConst.ROAD) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_road.png")),
                                p.x, p.y, null);
                    }if (gamePlayManager.map[i][j] == MapCodeConst.WATER) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_water.png")),
                                p.x, p.y, null);
                    }if(gamePlayManager.map[i][j] == MapCodeConst.TREE){
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_tree.png")),
                                p.x, p.y, null);
                    } if (gamePlayManager.map[i][j] == MapCodeConst.SOURCE) {
                        g.drawImage(ImageIO.read(new File("resource/Create map button/Map_truck.png")),
                                p.x - 72, p.y,null);
                    }

                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        for (Conveyor conveyorItems : conveyorList) {
            conveyorItems.draw(g);
        }

        g.setColor(Color.BLACK);
        g.drawString("Score: "+score+"\t Time:"+time+"\t High Score: "+score, 40, 40);
        gamePlayManager.box1.draw(g);
    }

//    public void drawThings(Graphics g){
//        for(int i = 0; i < 36; i++){
//            for(int j = 0; j < 36; j++){
//                if(gamePlayManager.map[i][j] >= 18 && gamePlayManager.map[i][j] <= 23) {
//                    LogicPoint lp = new LogicPoint(i, j);
//                    Point p = lp.convertToPoint();
//                    g.drawImage(gamePlayManager.imageMap[i][j], p.x, p.y, null);
//                }
//            }
//        }
//    }

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
