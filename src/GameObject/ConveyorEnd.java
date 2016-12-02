package GameObject;

import java.util.ArrayList;

/**
 * Created by Nhat on 02/12/2016.
 */
public class ConveyorEnd extends ConveyorFixed {
    protected ColorBox colorBox;
    protected ArrayList<ColorBox> colorBoxArrayList;
    public ConveyorEnd(int posX, int posY) {
        super(posX, posY);
    }
}
