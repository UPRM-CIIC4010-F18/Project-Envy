package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    public static BufferedImage[] IceSkill;

    public static SpriteSheet smokeHouseSheet;
    public static SpriteSheet iceSkillSheet;
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
        IceSkill = new BufferedImage[64];

        smokeHouse = new BufferedImage[7];
        try {
            map = ImageIO.read(getClass().getResourceAsStream("/Worlds/map.png"));
            smokeHouseSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/House.png")));
            iceSkillSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/iceSkill.png")));
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


            IceSkill[0] = iceSkillSheet.crop(0, 0, 50, 50);
            IceSkill[1] = iceSkillSheet.crop(0, 0, 50, 50);
            IceSkill[2] = iceSkillSheet.crop(52, 0, 50, 50);
            IceSkill[3] = iceSkillSheet.crop(0, 52, 50, 50);
            IceSkill[4] = iceSkillSheet.crop(52, 52, 50, 50);
            IceSkill[5] = iceSkillSheet.crop(104, 0, 50, 50);
            IceSkill[6] = iceSkillSheet.crop(0, 104, 50, 50);
            IceSkill[7] = iceSkillSheet.crop(52, 104, 50, 50);

            IceSkill[8] = iceSkillSheet.crop(104,52, 50, 50);
            IceSkill[9] = iceSkillSheet.crop(104,104, 50, 50);
            IceSkill[10] = iceSkillSheet.crop(156,0, 50, 50);
            IceSkill[11] = iceSkillSheet.crop(0,156, 50, 50);
            IceSkill[12] = iceSkillSheet.crop(52,156, 50, 50);
            IceSkill[13] = iceSkillSheet.crop(156,52, 50, 50);
            IceSkill[14] = iceSkillSheet.crop(104,156, 50, 50);
            IceSkill[15] = iceSkillSheet.crop(156,104, 50, 50);

            IceSkill[16] = iceSkillSheet.crop(208,0, 50, 50);
            IceSkill[17] = iceSkillSheet.crop(0,208, 50, 50);
            IceSkill[18] = iceSkillSheet.crop(52,208, 50, 50);
            IceSkill[19] = iceSkillSheet.crop(208,52, 50, 50);
            IceSkill[20] = iceSkillSheet.crop(156,156, 50, 50);
            IceSkill[21] = iceSkillSheet.crop(104,208, 50, 50);
            IceSkill[22] = iceSkillSheet.crop(208,104, 50, 50);
            IceSkill[23] = iceSkillSheet.crop(156,208, 50, 50);

            IceSkill[24] = iceSkillSheet.crop(208,156, 50, 50);
            IceSkill[25] = iceSkillSheet.crop(260,0, 50, 50);
            IceSkill[26] = iceSkillSheet.crop(0,260, 50, 50);
            IceSkill[27] = iceSkillSheet.crop(52,260, 50, 50);
            IceSkill[28] = iceSkillSheet.crop(260,52, 50, 50);
            IceSkill[29] = iceSkillSheet.crop(104,260, 50, 50);
            IceSkill[30] = iceSkillSheet.crop(260,104, 50, 50);
            IceSkill[31] = iceSkillSheet.crop(208,208, 50, 50);

            IceSkill[63] = iceSkillSheet.crop(0, 0, 50, 50);
            IceSkill[62] = iceSkillSheet.crop(0, 0, 50, 50);
            IceSkill[61] = iceSkillSheet.crop(52, 0, 50, 50);
            IceSkill[60] = iceSkillSheet.crop(0, 52, 50, 50);
            IceSkill[59] = iceSkillSheet.crop(52, 52, 50, 50);
            IceSkill[58] = iceSkillSheet.crop(104, 0, 50, 50);
            IceSkill[57] = iceSkillSheet.crop(0, 104, 50, 50);
            IceSkill[56] = iceSkillSheet.crop(52, 104, 50, 50);

            IceSkill[55] = iceSkillSheet.crop(104,52, 50, 50);
            IceSkill[54] = iceSkillSheet.crop(104,104, 50, 50);
            IceSkill[53] = iceSkillSheet.crop(156,0, 50, 50);
            IceSkill[52] = iceSkillSheet.crop(0,156, 50, 50);
            IceSkill[51] = iceSkillSheet.crop(52,156, 50, 50);
            IceSkill[50] = iceSkillSheet.crop(156,52, 50, 50);
            IceSkill[49] = iceSkillSheet.crop(104,156, 50, 50);
            IceSkill[48] = iceSkillSheet.crop(156,104, 50, 50);

            IceSkill[47] = iceSkillSheet.crop(208,0, 50, 50);
            IceSkill[46] = iceSkillSheet.crop(0,208, 50, 50);
            IceSkill[45] = iceSkillSheet.crop(52,208, 50, 50);
            IceSkill[44] = iceSkillSheet.crop(208,52, 50, 50);
            IceSkill[43] = iceSkillSheet.crop(156,156, 50, 50);
            IceSkill[42] = iceSkillSheet.crop(104,208, 50, 50);
            IceSkill[41] = iceSkillSheet.crop(208,104, 50, 50);
            IceSkill[40] = iceSkillSheet.crop(156,208, 50, 50);

            IceSkill[39] = iceSkillSheet.crop(208,156, 50, 50);
            IceSkill[38] = iceSkillSheet.crop(260,0, 50, 50);
            IceSkill[37] = iceSkillSheet.crop(0,260, 50, 50);
            IceSkill[36] = iceSkillSheet.crop(52,260, 50, 50);
            IceSkill[35] = iceSkillSheet.crop(260,52, 50, 50);
            IceSkill[34] = iceSkillSheet.crop(104,260, 50, 50);
            IceSkill[33] = iceSkillSheet.crop(260,104, 50, 50);
            IceSkill[32] = iceSkillSheet.crop(208,208, 50, 50);









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
