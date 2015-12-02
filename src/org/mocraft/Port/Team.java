package org.mocraft.Port;

import org.mocraft.Nagato.Nagato;

public class Team extends Nagato {

	public static void loadTrapStatus() throws Exception {
		myScreen.click("img/Port/team.png");
		myScreen.wait("img/Global/trap1-select.png");
		for (int i = 1; i < 4; ++i) {
			myScreen.click("img/Global/trap" + (i + 1) + ".png");
			myScreen.wait("img/Global/trap" + (i + 1) + "-select.png");
			if (system.imgExactExists("img/Global/leving.png")) {
				myScreen.hover("img/Global/leving.png");
				mainGui.countField[i].setText("Leving...");
			} else {
				mainGui.countField[i].setText("Resting...");
			}
		}
		myScreen.click("img/Global/port.png");
	}

}
