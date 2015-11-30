package org.mocraft.Nagato;

import org.mocraft.Nagato.TypeDefine.WebStatus;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;

public class NagatoSystem extends Nagato {

	private String imgCat = "img/Global/cat.png";
	private String imgTv = "img/Global/teamviewer.png";
	private String imgNonInternet = "img/Global/nonInternet.png";
	private String imgGameStart = "img/Global/gameStart.png";
	private String imgAnchor = "img/Port/anchor.png";
	private String imgPort = "img/Global/port.png";

	private String imgF5 = "img/Global/f5.png";

	public WebStatus detectWebAndFix() {
		try {
			guiMain.log("> Detecting Web Status...");
			if (imgExists(imgNonInternet)) {
				guiMain.log(">> Detected Website offline!");
				processNonInternet();
			} else if (imgExists(imgCat)) {
				guiMain.log(">> Detected Cat Error!");
				processCat();
			} else if (imgExists(imgTv)) {
				guiMain.log(">> Detected TeamViewer Form!");
				processTv();
			} else if (imgExists(imgGameStart)) {
				guiMain.log(">> Detected Game Start Form!");
				processGameStart();
			} else if (imgExists(imgAnchor) || imgExists(imgPort)) {
				guiMain.log(">> Detected Game Process Normal!");
				return WebStatus.Normal;
			} else {
				guiMain.log(">> Detected Unknown Status! Retring...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.log(">> Unknow Error Occoured! Retry...");
		}
		return detectWebAndFix();
	}

	public void cycleDetectWebAndFix() {
		try {
			guiMain.log("Cycle Dectecting Web Status...");
			if (imgExists(imgNonInternet)) {
				guiMain.log(">> Detected Website offline!");
				processNonInternet();
			} else if (imgExists(imgCat)) {
				guiMain.log(">> Detected Cat Error!");
				processCat();
			} else if (imgExists(imgTv)) {
				guiMain.log(">> Detected TeamViewer Form!");
				processTv();
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.log(">> Unknow Error Occoured! Retry...");
			cycleDetectWebAndFix();
		}
	}
	
	private void processNonInternet() throws Exception {
		guiMain.log(">>> Solving NonInternet Error...");
		while (imgExactExists(imgNonInternet)) {
			guiMain.log(">> Reflashing Web...");
			click(imgF5);
		}
		guiMain.log(">>> NonInternet Error Solved!");
	}

	private void processCat() throws Exception {
		guiMain.log(">>> Solving Cat Error...");
		while (imgExactExists(imgCat)) {
			guiMain.log(">>> Reflashing Web...");
			click(imgF5);
			if (imgExactExists(imgCat)) {
				guiMain.log(">>> Cat Error UnSolved! Retry Atfer Minute.");
				screen.wait(60);
			}
		}
		guiMain.log(">>> Cat Error Solved!");
	}

	private void processTv() throws Exception {
		guiMain.log(">>> Solving Tv Form...");
		while (imgExactExists(imgTv)) {
			guiMain.log(">>> Exiting Tv Form...");
			click("img/Global/Yes" + (imgExactExists("img/Global/Yes.png") ? "" : "-select") + ".png");
		}
		guiMain.log(">>> Tv Form Solved!");
	}

	private void processGameStart() throws Exception {
		guiMain.log(">>> Solving GameStart Form...");
		while (imgExactExists(imgGameStart)) {
			guiMain.log(">>> Entering Game...");
			click(imgGameStart);
		}
		guiMain.log(">>> GameStart Form Solved!");
	}

	public void anchorLocate() {
		try {
			guiMain.log("> Anchor Locating...");
			screen.hover(imgAnchor);
			anchor = new Location(Mouse.at());
			guiMain.log("> Anchor Located!");
		} catch (Exception e) {
			e.printStackTrace();
			guiMain.log("> Unknow Error Occoured! Retry...");
			anchorLocate();
		}
	}

	/*
	 * Override Sikuli Method.
	 * 
	 */
	public void click(String path) throws Exception {
		if (imgExists(path)) {
			screen.click(path);
		}
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
