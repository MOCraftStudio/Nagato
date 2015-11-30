package org.mocraft.Nagato.Port;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TypeDefine.TrapType;
import org.mocraft.Nagato.Gui.GuiTesk;
import org.mocraft.Nagato.Gui.LevyData;

public class Attack extends Nagato {

	private String imgAttack = "img/Port/attack.png";
	private String imgLevy = "img/Attack/levy.png";
	private String imgDecide = "img/Attack/Levy/decide.png";
	private String imgStart = "img/Attack/Levy/start.png";
	private String imgPort = "img/Global/port.png";
	
	public void detectTargetAndSendLevy() {
		if(!team.hasTrapNeedLevy()) { return; }
		try {
			guiMain.log("Detected Tesked Levy, Processing...");
			system.click(imgAttack);
			system.click(imgLevy);
			
			for(int i = 1; i < 4; ++i) {
				int target = GuiTesk.target[i];
				if(target == 0 || !guiMain.countField[i].getText().equals("Resting...")) { continue; }
				sectionChoose(target);
				system.click("img/Attack/Levy/" + target + ".png");
				system.click(imgDecide);
				system.click("img/Global/trap" + (i + 1) + ".png");
				system.click(imgStart);
				//guiMain.countField[i].setText("Leving...");
				traps.get(i).setType(TrapType.ScheduledLeving);
				traps.get(i).start(LevyData.time[target]);
				guiMain.log("> Trap " + (i + 1) +  ", Levy Target: " + LevyData.name[target]);
				screen.wait(5.0);
			}
			guiMain.log("Tesked Levy Section Processed!");
			system.click(imgPort);
			screen.hover(anchor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sectionChoose(int i) throws Exception {
		if(1 <= i && i <= 8) {
			system.click("img/Attack/Levy/I.png");
		} else if(9 <= i && i <= 16) {
			system.click("img/Attack/Levy/II.png");
		} else if(17 <= i && i <= 24) {
			system.click("img/Attack/Levy/III.png");
		} else if(25 <= i && i <= 32) {
			system.click("img/Attack/Levy/IV.png");
		} else if(33 <= i && i <= 40) {
			system.click("img/Attack/Levy/V.png");
		}
		return;
	}
	
}
