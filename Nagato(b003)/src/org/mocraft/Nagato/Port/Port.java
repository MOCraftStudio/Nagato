package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Port extends Nagato {

	private String imgFlag = "img/Port/flag.png";
	private String imgTeam = "img/Port/team.png";
	private String imgPort = "img/Global/port.png";
	private String imgTrap2N = "img/Port/trap2-name.png";
	private String imgTrap3N = "img/Port/trap3-name.png";
	private String imgTrap4N = "img/Port/trap4-name.png";
	private String imgNext = "img/Port/next.png";
	
	public void detectFlagAndProcess() {
		if(!team.hasTrapLeving() && !team.hasTrapBacked() && !system.imgExactExists(imgFlag)) { return; }
		try {
			if(!system.imgExactExists(imgFlag)) {
				guiMain.log("Flag Updating...");
				system.click(imgTeam);
				system.click(imgPort);
				screen.hover(anchor);
				return;
			}
			guiMain.log("Detected Flag, Processing...");
			system.click(imgFlag);
			screen.wait(imgNext, 60.0);
			if(system.imgExactExists(imgTrap2N)) {
				traps.get(1).cancel();
				guiMain.log("> Trap 2 Detected!");
				guiMain.countField[1].setText("Need Surply!");
				guiMain.countField[1].setBackground(Color.RED);
				traps.get(1).setType(TrapType.NeedSurply);
			} else if(system.imgExactExists(imgTrap3N)) {
				traps.get(2).cancel();
				guiMain.log("> Trap 3 Detected!");
				guiMain.countField[2].setText("Need Surply!");
				guiMain.countField[2].setBackground(Color.RED);
				traps.get(2).setType(TrapType.NeedSurply);
			} else if(system.imgExactExists(imgTrap4N)) {
				traps.get(3).cancel();
				guiMain.log("> Trap 4 Detected!");
				guiMain.countField[3].setText("Need Surply!");
				guiMain.countField[3].setBackground(Color.RED);
				traps.get(3).setType(TrapType.NeedSurply);
			}
			system.click(imgNext);
			system.click(imgNext);
			detectFlagAndProcess();
			guiMain.log("Flag Section Processed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
