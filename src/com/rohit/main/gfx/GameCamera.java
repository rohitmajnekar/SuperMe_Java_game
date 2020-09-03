package com.rohit.main.gfx;

import com.rohit.main.Handler;
import com.rohit.main.entities.Entity;
import com.rohit.main.tiles.Tile;

public class GameCamera {
	
	private float xOffSet, yOffSet;
	private Handler handler; 
	public GameCamera(Handler handler,float xOffSet, float yOffSet) {
		this.handler = handler;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
	
	public void checkBlankSpace() {
		if (xOffSet < 0 ) {
			
			xOffSet = 0;
			
		}else if(xOffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffSet = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if (yOffSet < 0) {
			yOffSet = 0;
			
		}else if (yOffSet > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffSet = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}

	
	public void centerOnEntity(Entity e) {
		xOffSet = e.getX() - handler.getWidth()/2 +75 ;
		yOffSet = e.getY() - handler.getHeight()/2 + 75;
		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt) {

		xOffSet += xAmt;
		yOffSet += yAmt;
		checkBlankSpace();
	}
	
	public float getxOffSet() {
		return xOffSet;
	}

	public void setxOffSet(float xOffSet) {
		this.xOffSet = xOffSet;
	}

	public float getyOffSet() {
		return yOffSet;
	}

	public void setyOffSet(float yOffSet) {
		this.yOffSet = yOffSet;
	}
	
	
}
