package GameObject;

import Helper.AnimationHelper;

import java.awt.*;

/**
 * Created by Hoangelato on 20/11/2016.
 */
public class ConveyorYEnd extends ConveyorEnd{
    public ConveyorYEnd(int posX, int posY) {
        super(posX, posY);
//        this.animation = new AnimationHelper("resource/conveyor/end_down/end_down(", 199, 4);
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
