package controller;

import model.Trains;
import view.SearchDelete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OpenSearchDeleteAction implements ActionListener {
    SearchDelete dialog=null;
    Trains trains;
    JTable mainTable;
    String name;

    public OpenSearchDeleteAction(Trains trains, JTable mainTable, String name) {
        this.trains = trains;
        this.mainTable=mainTable;
        this.name=name;
    }

    public void actionPerformed(ActionEvent event) {
        if (dialog == null) // в первый раз
        {
            dialog = new SearchDelete(null, trains,name,mainTable);
        }
        dialog.dialog.setVisible(true); // отобразить диалог


    }
}
