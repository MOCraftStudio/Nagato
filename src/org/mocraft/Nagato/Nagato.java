package org.mocraft.Nagato;

import java.util.Timer;

import org.mocraft.Nagato.Gui.GuiInfo;
import org.mocraft.Nagato.Gui.GuiMain;
import org.mocraft.Nagato.Gui.GuiManager;
import org.mocraft.Nagato.Gui.GuiTesk;
import org.mocraft.Nagato.Port.Attack;
import org.mocraft.Nagato.Port.Port;
import org.mocraft.Nagato.Port.Surply;
import org.mocraft.Nagato.Port.Team;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class Nagato {

	private static Timer tick = new Timer();
	public static NagatoSystem system = new NagatoSystem();
	public static GuiManager guiManager = new GuiManager();
	public static GuiMain guiMain = new GuiMain();
	public static GuiTesk guiTesk = new GuiTesk();
	public static GuiInfo guiInfo = new GuiInfo();
	public static Port port = new Port();
	public static Team team = new Team();
	public static Surply surply = new Surply();
	public static Attack attack = new Attack();

	public static Screen globalScreen = new Screen();
	public static Region gameForm = new Region(0, 0, 800, 480);
	public static Location zeroPoint = new Location(0, 0);
	public static Trap traps[] = new Trap[4];

	public static void main(String[] args) {
		initNagato();
		tick.schedule(system.new MainThread(), 0, 1000);
	}

	private static void initNagato() {
		try {
			guiMain.log("Initing Nagato...");
			system.detectWebAndFix(); // Exception has been processed.
			system.anchorLocate(); // Exception has been processed.
			for (int i = 0; i < 4; ++i) {
				traps[i] = new Trap(i);
			}
			team.initTrapStatus(); // Exception has been processed.
			guiMain.log("Nagato Inited!");
			guiMain.btnTesk.setEnabled(true);
		} catch (Exception e) {
			guiMain.btnTesk.setEnabled(false);
			e.printStackTrace();
			guiMain.log(e.toString());
			guiMain.log("Natato Init Failed! Retry...");
			initNagato();
		}
	}
}
