package Screens;

import CreateMapExtension.LogicPoint;
import CreateMapExtension.Operation;
import CreateMapExtension.OperationConst;
import CreateMapExtension.OperationToMapExtensions;
import Game.GameWindow;
import com.sun.glass.ui.Size;
import sun.rmi.runtime.Log;

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

    private Stack<Operation> stack = new Stack<Operation>();
    private int status = OperationConst.none;
    private Point startDrag = new Point();
    private Point finishDrag = new Point();
    GameWindow gameWindow;
    BufferedImage background, sourceBtn, conveyorBtn, deleteBtn, eraseBtn, newBtn, planeBtn, saveBtn, shipBtn, truckBtn, undoBtn;

    Rectangle backgroundRect, sourceRect, deleteRect, conveyorRect, eraseRect, newRect, planeRect, saveRect, shipRect, truckRect, undoRect;

    private  final Size buttonSize = new Size(50,50);
    public CreateMapScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.loadImage();
        this.makeRect();
    }

    private void loadImage(){

        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
            sourceBtn = ImageIO.read(new File("resource/Create map button/source.png"));
            conveyorBtn = ImageIO.read(new File("resource/Create map button/conveyor.png"));
            deleteBtn = ImageIO.read(new File("resource/Create map button/delete.png"));
            eraseBtn = ImageIO.read(new File("resource/Create map button/eraser.png"));
            newBtn = ImageIO.read(new File("resource/Create map button/new.png"));
            planeBtn = ImageIO.read(new File("resource/Create map button/plane.png"));
            saveBtn = ImageIO.read(new File("resource/Create map button/save.png"));
            shipBtn = ImageIO.read(new File("resource/Create map button/ship.png"));
            truckBtn = ImageIO.read(new File("resource/Create map button/truck.png"));
            undoBtn = ImageIO.read(new File("resource/Create map button/undo.png"));

            background = setSize(background, this.gameWindow.windowSize);
            sourceBtn = setSize(sourceBtn, this.buttonSize);
            conveyorBtn = setSize(conveyorBtn, this.buttonSize);
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

        backgroundRect = new Rectangle(0, 0, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
        deleteRect = makeRect(0);
        eraseRect = makeRect(1);
        newRect = makeRect(2);
        planeRect = makeRect(3);
        saveRect = makeRect(4);
        shipRect = makeRect(5);
        truckRect = makeRect(6);
        undoRect = makeRect(7);
        conveyorRect = makeRect(8);
        sourceRect = makeRect(10);
    }

    private Rectangle makeRect(int i){
        return new Rectangle(i * this.buttonSize.width + 10 + 10 * i, 30, this.buttonSize.width, this.buttonSize.height);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, backgroundRect.x, backgroundRect.y, null);
        this.drawMenu(g);
        this.drawMap(g);
    }

    private void drawMenu(Graphics g){
        g.drawImage(deleteBtn, deleteRect.x , deleteRect.y , null);
        g.drawImage(eraseBtn, eraseRect.x, eraseRect.y, null);
        g.drawImage(newBtn, newRect.x, newRect.y, null);
        g.drawImage(planeBtn, planeRect.x, planeRect.y, null);
        g.drawImage(saveBtn, saveRect.x, saveRect.y, null);
        g.drawImage(shipBtn, shipRect.x, shipRect.y, null);
        g.drawImage(truckBtn, truckRect.x, truckRect.y, null);
        g.drawImage(undoBtn, undoRect.x, undoRect.y, null);
        g.drawImage(conveyorBtn, conveyorRect.x, conveyorRect.y, null);
        g.drawImage(sourceBtn, sourceRect.x, sourceRect.height, null);
    }

    private void drawMap(Graphics g){
        int[][] map = OperationToMapExtensions.execute(stack);

        // thuc hien draw cai map kia
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("p =" +  e.getX() + "," + e.getY());

        if (conveyorRect.contains(e.getX(), e.getY())){
            status = OperationConst.dragConveyor;
            return;
        }
        if (deleteRect.contains(e.getX(), e.getY())){
            status = OperationConst.none;
            stack.clear();
            return;
        }
        if (planeRect.contains(e.getX(), e.getY())){
            status = OperationConst.setPlane;
            return;
        }
        if (newRect.contains(e.getX(), e.getY())){

        }
        if (shipRect.contains(e.getX(), e.getY())){
            status = OperationConst.setShip;
            return;
        }
        if (truckRect.contains(e.getX(), e.getY())){
            status = OperationConst.setTruck;
            return;
        }
        if (sourceRect.contains(e.getX(), e.getY())){
            status = OperationConst.setSource;
            return;
        }
        if (backgroundRect.contains(e.getX(), e.getY())){
            switch (status){
                case OperationConst.setSource:
                case OperationConst.setRoad:
                case OperationConst.setPlane:
                case OperationConst.setShip:
                case OperationConst.setTree:
                case OperationConst.setTruck:
                case OperationConst.setWater:
                {   // Nhat thiet ke cai LogicPoint nay hoi ngu :D
                    LogicPoint lp = LogicPoint.convertPointToLogicPoint(e.getPoint());
                    Point p1 = new Point(lp.logicX, lp.logicY);
                    Operation op = new Operation(status, p1, new Point());
                    stack.push(op);
                    System.out.println("push operate :" + op.getCode() + "," + op.getP1() + "-" + op.getP2());
                    break;
                }

            }
            return;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (backgroundRect.contains(e.getX(), e.getY())){
            if (status == OperationConst.dragConveyor){
                LogicPoint lp = LogicPoint.convertPointToLogicPoint(e.getPoint());
                startDrag = new Point(lp.logicX, lp.logicY);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (backgroundRect.contains(e.getX(), e.getY())){
            if (status == OperationConst.dragConveyor){
                LogicPoint lp = LogicPoint.convertPointToLogicPoint(e.getPoint());
                finishDrag = new Point(lp.logicX, lp.logicY);
                // kiem tra xem finish va start co hop le:
                if((startDrag.x == finishDrag.x) || (startDrag.y == finishDrag.y)){
                    Operation op = new Operation(OperationConst.dragConveyor, startDrag, finishDrag);
                    stack.push(op);
                    System.out.println("vua push conveyor" + op.getCode() + "doan thang : " + op.getP1() + op.getP2());
                }

            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}