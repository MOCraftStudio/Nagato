package org.mocraft.Gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuiExit implements ActionListener {

	public JFrame frame;
	public JLabel lbl;
	public JButton confirm, cancel;
	
	public GuiExit() {
		GridBagConstraints lblGrid = new GridBagConstraints();
		GridBagConstraints confirmGrid = new GridBagConstraints();
		GridBagConstraints cancelGrid = new GridBagConstraints();
		
		frame = new JFrame("Exit");
		frame.setSize(250, 100);
		frame.setLayout(new GridBagLayout());
		
		lbl = new JLabel("Confirm to exit program ?");
		lblGrid.gridx = 0;
		lblGrid.gridy = 0;
		lblGrid.gridwidth = 2;
		lblGrid.gridheight = 1;
		frame.add(lbl, lblGrid);

		confirm = new JButton("Confirm");
		confirmGrid.gridx = 0;
		confirmGrid.gridy = 1;
		confirm.setActionCommand("confirm");
		confirm.addActionListener(this);
		frame.add(confirm, confirmGrid);

		cancel = new JButton("Cancel");
		cancelGrid.gridx = 1;
		cancelGrid.gridy = 1;
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		frame.add(cancel, cancelGrid);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("confirm")) {
			frame.dispose();
			GuiMain.frame.dispose();
		} else {
			frame.dispose();
		}
	}
	
}
