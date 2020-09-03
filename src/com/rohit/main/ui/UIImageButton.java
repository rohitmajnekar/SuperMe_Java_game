package com.rohit.main.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.rohit.main.gfx.Assets;

public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;

	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		if (hoovering)
			g.drawImage(Assets.button_start[0], (int)x, (int)y, width, height, null);
		else
			g.drawImage(Assets.button_start[1], (int)x, (int)y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
