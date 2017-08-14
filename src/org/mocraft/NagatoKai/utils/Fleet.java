package org.mocraft.NagatoKai.utils;

import org.mocraft.NagatoKai.Nagato;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fleet {

    private Nagato instance;
    private Timer timer;
    private final int id;
    private String name;
    private int time;
    private FleetStatus status;

    public Fleet(Nagato instance, int id) {
        this.instance = instance;
        this.id = id;
        this.status = FleetStatus.Unknown;
    }

    public void start(int _time) {
        time = _time;
        timer = new Timer();
        timer.schedule(new Count(), 0, 1000);
    }
    public FleetStatus getStatus() { return status;}

    public void setStatus(FleetStatus s) { status = s; }

    public void cancel() {
        if(status == FleetStatus.Backed || status == FleetStatus.Leving || timer == null) { return; }
        timer.cancel();
    }

    private String formatTime(int t) {
        String h = String.format("%02d", t / 3600);
        String m = String.format("%02d", (t % 3600) / 60);
        String s = String.format("%02d", (t % 3600) % 60);
        return h + "時間" + m + "分" + s + "秒";
    }

    class Count extends TimerTask {

        @Override
        public void run() {
            if(time > 0) {
                instance.guiMain.getCountField(id).setBackground(Color.WHITE);
                instance.guiMain.getCountField(id).setText(formatTime(time));
                status = FleetStatus.ScheduledLevy;
                time--;
            } else {
                instance.guiMain.getCountField(id).setBackground(Color.ORANGE);
                instance.guiMain.getCountField(id).setText("Backed");
                status = FleetStatus.Backed;
                timer.cancel();
            }
        }
    }

}
