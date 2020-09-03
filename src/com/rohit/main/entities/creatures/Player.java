package com.rohit.main.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rohit.main.Handler;
import com.rohit.main.entities.Entity;
import com.rohit.main.gfx.Animation;
import com.rohit.main.gfx.Assets;

public class Player extends Creature {
	
	private Animation leftWalk;
	private Animation rightWalk, fireBall;
	private int go = 0;
	
	private long lastAttactTimer, attactCooldown = 300, attactTimer = attactCooldown;
	

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x =16;
		bounds.y =32;
		bounds.width = 32;
		bounds.height = 32;
		
		rightWalk = new Animation(200, Assets.player_right);
		leftWalk = new Animation(200, Assets.player_left);
		fireBall = new Animation(200, Assets.fire_ball);
		
	}

	@Override
	public void tick() {
		//Animation
		leftWalk.tick();
		rightWalk.tick();
		fireBall.tick();
		getInput();
		Move();
		handler.getGameCamera().centerOnEntity(this);
		checkAttacts();
	}
	
	public void checkAttacts() {
		attactTimer += System.currentTimeMillis() - lastAttactTimer;
		lastAttactTimer = System.currentTimeMillis();
		if (attactTimer < attactCooldown)
			return;
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		
		if (handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y - arSize;
		}else if (handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width/2 - arSize/2;
			ar.y = cb.y + cb.height;
		}else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height/2 - arSize/2;
		}else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height/2 - arSize/2;
		}else {
			return;			
		}
		
		attactTimer = 0;
		
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				e.hurt(1);
				return;
			}
				
			 
		}
		
	}
	
	public void die() {
		System.out.print("yes");
	}
	
	public void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		if (yMove < 0) {
			g.drawImage(Assets.player_up, (int) (x - handler.getGameCamera().getxOffSet()),(int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		}else if (xMove > 0) {
			g.drawImage(leftWalk.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		}else if (xMove < 0) {
			g.drawImage(rightWalk.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		}else if (yMove > 0) {
			g.drawImage(Assets.player_right[1], (int) (x - handler.getGameCamera().getxOffSet()),(int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		}
		else {
			g.drawImage(Assets.player_left[0], (int) (x - handler.getGameCamera().getxOffSet()),(int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		}
		
		
		if (handler.getKeyManager().aUp) {
			Rectangle cb = getCollisionBounds(0, 0);
			int arSize = 20;
			g.drawImage(fireBall.getCurrentFrame(), (int) ((cb.x + cb.width/2 - arSize/2) - handler.getGameCamera().getxOffSet()-5),(int) ((cb.y - arSize) - handler.getGameCamera().getyOffSet()-40 + go), 30, 30, null);
			go -=1;
			if (go == -30) {
				go=0;	
			}
		}else if (handler.getKeyManager().aDown) {
			Rectangle cb = getCollisionBounds(0, 0);
			int arSize = 20;
			g.drawImage(fireBall.getCurrentFrame(), (int) ((cb.x + cb.width/2 - arSize/2) - handler.getGameCamera().getxOffSet()-5),(int) ((cb.y + cb.height) - handler.getGameCamera().getyOffSet() + go), 30, 30, null);
			go +=1;
			if (go == 30) {
				go=0;	
			}
		}else if (handler.getKeyManager().aLeft) {
			Rectangle cb = getCollisionBounds(0, 0);
			int arSize = 20;
			g.drawImage(fireBall.getCurrentFrame(), (int) ((cb.x - arSize) - handler.getGameCamera().getxOffSet()+ go),(int) ((cb.y + cb.height/2 - arSize/2) - handler.getGameCamera().getyOffSet()- 20), 30, 30, null);
			go -=1;
			if (go == -30) {
				go=0;	
			}
		}else if (handler.getKeyManager().aRight) {
			Rectangle cb = getCollisionBounds(0, 0);
			int arSize = 20;
			g.drawImage(fireBall.getCurrentFrame(), (int) ((cb.x + cb.width) - handler.getGameCamera().getxOffSet() + go),(int) ((cb.y + cb.height/2 - arSize/2) - handler.getGameCamera().getyOffSet()-20), 30, 30, null);
			go +=1;
			if (go == 30) {
				go=0;	
			}
		}
			
			
	}
	
	

}
