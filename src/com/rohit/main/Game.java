package com.rohit.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.rohit.main.display.Display;
import com.rohit.main.gfx.Assets;
import com.rohit.main.gfx.GameCamera;
import com.rohit.main.input.KeyManager;
import com.rohit.main.input.MouseManager;
import com.rohit.main.states.GameState;
import com.rohit.main.states.MenuState;
import com.rohit.main.states.SettingState;
import com.rohit.main.states.State;

public class Game implements Runnable {

	private Display display;
	private Thread thread;
	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private GameCamera gameCamera;
	private Handler handler;
	
	public State gameState;
	public State menuState;
	public State settingState;
	
 	private String title;
	private int width, height;
	

	public Game (String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}
	
	public void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		handler = new Handler (this);
		gameCamera = new GameCamera(handler,0,0);
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		settingState = new SettingState(handler);
		
		State.setState(menuState);
		
		
	}
	
	public void tick()
	{
		keyManager.tick();
		
		if (State.getState()!= null)
		{
			State.getState().tick();
		}
	}
	
	public void render() 
	{
		
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
//		***DRAW***
		if (State.getState()!= null)
		{
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose(); 
	}
	
	public void run()
	{
		init ();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer=0;
		int ticks =0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
				if (delta >= 1) {
					tick();
					render();
					ticks++;
					delta--;
				}
			if (timer >= 1000000000) 
			{
				System.out.println("Fps is " + ticks);
				ticks = 0;
				timer = 0;
			}
		}stop();
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	public synchronized void start()
	{
		if (running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop()
	{
		if (!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
	
}
