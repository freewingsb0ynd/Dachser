package Screens;

import Game.GameWindow;
import com.sun.glass.ui.Size;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Admin on 11/13/2016.
 */
public class MenuScreen extends Screen implements MouseListener {
    BufferedImage background, playImage, instructionImage, aboutImage, exitImage, createmapImage;
    Rectangle playRect, instructionRect, aboutRect, exitRect, createmapRect;
    GameWindow gameWindow;
    final Point firstButtonLocation = new Point(200, 50);
    final Size buttonSize = new Size(300, 80);
    public MenuScreen(GameWindow gameWindow) throws IOException {
        loadImage();
        makeRect();
        this.gameWindow = gameWindow;
    }

    private void loadImage(){
        try {
            playImage = ImageIO.read(new File("resource/menu button/play button.png"));
            createmapImage = ImageIO.read(new File("resource/menu button/create map button.png"));
            instructionImage = ImageIO.read(new File("resource/menu button/instruction button.png"));
            aboutImage = ImageIO.read(new File("resource/menu button/about button.png"));
            exitImage = ImageIO.read(new File("resource/menu button/exit button.png"));
            background = ImageIO.read(new File("resource/image/image 96.bmp"));

            playImage = setSize(playImage,buttonSize.width,buttonSize.height);
            createmapImage = setSize(createmapImage,buttonSize.width,buttonSize.height);
            instructionImage = setSize(instructionImage,buttonSize.width,buttonSize.height);
            aboutImage = setSize(aboutImage,buttonSize.width,buttonSize.height);
            exitImage = setSize(exitImage,buttonSize.width,buttonSize.height);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeRect(){

            playRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y,playImage.getWidth(), playImage.getHeight());
            createmapRect = new Rectangle(firstButtonLocation.x , firstButtonLocation.y + 1 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
            instructionRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 2 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
            aboutRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 3 * buttonSize.height, playImage.getWidth(), playImage.getHeight());
            exitRect = new Rectangle(firstButtonLocation.x, firstButtonLocation.y + 4 * buttonSize.height,playImage.getWidth(), playImage.getHeight());

    }

    @Override
    public void update() {
        //if click play push Gameplay Screen

        //if click about push About Screen
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(playImage,playRect.x,playRect.y,null);
        g.drawImage(createmapImage,createmapRect.x,createmapRect.y,null);
        g.drawImage(instructionImage,instructionRect.x, instructionRect.y,null);
        g.drawImage(aboutImage,aboutRect.x,aboutRect.y,null);
        g.drawImage(exitImage,exitRect.x,exitRect.y,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (playRect.contains(e.getX(),e.getY())){
            // kich vao play
            GamePlayScreen GamePlayScreen = new GamePlayScreen();
//            gameWindow.addMouseListener(GamePlayScreen);
            GameManager.getInstance().getStackScreen().push(GamePlayScreen);
        }
        if (createmapRect.contains(e.getX(), e.getY())){
            //kich vao createmap
        }
        if (aboutRect.contains(e.getX(),e.getY())){
            //kich vao about
            AboutScreen aboutScreen= new AboutScreen(gameWindow);
            gameWindow.addMouseListener(aboutScreen);
            gameWindow.removeMouseListener(this);
            GameManager.getInstance().getStackScreen().push(aboutScreen);
            System.out.println(GameManager.getInstance().getStackScreen().size());
        }
        if (instructionRect.contains(e.getX(), e.getY())){
            //kich vao instruction
            GameInstructionScreen gameInstructionScreen= new GameInstructionScreen(gameWindow);
            gameWindow.addMouseListener(gameInstructionScreen);
            gameWindow.removeMouseListener(this);
            GameManager.getInstance().getStackScreen().push(gameInstructionScreen);
            System.out.println(GameManager.getInstance().getStackScreen().size());
        }
        if (exitRect.contains(e.getX(), e.getY())){
            //kich vao exit
            System.exit(1);
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
