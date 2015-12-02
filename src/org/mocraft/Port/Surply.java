package org.mocraft.Port;

import org.mocraft.Nagato.Nagato;

public class Surply extends Nagato {

	public static void surply(int trap) throws Exception {
		myScreen.click("img/Port/surply.png");
		myScreen.wait("img/Global/trap1-select.png");
		myScreen.click("img/Global/trap" + trap + ".png");
		if (system.imgExists("img/Surply/need.png", (float) 0.8)) {
			myScreen.click("img/Surply/selectAll.png");
		}
		myScreen.click("img/Global/port.png");
		return;
	}

	public static void surply(int[] traps) throws Exception {
		myScreen.click("img/Port/surply.png");
		myScreen.wait("img/Global/trap1-select.png");
		for (int i = 0; i < traps.length; ++i) {
			myScreen.click("img/Global/trap" + traps[i] + ".png");
			if (system.imgExists("img/Surply/need.png", (float) 0.8)) {
				myScreen.click("img/Surply/selectAll.png");
			}
		}
		myScreen.click("img/Global/port.png");
		return;
	}
}
