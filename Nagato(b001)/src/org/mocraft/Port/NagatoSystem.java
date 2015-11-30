package org.mocraft.Port;

import java.util.Date;

import javax.swing.JTextArea;

import org.mocraft.Nagato.Nagato;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class NagatoSystem extends Nagato {

	public boolean checkCatAndTv() {
		try {
			if (myScreen.exists("img/Global/cat.png") != null) {
				processCat(myScreen);
			} else if (myScreen.exists("img/Global/teamviewer.png") != null) {
				processTv(myScreen);
			} else {
				return false;
			}
		} catch (Exception e) {
			checkCatAndTv();
		}
		return true;
	}

	private void processCat(Screen s) throws Exception {
		while (imgExists("img/Global/cat.png") || imgExists("img/Global/nonInternet.png")) {
			myScreen.click("img/Global/f5.png");
			wait(5);
		}
	}

	private void processTv(Screen s) throws Exception {
		if (imgExists("img/Global/teamviewer.png")) {
			if (imgExists("img/Global/yes.png")) {
				s.click("img/Global/yes.png");
			} else {
				s.click("img/Global/yes-select.png");
			}
		}
	}

	public void logLine(JTextArea area, String text) {
		area.setText(area.getText() + "[" + getTime() + "]" + text + "\n");
	}
	
	@SuppressWarnings("deprecation")
	public String getTime() {
		Date now = new Date();
		return (now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds()).toString();
	}
	
	public boolean imgExists(String path) {
		return (myScreen.exists(path) == null ? false : true);
	}
	
	public boolean imgExists(String path, float offset) {
		return (myScreen.exists(new Pattern(path).similar(offset)) == null ? false : true);
	}
	
	public boolean imgExactExists(String path) {
		return (myScreen.exists(new Pattern(path).exact()) == null ? false : true);
	}
}
