package org.mocraft.Nagato;

import org.mocraft.Gui.GuiMain;
import org.mocraft.Gui.GuiManager;
import org.mocraft.Gui.GuiTesk;
import org.mocraft.Port.Attack;
import org.mocraft.Port.Port;
import org.mocraft.Port.Surply;
import org.mocraft.Port.Team;
import org.sikuli.script.Location;
import org.sikuli.script.Screen;

public class Nagato {

	public static NagatoSystem system = new NagatoSystem();
	public static Port port = new Port();
	public static Team team = new Team();
	public static Attack attack = new Attack();
	public static Surply surply = new Surply();
	public static GuiManager guiManager = new GuiManager();
	public static GuiMain guiMain = new GuiMain();
	public static GuiTesk guiTesk = new GuiTesk();

	public static TrapTimer traps[] = new TrapTimer[4];
	public static Screen screen = new Screen();
	public static Location anchor = new Location(0, 0);

	public static void main(String[] args) {
		initNagato();
		while (true) {
			try {
				attack.levy(traps);
				for(int i = 1; i < 4; ++i) {
					if(guiMain.countField[i].getText().equals("Leving...")) {
						port.processFlag();
						screen.wait(30.0);	
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void initNagato() {
		try {
			system.log(guiMain.logArea, "Initing Nagato...");
			system.checkCat();
			system.checkAnchor();
			team.getTrapStatus();
			system.log(guiMain.logArea, "Trap Initing...");
			for (int i = 0; i < 4; ++i) {
				traps[i] = new TrapTimer(i);
				system.log(guiMain.logArea, "> Trap " + traps[i].getTrap() + " Inited!");
			}
			system.log(guiMain.logArea, "Trap Inited!");
			system.log(guiMain.logArea, "Nagato Initied!");
			guiMain.btnTesk.setEnabled(true);
		} catch (Exception e) {
			system.log(guiMain.logArea, "Initie Nagato Failed, Retry...");
			guiMain.btnTesk.setEnabled(false);
			initNagato();
		}
	}
}
