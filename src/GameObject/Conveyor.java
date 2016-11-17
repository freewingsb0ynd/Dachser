package GameObject;

import Helper.AnimationHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/**
 * Created by PC on 16/11/2016.
 */
public class Conveyor extends GameObject {
    public static final int TYPE_X_END = 1;
    public static final int TYPE_X_MID = 2;
    public static final int TYPE_Y_END = 3;
    public static final int TYPE_Y_MID = 4;

    AnimationHelper animationHelper;

    @Override
    public void loadImage() {
    }

    protected Conveyor(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Conveyor(int posX, int posY, int type) {
        switch (type){
            case TYPE_X_END:
                new ConveyorXEnd(posX,posY);
                break;
            case TYPE_X_MID:
                new ConveyorXMid(posX,posY);
                break;
            default:
                break;
        }
    }

    public void update() {
        animationHelper.update();
    }


    @Override
    public void draw(Graphics g) {
        animationHelper.draw(g,this.posX,this.posY);
    }
}
