package org.mocraft.Nagato;

public class Levy {

	private String name;
	private int time;
	private int[] reward = new int[4];
	
	public Levy(String n, int t, int[] r) {
		this.name = n;
		this.time = t;
		for(int i = 0; i < 4; ++i) {
			this.reward[i] = r[i];
		}
	}
	
	public String getName() { return name; }
	public int getTime() { return time; }
	public int getOil() { return reward[0]; }
	public int getBullet() { return reward[1]; }
	public int getSteel() { return reward[2]; }
	public int getAluminum() { return reward[3]; }
	
}
