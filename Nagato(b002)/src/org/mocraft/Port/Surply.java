package org.mocraft.Port;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TrapTimer;

public class Surply extends Nagato {
	
	public void surply(TrapTimer trap) throws Exception {
		system.log(guiMain.logArea, "Start Surpling...");
		system.click("img/Port/surply.png");
		screen.wait("img/Global/trap1-select.png");
		system.click("img/Global/trap" + trap.getTrap() + ".png");
		
		if(trap.getTrap() != 1) { system.click("img/Global/trap" + trap.getTrap() + ".png"); }
		if(system.imgExactExists("img/Surply/need.png")) {
			system.click("img/Surply/selectAll.png");
			system.log(guiMain.logArea, "> Trap " + trap.getTrap() + " Surplied!");
		}
		system.click("img/Global/port.png");
		system.log(guiMain.logArea, "Traps Surplied!");
		screen.hover("img/Port/anchor.png");
		return;
	}
	
	public void surply(TrapTimer[] traps) throws Exception {
		system.log(guiMain.logArea, "Start Surpling...");
		system.click("img/Port/surply.png");
		screen.wait("img/Global/trap1-select.png");
		for(int i = 0; i < 4; ++i) {
			system.click("img/Global/trap" + traps[i].getTrap() + ".png");
		
			if(traps[i].getTrap() != 1) { system.click("img/Global/trap" + traps[i].getTrap() + ".png"); }
			if(system.imgExactExists("img/Surply/need.png")) {
				system.click("img/Surply/selectAll.png");
				system.log(guiMain.logArea, "> Trap " + traps[i].getTrap() + " Surplied!");
			}
		}
		system.click("img/Global/port.png");
		system.log(guiMain.logArea, "Traps Surplied!");
		screen.hover("img/Port/anchor.png");
		return;
	}

}
