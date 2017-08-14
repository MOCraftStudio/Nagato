package org.mocraft.NagatoKai.idle;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.utils.FleetStatus;
import org.mocraft.NagatoKai.utils.ImageData;
import org.mocraft.NagatoKai.utils.LevyData;

// 出擊
public class Attack {

    private Nagato instance;

    public Attack(Nagato instance) {
        this.instance = instance;
    }

    @Deprecated
    public void detectTargetAndSendLevy() {
        if(!instance.team.hasTrapNeedLevy()) { return; }
        try {
            instance.guiMain.logln("Detected Tesked Levy, Processing...");
            instance.system.click(ImageData.portAttack);
            instance.gameForm.wait(ImageData.levy.img(), 5.0);
            instance.system.click(ImageData.levy);
            instance.gameForm.wait(ImageData.levyI.img(), 5.0);

            for(int i = 1; i < 4; ++i) {
                int target = instance.guiMain.getGuiLevy().getTarget(i);
                if(target == 0 || !instance.guiMain.getCountField(i).getText().equals("Resting...")) { continue; }
                sectionChoose(target);
                instance.system.click("img/Attack/Levy/" + target + ".png");
                instance.system.click(ImageData.levyDecide);
                instance.gameForm.wait("img/Global/trap2-select.png", 5.0);
                instance.system.click("img/Global/trap" + (i + 1) + ".png");
                instance.system.click(ImageData.levyStartv2);
                instance.fleets[i].setStatus(FleetStatus.ScheduledLevy);
                instance.fleets[i].start(LevyData.levys[target].getTime());
                instance.guiMain.logln("> Trap " + (i + 1) +  ", Levy Target: " + LevyData.levys[target].getName());
                instance.gameForm.wait(5.0);
            }
            instance.guiMain.logln("Tesked Levy Section Processed!");
            instance.system.click(ImageData.globalPort);
            instance.gameForm.hover(instance.instance.zeroPoint);
            instance.port.processFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detectTargetAndSendLevyKai() {
        if(!instance.team.hasTrapNeedLevy()) { return; }
        try {
            instance.guiMain.logln("Detected Tesked Levy, Processing...");
            instance.system.clickWait(ImageData.portAttack, ImageData.levy, 5.0);
            instance.system.clickWait(ImageData.levy, ImageData.levyI, 5.0);

            for(int i = 1; i < 4; ++i) {
                int target = instance.guiMain.getGuiLevy().getTarget(i);
                if(target == 0 || !instance.guiMain.getCountField(i).getText().equals("Resting...")) { continue; }
                sectionChoose(target);
                instance.system.click("img/Attack/Levy/" + target + ".png");
                if(instance.system.imgExists(ImageData.levyAbandon.img())) {
                    instance.fleets[i].setStatus(FleetStatus.TaskError);
                    instance.guiMain.logln("> Fleet " + (i + 1) + ", Levy Target: " + LevyData.levys[target].getName() + " has error.");
                    continue;
                }
                instance.system.clickWait(ImageData.levyDecide, "img/Global/trap2-select.png", 5.0);
                instance.system.clickWait("img/Global/trap" + (i + 1) + ".png", ImageData.levyStartv2, 5.0);
                if(detectNeedSurplyBeforeSend()) {
                    instance.fleets[i].setStatus(FleetStatus.NeedSurply);
                    instance.guiMain.logln("> Fleet " + (i + 1) + "is missed surply, cancel task.");
                    continue;
                }
                instance.system.click(ImageData.levyStartv2);
                instance.gameForm.wait(5.0);
                instance.gameForm.wait("img/Attack/Levy/levySendedAnchor.png", 5.0);
                instance.fleets[i].setStatus(FleetStatus.ScheduledLevy);
                instance.fleets[i].start(LevyData.levys[target].getTime());
                instance.guiMain.logln("> Fleet " + (i + 1) +  ", Levy Target: " + LevyData.levys[target].getName());
            }
            instance.guiMain.logln("Tasked Levy Section Processed!");
            instance.system.clickWait(ImageData.globalPort, ImageData.portAnchor, 5.0);
            instance.gameForm.hover(instance.instance.zeroPoint);
            instance.port.processFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sectionChoose(int i) throws Exception {
        if(1 <= i && i <= 8) {
            instance.system.click(ImageData.levyI);
        } else if(9 <= i && i <= 16) {
            instance.system.click(ImageData.levyII);
        } else if(17 <= i && i <= 24) {
            instance.system.click(ImageData.levyIII);
        } else if(25 <= i && i <= 32) {
            instance.system.click(ImageData.levyIV);
        } else if(33 <= i && i <= 40) {
            instance.system.click(ImageData.levyV);
        }
        return;
    }

    private boolean detectNeedSurplyBeforeSend() throws Exception {
        if(instance.system.imgExists("img/Attack/Levy/needOil.png"))
            return true;
        else if(instance.system.imgExists("img/Attack/Levy/needOilDanger.png"))
            return true;
        else if(instance.system.imgExists("img/Attack/Levy/needBullet.png"))
            return true;
        else if(instance.system.imgExists("img/Attack/Levy/needBulletDanger.png"))
            return true;
        return false;
    }

}
