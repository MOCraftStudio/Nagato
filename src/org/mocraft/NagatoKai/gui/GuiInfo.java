package org.mocraft.NagatoKai.gui;

import org.mocraft.NagatoKai.Nagato;
import org.mocraft.NagatoKai.NagatoSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiInfo extends GuiConstructor implements ActionListener {

    private Nagato instance;
    private JFrame frame;
    private JButton btnConfirm = new JButton("Confirm");

    public GuiInfo(Nagato instance) {
        this.instance = instance;
        frame = new JFrame("Nagato - Info");
        frame.setSize(300, 260);
        frame.setLayout(new GridBagLayout());

        frame.add(new JLabel("MOCraft Studio"), setComponent(null, 0, 1));
        frame.add(new JLabel("Build version: " + NagatoSystem.buildVersion), setComponent(null, 0, 2));
        frame.add(new JLabel("Release version: " + NagatoSystem.officialVersion), setComponent(null, 0, 3));

        btnConfirm.addActionListener(this);
        btnConfirm.setActionCommand("confirm");
        frame.add(btnConfirm, setComponent(null, 0, 4));
    }

    public void setVisible(boolean sw) {
        frame.setVisible(sw);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("confirm")) {
            this.frame.dispose();
        }
    }

}
