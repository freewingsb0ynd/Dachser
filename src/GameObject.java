import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Hoangelato on 04/11/2016.
 */
public abstract class GameObject {
    private int posX, posY;
    BufferedImage sprite;



    public void draw(Graphics g){
        g.drawImage(sprite, posX, posY, null);
    }
}
