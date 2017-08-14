package org.mocraft.NagatoKai.gui;

import org.mocraft.NagatoKai.Nagato;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiExit extends GuiConstructor implements ActionListener {

    private Nagato instance;
    private JFrame frame;
    private JLabel lbl;
    private JButton confirm, cancel;

    public GuiExit(Nagato instance) {
        this.instance = instance;

        frame = new JFrame("Exit");
        frame.setSize(250, 100);
        frame.setLayout(new GridBagLayout());

        lbl = new JLabel("Confirm to exit program ?");
        frame.add(lbl, setComponent(null, 0, 0, 2, 1));

        confirm = new JButton("Confirm");
        confirm.setActionCommand("confirm");
        confirm.addActionListener(this);
        frame.add(confirm, setComponent(null, 0, 1));

        cancel = new JButton("Cancel");
        cancel.setActionCommand("cancel");
        cancel.addActionListener(this);
        frame.add(cancel, setComponent(null, 1, 1));
    }

    public void setVisible(boolean sw) {
        frame.setVisible(sw);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("confirm")) {
            frame.dispose();
            instance.guiMain.dispose();
        } else {
            frame.dispose();
        }
    }


}
