package controller;

import controller.parser.DOM;
import model.Trains;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveAction implements ActionListener {
    JFileChooser fileChooser;
    Trains trains;

    public SaveAction(Trains trains) {
        this.trains = trains;
    }

    public void actionPerformed(ActionEvent event) {
        fileChooser = new JFileChooser(".//.//");
        int openFile = fileChooser.showDialog(null, "Сохранить файл");
        if (openFile == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();


                DOM DOMparser = new DOM(file, trains);
                DOMparser.save();

        }
    }
}