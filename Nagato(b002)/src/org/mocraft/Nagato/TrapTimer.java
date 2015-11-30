package org.mocraft.Nagato;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import org.mocraft.Gui.LevyData;

public class TrapTimer extends TimerTask {

	private static Timer timer = new Timer();
	public static int trap;
	public static int target = 0;
	private static int time;
	public static TrapType type;

	public TrapTimer(int _trap) { setTrap(trap = (_trap + 1)); }
	
	public void setTrap(int _trap) { trap = _trap; }
	
	public void setType(TrapType _type) { type = _type; }

	public void setTarget(int _target) { target = _target; }

	public void setTime(int _time) { time = (_time + 20); }

	public TrapType getType() { return type; }
	
	public int getTarget() { return target; }
	
	public int getTrap() { return trap; }
	
	public String formatTime(int t) {
		String s = String.format("%02d", t % 60);
		String m = String.format("%02d", t / 60);
		String h = String.format("%02d", t / 3600);
		return h + "時間" + m + "分" + s + "秒";
	}

	@Override
	public void run() {
		try {
			if (time == 0) {
				timer.cancel();
				Nagato.guiMain.countField[trap - 1].setBackground(Color.GREEN);
				Nagato.guiMain.countField[trap - 1].setText("Resting...");
				Nagato.port.processFlag();
				setType(TrapType.Rest);
			} else {
				Nagato.guiMain.countField[trap - 1].setBackground(Color.WHITE);
				Nagato.guiMain.countField[trap - 1].setText(formatTime(time));
				time--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			setTime(LevyData.time[target]);
			setType(TrapType.Levy);
			timer.schedule(this, 0, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
