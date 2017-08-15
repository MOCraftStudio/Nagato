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
        Loc[] fleetLoc = {null, ImageData.teamFleet2, ImageData.teamFleet3, ImageData.teamFleet4};
        Loc[] fleetSLoc = {null, ImageData.teamFleet2S, ImageData.teamFleet3S, ImageData.teamFleet4S};

        try {
            instance.system.clickWait(ImageData.portTeam, ImageData.teamFleet1S, 5.0);

            for(int i = 1; i < 5; ++i) {
                if(instance.fleets[i - 1].getStatus() != FleetStatus.Unknown)
                    continue;

                if(i != 1)
                    instance.system.clickWait(fleetLoc[i - 1], fleetSLoc[i - 1], 5.0);

                if(instance.system.imgExactExists(ImageData.leving)) {
                    instance.guiMain.getCountField(i - 1).setText("Leving...");
                    instance.guiMain.getLblTarget(i - 1).setText("<Unknown>");
                    instance.fleets[i - 1].setStatus(FleetStatus.Leving);
                } else {
                    instance.guiMain.getCountField(i - 1).setText("Resting...");
                    instance.guiMain.getLblTarget(i - 1).setText(" ");
                    instance.fleets[i - 1].setStatus(FleetStatus.Resting);
                }
                instance.guiMain.logln(">>> Fleet " + i + " Has Updated!");
            }
            instance.system.clickWait(ImageData.globalPort, ImageData.portTeam, 5.0);
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