package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Surply extends Nagato {

	//private String imgSurply = "img/Port/surply.png";
	//private String imgSelectAll = "img/Surply/selectAll.png";
	//private String imgPort = "img/Global/port.png";
	
	public void detectNeedAndSurply() {
		if(!team.hasTrapNeedSurply()) { return; }
		try {
			guiMain.log("Detected Need Surply, Processing...");
			gameForm.click(ImageData.portSurply.randomLoc());
			gameForm.wait(ImageData.surplySelectAll.img());
			
			for(int i = 0; i < 4; ++i) {
				if(traps[i].getType() != TrapType.NeedSurply) { continue; }
				gameForm.click("img/Global/trap" + (i + 1) + ".png");
				gameForm.click(ImageData.surplySelectAll.randomLoc());
				guiMain.countField[i].setBackground(Color.WHITE);
				guiMain.countField[i].setText("Resting...");
				traps[i].setType(TrapType.Resting);
				guiMain.log("> Trap " + (i + 1) + " Surplied!");
			}
			gameForm.click(ImageData.globalPort.randomLoc());
			gameForm.wait(ImageData.portAnchor.img());
			guiMain.log("Suply Section Processed!");
			gameForm.hover(zeroPoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
