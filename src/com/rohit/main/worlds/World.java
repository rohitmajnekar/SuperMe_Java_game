package com.rohit.main.worlds;

import java.awt.Graphics;

import com.rohit.main.Handler;
import com.rohit.main.entities.EntityManager;
import com.rohit.main.entities.creatures.Player;
import com.rohit.main.entities.statics.Ball;
import com.rohit.main.entities.statics.Tree;
import com.rohit.main.items.ItemManager;
import com.rohit.main.tiles.Tile;
import com.rohit.main.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int [][] tiles;
	
	private EntityManager entityManager;
	private ItemManager itemManager;
	
	public World (Handler handler, String path) {
		this.handler = handler; 
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Tree(handler, 450, 191));
		entityManager.addEntity(new Ball(handler, 450, 320 + 64*3));
		
		
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setX(spawnY);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, (handler.getGameCamera().getxOffSet())/Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffSet()+ handler.getWidth())/Tile.TILEWIDTH + 1 );
		int yStart = (int) Math.max(0, (handler.getGameCamera().getyOffSet())/Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffSet() + handler.getHeight()) / Tile.TILEHEIGHT+ 1);
		for (int y= yStart; y< yEnd; y++) {
			for (int x = xStart; x< xEnd; x++) {
				getTile(x, y).render(g, (int)(x*Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()), (int)(y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()));
			}
		}
		
		itemManager.render(g);
		
		entityManager.render(g);
		
	}
	


	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.dirtTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t== null)
			return Tile.dirtTile;
		return t;
		
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for (int y=0;y< height;y++) {
			for (int x=0;x<width;x++) {
				tiles[x][y]	= Utils.parseInt(tokens[(x + y *width) + 4]);
			}
		}

	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
}
