package Screens;

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

    public GamePlayScreen() {
        loadBackground();
        gamePlayManager = new GamePlayManager();
        this.conveyorList = gamePlayManager.conveyorList;
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
        for (Conveyor conveyorItems : conveyorList) {
            conveyorItems.draw(g);
        }
        gamePlayManager.box1.draw(g);
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
