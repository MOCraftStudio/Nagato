package org.mocraft.Nagato.Gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.mocraft.Nagato.Nagato;

public class GuiTesk extends Nagato implements ActionListener {

	public JFrame frame;
	public JLabel lblTrap[] = new JLabel[4];
	public static JComboBox<?> targetBox[] = new JComboBox[4];
	public JButton confirm, cancel;
	
	public static int target[] = new int[4];

	public GuiTesk() {
		GridBagConstraints lblTrapGrid[] = new GridBagConstraints[4];
		GridBagConstraints comboGrid[] = new GridBagConstraints[4];
		GridBagConstraints confirmGrid = new GridBagConstraints();
		GridBagConstraints cancelGrid = new GridBagConstraints();
		frame = new JFrame("Nagato - Tesk Manager");
		frame.setSize(300, 180);
		frame.setLayout(new GridBagLayout());

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
			comboGrid[i].fill = GridBagConstraints.HORIZONTAL;
			comboGrid[i].anchor = GridBagConstraints.EAST;
			frame.add(targetBox[i], comboGrid[i]);
			if (i == 0) { targetBox[i].setEnabled(false); }
		}

		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirm.setActionCommand("confirm");
		confirmGrid.gridx = 0;
		confirmGrid.gridy = 4;
		confirmGrid.fill = GridBagConstraints.HORIZONTAL;
		frame.add(confirm, confirmGrid);

		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");
		cancelGrid.gridx = 1;
		cancelGrid.gridy = 4;
		cancelGrid.fill = GridBagConstraints.HORIZONTAL;
		frame.add(cancel, cancelGrid);
	}

	public void setVisible(boolean sw) {
		frame.setVisible(sw);
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("confirm")) {
			for(int i = 0; i < 4; ++i) {
				target[i] = targetBox[i].getSelectedIndex();
			}
		}
		frame.dispose();
	}

}
