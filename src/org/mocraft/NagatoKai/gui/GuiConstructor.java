package org.mocraft.NagatoKai.gui;

import java.awt.*;

public class GuiConstructor {

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid.gridx = gridX;
        grid.gridy = gridY;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY);
        grid.gridwidth = gridWidth;
        grid.gridheight = gridHeight;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight);
        grid.weightx = weightX;
        grid.weighty = weightY;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY, Insets insets) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight, weightX, weightY);
        grid.insets = insets;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY, int fill, Insets insets) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight, weightX, weightY, insets);
        grid.fill = fill;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY, int fill, int anchor) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight, weightX, weightY);
        grid.fill = fill;
        grid.anchor = anchor;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY, Insets insets, int anchor) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight, weightX, weightY, insets);
        grid.anchor = anchor;
        return grid;
    }

    public GridBagConstraints setComponent(GridBagConstraints grid,
                                           int gridX, int gridY, int gridWidth, int gridHeight,
                                           int weightX, int weightY, int fill, Insets insets, int anchor) {
        grid = grid == null ? new GridBagConstraints() : grid;
        grid = setComponent(grid, gridX, gridY, gridWidth, gridHeight, weightX, weightY, insets);
        grid.fill = fill;
        grid.anchor = anchor;
        return grid;
    }

}
