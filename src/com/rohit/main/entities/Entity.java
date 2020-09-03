package com.rohit.main.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rohit.main.Handler;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean active = true;
	

	public static final int DEFAULT_HEALTH = 4;
	protected int health; 

	
	public Entity (Handler handler, float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		health = DEFAULT_HEALTH;

		bounds = new Rectangle (0,0, width, height);
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);

	
	public boolean checkEntityCollisions(float xOffSet, float yOffSet) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffSet, yOffSet))) {
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffSet, float yOffSet) {
		return new Rectangle((int)(x + bounds.x + xOffSet), (int)(y + bounds.y + yOffSet), bounds.width, bounds.height);
	}
	
	
	public abstract void die();
	
	public void hurt (int amt) {
		health -= amt;
		if (health < 0) {
			active = false;
			die();
		}
	}

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
