package Screens;

import Game.GameWindow;
import GameObject.Direction;
import com.sun.glass.ui.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * Created by Admin on 11/24/2016.
 */
public class CreateMapScreen extends Screens.Screen implements MouseListener {
    private int[][] map = new int[40][40];

    GameWindow gameWindow;
    BufferedImage background,deleteBtn, eraseBtn, newBtn, planeBtn, saveBtn, shipBtn, truckBtn, undoBtn;

    Rectangle deleteRect, eraseRect, newRect, planeRect, saveRect, shipRect, truckRect, undoRect;

    private  final Size buttonSize = new Size(50,50);
    public CreateMapScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.loadImage();
        this.makeRect();
    }

    private void loadImage(){

        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
            deleteBtn = ImageIO.read(new File("resource/Create map button/delete.png"));
            eraseBtn = ImageIO.read(new File("resource/Create map button/eraser.png"));
            newBtn = ImageIO.read(new File("resource/Create map button/new.png"));
            planeBtn = ImageIO.read(new File("resource/Create map button/plane.png"));
            saveBtn = ImageIO.read(new File("resource/Create map button/save.png"));
            shipBtn = ImageIO.read(new File("resource/Create map button/ship.png"));
            truckBtn = ImageIO.read(new File("resource/Create map button/truck.png"));
            undoBtn = ImageIO.read(new File("resource/Create map button/undo.png"));

            background = setSize(background, this.gameWindow.windowSize);
            deleteBtn = setSize(deleteBtn, this.buttonSize);
            eraseBtn = setSize(eraseBtn, this.buttonSize);
            newBtn = setSize(newBtn, this.buttonSize);
            planeBtn = setSize(planeBtn, this.buttonSize);
            saveBtn = setSize(saveBtn, this.buttonSize);
            shipBtn = setSize(shipBtn, this.buttonSize);
            truckBtn = setSize(truckBtn, this.buttonSize);
            undoBtn = setSize(undoBtn, this.buttonSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){

        deleteRect = makeRect(0);
        eraseRect = makeRect(1);
        newRect = makeRect(2);
        planeRect = makeRect(3);
        saveRect = makeRect(4);
        shipRect = makeRect(5);
        truckRect = makeRect(6);
        undoRect = makeRect(7);
    }

    private Rectangle makeRect(int i){
        return new Rectangle(i * this.buttonSize.width + 10 + 10 * i, 30, this.buttonSize.width, this.buttonSize.height);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(deleteBtn, deleteRect.x , deleteRect.y , null);
        g.drawImage(eraseBtn, eraseRect.x, eraseRect.y, null);
        g.drawImage(newBtn, newRect.x, newRect.y, null);
        g.drawImage(planeBtn, planeRect.x, planeRect.y, null);
        g.drawImage(saveBtn, saveRect.x, saveRect.y, null);
        g.drawImage(shipBtn, shipRect.x, shipRect.y, null);
        g.drawImage(truckBtn, truckRect.x, truckRect.y, null);
        g.drawImage(undoBtn, undoRect.x, undoRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("p =" +  e.getX() + "," + e.getY());
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

        }
        if (deleteRect.contains(e.getX(), e.getY())){

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