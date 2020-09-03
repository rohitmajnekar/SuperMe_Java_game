package com.rohit.main.tiles;

import com.rohit.main.gfx.Assets;

public class BrickTile extends Tile {

	public BrickTile(int id) {
		super(Assets.brick, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
}
