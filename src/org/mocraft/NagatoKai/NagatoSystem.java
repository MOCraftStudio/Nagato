package org.mocraft.NagatoKai;

import org.mocraft.NagatoKai.utils.Fleet;
import org.mocraft.NagatoKai.utils.ImageData;
import org.mocraft.NagatoKai.utils.Loc;
import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.util.Calendar;
import java.util.TimerTask;

public class NagatoSystem {

    // Software Statics
    public static final String officialVersion = "PoiKai";
    public static final String buildVersion = "build0.4.2";

    private Nagato instance;
    private initor initor;
    private processor processor;

    // Static Images
    private String imgCat = "img/Global/cat.png";
    private String imgTv = "img/Global/teamviewer.png";
    private String imgNonInternet = "img/Global/nonInternet.png";
    private String imgGameStart = "img/Global/gameStart.png";
    private String imgAnchor = "img/Port/anchor.png";
    private String imgPort = "img/Global/port.png";
    private String imgF5 = "img/Global/f5.png";

    public NagatoSystem(Nagato instance) {
        this.instance = instance;
        this.initor = new initor();
        this.processor = new processor();
    }

    public void initNagato() {
        try {
            instance.guiMain.setLevyTask(false);
            instance.guiMain.logln("[ Initing Nagato ]");
            instance.guiMain.log("(1) ");
            initor.initDetectWebAndFix();
            for (int i = 0; i < 4; ++i)
                instance.fleets[i] = new Fleet(instance, i);

            instance.guiMain.log("(2) ");
            initor.initAnchorLocate();
            initor.initCheckFlag();

            instance.guiMain.logln("(3) Loading Trap Status:");
            instance.team.initTrapStatus();

            instance.guiMain.logln("[ Nagato  Inited ]");
            instance.guiMain.setLevyTask(true);
        } catch (Exception e) {
            e.printStackTrace();
            instance.guiMain.logln(">>> Init Failed! Retring...");
            initNagato();
        }
    }

    public boolean detectSleep() {
        Calendar time = Calendar.getInstance();
        int now = time.get(Calendar.HOUR_OF_DAY) * 3600 + time.get(Calendar.MINUTE) * 60;
        int start = instance.guiMain.getGuiLevy().getBeginEndTime(0);
        int end = instance.guiMain.getGuiLevy().getBeginEndTime(1);
        if (start >= end && ((now >= start && now <= 86340) || (now >= 0 && now <= end))) {
            return true;
        } else if (end >= start && now >= start && now <= end) {
            return true;
        } else {
            return false;
        }
    }

    public void globalClick(String path) throws Exception {
        if (globalImgExists(path)) {
            instance.globalScreen.click(path);
        }
    }

    public void click(String path) throws Exception {
        if (imgExists(path)) {
            instance.gameForm.click(path);
        }
    }

    public void click(Loc data) throws Exception {
        if(imgExists(data.img())) {
            instance.gameForm.click(data.randomLoc());
        }
    }

    public void clickWait(Loc clickWho, Loc waitWho, double timeout) throws Exception {
        if(imgExists(clickWho.img())) {
            instance.gameForm.click(clickWho.randomLoc());
        }
        instance.gameForm.wait(waitWho.img(), timeout);
    }

    public void clickWait(String clickWho, Loc waitWho, double timeout) throws Exception {
        if(imgExists(clickWho)) {
            instance.gameForm.click(clickWho);
        }
        instance.gameForm.wait(waitWho.img(), timeout);
    }

    public void clickWait(Loc clickWho, String waitWho, double timeout) throws Exception {
        if(imgExists(clickWho.img())) {
            instance.gameForm.click(clickWho.randomLoc());
        }
        instance.gameForm.wait(waitWho, timeout);
    }

    public void clickWait(String clickWho, String waitWho, double timeout) throws Exception {
        if(imgExists(clickWho)) {
            instance.gameForm.click(clickWho);
        }
        instance.gameForm.wait(waitWho, timeout);
    }

    public boolean globalImgExists(String path) { return (instance.globalScreen.exists(path) == null ? false : true); }

    public boolean imgExists(String path) {
        return (instance.gameForm.exists(path) == null ? false : true);
    }

    public boolean imgExactExists(String path) {
        return (instance.gameForm.exists(new Pattern(path).exact()) == null ? false : true);
    }

    class initor {

        public void initDetectWebAndFix() {
            try {
                instance.guiMain.log("Detecting Web Status: ");
                if (globalImgExists(imgNonInternet)) {
                    instance.guiMain.logln("Non Internet.");
                    instance.guiMain.log(">>> Processing NonInternet: ");
                    processor.processNonInternetAndCat();
                } else if (globalImgExists(imgTv)) {
                    instance.guiMain.logln("TeamViewer.");
                    instance.guiMain.log(">>> Processing Teamviewer: ");
                    processor.processTeamviewer();
                } else if (globalImgExists(imgAnchor)) {
                    instance.guiMain.logln("Playing.");
                    return;
                } else if(globalImgExists(imgPort)) {
                    instance.guiMain.logln("Ingame Forum.");
                    instance.guiMain.log(">>> Processing Redirect to Port: ");
                    processor.processInGame();
                } else if (globalImgExists(imgGameStart)) {
                    instance.guiMain.logln("Gamestart Forum.");
                    instance.guiMain.log(">>> Processing Redirect to Port: ");
                    processor.processGameStart();
                } else if (globalImgExists(imgCat)) {
                    instance.guiMain.logln("Cat");
                    instance.guiMain.log(">>> Processing Cat: ");
                    processor.processNonInternetAndCat();
                } else {
                    instance.guiMain.logln("Unknown.");
                    instance.guiMain.logln(">>> Retring...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            initDetectWebAndFix();
        }

        public void initAnchorLocate() {
            try {
                instance.guiMain.log("Locating Anchor: ");
                instance.globalScreen.hover(ImageData.portAnchor.img());
                instance.zeroPoint = new Location(Mouse.at().getX() - 775, Mouse.at().getY() - 450);
                instance.gameForm = new Region(instance.zeroPoint.getX(), instance.zeroPoint.getY(), 800, 480);
                instance.gameForm.hover(instance.zeroPoint);
                instance.guiMain.logln("Located!");
            } catch (Exception e) {
                e.printStackTrace();
                instance.guiMain.logln("Failed.");
                instance.guiMain.logln(">>> Retring...");
                initAnchorLocate();
            }
        }

        public void initCheckFlag() {
            try {
                instance.port.detectFlag();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    class processor {

        private void processNonInternetAndCat() throws Exception {
            globalClick(imgF5);
            instance.globalScreen.wait(10.0);
            if (!globalImgExists(imgNonInternet))
                instance.guiMain.logln("Success!");
            initor.initDetectWebAndFix();
        }

        private void processTeamviewer() throws Exception {
            globalClick("img/Global/Yes" + (imgExactExists("img/Global/Yes.png") ? "" : "-select") + ".png");
            instance.globalScreen.wait(10.0);

            if (!globalImgExists(imgTv))
                instance.guiMain.logln("Success!");
        }

        private void processInGame() throws Exception {
            globalClick(imgPort);
            instance.globalScreen.wait(10.0);

            if(!globalImgExists(imgPort))
                instance.guiMain.logln("Success!");
        }

        private void processGameStart() throws Exception {
            instance.globalScreen.wait(10.0);
            globalClick(imgGameStart);

            if (!globalImgExists(imgGameStart))
                instance.guiMain.logln("Success!");
        }

    }

    class MainThread extends TimerTask {

        @Override
        public void run() {
            if (instance.guiMain.getGuiLevy().getRadioSwitch() && detectSleep()) {
                instance.guiMain.logln("[ Sleeping ]");
            } else {
                initor.initDetectWebAndFix();
                instance.port.detectFlag();
                instance.surply.detectNeedAndSurply();
                //instance.attack.detectTargetAndSendLevy();
                instance.attack.detectTargetAndSendLevyKai();
                if (instance.team.hasTrapLeving()) {
                    instance.gameForm.wait(60.0);
                }
            }
        }

    }
}
