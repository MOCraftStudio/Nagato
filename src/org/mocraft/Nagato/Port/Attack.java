package org.mocraft.Nagato.Port;

import org.mocraft.Nagato.ImageData;
import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;
import org.mocraft.Nagato.Gui.LevyData;

public class Attack extends Nagato {

	//private String imgAttack = "img/Port/attack.png";
	//private String imgLevy = "img/Attack/levy.png";
	//private String imgDecide = "img/Attack/Levy/decide.png";
	//private String imgStart = "img/Attack/Levy/start.png";
	//private String imgPort = "img/Global/port.png";
	
	public void detectTrapAndSendExercise() {
		
	}
	
	
	public void detectTargetAndSendLevy() {
		if(!teamKai.hasTrapNeedLevy()) { return; }
		try {
			guiMain.logln("Detected Tesked Levy, Processing...");
			systemKai.click(ImageData.portAttack);
			gameForm.wait(ImageData.levy.img(), 5.0);
			systemKai.click(ImageData.levy);
			gameForm.wait(ImageData.levyI.img(), 5.0);
			
			for(int i = 1; i < 4; ++i) {
				int target = guiLevyTesk.getTarget(i);
				if(target == 0 || !guiMain.countField[i].getText().equals("Resting...")) { continue; }
				sectionChoose(target);
				systemKai.click("img/Attack/Levy/" + target + ".png");
				systemKai.click(ImageData.levyDecide);
				systemKai.click("img/Global/trap" + (i + 1) + ".png");
				systemKai.click(ImageData.levyStart);
				traps[i].setType(TrapType.ScheduledLeving);
				//traps[i].start(LevyData.time[target]);
				traps[i].start(LevyData.levys[target].getTime());
				//guiMain.targetLbl[i].setText(LevyData.name[target]);
				//guiMain.logln("> Trap " + (i + 1) +  ", Levy Target: " + LevyData.name[target]);
				guiMain.logln("> Trap " + (i + 1) +  ", Levy Target: " + LevyData.levys[target].getName());
				gameForm.wait(5.0);
			}
			guiMain.logln("Tesked Levy Section Processed!");
			systemKai.click(ImageData.globalPort);
			gameForm.hover(zeroPoint);
			portKai.processFlag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void sectionChoose(int i) throws Exception {
		if(1 <= i && i <= 8) {
			systemKai.click(ImageData.levyI);
		} else if(9 <= i && i <= 16) {
			systemKai.click(ImageData.levyII);
		} else if(17 <= i && i <= 24) {
			systemKai.click(ImageData.levyIII);
		} else if(25 <= i && i <= 32) {
			systemKai.click(ImageData.levyIV);
		} else if(33 <= i && i <= 40) {
			systemKai.click(ImageData.levyV);
		}
		return;
	}
	
}
