package org.mocraft.Port;

import java.util.ArrayList;

import org.mocraft.Nagato.Nagato;
import org.mocraft.Nagato.TrapType;

public class Port extends Nagato {

	public void processFlag() throws Exception {
		if (!system.imgExactExists("img/Port/flag.png")) {
			system.click("img/Port/team.png");
			system.click("img/Global/port.png");
			return;
		}
		ArrayList<Integer> needSurply = new ArrayList<Integer>();
		checkFlag(needSurply);
		system.log(guiMain.logArea, "Flag Processed!");
		
		int[] surplyArray = new int[needSurply.size()];
		for (int i = 0; i < needSurply.size(); ++i) {
			surplyArray[i] = needSurply.get(i);
		}
		for (int i = 0; i < needSurply.size(); ++i) {
			int index = needSurply.get(i);
			traps[index].setType(TrapType.Rest);
			System.out.println(index);
			guiMain.countField[index].setText("Resting...");
			surply.surply(traps[index]);
		}
		return;
	}

	public ArrayList<Integer> checkFlag(ArrayList<Integer> needSurply) throws Exception {
		if (system.imgExactExists("img/Port/flag.png")) {
			system.log(guiMain.logArea, "Processing Flag...");
			system.click("img/Port/flag.png");

			if (system.imgExactExists("img/Port/trap2-name.png")) {
				screen.hover("img/Port/trap2-name.png");
				needSurply.add(1);
			} else if (system.imgExactExists("img/Port/trap3-name.png")) {
				screen.hover("img/Port/trap3-name.png");
				needSurply.add(2);
			} else if (system.imgExactExists("img/Port/trap4-name.png")) {
				screen.hover("img/Port/trap4-name.png");
				needSurply.add(3);
			}
			system.click("img/Port/next.png");
			system.click("img/Port/next.png");
			return checkFlag(needSurply);
		} else {
			return needSurply;
		}
	}

}
