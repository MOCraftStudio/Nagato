package org.mocraft.NagatoKai.gui;

import org.mocraft.NagatoKai.Nagato;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMain extends GuiConstructor implements ActionListener {

    private Nagato instance;
    private boolean levyTask;
    // Gui
    private GuiLevy guiLevy;
    private GuiInfo guiInfo;
    private GuiExit guiExit;
    // Components
    private JFrame frame;
    private JMenuBar menuBar;
    private JLabel lblTarget[] = new JLabel[4];
    private JTextField fieldCount[] = new JTextField[4];
    private JTextArea logArea;

    public GuiMain(Nagato instance) {
        this.instance = instance;
        this.guiLevy = new GuiLevy(instance);
        this.guiInfo = new GuiInfo(instance);
        this.guiExit = new GuiExit(instance);

        frame = new JFrame("Nagato");
        frame.setSize(300, 450);
        frame.setLayout(new GridBagLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = initMenu();
        GridBagConstraints menuGrid = setComponent(null,0, 0, 2, 1, 1, 0, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0));
        frame.add(menuBar, menuGrid);

        for(int i = 0; i < 4; ++i) {
            GridBagConstraints lblGrid = setComponent(null,0, i + 2, 1, 1, 0, 0, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), GridBagConstraints.WEST);
            lblTarget[i] = new JLabel(" ");
            frame.add(lblTarget[i], lblGrid);

            GridBagConstraints countGrid = setComponent(null,1, i + 2, 1, 1, 0, 0, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), GridBagConstraints.EAST);
            fieldCount[i] = new JTextField(10);
            frame.add(fieldCount[i], countGrid);
        }

        logArea = new JTextArea();
        GridBagConstraints logGrid = setComponent(null, 0, 6, 2, 4, 0, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5));
        logArea.setEditable(false);
        ((DefaultCaret) logArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        frame.add(new JScrollPane(logArea), logGrid);

        frame.setVisible(true);
    }

    public void log(String msg) { logArea.setText(logArea.getText() + msg); }

    public void logln(String msg) { logArea.setText(logArea.getText() + msg + "\n"); }

    public void dispose() {
        this.frame.dispose();
    }

    public JTextField getCountField(int index) { return index <= 3 ? this.fieldCount[index] : null; }

    public JLabel getLblTarget(int index) { return index <= 3 ? this.lblTarget[index] : null; }

    public GuiLevy getGuiLevy() { return this.guiLevy; }

    public void setLevyTask(boolean levyTask) {
        this.levyTask = levyTask;
        setMenuEnable(levyTask);
    }

    public void setMenuEnable(boolean enable) {
        for(int i = 0; i < menuBar.getMenuCount(); i++) {
            menuBar.getMenu(i).setEnabled(enable);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        if(cmd.equalsIgnoreCase("levy") && levyTask) {
            guiLevy.setVisible(true);
        } else if(cmd.equalsIgnoreCase("Info")) {
            guiInfo.setVisible(true);
        } else if(cmd.equalsIgnoreCase("Exit")) {
            guiExit.setVisible(true);
        }
    }

    public JMenuBar initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menus[] = {new JMenu("Basic"), new JMenu("Task"), new JMenu("Others")};
        JMenuItem items[][] = {
                {new JMenuItem("Setup"), new JMenuItem("Exit")},
                {new JMenuItem("Levy")},
                {new JMenuItem("Info."), new JMenuItem("About")}
        };

        for(int i = 0; i < menus.length; ++i) {
            JMenuItem item[] = items[i];
            for(JMenuItem iterate_item : item) {
                iterate_item.addActionListener(this);
                iterate_item.setActionCommand(iterate_item.getName());
                menus[i].add(iterate_item);
            }
            menuBar.add(menus[i]);
        }
        return menuBar;
    }

}
