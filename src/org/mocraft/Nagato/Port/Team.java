package org.mocraft.Nagato.Port;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Loc;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Team extends Nagato {

	//private String imgTeam = "img/Port/team.png";
	private String imgTrap1S = "img/Global/trap1-select.png";
	//private String imgPort = "img/Global/port.png";
	
	public void initTrapStatus() {
		guiMain.log("> Trap Status Initing...");
		try {
			gameForm.click(ImageData.portTeam.randomLoc());
			gameForm.wait(imgTrap1S);
			
			for(int i = 1; i < 5; ++i) {
				if(i != 1) { 
					Loc trap = new Loc(18, 18, 122 + 30 * (i - 1), 109, "");
					gameForm.click(trap.randomLoc());
					//gameForm.wait("img/Global/trap" + i + "-select.png");
					gameForm.wait(1.0);
				}
				if(system.imgExactExists("img/Team/leving.png")) {
					guiMain.countField[i - 1].setText("Leving...");
					traps[i - 1].setType(TrapType.Leving);
				} else {
					guiMain.countField[i - 1].setText("Resting...");
					traps[i - 1].setType(TrapType.Resting);
				}
				guiMain.log(">> Trap " + i + " Has Updated!");
			}
			gameForm.click(ImageData.globalPort.randomLoc());
			gameForm.wait(ImageData.portTeam.img());
			guiMain.log("> Trap Status Inited!");
			gameForm.hover(zeroPoint);
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.log("> Unknow Error Occoured! Retry...");
			initTrapStatus();
		}
	}

	public boolean hasTrapLeving() {
		for(int i = 1; i < 4; ++i) {
			if(traps[i].getType() == TrapType.Leving) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTrapBacked() {
		for(int i = 1; i < 4; ++i) {
			if(traps[i].getType() == TrapType.Backed) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasTrapNeedLevy() {
		for(int i = 1; i < 4; ++i) {
			if(traps[i].getType() == TrapType.Resting && guiTesk.getTarget(i) != 0) {
				return true;
			}
		}
		return false;
	}

	public boolean hasTrapNeedSurply() {
		for(int i = 0; i < 4; ++i) {
			if(traps[i].getType() == TrapType.NeedSurply) {
				return true;
			}
		}
		return false;
	}
}
