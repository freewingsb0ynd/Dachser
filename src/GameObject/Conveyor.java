package GameObject;

import java.awt.*;

/**
 * Created by Hoangelato on 16/11/2016.
 */
public class Conveyor extends GameObject {

    public static Direction convertIndextoDirection(int index) {
        switch (index) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            default:
                return Direction.NONE;
        }

    }


    @Override
    public void loadImage() {
    }


    public Conveyor(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }



    public void update() {
        //animation.update();
    }


    @Override
    public void draw(Graphics g) {
        //animation.draw(g,this.posX,this.posY);
    }


}
