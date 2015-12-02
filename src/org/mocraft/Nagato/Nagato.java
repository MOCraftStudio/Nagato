package org.mocraft.Nagato;

import java.util.ArrayList;

import org.mocraft.Nagato.Gui.GuiMain;
import org.mocraft.Nagato.Gui.GuiManager;
import org.mocraft.Nagato.Gui.GuiTesk;
import org.mocraft.Nagato.Port.Attack;
import org.mocraft.Nagato.Port.Port;
import org.mocraft.Nagato.Port.Surply;
import org.mocraft.Nagato.Port.Team;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

public class Nagato {

	public static NagatoSystem system = new NagatoSystem();
	public static GuiManager guiManager = new GuiManager();
	public static GuiMain guiMain = new GuiMain();
	public static GuiTesk guiTesk = new GuiTesk();
	public static Port port = new Port();
	public static Team team = new Team();
	public static Surply surply = new Surply();
	public static Attack attack = new Attack();

	public static Screen screen = new Screen();
	public static Location anchor = new Location(0, 0);
	public static ArrayList<Trap> traps = new ArrayList<Trap>();
	
	public static void main(String[] args) {		
		initNagato();
		while (true) {
			system.cycleDetectWebAndFix();
			port.detectFlagAndProcess();
			surply.detectNeedAndSurply();
			port.detectFlagAndProcess();
			attack.detectTargetAndSendLevy();
			if(team.hasTrapLeving()) { screen.wait(60.0); }
		}
	}

	private static void initNagato() {
		try {
			guiMain.log("Initing Nagato...");
			system.detectWebAndFix(); // Exception has been processed.
			system.anchorLocate(); // Exception has been processed.
			for(int i = 0; i < 4; ++i) { traps.add(new Trap(i)); }
			team.getAllTrapStatus(); // Exception has been processed.
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
