package GameObject;

import Helper.AnimationHelper;

import java.awt.*;

/**
 * Created by PC on 17/11/2016.
 */
public class ConveyorXEnd extends Conveyor {
    public ConveyorXEnd(int posX, int posY) {
        super(posX, posY);
        super.animationHelper = new AnimationHelper("resource/conveyor/1_end/1_end(", 199, 4);
    }

}
