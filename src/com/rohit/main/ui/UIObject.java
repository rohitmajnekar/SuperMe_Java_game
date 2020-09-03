package com.rohit.main.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
	
	protected float x, y;
	protected int width, height;
	protected boolean hoovering = false;
	protected Rectangle bounds;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle ((int)x, (int)y + 40, width, height - 64);
		
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove (MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY()))
			hoovering = true;
		else
			hoovering = false;
	}
	
	public void onMouseRelease(MouseEvent e) {
		if (hoovering)
			onClick();
	}

	
	// GETTER AND SETTLER 
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHoovering() {
		return hoovering;
	}

	public void setHoovering(boolean hoovering) {
		this.hoovering = hoovering;
	}
}
