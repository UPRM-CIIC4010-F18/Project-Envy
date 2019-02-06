package Game.GameStates;

import Main.Handler;
import Resources.Images;
import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

	private UIManager uiManager;
	int waitTimeForInput;


    public PauseState(Handler handler) {
        super(handler);


        
        uiManager = new UIManager(handler);

        

        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-64, handler.getHeight()/2-32, 128, 64, Images.butstart, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
                System.exit(0);
            }
        }));
        
        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-300, handler.getHeight()/2-32, 128, 64, Images.Resume, new ClickListlener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
        		State.setState(handler.getGame().mapState);
            }
        }));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
    	uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
    	Graphics2D g2 = (Graphics2D)g;
    	
    	g2.setColor(new Color(61, 68, 128));
    	g2.fill(new Rectangle( 0, 0, this.handler.getWidth(), this.handler.getHeight()));
    	
    	uiManager.Render(g);
    }
}
