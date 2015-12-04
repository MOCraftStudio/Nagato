package org.mocraft.Nagato;

import java.util.Timer;

import org.mocraft.Nagato.Gui.GuiInfo;
import org.mocraft.Nagato.Gui.GuiLevy;
import org.mocraft.Nagato.Gui.GuiMain;
import org.mocraft.Nagato.Gui.GuiManager;
import org.mocraft.Nagato.Port.Attack;
import org.mocraft.Nagato.Port.PortKai;
import org.mocraft.Nagato.Port.Surply;
import org.mocraft.Nagato.Port.TeamKai;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class Nagato {

	private static Timer tick = new Timer();
	public static NagatoSystemKai systemKai = new NagatoSystemKai();
	public static GuiManager guiManager = new GuiManager();
	public static GuiMain guiMain = new GuiMain();
	public static GuiLevy guiLevyTesk = new GuiLevy();
	public static GuiInfo guiInfo = new GuiInfo();
	public static PortKai portKai = new PortKai();
	public static TeamKai teamKai = new TeamKai();
	public static Surply surply = new Surply();
	public static Attack attack = new Attack();

	public static Screen globalScreen = new Screen();
	public static Region gameForm = new Region(0, 0, 800, 480);
	public static Location zeroPoint = new Location(0, 0);
	public static Trap traps[] = new Trap[4];

	public static void main(String[] args) {
		systemKai.initNagato();
		tick.schedule(systemKai.new MainThread(), 0, 5000);
	}

}
