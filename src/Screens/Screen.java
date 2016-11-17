package Screens;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by PC on 17/11/2016.
 */
public abstract class Screen{
    public abstract void update();
    public abstract void draw(Graphics g);
    final BufferedImage resize (BufferedImage sbi, int n) {

        if (sbi != null) {
            int w = sbi.getWidth();
            int h = sbi.getHeight();
            Image tmp = sbi.getScaledInstance(w / n, h / n, Image.SCALE_SMOOTH);
            BufferedImage dbi = new BufferedImage(w / n, h / n, BufferedImage.TYPE_INT_ARGB);
            dbi.getGraphics().drawImage(tmp, 0, 0, null);
            return dbi;
        }
        return null;
    }

}
