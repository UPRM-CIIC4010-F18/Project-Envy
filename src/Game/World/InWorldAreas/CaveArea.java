package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Statics.LightStatue;
import Game.World.Walls;

public class CaveArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInCave = false;

    private int imageWidth = 3680, imageHeight = 4000;
    public final static int playerXSpawn = -380, playerYSpawn = -3180
    		;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> caveWalls;

    public CaveArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Cave";
        handler.setXInWorldDisplacement(playerXSpawn);
        handler.setYInWorldDisplacement(playerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;

        

        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,700, 2000,"InWorldState","Sergio","Cave","EnemyOne",150,25,80,1,8,12,20,10,20,10,1,10,"None","Thunder",null,null)); //lvl 2 difficulty
        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,3000, 1000,"InWorldState","Cave Dweller","Cave","EnemyOne",100,25,60,10,1,12,20,10,20,13,1,10,"None","Thunder",null,null)); // lvl 1 difficulty

        this.entityManager.AddEntity(new LightStatue (handler, 2080, 1770));
        
        caveWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : caveWalls) {
            w.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fill(background);

        g.drawImage(Images.ScaledCave, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : caveWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.black);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);

    }

    private void AddWalls() {


        caveWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));								// Left Border
        caveWalls.add(new InWorldWalls(handler, 0, imageHeight-100, imageWidth/3, 50, "Wall"));					// Bottom LeftBorder (Left side relative to Entrance)
        caveWalls.add(new InWorldWalls(handler, imageWidth/2-350, imageHeight-100, imageWidth/4, 50, "Wall"));	// Bottom RightBorder (Right side relative to Entrance)
        caveWalls.add(new InWorldWalls(handler, 0, 130, imageWidth, 10, "Wall"));								//
        caveWalls.add(new InWorldWalls(handler, imageWidth - 130, 0, 10, imageHeight, "Wall"));					// Right Border


        caveWalls.add(new InWorldWalls(handler, 200, 3400, 400, 400, "Wall"));									// Left side Pond
        caveWalls.add(new InWorldWalls(handler, 500, 3075, 125, 100, "Wall"));									// Left side Water Hole

        caveWalls.add(new InWorldWalls(handler, 2440, 3355, 1, 500, "Wall"));									// Water Lake
        caveWalls.add(new InWorldWalls(handler, 1985, 3190, 500, 140, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 1665, 3030, 500, 140, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 1495, 2285, 1040, 700, "Wall"));								//
        caveWalls.add(new InWorldWalls(handler, 1595, 2985, 100, 100, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 2520, 2750, 800, 1, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 3258, 2608, 400, 400, "Wall"));									//

        caveWalls.add(new InWorldWalls(handler, 216, 500, 1030, 1000, "Wall"));									// Lava Lake
        caveWalls.add(new InWorldWalls(handler, 1246, 518, 300, 415, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 222, 1428, 1010, 130, "Wall"));									//
        caveWalls.add(new InWorldWalls(handler, 184, 1640, 100, 100, "Wall"));									// Lava Hole



        caveWalls.add(new InWorldWalls(handler, 176, 140, 455, 345, "Wall"));									// TopLeft Side Wall with torch
        caveWalls.add(new InWorldWalls(handler, 661, 205, 120, 100, "Wall"));									// Hole next to TopLeft Side wall with torch


        caveWalls.add(new InWorldWalls(handler, 1940, 2130, 100, 100, "Wall"));									// Pond next to Statue
        caveWalls.add(new InWorldWalls(handler, 2066, 2050, 180, 125, "Wall"));                                  //Statue
        caveWalls.add(new InWorldWalls(handler, 3380, 510, 120, 100, "Wall"));									// Hole next to Exit
        caveWalls.add(new InWorldWalls(handler, 2744, 140, 200, 300, "Wall"));									// Left wall relative to Exit
        caveWalls.add(new InWorldWalls(handler, 3288, 140, 200, 300, "Wall"));									// Right wall relative to Exit

        caveWalls.add(new InWorldWalls(handler, imageWidth/3, imageHeight, 300, 50, "Wall"));					// Entrance

        caveWalls.add(new InWorldWalls(handler, 2950, 340, 320, 100, "Start Exit"));							// Exit at Start
        caveWalls.add(new InWorldWalls(handler, 1230, 3900, 280, 100, "End Exit"));							// Exit at End



    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return caveWalls;
    }
}




