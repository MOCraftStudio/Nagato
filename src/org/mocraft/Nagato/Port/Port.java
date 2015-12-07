package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

@Deprecated
public class Port extends Nagato {

	private String imgFlag = "img/Port/flag.png";
	//private String imgTeam = "img/Port/team.png";
	//private String imgPort = "img/Global/port.png";
	private String imgTrap2N = "img/Port/trap2-name.png";
	private String imgTrap3N = "img/Port/trap3-name.png";
	private String imgTrap4N = "img/Port/trap4-name.png";
	private String imgNext = "img/Port/next.png";
	
	public void detectFlagAndProcess() {
		if(!teamKai.hasTrapLeving() && !teamKai.hasTrapBacked() && !systemKai.imgExactExists(imgFlag)) { return; }
		try {
			if(!systemKai.imgExactExists(imgFlag)) {
				guiMain.logln("Flag Updating...");
				gameForm.click(ImageData.portTeam.randomLoc());
				gameForm.wait(ImageData.globalPort.img(), 5.0);
				gameForm.click(ImageData.globalPort.randomLoc());
				gameForm.wait(ImageData.portTeam.img(), 5.0);
				gameForm.hover(zeroPoint);
				return;
			}
			guiMain.logln("Detected Flag, Processing...");
			gameForm.click(imgFlag);
			gameForm.wait(imgNext, 60.0);
			if(systemKai.imgExactExists(imgTrap2N)) {
				traps[1].cancel();
				guiMain.logln("> Trap 2 Detected!");
				guiMain.countField[1].setText("Need Surply!");
				guiMain.countField[1].setBackground(Color.RED);
				guiMain.targetLbl[1].setText(" ");
				traps[1].setType(TrapType.NeedSurply);
			} else if(systemKai.imgExactExists(imgTrap3N)) {
				traps[2].cancel();
				guiMain.logln("> Trap 3 Detected!");
				guiMain.countField[2].setText("Need Surply!");
				guiMain.countField[2].setBackground(Color.RED);
				guiMain.targetLbl[2].setText(" ");
				traps[2].setType(TrapType.NeedSurply);
			} else if(systemKai.imgExactExists(imgTrap4N)) {
				traps[3].cancel();
				guiMain.logln("> Trap 4 Detected!");
				guiMain.countField[3].setText("Need Surply!");
				guiMain.countField[3].setBackground(Color.RED);
				guiMain.targetLbl[3].setText(" ");
				traps[3].setType(TrapType.NeedSurply);
			}
			gameForm.click(imgNext);
			gameForm.click(imgNext);
			gameForm.wait(ImageData.portAttack.img(), 5.0);
			detectFlagAndProcess();
			guiMain.logln("Flag Section Processed!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
