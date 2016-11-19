package GameObject;

import Helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class ConveyorXEnd extends ConveyorMoving {
    public ConveyorXEnd(int posX, int posY) {
        super(posX, posY);
        this.animation = new AnimationHelper("resource/conveyor/1_end/1_end(", 199, 4);
    }

    public void update() {
        super.update();
        //animation.update();
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
//        animation.draw(g,this.posX,this.posY);
    }
}
