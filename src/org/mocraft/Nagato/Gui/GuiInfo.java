package org.mocraft.Nagato.Gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.mocraft.Nagato.NagatoSystemKai;

public class GuiInfo extends NagatoSystemKai implements ActionListener {

	public JFrame frame;
	private JLabel lbl[] = new JLabel[3];
	private JButton btnConfirm = new JButton("Confirm");
	
	public GuiInfo() {
		int i = 0;
		GridBagConstraints lblGrid[] = new GridBagConstraints[3];
		frame = new JFrame("Nagato - Info");
		frame.setSize(300, 260);
		frame.setLayout(new GridBagLayout());
		
		lbl[i] = new JLabel("MOCraft Studio");
		lblGrid[i] = new GridBagConstraints();
		lblGrid[i].gridx = 0;
		lblGrid[i].gridy = 1;
		frame.add(lbl[i], lblGrid[i]);
		i++;
		
		lbl[i] = new JLabel("Build version: " + buildVersion);
		lblGrid[i] = new GridBagConstraints();
		lblGrid[i].gridx = 0;
		lblGrid[i].gridy = 2;
		frame.add(lbl[i], lblGrid[i]);
		i++;
		
		lbl[i] = new JLabel("Release version: " + officialVersion);
		lblGrid[i] = new GridBagConstraints();
		lblGrid[i].gridx = 0;
		lblGrid[i].gridy = 3;
		frame.add(lbl[i], lblGrid[i]);
		i++;
		
		btnConfirm.addActionListener(this);
		btnConfirm.setActionCommand("confirm");
		GridBagConstraints btnGrid = new GridBagConstraints();
		btnGrid.gridx = 0;
		btnGrid.gridy = 4;
		frame.add(btnConfirm, btnGrid);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("confirm")) {
			this.frame.dispose();
		}
	}
	
}
