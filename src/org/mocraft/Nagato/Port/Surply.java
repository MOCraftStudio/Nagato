package org.mocraft.Nagato.Port;

import java.awt.Color;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;

public class Surply extends Nagato {

	private String imgSurply = "img/Port/surply.png";
	private String imgSelectAll = "img/Surply/selectAll.png";
	private String imgPort = "img/Global/port.png";
	
	public void detectNeedAndSurply() {
		if(!team.hasTrapNeedSurply()) { return; }
		try {
			guiMain.log("Detected Need Surply, Processing...");
			system.click(imgSurply);
			
			for(int i = 0; i < 4; ++i) {
				if(traps[i].getType() != TrapType.NeedSurply) { continue; }
				system.click("img/Global/trap" + (i + 1) + ".png");
				system.click(imgSelectAll);
				guiMain.countField[i].setBackground(Color.WHITE);
				guiMain.countField[i].setText("Resting...");
				traps[i].setType(TrapType.Resting);
				guiMain.log("> Trap " + (i + 1) + " Surplied!");
			}
			system.click(imgPort);
			guiMain.log("Suply Section Processed!");
			screen.hover(anchor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
