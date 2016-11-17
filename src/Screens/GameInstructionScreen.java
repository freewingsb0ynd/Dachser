package Screens;

import Game.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/14/2016.
 */
public class GameInstructionScreen extends Screens.Screen implements MouseListener {
    GameWindow gameWindow;
    BufferedImage background;
    BufferedImage backButton, nextButton, homeButton;
    BufferedImage guideBox;
    BufferedImage[] instruction = new BufferedImage[10];
    int currentImageNumber = 1;
    int maxImageNumber = 3;
    int minImageNumber = 1;

    Rectangle nextRect, backRect, homeRect;
    final Rectangle box = new Rectangle(100,100,500,300);   // vi tri cua man hinh con huong dan
    public GameInstructionScreen(GameWindow gameWindow){
        this.gameWindow = gameWindow;
        this.loadImage();
        this.makeRect();
    }

    private void loadImage(){

        try {
            background = ImageIO.read(new File("resource/instruction button/instruction background.png"));
            nextButton = ImageIO.read(new File("resource/instruction button/next button.png"));
            backButton = ImageIO.read(new File("resource/instruction button/back button.png"));
            homeButton = ImageIO.read(new File("resource/instruction button/home button.gif"));

            instruction[1] = ImageIO.read(new File("resource/instruction button/instruction1.png"));
            instruction[2] = ImageIO.read(new File("resource/instruction button/instruction2.png"));
            instruction[3] = ImageIO.read(new File("resource/instruction button/instruction3.png"));

            backButton = setSize(backButton,50, 50);
            nextButton = setSize(nextButton, 50, 50);
            homeButton = setSize(homeButton, 100, 100);
            guideBox = setSize(instruction[1],box.width,box.height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){

        backRect = new Rectangle(box.x ,box.y + guideBox.getHeight(), backButton.getWidth(), backButton.getHeight());
        nextRect = new Rectangle(box.x + guideBox.getWidth() - nextButton.getWidth(), box.y + guideBox.getHeight(),nextButton.getWidth(),nextButton.getHeight());
        homeRect = new Rectangle(this.gameWindow.getWidth()/2 - homeButton.getWidth()/2, this.gameWindow.getHeight() - homeButton.getHeight(), homeButton.getWidth(), homeButton.getHeight());

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(guideBox, box.x , box.y, null);
        g.drawImage(backButton, backRect.x, backRect.y, null);
        g.drawImage(nextButton, nextRect.x, nextRect.y, null);
        g.drawImage(homeButton, homeRect.x, homeRect.y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (nextRect.contains(e.getX(),e.getY())){
            if (currentImageNumber < maxImageNumber) {
                currentImageNumber += 1;
                guideBox = setSize(instruction[currentImageNumber],box.width, box.height);
            }
        }
        if (backRect.contains(e.getX(),e.getY())){
            if (currentImageNumber > minImageNumber) {
                currentImageNumber -= 1;
                guideBox = setSize(instruction[currentImageNumber],box.width, box.height);
            }
        }

        if (homeRect.contains(e.getX(), e.getY())){
            System.out.println("click home");
            Screens.GameManager.getInstance().getStackScreen().pop();
            this.gameWindow.removeMouseListener(this);
            this.gameWindow.addMouseListener(GameManager.getInstance().getStackScreen().peek());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}