package org.mocraft.NagatoKai;

import org.mocraft.NagatoKai.gui.GuiMain;
import org.mocraft.NagatoKai.idle.Attack;
import org.mocraft.NagatoKai.idle.Port;
import org.mocraft.NagatoKai.idle.Surply;
import org.mocraft.NagatoKai.idle.Team;
import org.mocraft.NagatoKai.utils.Fleet;
import org.sikuli.script.Location;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import java.util.Timer;

public class Nagato {

    public static Screen globalScreen = new Screen();
    public static Region gameForm = new Region(0, 0, 800, 480);
    public static Location zeroPoint = new Location(0, 0);
    public static Fleet fleets[] = new Fleet[4];

    public static Nagato instance;
    public static NagatoSystem system;
    public static Port port;
    public static Team team;
    public static Surply surply;
    public static Attack attack;
    public static GuiMain guiMain;

    private static Timer tick = new Timer();

    public static void main(String[] args) {
        instance = new Nagato();
        system = new NagatoSystem(instance);
        port = new Port(instance);
        team = new Team(instance);
        surply = new Surply(instance);
        attack = new Attack(instance);
        guiMain = new GuiMain(instance);

        system.initNagato();
        tick.schedule(system.new MainThread(), 0, 5000);
    }

}
