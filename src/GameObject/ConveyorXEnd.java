package GameObject;

import Helper.AnimationHelper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hoangelato on 17/11/2016.
 */
public class ConveyorXEnd extends ConveyorEnd{
    public ConveyorXEnd(int posX, int posY) {
        super(posX, posY);
        loadImage();
    }

    public void update() {
//        super.update();
        //animation.update();
    }

    @Override
    public void loadImage() {
        super.loadImage();
        try {
            this.sprite = ImageIO.read(new File("resource/conveyor/x_end/white_x_end.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void draw(Graphics g) {
//        super.draw(g);
//        animation.draw(g,this.posX,this.posY);
    }
}
