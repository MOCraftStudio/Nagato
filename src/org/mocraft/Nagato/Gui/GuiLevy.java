package org.mocraft.Nagato.Gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import org.mocraft.Nagato.Nagato;

public class GuiLevy extends Nagato implements ActionListener {
	
	private JFrame frame;
	private JLabel lblTrap[] = new JLabel[4];
	private JLabel lblStart, lblEnd;
	private JComboBox<?> targetBox[] = new JComboBox[4];
	private JComboBox<?> hBox[] = new JComboBox[2];
	private JComboBox<?> mBox[] = new JComboBox[2];
	private JRadioButton timeSwitch = new JRadioButton("Enable Time");
	private JButton confirm, cancel;
	
	private static int target[] = new int[4];
	private static int beginEndTime[] = new int[2];
	private static boolean radioSwitch;

	public GuiLevy() {
		frame = new JFrame("Nagato - Levy Manager");
		frame.setSize(330, 260);
		frame.setLayout(new GridBagLayout());

		trapSection();
		timeSection();
		buttonSection();		
	}

	public void setVisible(boolean sw) {
		frame.setVisible(sw);
		return;
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
	
	private void trapSection() {
		GridBagConstraints lblTrapGrid[] = new GridBagConstraints[4];
		GridBagConstraints comboGrid[] = new GridBagConstraints[4];
		for (int i = 0; i < 4; ++i) {
			lblTrapGrid[i] = new GridBagConstraints();
			lblTrap[i] = new JLabel("Trap 0" + (i + 1));
			lblTrapGrid[i].gridx = 0;
			lblTrapGrid[i].gridy = i;
			frame.add(lblTrap[i], lblTrapGrid[i]);

			comboGrid[i] = new GridBagConstraints();
			targetBox[i] = new JComboBox<String>(LevyData.name);
			comboGrid[i].gridx = 1;
			comboGrid[i].gridy = i;
			comboGrid[i].gridwidth = 2;
			comboGrid[i].fill = GridBagConstraints.HORIZONTAL;
			comboGrid[i].anchor = GridBagConstraints.EAST;
			frame.add(targetBox[i], comboGrid[i]);
			if (i == 0) { targetBox[i].setEnabled(false); }
		}
	}

	private void timeSection() {
		timeSwitch.addActionListener(this);
		timeSwitch.setActionCommand("timeSwitch");
		GridBagConstraints rdbGrid = new GridBagConstraints();
		rdbGrid.gridx = 0;
		rdbGrid.gridy = 4;
		frame.add(timeSwitch, rdbGrid);
		
		lblStart = new JLabel("Start");
		lblStart.setEnabled(false);
		GridBagConstraints lblStartGrid = new GridBagConstraints();
		lblStartGrid.gridx = 0;
		lblStartGrid.gridy = 5;
		frame.add(lblStart, lblStartGrid);
		
		hBox[0] = new JComboBox<String>(hour);
		hBox[0].setEnabled(false);
		GridBagConstraints hStartGrid = new GridBagConstraints();
		hStartGrid.gridx = 1;
		hStartGrid.gridy = 5;
		frame.add(hBox[0], hStartGrid);
		mBox[0] = new JComboBox<String>(min);
		mBox[0].setEnabled(false);
		GridBagConstraints mStartGrid = new GridBagConstraints();
		mStartGrid.gridx = 2;
		mStartGrid.gridy = 5;
		frame.add(mBox[0], mStartGrid);
		
		lblEnd = new JLabel("End");
		lblEnd.setEnabled(false);
		GridBagConstraints lblEndGrid = new GridBagConstraints();
		lblEndGrid.gridx = 0;
		lblEndGrid.gridy = 6;
		frame.add(lblEnd, lblEndGrid);
		
		hBox[1] = new JComboBox<String>(hour);
		hBox[1].setEnabled(false);
		GridBagConstraints hEndGrid = new GridBagConstraints();
		hEndGrid.gridx = 1;
		hEndGrid.gridy = 6;
		frame.add(hBox[1], hEndGrid);
		mBox[1] = new JComboBox<String>(min);
		mBox[1].setEnabled(false);
		GridBagConstraints mEndGrid = new GridBagConstraints();
		mEndGrid.gridx = 2;
		mEndGrid.gridy = 6;
		frame.add(mBox[1], mEndGrid);
	}

	private void buttonSection() {
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setActionCommand("confirm");
		GridBagConstraints confirmGrid = new GridBagConstraints();
		confirmGrid.gridx = 0;
		confirmGrid.gridy = 7;
		//confirmGrid.fill = GridBagConstraints.HORIZONTAL;
		frame.add(confirm, confirmGrid);

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		GridBagConstraints cancelGrid = new GridBagConstraints();
		cancelGrid.gridx = 2;
		cancelGrid.gridy = 7;
		//cancelGrid.fill = GridBagConstraints.HORIZONTAL;
		frame.add(cancel, cancelGrid);
	}
}
