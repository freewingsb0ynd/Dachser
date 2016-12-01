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
    public Point p;
    private ColorBox color;
    private double speed;
    private Direction d;
    private static final int offsetX = 12;
    private static final int offsetY = 48;

    public Box(LogicPoint lp, ColorBox color) {
        p = lp.convertToPoint();
        p = new Point(p.x + 42 - 6 - 12, p.y + 62 - 3 - 48);

        this.color = color;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(new File("resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.05;
    }

    public Box(int posX, int posY, ColorBox color) {
        p = new Point(posX, posY);

        this.color = color;
        loadImage();
//        try {
//            this.sprite = ImageIO.read(new File("resource/play button/pink_box.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        speed = 0.05;
    }
//    public ColorBox getColor(){
//        return color;
//    }

    public boolean hasToCheckDirection() {
        int x = p.x + offsetX;
        int y = p.y + offsetY;
        int xRaw, yRaw;
        LogicPoint lp = LogicPoint.convertPointToLogicPoint(new Point(x, y));
        LogicPoint lpRaw;
        switch (d) {
            case UP:
                xRaw = x + 36;
                yRaw = y - 18;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw,yRaw));
                return lp.getLogicY() == lpRaw.getLogicY();
            case DOWN:
                xRaw = x - 36;
                yRaw = y + 18;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw,yRaw));
                return lp.getLogicY() == lpRaw.getLogicY();
            case LEFT:
                xRaw = x - 36;
                yRaw = y - 18;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw,yRaw));
                return lp.getLogicX() == lpRaw.getLogicX();
            case RIGHT:
                xRaw = x + 36;
                yRaw = y + 18;
                lpRaw = LogicPoint.convertPointToLogicPoint(new Point(xRaw,yRaw));
                return lp.getLogicX() == lpRaw.getLogicX();
        }
        return true;
    }

    public void movebyDirection() {
        switch (d) {
            case UP:
                p.setLocation(p.getX() + 36 * speed, p.getY() - 18 * speed);
                break;
            case DOWN:
                p.setLocation(p.getX() - 36 * speed, p.getY() + 18 * speed);
                break;
            case LEFT:
                p.setLocation(p.getX() - 36 * speed, p.getY() - 18 * speed);
                break;
            case RIGHT:
                p.setLocation(p.getX() + 36 * speed, p.getY() + 18 * speed);
                break;

        }

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(sprite, p.x, p.y, null);
    }

    @Override
    public void update() {
        super.update();
        if (hasToCheckDirection()) {
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
