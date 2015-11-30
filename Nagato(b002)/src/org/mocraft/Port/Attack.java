package org.mocraft.Port;

import org.mocraft.Gui.LevyData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TrapTimer;
import org.mocraft.Nagato.TrapType;

public class Attack extends Nagato {

	private boolean checkTrapsHasRest(TrapTimer[] trap) throws Exception {
		for(int i = 1; i < 4; ++i) {
			if(trap[i].getType() == TrapType.Rest) {
				return true;
			}
		}
		return false;
	} 
	
	public void levy(TrapTimer trap, int target) throws Exception {
		if(trap.getType() != TrapType.Rest) { return; }
		system.log(guiMain.logArea, "Start Leving Section...");
		system.click("img/Port/attack.png");
		screen.wait("img/Attack/levy.png", 10);
		system.click("img/Attack/levy.png");
		screen.wait("img/Attack/Levy/I.png");
		
		section(target);
		system.log(guiMain.logArea, "Sending Trap " + trap.getTrap() + " To Levy " + LevyData.name[target]);
		system.click("img/Attack/Levy/" + target + ".png");
		system.click("img/Attack/Levy/decide.png");
		screen.wait("img/Attack/Levy/start.png");
		system.click("img/Global/trap" + trap.getTrap() + ".png");
		system.click("img/Attack/Levy/start.png");
		trap.start();
		screen.wait("img/Attack/Levy/abandon-bright.png");
		screen.wait(5.0);
		system.log(guiMain.logArea, "Leving Section Sended!");
		system.click("img/Global/port.png");
		screen.hover(anchor);
		return;
	}
	
	public void levy(TrapTimer[] traps) throws Exception {
		if(!checkTrapsHasRest(traps)) { return; }
		system.log(guiMain.logArea, "Start Leving Section...");
		system.click("img/Port/attack.png");
		screen.wait("img/Attack/levy.png");
		system.click("img/Attack/levy.png");
		screen.wait("img/Attack/Levy/I.png");
		
		for(int i = 1; i < 4; ++i) {
			System.out.println(i + " X " + traps[i].getTarget());
			if(traps[i].getType() != TrapType.Rest || traps[i].getTarget() == 0) { continue; }
			System.out.println(i + " @ " + traps[i].getTrap() + " @ " + traps[i].getType().toString() + " @ " + traps[i].getTarget());
			section(traps[i].getTarget());
			system.log(guiMain.logArea, "Sending Trap " + traps[i].getTrap() + " To Levy " + LevyData.name[traps[i].getTarget()]);
			system.click("img/Attack/Levy/" + traps[i].getTarget() + ".png");
			screen.wait("img/Attack/Levy/decide.png");
			system.click("img/Attack/Levy/decide.png");
			screen.wait("img/Attack/Levy/start.png");
			system.click("img/Global/trap" + traps[i].getTrap() + ".png");
			system.click("img/Attack/Levy/start.png");
			traps[i].start();
			screen.wait("img/Attack/Levy/abandon-bright.png");
			screen.wait(5.0);
		}
		system.log(guiMain.logArea, "Leving Section Sended!");
		system.click("img/Global/port.png");
		screen.hover(anchor);
		return;
	}
	
	public void section(int target) throws Exception {
		if(target >= 9 && target <= 16) {
			system.click("img/Attack/Levy/II.png");
			screen.wait("img/Attack/Levy/9.png");
		}
	}
}
