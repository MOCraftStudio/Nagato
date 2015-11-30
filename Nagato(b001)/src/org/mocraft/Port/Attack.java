package org.mocraft.Port;

import org.mocraft.Nagato.LevyData;
import org.mocraft.Nagato.Nagato;

public class Attack extends Nagato {

	public static void attack(int trap, int target) throws Exception {
		myScreen.click("img/Port/attack.png");
		myScreen.wait("img/Attack/attack.png");
		myScreen.click("img/Attack/attack.png");
		myScreen.waitVanish("img/Attack/attack.png");
		
		
	}
	
	public static void levy(int trap, int target) throws Exception {
		myScreen.click("img/Port/attack.png");
		myScreen.wait("img/Attack/attack.png");
		myScreen.click("img/Attack/levy.png");
		myScreen.waitVanish("img/Attack/levy.png");

		if (target >= 1 && target <= 8) {
			myScreen.click("img/Attack/Levy/I.png");
		} else if (target >= 9 && target <= 16) {
			myScreen.click("img/Attack/Levy/II.png");
		}

		myScreen.click(LevyData.getImg(target));
		myScreen.click("img/Attack/Levy/decide.png");
		myScreen.click("img/Global/trap" + trap + ".png");
		myScreen.click("img/Attack/Levy/start.png");
		myScreen.wait(LevyData.getImg(target), 10);
		myScreen.click("img/Global/port.png");
		myScreen.waitVanish("img/Global/port.png");
		return;
	}

	public static void levy(int[] traps, int[] targets) throws Exception {
		myScreen.click("img/Port/attack.png");
		myScreen.waitVanish("img/Port/attack.png");
		myScreen.click("img/Attack/levy.png");
		myScreen.waitVanish("img/Attack/levy.png");

		for (int i = 0; i < traps.length - 1; ++i) {
			myScreen.click(LevyData.getImg(targets[i]));
			myScreen.click("img/Attack/Levy/decide.png");
			myScreen.click("img/Global/trap" + traps[i] + ".png");
			myScreen.click("img/Attack/Levy/start.png");
			myScreen.wait(LevyData.getImg(targets[i]), 10);
		}
		myScreen.click("img/Global/port.png");
		myScreen.waitVanish("img/Global/port.png");
		return;
	}
}
