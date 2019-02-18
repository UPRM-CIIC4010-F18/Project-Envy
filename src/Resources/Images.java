package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage title;
    public static BufferedImage[] titleChoose;
    public static BufferedImage titleImage;
    public static BufferedImage Pause;
    public static BufferedImage[] Resume;
    public static BufferedImage[] Quit;
    public static BufferedImage[] BTitle;
    public static BufferedImage[] Options;
    public static ImageIcon icon;

    public static BufferedImage map;
    public static Image Scaledmap;  

    public static BufferedImage[] battleBackground;
    public static BufferedImage[] Attack;
    public static BufferedImage[] Defend;
    public static BufferedImage[] Skill;

    public static SpriteSheet smokeHouseSheet;
    public static BufferedImage[] smokeHouse;
    
    public static BufferedImage CaveMap;   
    public static BufferedImage Area;
    public static BufferedImage Loading;
    public static Image ScaledCave;
    public static Image ScaledArea;
    public static BufferedImage tree;

    public Images() {

        butstart = new BufferedImage[3];
        BTitle = new BufferedImage[3];
        titleChoose = new BufferedImage[2];
        Options = new BufferedImage[3];
        Resume = new BufferedImage[2];
        Quit = new BufferedImage[2];
        

        battleBackground = new BufferedImage[2];
        
        Attack = new BufferedImage[1];
        Defend = new BufferedImage[1];
        Skill = new BufferedImage[1];

        smokeHouse = new BufferedImage[7];
        try {
            map = ImageIO.read(getClass().getResourceAsStream("/Worlds/map.png"));
            smokeHouseSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/House.png")));
            
            CaveMap = ImageIO.read(getClass().getResourceAsStream("/Worlds/CaveMap.png"));
            Area =ImageIO.read(getClass().getResourceAsStream("/Worlds/area.png"));
            tree = ImageIO.read(getClass().getResourceAsStream("/Sheets/Tree.png"));
            title = ImageIO.read(getClass().getResourceAsStream("/Sheets/menuImage.png"));
            Loading = ImageIO.read(getClass().getResourceAsStream("/Sheets/loading.jpg"));
            titleImage =  ImageIO.read(getClass().getResourceAsStream("/Sheets/Main3.png"));
            
            Pause = ImageIO.read(getClass().getResourceAsStream("/Buttons/Pause.png"));
            Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeButton3.png"));
            Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/ResumeButton1.png"));
            BTitle[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitle.png"));
            BTitle[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            BTitle[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/BTitleP.png"));
            Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
            Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            Options[2] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/Start1.png"));
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/Start2.png"));
            Quit[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Quit1.png"));
            Quit[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Quit2.png"));
            titleChoose[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Title1.png"));
            titleChoose[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Title2.png"));

            
            battleBackground[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/mountain river.jpg"));
            battleBackground[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/forest.jpg"));
            
            Attack[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Attack.png"));
            Defend[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Defend.png"));
            Skill[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Skill.png"));
            
            smokeHouse[0] = smokeHouseSheet.crop(20, 7, 19, 20);
            smokeHouse[1] = smokeHouseSheet.crop(68, 7, 19, 20); 
            smokeHouse[2] = smokeHouseSheet.crop(116, 7, 19, 20); 
            smokeHouse[3] = smokeHouseSheet.crop(164, 7, 19, 20); 
            smokeHouse[4] = smokeHouseSheet.crop(212, 7, 19, 20); 
            smokeHouse[5] = smokeHouseSheet.crop(260, 7, 19, 20); 
            smokeHouse[6] = smokeHouseSheet.crop(308, 7, 19, 20); 

            icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));


        }catch (IOException e) {
        e.printStackTrace();
    }
        Scaledmap = Images.map.getScaledInstance(8000, 6000, Image.SCALE_SMOOTH);
        ScaledCave = Images.CaveMap.getScaledInstance(3680, 4000, Image.SCALE_SMOOTH); // 368x400 pixel image
        ScaledArea = Images.Area.getScaledInstance(8000, 6000, Image.SCALE_SMOOTH);
        
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
