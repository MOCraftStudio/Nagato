package org.mocraft.Nagato;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import org.mocraft.Nagato.TypeDefine.TrapType;

public class Trap extends Nagato {

	private Timer timer;
	private final int id;
	private String name;
	private int time;
	private TrapType type;
	
	public Trap(int _id) {
		this.id = _id;
		this.type = TrapType.Resting;
	}
	
	public void start(int _time) {
		time = _time;
		timer = new Timer();
		timer.schedule(new Count(), 0, 1000);
	}
	
	public String getName() { return name; }
	public TrapType getType() { return type; }
	public void setName(String n) { name = n; }
	public void setType(TrapType t) { type = t; }
	public void cancel() {
		if(type == TrapType.Backed || type == TrapType.Leving) { return; }
		timer.cancel();
	}

	private String formatTime(int t) {
		String h = String.format("%02d", t / 3600);
		String m = String.format("%02d", (t % 3600) / 60);
		String s = String.format("%02d", (t % 3600) % 60);
		return h + "時間" + m + "分" + s + "秒";
	}

	class Count extends TimerTask {

		@Override
		public void run() {
			if(time > 0) {
				guiMain.countField[id].setBackground(Color.WHITE);
				guiMain.countField[id].setText(formatTime(time));
				type = TrapType.ScheduledLeving;
				time--;
			} else {
				guiMain.countField[id].setBackground(Color.ORANGE);
				guiMain.countField[id].setText("Backed");
				type = TrapType.Backed;
				timer.cancel();
			}
		}

	}
}

