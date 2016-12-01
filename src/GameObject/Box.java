package GameObject;

import Helper.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 11/5/2016.
 */
public class Box extends GameObject {
    private double realX;
    private double realY;
    private ColorBox color;
    private double speed;
    private LogicPoint logicPointBox;
    public boolean isAllowToChange = true;

    private Direction direction = Direction.NONE;
    private static final int offsetX = 12;
    private static final int offsetY = 48;

    public void setDirection(Direction d) {
        this.direction = d;
    }

    public Direction getDirection() {
        return direction;
    }

    public Box(LogicPoint lp, ColorBox color) {
        Point p = lp.convertToPoint();
        p = new Point(p.x + 42 - 6 - 12, p.y + 62 - 3 - 48);
        realX = posX = p.x;
        realY = posY = p.y;
        logicPointBox = LogicPoint.convertPointToLogicPoint(new Point(posX + 12, posY + 48));
        this.color = color;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(new File("resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.01;
    }

    public Box(int x, int y, ColorBox color) {
        realX = posX = x;
        realY = posY = y;

        this.color = color;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(new File("resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.02;
    }

    public ColorBox getColor() {
        return color;
    }

    public void checkToChangDirection() {
        int x = posX + offsetX;
        int y = posY + offsetY;
        int xRaw, yRaw;
//        LogicPoint logicPointBox = LogicPoint.convertPointToLogicPoint(new Point(x, y));
//        Point map = logicPointBox.convertToPoint();
//        int midMapX = map.x + 42;
//        int midMapY = map.y + 62;
        LogicPoint lpRaw;
        switch (direction) {
            case UP:
//                return Math.abs(y - midMapY) < 3;

                xRaw = x + 18;
                yRaw = y - 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointBox.getLogicY() == lpRaw.getLogicY()) {
                    isAllowToChange = false;
                } else {
                    System.out.println("zzzzz");
                    logicPointBox = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowToChange = true;
                }
                break;

            case DOWN:
//                return Math.abs(y - midMapY) < 3;
                xRaw = x - 18;
                yRaw = y + 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointBox.getLogicY() == lpRaw.getLogicY()) {
                    isAllowToChange = false;
                } else {
                    logicPointBox = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowToChange = true;
                }
                break;
            case LEFT:
//                return Math.abs(x - midMapX) < 3;
                xRaw = x - 18;
                yRaw = y - 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointBox.getLogicX() == lpRaw.getLogicX()) {
                    isAllowToChange = false;
                } else {
                    logicPointBox = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowToChange = true;
                }
                break;
            case RIGHT:
//                return Math.abs(x - midMapX) < 3;
                xRaw = x + 18;
                yRaw = y + 9;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw, yRaw));
                if (logicPointBox.getLogicX() == lpRaw.getLogicX()) {
                    isAllowToChange = false;
                } else {
                    logicPointBox = new LogicPoint(lpRaw.getLogicX(), lpRaw.getLogicY());
                    isAllowToChange = true;
                }
                break;
            default:
                isAllowToChange = false;
        }
    }

    public LogicPoint getLogicPoint() {
        return LogicPoint.convertPointToLogicPoint(new Point(posX + 12, posY + 48));
    }

//    public void checkToChangDirection() {
//        LogicPoint logicMap = this.getLogicPoint();
//        int midLogicMapX = logicMap.getLogicX() + 42;
//        int midLogicMapY = logicMap.getLogicY() + 62;
//        int x = posX + offsetX;
//        int y = posY + offsetY;
//        if ((x - midLogicMapX) * (x - midLogicMapX) + (y - midLogicMapY) * (y - midLogicMapY) < 5000) {
//            isAllowToChange = true;
//        } else isAllowToChange = false;
//    }

    public void movebyDirection() {
        Point p = new Point(posX, posY);
        double newRealX = realX, newRealY = realY;
        switch (direction) {
            case UP:
                newRealX = realX + 36 * speed;
                newRealY = realY - 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case DOWN:
                newRealX = realX - 36 * speed;
                newRealY = realY + 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case LEFT:
                newRealX = realX - 36 * speed;
                newRealY = realY - 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;
            case RIGHT:
                newRealX = realX + 36 * speed;
                newRealY = realY + 18 * speed;
                p.setLocation(newRealX, newRealY);
                break;

        }
        this.posX = p.x;
        this.posY = p.y;
        realX = newRealX;
        realY = newRealY;

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(sprite, posX, posY, null);
    }

    @Override
    public void update() {
        super.update();
        if (isAllowToChange) {
            changeDirection();
        }
    }

    private void changeDirection() {

    }

    @Override
    void loadImage() {
        loadImageByColor(this.color);
    }

    private void loadImageByColor(ColorBox c) {
        try {
            switch (c) {
                case RED:
                    this.sprite = ImageIO.read(new File("resource/play button/red_box.png"));
                    break;
                case YELLOW:
                    this.sprite = ImageIO.read(new File("resource/play button/yellow_box.png"));
                    break;
                case PINK:
                    this.sprite = ImageIO.read(new File("resource/play button/pink_box.png"));
                    break;
                case BLUE:
                    this.sprite = ImageIO.read(new File("resource/play button/blue_box.png"));
                    break;
                case GREEN:
                    this.sprite = ImageIO.read(new File("resource/play button/green_box.png"));
                    break;
                case WHITE:
                    this.sprite = ImageIO.read(new File("resource/play button/null_box.png"));
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
