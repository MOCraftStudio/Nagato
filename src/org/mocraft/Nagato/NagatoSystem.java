package org.mocraft.Nagato;

import java.util.Calendar;
import java.util.TimerTask;

import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

@Deprecated
public class NagatoSystem extends Nagato {

	public static final String buildVersion = "b040";
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
			detectWebAndFix(); // Exception has been processed. 
			
			anchorLocate(); // Exception has been processed.
			for (int i = 0; i < 4; ++i) {
				traps[i] = new Trap(i);
			}
			teamKai.initTrapStatus(); // Exception has been processed.
			guiMain.logln("Nagato Inited!");
			//guiMain.btnTesk.setEnabled(true);
		} catch (Exception e) {
			//guiMain.btnTesk.setEnabled(false);
			e.printStackTrace();
			guiMain.logln("Natato Init Failed! Retry...");
			initNagato();
		}
	}
	
	public void detectWebAndFix() {
		try {
			guiMain.logln("> Detecting Web Status...");
			if (imgExists(imgNonInternet)) {
				guiMain.logln(">> Detected Website offline!");
				processNonInternet();
			} else if (imgExists(imgCat)) {
				guiMain.logln(">> Detected Cat Error!");
				processCat();
			} else if (globalImgExists(imgTv)) {
				guiMain.logln(">> Detected TeamViewer Form!");
				processTv();
			} else if (imgExists(imgGameStart)) {
				guiMain.logln(">> Detected Game Start Form!");
				processGameStart();
			} else if (globalImgExists(imgAnchor) || imgExists(imgPort)) {
				guiMain.logln(">> Detected Game Process Normal!");
				return;
			} else {
				guiMain.logln(">> Detected Unknown Status! Retring...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.logln(">> Unknow Error Occoured! Retry...");
		}
		detectWebAndFix();
	}
	public void cycleDetectWebAndFix() {
		try {
			guiMain.logln("Cycle Dectecting Web Status...");
			if (imgExists(imgNonInternet)) {
				guiMain.logln(">> Detected Website offline!");
				processNonInternet();
			} else if (imgExists(imgCat)) {
				guiMain.logln(">> Detected Cat Error!");
				processCat();
			} else if (globalImgExists(imgTv)) {
				guiMain.logln(">> Detected TeamViewer Form!");
				processTv();
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.logln(">> Unknow Error Occoured! Retry...");
			cycleDetectWebAndFix();
		}
	}
	
	private void processNonInternet() throws Exception {
		guiMain.logln(">>> Solving NonInternet Error...");
		while (imgExactExists(imgNonInternet)) {
			guiMain.logln(">> Reflashing Web...");
			globalClick(imgF5);
		}
		guiMain.logln(">>> NonInternet Error Solved!");
	}

	private void processCat() throws Exception {
		guiMain.logln(">>> Solving Cat Error...");
		while (imgExactExists(imgCat)) {
			guiMain.logln(">>> Reflashing Web...");
			globalClick(imgF5);
			if (imgExactExists(imgCat)) {
				guiMain.logln(">>> Cat Error UnSolved! Retry Atfer Minute.");
				gameForm.wait(60);
			}
		}
		guiMain.logln(">>> Cat Error Solved!");
	}

	private void processTv() throws Exception {
		guiMain.logln(">>> Solving Tv Form...");
		while (globalImgExists(imgTv)) {
			guiMain.logln(">>> Exiting Tv Form...");
			globalClick("img/Global/Yes" + (imgExactExists("img/Global/Yes.png") ? "" : "-select") + ".png");
		}
		guiMain.logln(">>> Tv Form Solved!");
	}

	private void processGameStart() throws Exception {
		guiMain.logln(">>> Solving GameStart Form...");
		while (imgExactExists(imgGameStart)) {
			guiMain.logln(">>> Entering Game...");
			click(imgGameStart);
		}
		guiMain.logln(">>> GameStart Form Solved!");
	}

	public void anchorLocate() {
		try {
			guiMain.logln("> Anchor Locating...");
			globalScreen.hover(imgAnchor);
			zeroPoint = new Location(Mouse.at().getX() - 775, Mouse.at().getY() - 450);
			gameForm = new Region(zeroPoint.getX(), zeroPoint.getY(), 800, 480);
			gameForm.hover(zeroPoint);
			guiMain.logln("> Anchor Located!");
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.logln("> Unknow Error Occoured! Retry...");
			anchorLocate();
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
	
	/*
	 * Override Sikuli Method.
	 * 
	 */
	public void click(String path) throws Exception {
		if (imgExists(path)) {
			gameForm.click(path);
		}
	}

	public void globalClick(String path) throws Exception {
		if (globalImgExists(path)) {
			globalScreen.click(path);
		}
	}
	
	public boolean globalImgExists(String path) {
		return (globalScreen.exists(path) == null ? false : true);
	}
	
	public boolean imgExactExists(String path) {
		return (gameForm.exists(new Pattern(path).exact()) == null ? false : true);
	}

	public boolean imgExists(String path) {
		return (gameForm.exists(path) == null ? false : true);
	}

	public boolean imgExists(String path, double value) {
		return (gameForm.exists(path, value) == null ? false : true);
	}

	class MainThread extends TimerTask {	
		
		@Override
		public void run() {
			if (guiLevyTesk.getRadioSwitch() && detectSleep()) {
				guiMain.logln("Sleeping...");
			} else {
				cycleDetectWebAndFix();
				//portKai.detectFlagAndProcess();
				surply.detectNeedAndSurply();
				//portKai.detectFlagAndProcess();
				attack.detectTargetAndSendLevy();
				if (teamKai.hasTrapLeving()) {
					gameForm.wait(60.0);
				}
			}
		}
	}
}
