package GameObject;

import CreateMapExtension.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by admin on 11/5/2016.
 */
public class Box extends GameObject{
    private Point p;
    private ColorBox color;
    private double speed;

    public Box(LogicPoint lp, ColorBox color) {
        p = lp.convertToPoint();

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


    public void movebyDirection(Direction d){
        switch (d){
            case UP:
                p.setLocation(p.getX()+36*speed,p.getY()-18*speed);
                break;
            case DOWN:
                p.setLocation(p.getX()-36*speed,p.getY()+18*speed);
                break;
            case LEFT:
                p.setLocation(p.getX()-36*speed,p.getY()-18*speed);
                break;
            case RIGHT:
                p.setLocation(p.getX()+36*speed,p.getY()+18*speed);
                break;

        }
        //check direction cua square

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawImage(sprite,p.x,p.y,null);
    }

    @Override
    void loadImage() {
        loadImageByColor(this.color);
    }

    private void loadImageByColor(ColorBox c) {
        try {
            switch (c){
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
                default: break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
