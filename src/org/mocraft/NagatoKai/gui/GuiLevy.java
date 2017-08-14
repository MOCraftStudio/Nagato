package org.mocraft.NagatoKai.gui;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.utils.LevyData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiLevy extends GuiConstructor implements ActionListener {

    private Nagato instance;
    private static int target[] = new int[4];
    private static int beginEndTime[] = new int[2];
    private static boolean radioSwitch;
    // Components
    private JFrame frame;
    private JLabel lblFleet[] = new JLabel[4];
    private JComboBox<?> targetBox[] = new JComboBox[4];
    private JRadioButton timeSwitch = new JRadioButton("Enable Time");
    private JLabel lblStart, lblEnd;
    private JComboBox<?> hBox[] = new JComboBox[2];
    private JComboBox<?> mBox[] = new JComboBox[2];
    private JButton confirm, cancel;

    public GuiLevy(Nagato instance) {
        this.instance = instance;
        frame = new JFrame("Nagato - Levy Manager");
        frame.setSize(330, 260);
        frame.setLayout(new GridBagLayout());

        fleetSection();
        timeSection();
        buttonSection();
    }

    public void setVisible(boolean sw) {
        frame.setVisible(sw);
    }

    public int getTarget(int i) { return target[i]; }

    public int getBeginEndTime(int i) { return beginEndTime[i]; }

    public boolean getRadioSwitch() { return radioSwitch; }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("confirm")) {
            for(int i = 0; i < 4; ++i) {
                target[i] = targetBox[i].getSelectedIndex();
            }
            beginEndTime[0] = hBox[0].getSelectedIndex() * 3600 + mBox[0].getSelectedIndex() * 60;
            beginEndTime[1] = hBox[1].getSelectedIndex() * 3600 + mBox[1].getSelectedIndex() * 60;
            radioSwitch = timeSwitch.isSelected();
        } else if(cmd.equals("timeSwitch")) {
            lblStart.setEnabled(!lblStart.isEnabled());
            hBox[0].setEnabled(!hBox[0].isEnabled());
            mBox[0].setEnabled(!mBox[0].isEnabled());
            lblEnd.setEnabled(!lblEnd.isEnabled());
            hBox[1].setEnabled(!hBox[1].isEnabled());
            mBox[1].setEnabled(!mBox[1].isEnabled());
            return;
        }
        frame.dispose();
    }

    private final static String hour[] = {
            "0",	"1",	"2",	"3",	"4",	"5",	"6",	"7",	"8",	"9",
            "10",	"11",	"12",	"13",	"14",	"15",	"16",	"17",	"18",	"19",
            "20",	"21",	"22",	"23"
    };
    private final static String min[] = {
            "0",	"1",	"2",	"3",	"4",	"5",	"6",	"7",	"8",	"9",
            "10",	"11",	"12",	"13",	"14",	"15",	"16",	"17",	"18",	"19",
            "20",	"21",	"22",	"23",	"24",	"25",	"26",	"27",	"28",	"29",
            "30",	"31",	"32",	"33",	"34",	"35",	"36",	"37",	"38",	"39",
            "40",	"41",	"42",	"43",	"44",	"45",	"46",	"47",	"48",	"49",
            "50",	"51",	"52",	"53",	"54",	"55",	"56",	"57",	"58",	"59"
    };

    private void fleetSection() {
        for (int i = 0; i < 4; ++i) {
            lblFleet[i] = new JLabel("Trap 0" + (i + 1));
            GridBagConstraints fleetGrid = setComponent(null, 0, i);
            frame.add(lblFleet[i], fleetGrid);

            targetBox[i] = new JComboBox<String>(LevyData.name);
            GridBagConstraints comboGrid = setComponent(1, i, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.EAST);
            frame.add(targetBox[i], comboGrid);
        }
        targetBox[0].setEnabled(false);
    }

    private void timeSection() {
        timeSwitch.addActionListener(this);
        timeSwitch.setActionCommand("timeSwitch");
        GridBagConstraints tsGrid = setComponent(null, 0, 4);
        frame.add(timeSwitch, tsGrid);

        lblStart = new JLabel("Start");
        lblStart.setEnabled(false);
        GridBagConstraints lblStartGrid = setComponent(null, 0, 5);
        frame.add(lblStart, lblStartGrid);

        hBox[0] = new JComboBox<>(hour);
        hBox[0].setEnabled(false);
        GridBagConstraints hStartGrid = setComponent(null, 1, 5);
        frame.add(hBox[0], hStartGrid);
        mBox[0] = new JComboBox<>(min);
        mBox[0].setEnabled(false);
        GridBagConstraints mStartGrid = setComponent(null, 2, 5);
        frame.add(mBox[0], mStartGrid);

        lblEnd = new JLabel("End");
        lblEnd.setEnabled(false);
        GridBagConstraints lblEndGrid = setComponent(null, 0, 6);
        frame.add(lblEnd, lblEndGrid);

        hBox[1] = new JComboBox<>(hour);
        hBox[1].setEnabled(false);
        GridBagConstraints hEndGrid = setComponent(null, 1, 6);
        frame.add(hBox[1], hEndGrid);
        mBox[1] = new JComboBox<>(min);
        mBox[1].setEnabled(false);
        GridBagConstraints mEndGrid = setComponent(null, 2, 6);
        frame.add(mBox[1], mEndGrid);
    }

    private void buttonSection() {
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setActionCommand("confirm");
        GridBagConstraints confirmGrid = setComponent(null, 0, 7);
        frame.add(confirm, confirmGrid);

        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        cancel.setActionCommand("cancel");
        GridBagConstraints cancelGrid = setComponent(null, 2, 7);
        frame.add(cancel, cancelGrid);
    }

    public GridBagConstraints setComponent(int gridX, int grixY, int gridWidth, int fill, int anchor) {
        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = gridX;
        grid.gridy = grixY;
        return grid;
    }

}
