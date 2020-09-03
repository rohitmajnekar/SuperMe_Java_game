package com.rohit.main.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 64, HEIGHT = 64;
	
	public static BufferedImage dirt, brick, stone, tree, player_up, ball;
	public static BufferedImage[] player_left, player_right;
	public static BufferedImage[] button_start;
	public static BufferedImage[] fire_ball;
	
	public static void init ()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("C:/Users/Mr. Roo/eclipse-workspace/Java_Game/res/texture/SpriteSheet.png"));
		
		player_right = new BufferedImage[2];
		player_left = new BufferedImage[2];
		button_start = new BufferedImage[2];
		fire_ball = new BufferedImage[2];
		
		
		button_start[0] = sheet.crop(WIDTH*2, HEIGHT*3, WIDTH, HEIGHT);
		button_start[1] = sheet.crop(WIDTH*3, HEIGHT*3, WIDTH, HEIGHT);
		
		player_left[0] = sheet.crop(WIDTH*2, HEIGHT, WIDTH, HEIGHT);
		player_left[1] = sheet.crop(WIDTH*3, HEIGHT, WIDTH, HEIGHT);
		
		player_right[0] = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		player_right[1] = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
		
		fire_ball[0] = sheet.crop(0, HEIGHT*3, WIDTH, HEIGHT);
		fire_ball[1] = sheet.crop(WIDTH, HEIGHT*3, WIDTH, HEIGHT);
		
		player_up = sheet.crop(WIDTH, HEIGHT*2, WIDTH, HEIGHT);
		
		ball = sheet.crop(0, HEIGHT*3, WIDTH, HEIGHT);
		tree = sheet.crop(WIDTH*2, HEIGHT*2, WIDTH, HEIGHT);
		dirt = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		brick = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
		stone = sheet.crop(WIDTH *3, 0, WIDTH, HEIGHT);
		
	}
	

}
