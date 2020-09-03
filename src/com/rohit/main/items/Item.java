package com.rohit.main.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.rohit.main.Handler;
import com.rohit.main.gfx.Assets;

public class Item {
	//handlers
	
	public static Item[] items = new Item[256];
	public static Item ballItem = new Item(Assets.ball,"Ball", 0);
	public static Item treeItem = new Item(Assets.tree,"tree", 1);
	
	
	//classes
	public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED = -1;
	
	protected Handler handler;
	protected BufferedImage texture;
	protected String name;
	protected int id;
	
	protected int x, y, count;
	
	public Item (BufferedImage texture, String name, int id) {
		
		this.texture = texture;
		this.name = name;
		this.id = id;
		count = 1;
		items[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render (Graphics g) {
		if (handler == null) {
			return;
		}
		render(g, (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()));
	}
	
	public void render (Graphics g, int x, int y) {
		g.drawImage(texture, x, y,ITEMWIDTH, ITEMHEIGHT, null);
	}
	
	public Item createNew(int x, int y) {
		Item i = new Item (texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	public void setPosition( int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public BufferedImage getTexure() {
		return texture;
	}

	public void setTexure(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
