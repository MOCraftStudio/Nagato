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
		if(!teamKai.hasTrapNeedSurply()) { return; }
		try {
			guiMain.logln("Detected Need Surply, Processing...");
			systemKai.click(ImageData.portSurply);
			gameForm.wait(ImageData.surplySelectAll.img());
			
			for(int i = 0; i < 4; ++i) {
				if(traps[i].getType() != TrapType.NeedSurply) { continue; }
				systemKai.click("img/Global/trap" + (i + 1) + ".png");
				systemKai.click(ImageData.surplySelectAll);
				guiMain.countField[i].setBackground(Color.WHITE);
				guiMain.countField[i].setText("Resting...");
				traps[i].setType(TrapType.Resting);
				guiMain.logln("> Trap " + (i + 1) + " Surplied!");
				gameForm.wait(1.0);
			}
			systemKai.click(ImageData.globalPort);
			gameForm.wait(ImageData.portAnchor.img());
			guiMain.logln("Suply Section Processed!");
			gameForm.hover(zeroPoint);
			portKai.processFlag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
