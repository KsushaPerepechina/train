package controller.tableButtons;


import model.Train;
import model.Trains;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LastPage implements ActionListener {
    Trains trains;
    TrainsModel trainsModel;
    JTable table;

    public LastPage(Trains trains, JTable table) {
        this.trains = trains;
        this.table = table;
    }

    public void actionPerformed(ActionEvent event) {
        trains.selectedPage= trains.numberOfPages;

        List<Train> trainsList= trains.trains.subList((trains.selectedPage-1)* trains.visibleCount, trains.trains.size());
        Trains newTrains = new Trains(trainsList);

        trainsModel = new TrainsModel(newTrains, trains);
        table.setModel(trainsModel.getModel());
    }
}
