package controller.input;

import model.Trains;
import view.Input;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OpenInputAction implements ActionListener {
    Input dialog=null;
    Trains trains;
    JTable mainTable;

    public OpenInputAction(Trains trains, JTable mainTable) {
        this.trains = trains;
        this.mainTable=mainTable;
    }

    public void actionPerformed(ActionEvent event) {
        if (dialog == null) // в первый раз
        {
            dialog = new Input(null, "Ввод", trains,mainTable);
        }
        dialog.dialog.setVisible(true); // отобразить диалог
    }
}
