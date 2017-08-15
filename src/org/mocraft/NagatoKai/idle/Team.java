package org.mocraft.NagatoKai.idle;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.utils.FleetStatus;
import org.mocraft.NagatoKai.utils.ImageData;
import org.mocraft.NagatoKai.utils.Loc;

// 編成
public class Team {

    private Nagato instance;
    private String imgTrap1S = "img/Global/trap1-select.png";

    public Team(Nagato instance) {
        this.instance = instance;
    }

    public void initTrapStatus() {
        try {
            instance.system.click(ImageData.portTeam);
            instance.gameForm.wait(imgTrap1S, 5.0);

            for(int i = 1; i < 5; ++i) {
                if(instance.fleets[i - 1].getStatus() != FleetStatus.Unknown)
                    continue;

                if(i != 1) {
                    Loc trap = new Loc(18, 18, 122 + 30 * (i - 1), 109, "");
                    instance.gameForm.click(trap.randomLoc());
                    instance.gameForm.wait(1.0);
                }
                if(instance.system.imgExactExists("img/Team/leving.png")) {
                    instance.guiMain.getCountField(i - 1).setText("Leving...");
                    instance.guiMain.getLblTarget(i - 1).setText("<Unknown>");
                    instance.fleets[i - 1].setStatus(FleetStatus.Leving);
                } else {
                    instance.guiMain.getCountField(i - 1).setText("Resting...");
                    instance.guiMain.getLblTarget(i - 1).setText(" ");
                    instance.fleets[i - 1].setStatus(FleetStatus.Resting);
                }
                instance.guiMain.logln(">>> Trap " + i + " Has Updated!");
            }
            instance.system.click(ImageData.globalPort);
            instance.gameForm.wait(ImageData.portTeam.img(), 5.0);
            instance.gameForm.hover(instance.zeroPoint);
        } catch (Exception e) {
            e.printStackTrace();
            instance.guiMain.logln("> Unknown Error Occurred! Retry...");
            instance.system.initor.initDetectWebAndFix();
        }
    }

    public boolean hasLeving() {
        for(int i = 1; i < 4; ++i) {
            if(instance.fleets[i].getStatus() == FleetStatus.Leving) {
                return true;
            }
        }
        return false;
    }

    public boolean hasBacked() {
        for(int i = 1; i < 4; ++i) {
            if(instance.fleets[i].getStatus() == FleetStatus.Backed) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNeedLevy() {
        for(int i = 1; i < 4; ++i) {
            if(instance.fleets[i].getStatus() == FleetStatus.Resting && instance.guiMain.getGuiLevy().getTarget(i) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNeedSurply() {
        for(int i = 0; i < 4; ++i) {
            if(instance.fleets[i].getStatus() == FleetStatus.NeedSurply) {
                return true;
            }
        }
        return false;
    }
}