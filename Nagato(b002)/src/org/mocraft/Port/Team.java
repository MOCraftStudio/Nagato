package org.mocraft.Port;

import org.mocraft.Nagato.Nagato;

public class Team extends Nagato {

	public void getTrapStatus() throws Exception {
		system.log(guiMain.logArea, "Loading Trap Status...");
		system.click("img/Port/team.png");
		screen.wait("img/Global/trap1-select.png", 10);
		for(int i = 2; i < 5; ++i) {
			system.click("img/Global/trap" + i + ".png");
			screen.wait("img/Global/trap" + i + "-select.png");
			if(system.imgExactExists("img/Team/leving.png")) {
				guiMain.countField[i - 1].setText("Leving...");
			} else {
				guiMain.countField[i - 1].setText("Resting...");
			}
			system.log(guiMain.logArea, "> Trap " + i + " status updated!");
		}
		system.click("img/Global/port.png");
		system.log(guiMain.logArea, "Trap Status Updated!");
		screen.hover("img/Port/anchor.png");
	}
	
}
