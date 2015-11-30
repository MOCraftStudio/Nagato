package org.mocraft.Gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.mocraft.Nagato.Nagato;

public class MainGui extends Nagato implements Runnable, ActionListener {

	public static JFrame frame;
	public static JMenuBar menuBar;
	public static JButton btnTesk;
	public static JLabel targetLbl[] = new JLabel[4];
	public static JTextField countField[] = new JTextField[4];
	public static JTextArea logArea;
	
	public MainGui() {
		GridBagConstraints menuBarGrid = guiManager.setComponent(0, 0, 2, 1, 0, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));
		GridBagConstraints teskGrid = guiManager.setComponent(0, 1, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2));
		GridBagConstraints labelGrid[] = new GridBagConstraints[4];
		GridBagConstraints countGrid[] = new GridBagConstraints[4];
		GridBagConstraints logGrid = guiManager.setComponent(0, 6, 2, 4, 0, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
		
		frame = new JFrame("Nagato");
		frame.setSize(200, 300);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = initMenu(); 
		frame.add(menuBar, menuBarGrid);
		
		btnTesk = new JButton("Tesk");
		btnTesk.setActionCommand("tesk");
		btnTesk.addActionListener(this);
		frame.add(btnTesk, teskGrid);
		
		for(int i = 0; i < 4; ++i) {
			labelGrid[i] = guiManager.setComponent(0, i + 2, 1, 1, 0, 0, new Insets(2, 2, 2, 2));
			targetLbl[i] = new JLabel(" ");
			frame.add(targetLbl[i], labelGrid[i]);
			
			countGrid[i] = guiManager.setComponent(1, i + 2, 1, 1, 0, 0, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), GridBagConstraints.EAST);
			countField[i] = new JTextField(10);
			frame.add(countField[i], countGrid[i]);
		}
		
		logArea = new JTextArea();
		frame.add(logArea, logGrid);
		
		frame.setVisible(true);
	}
	
	public void setVisible(boolean sw) {
		frame.setVisible(sw);
		return;
	}
	
	public static JMenuBar initMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menus[] = {new JMenu("Basic"), new JMenu("Others")};
		JMenuItem itemss[][]  = {{new JMenuItem("Setup"), new JMenuItem("Exit")},
								{new JMenuItem("FAQ"), new JMenuItem("About")}};
		
		for(int i = 0; i < menus.length; ++i) {
			JMenuItem items[] = itemss[i];
			for(int j = 0; j < items.length; ++j) {
				if(items[j] == itemss[0][1]) { menus[i].addSeparator(); }
				menus[i].add(items[j]);
			}
			menuBar.add(menus[i]);
		}
		return menuBar;
	}
	
	@Override
	public void run() {  }

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("tesk")) {
			teskGuiThread.setVisible(true);
		}
	}
}
