package org.mocraft.NagatoKai.idle;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.utils.FleetStatus;
import org.mocraft.NagatoKai.utils.ImageData;

import java.awt.*;

// 母港
public class Port {

    private Nagato instance;
    private String imgFlag = "img/Port/flag.png";
    private String imgTrap2N = "img/Port/trap2-name.png";
    private String imgTrap3N = "img/Port/trap3-name.png";
    private String imgTrap4N = "img/Port/trap4-name.png";
    private String imgNext = "img/Port/next.png";

    public Port(Nagato instance) {
        this.instance = instance;
    }

    public void detectFlag() {
        try {
            instance.guiMain.log("(*) Detecting Flag: ");
            if ((instance.team.hasLeving() || instance.team.hasBacked()) && !instance.system.imgExactExists(imgFlag)) {
                instance.guiMain.logln("None.");
                instance.system.clickWait(ImageData.portTeam, ImageData.globalPort, 5.0);
                instance.system.clickWait(ImageData.globalPort, ImageData.portTeam, 5.0);
                instance.gameForm.hover(instance.zeroPoint);
                return;
            } else if(!instance.system.imgExactExists(imgFlag)) {
                instance.guiMain.logln("None.");
                return;
            }
            instance.guiMain.logln("Detected!");
            processFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processFlag() {
        if (!instance.system.imgExactExists(imgFlag)) { return; }
        try {
            instance.system.clickWait(imgFlag, imgNext, 60.0);
            if (instance.system.imgExactExists(imgTrap2N)) {
                instance.fleets[1].cancel();
                instance.guiMain.logln(">>> Trap 2 Detected!");
                instance.guiMain.getCountField(1).setText("Need Surply!");
                instance.guiMain.getCountField(1).setBackground(Color.RED);
                instance.fleets[1].setStatus(FleetStatus.NeedSurply);
            } else if (instance.system.imgExactExists(imgTrap3N)) {
                instance.fleets[2].cancel();
                instance.guiMain.logln(">>> Trap 3 Detected!");
                instance.guiMain.getCountField(2).setText("Need Surply!");
                instance.guiMain.getCountField(2).setBackground(Color.RED);
                instance.fleets[2].setStatus(FleetStatus.NeedSurply);
            } else if (instance.system.imgExactExists(imgTrap4N)) {
                instance.fleets[3].cancel();
                instance.guiMain.logln(">>> Trap 4 Detected!");
                instance.guiMain.getCountField(3).setText("Need Surply!");
                instance.guiMain.getCountField(3).setBackground(Color.RED);
                instance.fleets[3].setStatus(FleetStatus.NeedSurply);
            }
            instance.gameForm.wait(imgNext, 10.0);
            instance.system.clickWait(imgNext, imgNext, 10.0);
            instance.system.clickWait(imgNext, ImageData.portAttack.img(),5.0);
            processFlag();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
