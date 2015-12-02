package org.mocraft.Nagato;

import org.mocraft.Gui.GuiMain;
import org.mocraft.Gui.GuiManager;
import org.mocraft.Gui.GuiTesk;
import org.mocraft.Gui.TrapStatus;
import org.mocraft.Port.NagatoSystem;
import org.mocraft.Port.Surply;
import org.mocraft.Port.Team;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Screen;

public class Nagato {

	public static NagatoSystem system = new NagatoSystem();
	public GuiManager guiManager = new GuiManager();
	public static GuiMain mainGui = new GuiMain();
	public static GuiTesk teskGuiThread = new GuiTesk();
	public static TrapTimer trapTimers[] = new TrapTimer[4];
	public static Screen myScreen = new Screen();
	public static Location anchor = new Location(0, 0);
	
	public static void main(String[] args) {
		initNagato();
		try {
			checkFlag();
			Team.loadTrapStatus();
			while (true) {
				for (int i = 1; i < 4; ++i) {
					if (trapTimers[i].status != TrapStatus.LEVY) {
						trapTimers[i].start();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			system.checkCatAndTv();
		}
	}

	private static boolean initNagato() {
		boolean result = false;
		try {
			for (int i = 0; i < 4; ++i) {
				trapTimers[i] = new TrapTimer();
			}
			
			system.logLine(mainGui.logArea, "Initing Nagato...");
			if(!system.imgExists("img/Port/attack.png") && system.imgExists("img/Global/port.png")) {
				system.logLine(mainGui.logArea, "Backing To Port...");
				myScreen.click("img/Global/port.png");
				myScreen.wait("img/Port/team.png");
				system.logLine(mainGui.logArea, "Entered Port...");
			}
			
			system.logLine(mainGui.logArea, "Locating Anchor...");
			myScreen.hover("img/Port/anchor.png");
			anchor = Mouse.at();
			system.logLine(mainGui.logArea, "Anchor Located...");
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	private static boolean checkFlag() {
		try {
			if (system.imgExactExists("img/Port/flag.png")) {
				myScreen.click("img/Port/flag.png");
				myScreen.wait("img/Port/next.png", 15);
				myScreen.click("img/Port/next.png", 15);

				if (!system.imgExactExists("img/Port/flag.png")) {
					Surply.surply(new int[] { 1, 2, 3, 4 });
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			system.checkCatAndTv();
		}
		return false;
	}
}