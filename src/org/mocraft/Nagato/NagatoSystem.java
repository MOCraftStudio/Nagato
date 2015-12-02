package org.mocraft.Nagato;

import javax.swing.JTextArea;

import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;

public class NagatoSystem extends Nagato {

	public boolean checkCat() {
		try {
			log(guiMain.logArea, "Checking Status...");
			if (imgExists("img/Global/cat.png") || imgExists("img/Global/nonInternet.png")) {
				log(guiMain.logArea, "Detected Cat Error...");
				click("img/Global/f5.png");
				return checkCat();
			} else if(imgExists("img/Global/gameStart.png")) {
				log(guiMain.logArea, "Entering Game...");
				click("img/Global/gameStart.png");
				return false;
			} else if(imgExists("img/Port/anchor.png")) {
				log(guiMain.logArea, "Entered Game!");
				return false;
			} else if(imgExists("img/Global/port.png")) {
				log(guiMain.logArea, "Redirecting To Port...");
				click("img/Global/port.png");
				return false;
			} else {
				log(guiMain.logArea, "Connected Failed, Retry...");
				return checkCat();
			}
		} catch (Exception e) {
			checkCat();
		}
		return true;
	}

	public boolean checkAnchor() throws Exception {
		log(guiMain.logArea, "Locating Anchor...");
		if(imgExists("img/Port/anchor.png")) {
			screen.hover("img/Port/anchor.png");
			anchor = new Location(Mouse.at());
			log(guiMain.logArea, "Anchor Located!");
			return true;
		} else {
			log(guiMain.logArea, "Unable Locate Anchor, Retry...");
			return checkAnchor();
		}
	}
	
	public void log(JTextArea area, String msg) {
		area.setText(area.getText() + msg + "\n");
	}

	public void click(String path) throws Exception {
		if (imgExists(path))
			screen.click(path);
	}

	public boolean imgExactExists(String path) {
		return (screen.exists(new Pattern(path).exact()) == null ? false : true);
	}
	
	public boolean imgExists(String path) {
		return (screen.exists(path) == null ? false : true);
	}
	
	public boolean imgExists(String path, double value) {
		return (screen.exists(path, value) == null ? false : true);
	}

}
