package controller.tableButtons;


import model.Trains;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PreviousPage implements ActionListener {
    Trains trains;
    TrainsModel trainsModel;
    JTable table;

    public PreviousPage(Trains trains, JTable table) {
        this.trains = trains;
        this.table = table;

    }

    public void actionPerformed(ActionEvent event) {
        try {
            trains.selectedPage--;

            List trainsList = trains.trains.subList((trains.selectedPage - 1) * trains.visibleCount, trains.selectedPage * trains.visibleCount);
            Trains newTrains = new Trains(trainsList);


            trainsModel = new TrainsModel(newTrains, trains);
            table.setModel(trainsModel.getModel());
        }catch(Exception e){
            JOptionPane.showMessageDialog
                    (null, "Больше нет страниц", "Ошибка", JOptionPane.ERROR_MESSAGE);
            trains.selectedPage++;
        }
    }
}