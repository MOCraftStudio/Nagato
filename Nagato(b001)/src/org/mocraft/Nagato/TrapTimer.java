package org.mocraft.Nagato;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

import org.mocraft.Gui.AtkType;
import org.mocraft.Gui.TrapStatus;
import org.mocraft.Port.Attack;

public class TrapTimer extends TimerTask {

	public static Timer timer = new Timer();
	public TrapStatus status;
	public static int trap;
	public static AtkType type;
	public static int target;
	public static int time;
	
	public void setTarget(int _trap, AtkType _type, int _target) {
		trap = _trap;
		type = _type;
		target = _target;
		return;
	}

	@Override
	public void run() {
		if(time == 0) {
			timer.cancel();
			Nagato.mainGui.countField[trap].setBackground(Color.GREEN);
			status = TrapStatus.REST;
		} else {
			time--;
		}
		Nagato.mainGui.countField[trap].setText(Integer.toString(time));
	}
	
	public void start() {
		try {
			if(type == AtkType.Null) {
			} else if(type == AtkType.Attack) {
			} else if(type == AtkType.Practice) {
			} else if(type == AtkType.Levy) {
				Attack.levy(trap, target);
				status = TrapStatus.LEVY;
				time = LevyData.time[target];
				timer.schedule(this, 0, 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//Nagato.system.checkCatAndTv();
		}
	}
}
