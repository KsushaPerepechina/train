package controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OkDialogAction implements ActionListener {
    JDialog dialog;

    public OkDialogAction(JDialog dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent event) {
        dialog.setVisible(false);
    }
};