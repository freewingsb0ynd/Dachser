package Screens;

import GameObject.Conveyor;
import Helper.AnimationCreator;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by PC on 17/11/2016.
 */
public class GamePlayScreen extends Screen {
    Conveyor conveyor1End;
    AnimationCreator conveyor1EndAni;
    AnimationCreator conveyor1MidAni;

    public GamePlayScreen() {
        conveyor1End = new Conveyor(90,90);
        conveyor1EndAni = new AnimationCreator("resource/conveyor/1_end/1_end(", 199, 4);
        conveyor1MidAni = new AnimationCreator("resource/conveyor/1_mid1/1_mid1(", 199, 4);
    }

    @Override
    public void update() {
        conveyor1EndAni.update();
        conveyor1MidAni.update();

    }

    @Override
    public void draw(Graphics g) {
        conveyor1End.draw(g);
//        conveyor1MidAni.draw(g, 100+38, 100-18);
//        conveyor1EndAni.draw(g, 100, 100);
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
