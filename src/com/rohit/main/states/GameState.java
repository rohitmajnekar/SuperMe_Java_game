package com.rohit.main.states;

import java.awt.Graphics;

import com.rohit.main.Handler;
import com.rohit.main.entities.creatures.Player;
import com.rohit.main.entities.statics.Tree;
import com.rohit.main.worlds.World;

public class GameState extends State {
	
	private World world;

	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "res/Worlds/World1.txt");
		
		handler.setWorld(world);

	}
	
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		
		world.render(g);
	}
	
	

}
