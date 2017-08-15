package org.mocraft.NagatoKai.utils;

import org.mocraft.NagatoKai.Nagato;
import org.sikuli.script.Location;

import java.util.Random;

public class Loc extends Nagato {

    private int width, height;
    private Location loc = new Location(0, 0);
    private String imgString = "";

    public Loc(int w, int h, int x, int y, String s) {
        this.width = w - 4;
        this.height = h - 4;
        this.loc = new Location(x + 2, y + 2);
        this.imgString = s;
    }

    public int width() { return width; }
    public int height() { return height; }
    public Size regionSize() { return new Size(width + 8, height + 8); }
    public String img() { return imgString; }

    public Location regionLoc() {
        return new Location(loc.getX() + instance.zeroPoint.getX() - 4, loc.getY() + instance.zeroPoint.getY() - 4);
    }

    public Location topLeftLoc() {
        return new Location(loc.getX() + instance.zeroPoint.getX(), loc.getY() + instance.zeroPoint.getY());
    }

    public Location centerLoc() {
        return new Location(instance.zeroPoint.getX() + loc.getX() + width / 2, instance.zeroPoint.getY() + loc.getY() + height / 2);
    }

    public Location randomLoc() {
        Random rand = new Random();
        Location l = new Location(
                instance.zeroPoint.getX() + loc.getX() + rand.nextInt(width),
                instance.zeroPoint.getY() + loc.getY() + rand.nextInt(height)
        );
        return l;
    }

}
