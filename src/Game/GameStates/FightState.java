package Game.GameStates;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.BaseDynamicEntity;
import Game.Entities.Dynamics.BaseHostileEntity;
import Game.Entities.Dynamics.Player;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.sun.deploy.uitoolkit.impl.fx.ui.UITextArea;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Display.UI.UIObject;

public class FightState extends State{
	private UIManager uiManager;
	EntityManager entityManager;
	Player player;
	BaseHostileEntity enemy;
	private int optionSelect;




    public FightState(Handler handler, BaseDynamicEntity player ,BaseHostileEntity enemy) {
        super(handler);
        this.player = new Player(handler, (int) handler.getWidth() / 5, (int) handler.getHeight() * 2/3);
        
        
        
        entityManager = new EntityManager(handler, (Player) player);
		this.handler.setEntityManager(entityManager);
        setUiManager();
        optionSelect = 0;
        

    }

    public FightState(Handler handler, BaseDynamicEntity player,BaseDynamicEntity[] enemie) {
        super(handler);

    }

    @Override
    public void tick() {
    	PlayerInput();
    	//uiManager.tick();
        ///TEMP CODE TO EXIT FIGHT///
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            PauseState.lastState = State.getState();
            State.setState(handler.getGame().pauseState);
        }
        /////////////////////////////


    }

    @Override
    public void render(Graphics g) {
    	
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setBackground(new Color(61, 68, 128));
    	g2.drawImage(Images.battleBackground[1], 0, 0, null);
    	g2.setColor(new Color(35, 65, 79));
    	g2.fillRoundRect(handler.getWidth() * 1/10, handler.getHeight()* 4/5, handler.getWidth() * 8/10, handler.getHeight()/7, 50, 50);
    	g2.setColor(new Color(52, 58, 61));
    	g2.fillRoundRect((handler.getWidth() * 1/10) + 10, (handler.getHeight()* 4/5) + 10, handler.getWidth() * 1/11, handler.getHeight()/7 -20, 50, 50);
    	g2.setFont(new Font("Bank Gothic",3,15));
    	g2.setColor(Color.white);
    	g2.drawString("Character Info", (handler.getWidth() * 1/10) + 35, (handler.getHeight()* 4/5) + 28);
    	
    	
    	
    	entityManager.render(g);
    	uiManager.Render(g);
    	
    	
//    	g2.setFont(new Font("MONOSPACED", 3, 80));
//    	
//    	g2.setColor(Color.red);
//    	g2.drawString("FIGHT!", handler.getWidth()/2 - 110, handler.getHeight()/2);
    	
    	
    	
    	
    }
    
    
    private void PlayerInput() {
    	boolean action = false;
		if (handler.getKeyManager().down || handler.getKeyManager().up) {}
        if (handler.getKeyManager().right && !action){
//        	choose options to the right
        	if(optionSelect < uiManager.getObjects().size()-1) {
        		optionSelect++;
        		System.out.println(optionSelect);
        	}
        	action = true;
        }
        if (handler.getKeyManager().left && !action){
//        	choose options to the left
        	if(optionSelect > 0){
        		optionSelect--;
        		System.out.println(optionSelect);
        	
        	}
        	action = true;
        }
        
        uiManager.getObjects().get(optionSelect);
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && !action)
        	uiManager.getObjects().get(optionSelect).onClick();
        action = true;
        
	}
    
 
    
    private void setUiManager() {
    	uiManager = new UIManager(handler);
    	
    	
    	

    	//Attack
        uiManager.addObjects(new UIImageButton(handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
                System.out.println("First option");
                
            }
        }));
       
        //Defend
        uiManager.addObjects(new UIImageButton(2*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                
            }
        }));
        
        //Skills
        uiManager.addObjects(new UIImageButton(3*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                
            }
        }));
        
        //Run?
        uiManager.addObjects(new UIImageButton(4*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Quit, new ClickListlener() {
            @Override
            public void onClick() {
            	System.out.println("It works");
                
            }
        }));
      
    	
    }
}
