package org.mocraft.NagatoKai.idle;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.utils.FleetStatus;
import org.mocraft.NagatoKai.utils.ImageData;

import java.awt.*;

// 補給
public class Surply {

    private Nagato instance;

    public Surply(Nagato instance) {
        this.instance = instance;
    }

    public void detectNeedAndSurply() {
        if(!instance.team.hasNeedSurply()) { return; }
        try {
            instance.guiMain.logln("Detected Need Surply, Processing...");
            instance.system.click(ImageData.portSurply);
            instance.gameForm.wait(ImageData.surplySelectAll.img(), 5.0);

            for(int i = 0; i < 4; ++i) {
                if(instance.fleets[i].getStatus() != FleetStatus.NeedSurply) { continue; }
                instance.system.click("img/Global/trap" + (i + 1) + ".png");
                instance.system.click(ImageData.surplySelectAll);
                instance.guiMain.getCountField(i).setBackground(Color.WHITE);
                instance.guiMain.getCountField(i).setText("Resting...");
                instance.fleets[i].setStatus(FleetStatus.Resting);
                instance.guiMain.logln("> Trap " + (i + 1) + " Surplied!");
                instance.gameForm.wait(1.0);
            }
            instance.system.click(ImageData.globalPort);
            instance.gameForm.wait(ImageData.portAnchor.img(), 5.0);
            instance.guiMain.logln("Suply Section Processed!");
            instance.gameForm.hover(instance.zeroPoint);
            instance.port.processFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
