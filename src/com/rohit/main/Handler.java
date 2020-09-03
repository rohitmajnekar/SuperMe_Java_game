package com.rohit.main;

import com.rohit.main.gfx.GameCamera;
import com.rohit.main.input.KeyManager;
import com.rohit.main.input.MouseManager;
import com.rohit.main.worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;	
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
}
