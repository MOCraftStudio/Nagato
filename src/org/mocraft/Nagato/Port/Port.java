package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Port extends Nagato {

	private String imgFlag = "img/Port/flag.png";
	//private String imgTeam = "img/Port/team.png";
	//private String imgPort = "img/Global/port.png";
	private String imgTrap2N = "img/Port/trap2-name.png";
	private String imgTrap3N = "img/Port/trap3-name.png";
	private String imgTrap4N = "img/Port/trap4-name.png";
	private String imgNext = "img/Port/next.png";
	
	public void detectFlagAndProcess() {
		if(!team.hasTrapLeving() && !team.hasTrapBacked() && !system.imgExactExists(imgFlag)) { return; }
		try {
			if(!system.imgExactExists(imgFlag)) {
				guiMain.log("Flag Updating...");
				gameForm.click(ImageData.portTeam.randomLoc());
				gameForm.wait(ImageData.globalPort.img());
				gameForm.click(ImageData.globalPort.randomLoc());
				gameForm.wait(ImageData.portTeam.img());
				gameForm.hover(zeroPoint);
				return;
			}
			guiMain.log("Detected Flag, Processing...");
			gameForm.click(imgFlag);
			gameForm.wait(imgNext, 60.0);
			if(system.imgExactExists(imgTrap2N)) {
				traps[1].cancel();
				guiMain.log("> Trap 2 Detected!");
				guiMain.countField[1].setText("Need Surply!");
				guiMain.countField[1].setBackground(Color.RED);
				traps[1].setType(TrapType.NeedSurply);
			} else if(system.imgExactExists(imgTrap3N)) {
				traps[2].cancel();
				guiMain.log("> Trap 3 Detected!");
				guiMain.countField[2].setText("Need Surply!");
				guiMain.countField[2].setBackground(Color.RED);
				traps[2].setType(TrapType.NeedSurply);
			} else if(system.imgExactExists(imgTrap4N)) {
				traps[3].cancel();
				guiMain.log("> Trap 4 Detected!");
				guiMain.countField[3].setText("Need Surply!");
				guiMain.countField[3].setBackground(Color.RED);
				traps[3].setType(TrapType.NeedSurply);
			}
			gameForm.click(imgNext);
			gameForm.click(imgNext);
			gameForm.wait(ImageData.portAttack.img());
			detectFlagAndProcess();
			guiMain.log("Flag Section Processed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
