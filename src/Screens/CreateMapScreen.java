package Screens;

import CreateMapExtension.*;
import Game.GameWindow;
import com.sun.deploy.security.CredentialManager;
import com.sun.glass.ui.Size;
import org.omg.CORBA.MARSHAL;
import sun.rmi.runtime.Log;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/24/2016.
 */
public class CreateMapScreen extends Screens.Screen implements MouseListener {

//    private Stack<Operation> stack = new Stack<Operation>();
    private int status = OperationConst.none;
    private LogicPoint startDrag = new LogicPoint();
    private LogicPoint finishDrag = new LogicPoint();
    GameWindow gameWindow;
    private BufferedImage background, sourceBtn, conveyorBtn, deleteBtn, eraseBtn, planeBtn, saveBtn, shipBtn, truckBtn, undoBtn, waterBtn;

    private Rectangle backgroundRect, sourceRect, deleteRect, conveyorRect, eraseRect, planeRect, saveRect, shipRect, truckRect, undoRect, waterRect;

    private BufferedImage[] imageInMap = new BufferedImage[30];
    private BufferedImage cursorImage;
    private Cursor cursor = Cursor.getDefaultCursor();

    private CreateMapManager createMapManager = new CreateMapManager();
    private final Point pointO = new Point(10,30);
    private  final Size buttonSize = new Size(50,50);
    public CreateMapScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.loadImage();
        this.loadImageInMap();
        this.makeRect();
    }

    private void loadImage(){

        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
            sourceBtn = ImageIO.read(new File("resource/Create map button/Button_source.png"));
            conveyorBtn = ImageIO.read(new File("resource/Create map button/Button_conveyor.png"));
            deleteBtn = ImageIO.read(new File("resource/Create map button/Button_delete.png"));
            eraseBtn = ImageIO.read(new File("resource/Create map button/Button_eraser.png"));
            planeBtn = ImageIO.read(new File("resource/Create map button/Button_plane.png"));
            saveBtn = ImageIO.read(new File("resource/Create map button/Button_save.png"));
            shipBtn = ImageIO.read(new File("resource/Create map button/Button_ship.png"));
            truckBtn = ImageIO.read(new File("resource/Create map button/Button_truck.png"));
            undoBtn = ImageIO.read(new File("resource/Create map button/Button_undo.png"));
            waterBtn = ImageIO.read(new File("resource/Create map button/zz.png"));

//            background = setSize(background, this.gameWindow.windowSize);
            waterBtn = setSize(waterBtn, this.buttonSize);
            sourceBtn = setSize(sourceBtn, this.buttonSize);
            conveyorBtn = setSize(conveyorBtn, this.buttonSize);
            deleteBtn = setSize(deleteBtn, this.buttonSize);
            eraseBtn = setSize(eraseBtn, this.buttonSize);
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

        backgroundRect = new Rectangle(8, 31, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
        deleteRect = makeRect(0);
        eraseRect = makeRect(1);
        sourceRect = makeRect(2);
        planeRect = makeRect(3);
        saveRect = makeRect(4);
        shipRect = makeRect(5);
        truckRect = makeRect(6);
        undoRect = makeRect(7);
        conveyorRect = makeRect(8);
        waterRect = makeRect(9);

    }

    private Rectangle makeRect(int i){
        return new Rectangle(i * this.buttonSize.width + 10 + 10 * i, pointO.y, this.buttonSize.width, this.buttonSize.height);
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
        g.drawImage(planeBtn, planeRect.x, planeRect.y, null);
        g.drawImage(saveBtn, saveRect.x, saveRect.y, null);
        g.drawImage(shipBtn, shipRect.x, shipRect.y, null);
        g.drawImage(truckBtn, truckRect.x, truckRect.y, null);
        g.drawImage(undoBtn, undoRect.x, undoRect.y, null);
        g.drawImage(conveyorBtn, conveyorRect.x, conveyorRect.y, null);
        g.drawImage(sourceBtn, sourceRect.x, sourceRect.y, null);
        g.drawImage(waterBtn, waterRect.x, waterRect.y, null);
    }

    private void drawMap(Graphics g){
        int[][] map = createMapManager.getMap();
        for (int sum=0; sum<=60; sum++){
            for (int i = 0; i<=sum; i++) {
                int j = sum - i;
                if ((i<=35) && (j<=35)){
//                    System.out.println(" i:" + i + " j : " + j + "map : " + map[i][j]);
                    LogicPoint lp = new LogicPoint(i, j);
                    Point p = lp.convertToPoint();
                    if (map[i][j] != 0) {
                        g.drawImage(imageInMap[map[i][j]], p.x, p.y, null);
                    }
                }
            }
        }
//         thuc hien draw cai map kia
    }

    private void loadImageInMap() {
        try{
            imageInMap[MapCodeConst.nonswitchDown] = imageInMap[MapCodeConst.conveyorDown] = ImageIO.read(new File("resource/Create map button/Map_nonswitch_down.png"));
            imageInMap[MapCodeConst.nonswitchLeft] = imageInMap[MapCodeConst.conveyorLeft] = ImageIO.read(new File("resource/Create map button/Map_nonswitch_left.png"));
            imageInMap[MapCodeConst.nonswitchRight] = imageInMap[MapCodeConst.conveyorRight] = ImageIO.read(new File("resource/Create map button/Map_nonswitch_right.png"));
            imageInMap[MapCodeConst.nonswitchUp] = imageInMap[MapCodeConst.conveyorUp] = ImageIO.read(new File("resource/Create map button/Map_nonswitch_up.png"));

            imageInMap[MapCodeConst.endDown] = imageInMap[MapCodeConst.endLeft] = imageInMap[MapCodeConst.endRight] = imageInMap[MapCodeConst.endUp] = ImageIO.read(new File("resource/Create map button/Map_end.png"));

            imageInMap[MapCodeConst.switchDown] = ImageIO.read(new File("resource/Create map button/Map_switch_down.png"));
            imageInMap[MapCodeConst.switchLeft] = ImageIO.read(new File("resource/Create map button/Map_switch_left.png"));
            imageInMap[MapCodeConst.switchRight] = ImageIO.read(new File("resource/Create map button/Map_switch_right.png"));
            imageInMap[MapCodeConst.switchUp] = ImageIO.read(new File("resource/Create map button/Map_switch_up.png"));

            imageInMap[MapCodeConst.tree] = ImageIO.read(new File("resource/Create map button/Map_tree_1.png"));
            imageInMap[MapCodeConst.truck] = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
            imageInMap[MapCodeConst.plane] = ImageIO.read(new File("resource/Create map button/Map_up_plane.png"));
            imageInMap[MapCodeConst.water] = ImageIO.read(new File("resource/Create map button/Map_water.png"));
            imageInMap[MapCodeConst.ship] = ImageIO.read(new File("resource/Create map button/Map_ship.png"));
            imageInMap[MapCodeConst.source] = ImageIO.read(new File("resource/Create map button/Map_source.png"));

        }   catch (IOException e){
            System.out.println(e);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("p =" +  e.getX() + "," + e.getY());
        LogicPoint f = LogicPoint.convertPointToLogicPoint(e.getPoint());
        System.out.println("logic p =" +  f.getLogicX() + "," + f.getLogicY());

        if (conveyorRect.contains(e.getX(), e.getY())){
            status = OperationConst.dragConveyor;
            return;
        }
        if (deleteRect.contains(e.getX(), e.getY())){
            status = OperationConst.none;
            Operation op = new Operation(OperationConst.clickDelete, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            return;
        }
        if (planeRect.contains(e.getX(), e.getY())){
            status = OperationConst.setPlane;
            return;
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
        if (eraseRect.contains(e.getX(),e.getY())){
            status = OperationConst.clickEraser;
            return;
        }
        if (undoRect.contains(e.getX(),e.getY())){
            Operation op = new Operation(OperationConst.clickUndo, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            System.out.println("undo");
            return;
        }
        if (saveRect.contains(e.getX(),e.getY())){
            Operation op = new Operation(OperationConst.clickSave, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            return;
        }
        if (waterRect.contains(e.getX(),e.getY())){
            status = OperationConst.setWater;
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
                case OperationConst.clickEraser:
                {
                    LogicPoint lp = LogicPoint.convertPointToLogicPoint(e.getPoint());
                    Operation op = new Operation(status, lp, new LogicPoint());
                    createMapManager.execute(op);
                    System.out.println("push operate :" + op.getCode() + "," + op.getP1().getLogicX() + "," + op.getP1().getLogicY());
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
                startDrag = LogicPoint.convertPointToLogicPoint(e.getPoint());

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (backgroundRect.contains(e.getX(), e.getY())){
            if (status == OperationConst.dragConveyor){
                finishDrag = LogicPoint.convertPointToLogicPoint(e.getPoint());
//                if ((startDrag.getLogicX() == finishDrag.getLogicX()) && (startDrag.getLogicY() == finishDrag.getLogicY())){
//                    return;
//                }

                // kiem tra xem finish va start co hop le:
                if((startDrag.getLogicX() == finishDrag.getLogicX()) || (startDrag.getLogicY() == finishDrag.getLogicY())){
                    Operation op = new Operation(OperationConst.dragConveyor, startDrag, finishDrag);
                    createMapManager.execute(op);
                    System.out.println("vua push conveyor" + op.getCode() + "doan thang : " + op.getP1().getLogicX() + "," + op.getP1().getLogicY() + " ->" + op.getP2().getLogicX() + "," + op.getP2().getLogicY());
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