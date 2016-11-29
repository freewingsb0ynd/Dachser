package Screens;

import CreateMap.*;
import Game.GameWindow;
import com.sun.glass.ui.Size;

import javax.imageio.ImageIO;
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
    private int status = OperationConst.NONE;
    private LogicPoint startDrag = new LogicPoint();
    private LogicPoint finishDrag = new LogicPoint();
    GameWindow gameWindow;
    private BufferedImage background, sourceBtn, conveyorBtn, deleteBtn, eraseBtn,
            planeBtn, saveBtn, shipBtn, truckBtn, undoBtn, waterBtn, roadBtn, treeBtn;

    private Rectangle backgroundRect, sourceRect, deleteRect, conveyorRect, eraseRect, planeRect,
            saveRect, shipRect, truckRect, undoRect, waterRect, roadRect, treeRect;

    private BufferedImage[] imageInMap = new BufferedImage[30];
    private BufferedImage cursorImage;
    private Cursor cursor = Cursor.getDefaultCursor();

    private CreateMapManager createMapManager = new CreateMapManager();
    private final Point pointO = new Point(10, 30);
    private final Size buttonSize = new Size(50, 50);

    public CreateMapScreen(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        this.loadImage();
        this.loadImageInMap();
        this.makeRect();
    }

    private void loadImage() {

        try {
            background = ImageIO.read(new File("resource/Image/background_4.png"));
            sourceBtn = ImageIO.read(new File("resource/Create map button/Button_source.png"));
            conveyorBtn = ImageIO.read(new File("resource/Create map button/Button_conveyor.png"));

            deleteBtn = ImageIO.read(new File("resource/Create map button/Button_delete.png"));
            eraseBtn = ImageIO.read(new File("resource/Create map button/Button_eraser.png"));
            saveBtn = ImageIO.read(new File("resource/Create map button/Button_save.png"));
            undoBtn = ImageIO.read(new File("resource/Create map button/Button_undo.png"));

            planeBtn = ImageIO.read(new File("resource/Create map button/Button_plane.png"));
            shipBtn = ImageIO.read(new File("resource/Create map button/Button_ship.png"));
            truckBtn = ImageIO.read(new File("resource/Create map button/Button_truck.png"));

            waterBtn = ImageIO.read(new File("resource/Create map button/Button_water.png"));
            roadBtn = ImageIO.read(new File("resource/Create map button/Button_road.png"));
            treeBtn = ImageIO.read(new File("resource/Create map button/Button_tree.png"));


//            background = setSize(background, this.gameWindow.windowSize);
            waterBtn = setSize(waterBtn, this.buttonSize);
            roadBtn = setSize(roadBtn, this.buttonSize);
            treeBtn = setSize(treeBtn, this.buttonSize);

            sourceBtn = setSize(sourceBtn, this.buttonSize);
            conveyorBtn = setSize(conveyorBtn, this.buttonSize);

            saveBtn = setSize(saveBtn, this.buttonSize);
            deleteBtn = setSize(deleteBtn, this.buttonSize);
            eraseBtn = setSize(eraseBtn, this.buttonSize);
            undoBtn = setSize(undoBtn, this.buttonSize);

            planeBtn = setSize(planeBtn, this.buttonSize);
            shipBtn = setSize(shipBtn, this.buttonSize);
            truckBtn = setSize(truckBtn, this.buttonSize);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect() {

        backgroundRect = new Rectangle(8, 31, this.gameWindow.windowSize.width, this.gameWindow.windowSize.height);
        deleteRect = makeRect(0);
        eraseRect = makeRect(1);
        undoRect = makeRect(2);
        saveRect = makeRect(3);

        sourceRect = makeRect(5);
        conveyorRect = makeRect(6);

        planeRect = makeRect(8);
        shipRect = makeRect(9);
        truckRect = makeRect(10);


        waterRect = makeRect(12);
        roadRect = makeRect(13);
        treeRect = makeRect(14);

    }

    private Rectangle makeRect(int i) {
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

    private void drawMenu(Graphics g) {
        g.drawImage(deleteBtn, deleteRect.x, deleteRect.y, null);
        g.drawImage(eraseBtn, eraseRect.x, eraseRect.y, null);
        g.drawImage(saveBtn, saveRect.x, saveRect.y, null);
        g.drawImage(undoBtn, undoRect.x, undoRect.y, null);

        g.drawImage(conveyorBtn, conveyorRect.x, conveyorRect.y, null);
        g.drawImage(sourceBtn, sourceRect.x, sourceRect.y, null);

        g.drawImage(planeBtn, planeRect.x, planeRect.y, null);
        g.drawImage(shipBtn, shipRect.x, shipRect.y, null);
        g.drawImage(truckBtn, truckRect.x, truckRect.y, null);

        g.drawImage(waterBtn, waterRect.x, waterRect.y, null);
        g.drawImage(roadBtn, roadRect.x, roadRect.y, null);
        g.drawImage(treeBtn, treeRect.x, treeRect.y, null);
    }

    private void drawMap(Graphics g) {
        int[][] map = createMapManager.getMap();
        for (int sum = 15; sum <= 60; sum++) {
            for (int i = 0; i <= sum; i++) {
                int j = sum - i;
                if ((i <= 35) && (j <= 35)) {
//                    System.out.println(" i:" + i + " j : " + j + "map : " + map[i][j]);
                    LogicPoint lp = new LogicPoint(i, j);
                    Point p = lp.convertToPoint();
                    if (map[i][j] != MapCodeConst.NOTHING && map[i][j] != MapCodeConst.FORBIDDEN) {
                        g.drawImage(imageInMap[map[i][j]], p.x, p.y, null);
                    }
                }
            }
        }
//         thuc hien draw cai map kia
    }

    private void loadImageInMap() {
        try {
            imageInMap[MapCodeConst.NONSWITCH_DOWN]
                = imageInMap[MapCodeConst.CONVEYOR_DOWN]
                    = ImageIO.read(new File("resource/Create map button/Map_nonswitch_down.png"));
            imageInMap[MapCodeConst.NONSWITCH_LEFT]
                = imageInMap[MapCodeConst.CONVEYOR_LEFT]
                    = ImageIO.read(new File("resource/Create map button/Map_nonswitch_left.png"));
            imageInMap[MapCodeConst.NONSWITCH_RIGHT]
                = imageInMap[MapCodeConst.CONVEYOR_RIGHT]
                    = ImageIO.read(new File("resource/Create map button/Map_nonswitch_right.png"));
            imageInMap[MapCodeConst.NONSWITCH_UP]
                = imageInMap[MapCodeConst.CONVEYOR_UP]
                    = ImageIO.read(new File("resource/Create map button/Map_nonswitch_up.png"));

            imageInMap[MapCodeConst.END_DOWN]
                    = imageInMap[MapCodeConst.END_LEFT]
                    = imageInMap[MapCodeConst.END_RIGHT]
                    = imageInMap[MapCodeConst.END_UP]
                    = ImageIO.read(new File("resource/Create map button/Map_end.png"));

            imageInMap[MapCodeConst.SWITCH_DOWN] = ImageIO.read(new File("resource/Create map button/Map_switch_down.png"));
            imageInMap[MapCodeConst.SWITCH_LEFT] = ImageIO.read(new File("resource/Create map button/Map_switch_left.png"));
            imageInMap[MapCodeConst.SWITCH_RIGHT] = ImageIO.read(new File("resource/Create map button/Map_switch_right.png"));
            imageInMap[MapCodeConst.SWITCH_UP] = ImageIO.read(new File("resource/Create map button/Map_switch_up.png"));

            imageInMap[MapCodeConst.TRUCK] = ImageIO.read(new File("resource/Create map button/Map_truck.png"));
            imageInMap[MapCodeConst.PLANE] = ImageIO.read(new File("resource/Create map button/Map_plane.png"));
            imageInMap[MapCodeConst.SHIP] = ImageIO.read(new File("resource/Create map button/Map_ship.png"));

            imageInMap[MapCodeConst.TREE] = ImageIO.read(new File("resource/Create map button/Map_tree_1.png"));
            imageInMap[MapCodeConst.WATER] = ImageIO.read(new File("resource/Create map button/Map_water.png"));
            imageInMap[MapCodeConst.ROAD] = ImageIO.read(new File("resource/Create map button/Map_road.png"));

            imageInMap[MapCodeConst.SOURCE] = ImageIO.read(new File("resource/Create map button/Map_source.png"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("p =" + e.getX() + "," + e.getY());
        LogicPoint f = LogicPoint.convertPointToLogicPoint(e.getPoint());
        System.out.println("logic p =" + f.getLogicX() + "," + f.getLogicY());

        if (conveyorRect.contains(e.getX(), e.getY())) {
            status = OperationConst.DRAG_CONVEYOR;
            return;
        }
        if (deleteRect.contains(e.getX(), e.getY())) {
            status = OperationConst.NONE;
            Operation op = new Operation(OperationConst.CLICK_DELETE, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            return;
        }
        if (planeRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_PLANE;
            return;
        }
        if (shipRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_SHIP;
            return;
        }
        if (truckRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_TRUCK;
            return;
        }
        if (sourceRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_SOURCE;
            return;
        }
        if (eraseRect.contains(e.getX(), e.getY())) {
            status = OperationConst.CLICK_ERASER;
            return;
        }
        if (undoRect.contains(e.getX(), e.getY())) {
            Operation op = new Operation(OperationConst.CLICK_UNDO, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            System.out.println("undo");
            return;
        }
        if (saveRect.contains(e.getX(), e.getY())) {
            Operation op = new Operation(OperationConst.CLICK_SAVE, new LogicPoint(), new LogicPoint());
            createMapManager.execute(op);
            return;
        }
        if (waterRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_WATER;
            return;
        }
        if (roadRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_ROAD;
            return;
        }
        if (treeRect.contains(e.getX(), e.getY())) {
            status = OperationConst.SET_TREE;
            return;
        }
        if (backgroundRect.contains(e.getX(), e.getY())) {
            switch (status) {
                case OperationConst.SET_SOURCE:
                case OperationConst.SET_ROAD:
                case OperationConst.SET_TREE:
                case OperationConst.SET_WATER:
                case OperationConst.SET_PLANE:
                case OperationConst.SET_SHIP:
                case OperationConst.SET_TRUCK:
                case OperationConst.CLICK_ERASER: {
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
        if (backgroundRect.contains(e.getX(), e.getY())) {
            if (status == OperationConst.DRAG_CONVEYOR) {
                startDrag = LogicPoint.convertPointToLogicPoint(e.getPoint());

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (backgroundRect.contains(e.getX(), e.getY())) {
            if (status == OperationConst.DRAG_CONVEYOR) {
                finishDrag = LogicPoint.convertPointToLogicPoint(e.getPoint());
                // kiem tra xem finish va start co hop le:

                if ((startDrag.getLogicX() == finishDrag.getLogicX())
                        && (startDrag.getLogicY() == finishDrag.getLogicY())){
                    return;
                }

                if ((startDrag.getLogicX() == finishDrag.getLogicX())
                        || (startDrag.getLogicY() == finishDrag.getLogicY())) {
                    Operation op = new Operation(OperationConst.DRAG_CONVEYOR, startDrag, finishDrag);
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