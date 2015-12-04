package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class PortKai extends Nagato {

	private String imgFlag = "img/Port/flag.png";
	// private String imgTeam = "img/Port/team.png";
	// private String imgPort = "img/Global/port.png";
	private String imgTrap2N = "img/Port/trap2-name.png";
	private String imgTrap3N = "img/Port/trap3-name.png";
	private String imgTrap4N = "img/Port/trap4-name.png";
	private String imgNext = "img/Port/next.png";

	public void detectFlag() {
		try {
			guiMain.log("(*) Detecting Flag: ");
			if ((teamKai.hasTrapLeving() || teamKai.hasTrapBacked()) && !systemKai.imgExactExists(imgFlag)) {
				guiMain.logln("None.");
				systemKai.click(ImageData.portTeam.img());
				gameForm.wait(ImageData.globalPort.img());
				systemKai.click(ImageData.globalPort.img());
				gameForm.wait(ImageData.portTeam.img());
				gameForm.hover(zeroPoint);
				return;
			} else if(!systemKai.imgExactExists(imgFlag)) {
				guiMain.logln("None.");
				return;
			}
			guiMain.logln("Detected!");
			processFlag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processFlag() {
		if (!systemKai.imgExactExists(imgFlag)) { return; }
		try {
			systemKai.click(imgFlag);
			gameForm.wait(imgNext, 60.0);
			if (systemKai.imgExactExists(imgTrap2N)) {
				traps[1].cancel();
				guiMain.logln(">>> Trap 2 Detected!");
				guiMain.countField[1].setText("Need Surply!");
				guiMain.countField[1].setBackground(Color.RED);
				traps[1].setType(TrapType.NeedSurply);
			} else if (systemKai.imgExactExists(imgTrap3N)) {
				traps[2].cancel();
				guiMain.logln(">>> Trap 3 Detected!");
				guiMain.countField[2].setText("Need Surply!");
				guiMain.countField[2].setBackground(Color.RED);
				traps[2].setType(TrapType.NeedSurply);
			} else if (systemKai.imgExactExists(imgTrap4N)) {
				traps[3].cancel();
				guiMain.logln(">>> Trap 4 Detected!");
				guiMain.countField[3].setText("Need Surply!");
				guiMain.countField[3].setBackground(Color.RED);
				traps[3].setType(TrapType.NeedSurply);
			}
			systemKai.click(imgNext);
			systemKai.click(imgNext);
			gameForm.wait(ImageData.portAttack.img());
			processFlag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
