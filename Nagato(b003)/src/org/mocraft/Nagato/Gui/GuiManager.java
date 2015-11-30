package org.mocraft.Nagato.Gui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GuiManager {
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		return grid;
	}
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY, Insets insets) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		grid.insets = insets;
		return grid;
	}
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY, int fill, Insets insets) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		grid.fill = fill;
		grid.insets = insets;
		return grid;
	}
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY, int fill, int anchor) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		grid.fill = fill;
		grid.anchor = anchor;
		return grid;
	}
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY, Insets insets, int anchor) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		grid.anchor = anchor;
		grid.insets = insets;
		return grid;
	}
	
	public GridBagConstraints setComponent(int gridX, int gridY, int gridWidth, int gridHeight,
			int weightX, int weightY, int fill, Insets insets, int anchor) {
		GridBagConstraints grid = new GridBagConstraints();
		grid.gridx = gridX;
		grid.gridy = gridY;
		grid.gridwidth = gridWidth;
		grid.gridheight = gridHeight;
		grid.weightx = weightX;
		grid.weighty = weightY;
		grid.fill = fill;
		grid.anchor = anchor;
		grid.insets = insets;
		return grid;
	}
}
