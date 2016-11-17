package Screens;

import GameObject.Conveyor;
import Helper.AnimationHelper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

import static GameObject.Conveyor.*;

/**
 * Created by PC on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    Conveyor conveyor1End;


    Vector<Conveyor> conveyorList;

    public GamePlayScreen() {
        conveyorList = new Vector<Conveyor>();

        conveyorList.add(new Conveyor(92,90,TYPE_X_END));
        conveyorList.add(new Conveyor(92,90,TYPE_X_END));



    }

    @Override
    public void update() {
        for (Conveyor conveyorItems: conveyorList){
            conveyorItems.update();
        }


    }

    @Override
    public void draw(Graphics g) {
        conveyor1End.draw(g);

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
