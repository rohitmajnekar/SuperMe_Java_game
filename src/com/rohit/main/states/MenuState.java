package com.rohit.main.states;

import java.awt.Graphics;

import com.rohit.main.Handler;
import com.rohit.main.gfx.Assets;
import com.rohit.main.ui.ClickListener;
import com.rohit.main.ui.UIImageButton;
import com.rohit.main.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);  
		uiManager.addObject(new UIImageButton(200, 200, 128, 128, Assets.button_start, new ClickListener () {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}}));

	}
	
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
	

}
