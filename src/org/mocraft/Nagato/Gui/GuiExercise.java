package org.mocraft.Nagato.Gui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import org.mocraft.Nagato.Nagato;

public class GuiExercise extends Nagato {

	public JFrame frame;
	
	public GuiExercise() {
		frame = new JFrame("Nagato - Exercise Manager");
		frame.setSize(330, 260);
		frame.setLayout(new GridBagLayout());
		
		
	}
	
}
