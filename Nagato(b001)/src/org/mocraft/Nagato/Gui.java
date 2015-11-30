package org.mocraft.Nagato;

import javax.swing.JFrame;

public class Gui extends Nagato implements Runnable {

	public static JFrame frame;

	@Override
	public void run() {
		frame = new JFrame("Nagato");
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}
