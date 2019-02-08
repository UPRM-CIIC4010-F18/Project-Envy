package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage[] Resume;
    public static BufferedImage[] Quit;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Options;
    public static ImageIcon icon;
    public static Image Scaledmap;
    
    public static BufferedImage map;
    public static BufferedImage[] battleBackground;
    public static BufferedImage tree;

    public Images() {

        butstart = new BufferedImage[3];
        Resume = new BufferedImage[3];
        BTitle = new BufferedImage[3];
        Options = new BufferedImage[3];
        Quit = new BufferedImage[3];
        battleBackground = new BufferedImage[2];

        try {
            map = ImageIO.read(getClass().getResourceAsStream("/Sheets/map.png"));
            tree = ImageIO.read(getClass().getResourceAsStream("/Sheets/Tree.png"));
            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/Title.png"));
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/Pause.png"));
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Resume.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            Resume[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeP.png"));
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            BTitle[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
            Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            Options[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut
            Quit[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/RealQuitButton.png"));//normbut
            Quit[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/RealQuitButtonHover.png"));//hoverbut
            Quit[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/RealQuitButtonPressed.png"));//clickbut
            battleBackground[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/mountain river.jpg"));
            battleBackground[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/forest.jpg"));
            
            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));


        }catch (IOException e) {
        e.printStackTrace();
    }
        Scaledmap = Images.map.getScaledInstance(4000, 4000, Image.SCALE_SMOOTH);



    }	
    

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
