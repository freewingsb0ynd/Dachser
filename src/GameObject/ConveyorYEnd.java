package GameObject;

import Helper.AnimationHelper;

import java.awt.*;

/**
 * Created by PC on 20/11/2016.
 */
public class ConveyorYEnd extends ConveyorMoving{
    public ConveyorYEnd(int posX, int posY) {
        super(posX, posY);
        this.animation = new AnimationHelper("resource/conveyor/2_end/2_end(", 199, 4);
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
