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
	
	public void detectTargetAndSendLevy() {
		if(!team.hasTrapNeedLevy()) { return; }
		try {
			guiMain.log("Detected Tesked Levy, Processing...");
			gameForm.click(ImageData.portAttack.randomLoc());
			gameForm.wait(ImageData.levy.img(), 5);
			gameForm.click(ImageData.levy.randomLoc());
			gameForm.wait(ImageData.levyI.img(), 5);
			
			for(int i = 1; i < 4; ++i) {
				int target = guiTesk.getTarget(i);
				if(target == 0 || !guiMain.countField[i].getText().equals("Resting...")) { continue; }
				sectionChoose(target);
				gameForm.click("img/Attack/Levy/" + target + ".png");
				gameForm.click(ImageData.levyDecide.randomLoc());
				gameForm.click("img/Global/trap" + (i + 1) + ".png");
				gameForm.click(ImageData.levyStart.randomLoc());
				traps[i].setType(TrapType.ScheduledLeving);
				traps[i].start(LevyData.time[target]);
				guiMain.log("> Trap " + (i + 1) +  ", Levy Target: " + LevyData.name[target]);
				gameForm.wait(5.0);
			}
			guiMain.log("Tesked Levy Section Processed!");
			gameForm.click(ImageData.globalPort.randomLoc());
			gameForm.hover(zeroPoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sectionChoose(int i) throws Exception {
		if(1 <= i && i <= 8) {
			gameForm.click(ImageData.levyI.randomLoc());
		} else if(9 <= i && i <= 16) {
			gameForm.click(ImageData.levyII.randomLoc());
		} else if(17 <= i && i <= 24) {
			gameForm.click(ImageData.levyIII.randomLoc());
		} else if(25 <= i && i <= 32) {
			gameForm.click(ImageData.levyIV.randomLoc());
		} else if(33 <= i && i <= 40) {
			gameForm.click(ImageData.levyV.randomLoc());
		}
		return;
	}
	
}
