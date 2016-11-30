package GamePlay.GameObject;

import java.awt.*;

/**
 * Created by Hoangelato on 16/11/2016.
 */
public class Conveyor extends GameObject {




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
