package org.mocraft.Nagato;

import java.util.Calendar;
import java.util.TimerTask;

import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

public class NagatoSystemKai extends Nagato {

	public static final String buildVersion = "b041";
	public static final String officialVersion = "Poi";

	private String imgCat = "img/Global/cat.png";
	private String imgTv = "img/Global/teamviewer.png";
	private String imgNonInternet = "img/Global/nonInternet.png";
	private String imgGameStart = "img/Global/gameStart.png";
	private String imgAnchor = "img/Port/anchor.png";
	private String imgPort = "img/Global/port.png";

	private String imgF5 = "img/Global/f5.png";

	public void initNagato() {
		try {
			guiMain.logln("[ Initing Nagato ]");
			guiMain.log("(1) ");
			initDetectWebAndFix();
			for (int i = 0; i < 4; ++i) {
				traps[i] = new Trap(i);
			}
			guiMain.log("(2) ");
			initAnchorLocate();
			initCheckFlag();
			guiMain.logln("(3) Loading Trap Status:");
			teamKai.initTrapStatus();
			guiMain.logln("[ Nagato  Inited ]");
			guiMain.levyTesk = true;
		} catch (Exception e) {
			guiMain.levyTesk = false;
			e.printStackTrace();
			guiMain.logln(">>> Init Failed! Retring...");
			initNagato();
		}
	}

	public void initDetectWebAndFix() {
		try {
			guiMain.log("Detecting Web Status: ");
			if (globalImgExists(imgNonInternet)) {
				guiMain.logln("Non Internet.");
				guiMain.log(">>> Processing NonInternet: ");
				processNonInternetAndCat();
			} else if (globalImgExists(imgTv)) {
				guiMain.logln("TeamViewer.");
				guiMain.log(">>> Processing Teamviewer: ");
				processTeamviewer();
			} else if (globalImgExists(imgAnchor) || globalImgExists(imgPort)) {
				guiMain.logln("Playing.");
				return;
			} else if (globalImgExists(imgGameStart)) {
				guiMain.logln("Gamestart Forum.");
				processGameStart();
			} else if (globalImgExists(imgCat)) {
				guiMain.logln("Cat");
				guiMain.log(">>> Processing Cat: ");
				processNonInternetAndCat();
			} else {
				guiMain.logln("Unknown.");
				guiMain.logln(">>> Retring...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		initDetectWebAndFix();
	}
	
	public void initAnchorLocate() {
		try {
			guiMain.log("Locating Anchor: ");
			globalScreen.hover(ImageData.portAnchor.img());
			zeroPoint = new Location(Mouse.at().getX() - 775, Mouse.at().getY() - 450);
			gameForm = new Region(zeroPoint.getX(), zeroPoint.getY(), 800, 480);
			gameForm.hover(zeroPoint);
			guiMain.logln("Located!");
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.logln("Failed.");
			guiMain.logln(">>> Retring...");
			initAnchorLocate();
		}
	}

	public void initCheckFlag() {
		try {
			portKai.detectFlag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean detectSleep() {
		Calendar time = Calendar.getInstance();
		int now = time.get(Calendar.HOUR_OF_DAY) * 3600 + time.get(Calendar.MINUTE) * 60;
		int start = guiLevyTesk.getBeginEndTime(0);
		int end = guiLevyTesk.getBeginEndTime(1);
		if(start >= end && ((now >= start && now <= 86340) || ( now >= 0 && now <= end))) {
			return true;
		} else if(end >= start && now >= start && now <= end) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean globalImgExists(String path) {
		return (globalScreen.exists(path) == null ? false : true);
	}

	public void globalClick(String path) throws Exception {
		if (globalImgExists(path)) {
			globalScreen.click(path);
		}
	}

	public void click(String path) throws Exception {
		if (imgExists(path)) {
			gameForm.click(path);
		}
	}
	
	public void click(Loc data) throws Exception {
		if(imgExists(data.img())) {
			gameForm.click(data.randomLoc());
		}
	}
	
	public boolean imgExactExists(String path) {
		return (gameForm.exists(new Pattern(path).exact()) == null ? false : true);
	}
	
	public boolean imgExists(String path) {
		return (gameForm.exists(path) == null ? false : true);
	}

	private void processNonInternetAndCat() throws Exception {
		while (globalImgExists(imgNonInternet) || globalImgExists(imgCat)) {
			globalClick(imgF5);
			if (!globalImgExists(imgNonInternet)) {
				guiMain.logln("Success!");
			}
			globalScreen.wait(10.0);
		}
		return;
	}

	private void processTeamviewer() throws Exception {
		while (globalImgExists(imgTv)) {
			globalClick("img/Global/Yes" + (imgExactExists("img/Global/Yes.png") ? "" : "-select") + ".png");
			if (!globalImgExists(imgTv)) {
				guiMain.logln("Success!");
			}
		}
		return;
	}

	private void processGameStart() throws Exception {
		while (globalImgExists(imgGameStart)) {
			globalClick(imgGameStart);
			if (!globalImgExists(imgGameStart)) {
				guiMain.logln("Success!");
			}
		}
		return;
	}

	class MainThread extends TimerTask {

		@Override
		public void run() {
			if (guiLevyTesk.getRadioSwitch() && detectSleep()) {
				guiMain.logln("[ Sleeping ]");
			} else {
				initDetectWebAndFix();
				portKai.detectFlag();
				surply.detectNeedAndSurply();
				attack.detectTargetAndSendLevy();
				if (teamKai.hasTrapLeving()) {
					gameForm.wait(60.0);
				}
			}
		}
	}
}
