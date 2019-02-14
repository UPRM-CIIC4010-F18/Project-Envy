package Game.GameStates;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.BaseDynamicEntity;
import Game.Entities.Dynamics.BaseHostileEntity;
import Game.Entities.Dynamics.Player;
import Main.Handler;

import Resources.Images;
import sun.font.CreatedFontTracker;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ColorModel;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;

public class FightState extends State{
	private UIManager uiManager;
	EntityManager entityManager;
	Player player;
	BaseHostileEntity enemy;
	private int optionSelect;
	Image background;




    public FightState(Handler handler, BaseDynamicEntity player ,BaseHostileEntity enemy, State prev) {
        super(handler);
        handler.getMouseManager().setUimanager(uiManager);
        
        this.player = new Player(handler, (int) handler.getWidth() / 5, (int) handler.getHeight() * 2/3);
        entityManager = new EntityManager(handler, (Player) player);
		this.handler.setEntityManager(entityManager);
		
        setUiManager();
        backgroundSelect(prev);
        optionSelect = 0;
        
        

    }

    public FightState(Handler handler, BaseDynamicEntity player,BaseDynamicEntity[] enemie) {
        super(handler);

    }

    @Override
    public void tick() {
    	PlayerInput();
    	uiManager.tick();
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
    	
    	
    	
    	g2.setBackground(new Color(61,68,128));
    	g2.drawImage(background, 0, 0, null);
    	g2.setColor(new Color(35, 65, 79));
    	g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));
    	g2.fillRoundRect(handler.getWidth() * 1/10, handler.getHeight()* 4/5, handler.getWidth() * 8/10, handler.getHeight()/7, 50, 50);
    	g2.setColor(new Color(52, 58, 61));
    	g2.fillRoundRect((handler.getWidth() * 1/10) + 10, (handler.getHeight()* 4/5) + 10, handler.getWidth() * 1/11, handler.getHeight()/7 -20, 50, 50);
    	g2.setComposite(AlphaComposite.SrcOver);
    	g2.setFont(new Font("Bank Gothic",3,15));
    	g2.setColor(Color.white);
    	g2.drawString("Character Info", (handler.getWidth() * 1/10) + 35, (handler.getHeight()* 4/5) + 28);
    	
    	
    	
    	
    	entityManager.render(g2);
    	uiManager.Render(g2);
    	player.render(g2);
    	

    	
    	
    	
    	
    }
    
    /*
     * Disables player movement.  W and S no longer do anything, A and D move between the options
     */
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
        		optionSelect -= 1;
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
            	//for testing purposes
                System.out.println("First option");
                
            }
        }));
       
        //Defend
        uiManager.addObjects(new UIImageButton(2*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
            	System.out.println("Second option");
            }
        }));
        
        //Skills
        uiManager.addObjects(new UIImageButton(3*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
            	System.out.println("Third option");
            	
                
            }
        }));
        
        //Run?
        uiManager.addObjects(new UIImageButton(4*handler.getWidth()/5, 5*handler.getHeight()/6, 128, 64, Images.Quit, new ClickListlener() {
            @Override
            public void onClick() {
            	//for testing
            	System.out.println("It works");
            	System.exit(1);
                
            }
        }));
      
    	
    }
    
    
    //Sets the background according to the previous state
    //Current backgrounds where only added for testing purposes
    private void backgroundSelect(State prev) {

        if(prev.equals(handler.getGame().mapState))
        	background = Images.battleBackground[1];
        else
        	background = Images.battleBackground[0];
      
    	
    }
}
