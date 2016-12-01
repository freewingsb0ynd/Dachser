package GameObject;

import Helper.LogicPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hoangelato on 22/11/2016.
 */
public class ConveyorSwitch extends ConveyorFixed {
    Direction createdDirection = Direction.NONE;
    ArrayList<BufferedImage> sprites;
    BufferedImage spriteUp, spriteDown, spriteLeft, spriteRight;
    public Rectangle clickArea = new Rectangle();

    protected ConveyorSwitch(int posX, int posY) {
        super(posX, posY);

    }

    public ConveyorSwitch(LogicPoint logicPoint, Direction direction) {
        super(logicPoint.convertToPoint().x,logicPoint.convertToPoint().y);
        this.createdDirection = direction;
    }

    public ConveyorSwitch(int posX, int posY, Direction direction) {
        super(posX,posY);
        this.createdDirection = direction;
        clickArea = new Rectangle(posX+24,posY+32-9,36,18);
        loadImage();
    }

    @Override
    public void loadImage() {
        super.loadImage();
        sprites = new ArrayList<>();
        //for
        try {
            spriteUp = ImageIO.read(new File("resource/conveyor/x_switch/x_switch_up.png"));
            spriteDown = ImageIO.read(new File("resource/conveyor/x_switch/x_switch_down.png"));
            spriteLeft = ImageIO.read(new File("resource/conveyor/x_switch/x_switch_left.png"));
            spriteRight = ImageIO.read(new File("resource/conveyor/x_switch/x_switch_right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        sprites.add(spriteUp);
        sprites.add(spriteRight);
        sprites.add(spriteDown);
        sprites.add(spriteLeft);

        loadSpriteByDirection(this.createdDirection);


    }

    private void loadSpriteByDirection(Direction d) {
        switch (d){
            case UP:
                sprite = sprites.get(0);
                break;
            case RIGHT:
                sprite = sprites.get(1);
                break;
            case DOWN:
                sprite = sprites.get(2);
                break;
            case LEFT:
                sprite = sprites.get(3);
                break;

            default:break;
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.drawRect(posX+24,posY+32-9,36,18);
    }


    int index;

    public void changeDirection() {
        sprite = sprites.get((sprites.indexOf(sprite)+1)%4);
    }

}
