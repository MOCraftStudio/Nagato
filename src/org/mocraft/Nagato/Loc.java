package org.mocraft.Nagato;

import java.util.Random;

import org.sikuli.script.Location;

public class Loc extends Nagato {

	private int width, height;
	private Location loc = new Location(0, 0);
	private String imgString = "";

	public Loc(int w, int h, int x, int y, String s) {
		this.width = w;
		this.height = h;
		this.loc = new Location(x, y);
		this.imgString = s;
	}
	
	public int width() { return width; }
	public int height() { return height; }
	public String img() { return imgString; }

	public Location topLeftLoc() {
		return new Location(loc.getX() + zeroPoint.getX(), loc.getY() + zeroPoint.getY());
	}

	public Location centerLoc() {
		return new Location(zeroPoint.getX() + loc.getX() + width / 2, zeroPoint.getY() + loc.getY() + height / 2);
	}
	
	public Location randomLoc() {
		Random rand = new Random();
		Location l = new Location(
				zeroPoint.getX() + loc.getX() + rand.nextInt(width),
				zeroPoint.getY() + loc.getY() + rand.nextInt(height)
				);
		return l;
	}

}
