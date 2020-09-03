package com.rohit.main.entities.statics;

import java.awt.Graphics;

import com.rohit.main.Handler;
import com.rohit.main.gfx.Assets;
import com.rohit.main.items.Item;
import com.rohit.main.tiles.Tile;

public class Ball extends StaticEntity{

	public Ball(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT*2);
		
		bounds.x = 20;
		bounds.y = (int) (height/ 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height/1.5f);
	}
	
	public void tick() {
		
	}
	
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.ballItem.createNew((int)x,(int) y));
	}
	
	public void render(Graphics g) {
		g.drawImage(Assets.ball, (int)(x - handler.getGameCamera().getxOffSet()), (int)(y - handler.getGameCamera().getyOffSet()), width, height, null);	
	}
	
}
