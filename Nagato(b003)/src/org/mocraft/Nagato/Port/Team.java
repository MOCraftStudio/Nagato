package org.mocraft.Nagato.Port;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.Gui.GuiTesk;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Team extends Nagato {

	private String imgTeam = "img/Port/team.png";
	private String imgTrap1S = "img/Global/trap1-select.png";
	private String imgPort = "img/Global/port.png";

	public void getAllTrapStatus() {
		guiMain.log("> Trap Status Updating...");
		try {
			system.click(imgTeam);
			screen.wait(imgTrap1S);
			
			for(int i = 1; i < 5; ++i) {
				if(i != 1) { 
					system.click("img/Global/trap" + i + ".png");
					screen.wait("img/Global/trap" + i + "-select.png");
				}
				if(system.imgExactExists("img/Team/leving.png")) {
					guiMain.countField[i - 1].setText("Leving...");
					traps.get(i - 1).setType(TrapType.Leving);
				} else {
					guiMain.countField[i - 1].setText("Resting...");
					traps.get(i - 1).setType(TrapType.Resting);
				}
				guiMain.log(">> Trap " + i + " Has Updated!");
			}
			system.click(imgPort);
			guiMain.log("> Trap Status Updated!");
			screen.hover(anchor);
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.log("> Unknow Error Occoured! Retry...");
			getAllTrapStatus();
		}
	}

	public boolean hasTrapLeving() {
		for(int i = 1; i < 4; ++i) {
			if(traps.get(i).getType() == TrapType.Leving) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTrapBacked() {
		for(int i = 1; i < 4; ++i) {
			if(traps.get(i).getType() == TrapType.Backed) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTrapNeedLevy() {
		for(int i = 1; i < 4; ++i) {
			if(traps.get(i).getType() == TrapType.Resting && GuiTesk.target[i] != 0) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTrapNeedSurply() {
		for(int i = 0; i < 4; ++i) {
			if(traps.get(i).getType() == TrapType.NeedSurply) {
				return true;
			}
		}
		return false;
	}
}
