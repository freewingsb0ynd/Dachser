package GameObject;

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
    @Override
    public void loadImage() {
        try {
            this.sprite = ImageIO.read(new File("resource/conveyor/1_end/17.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Conveyor(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
