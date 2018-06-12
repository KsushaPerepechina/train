package controller.tableButtons;

import model.Train;
import model.Trains;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FirstPage implements ActionListener {
    Trains trains;
    TrainsModel trainsModel;
    JTable table;

    public FirstPage(Trains trains, JTable table) {
        this.trains = trains;
        this.table = table;

    }

    public void actionPerformed(ActionEvent event) {
        trains.selectedPage = 1;
        List<Train> trainsList;
        if (trains.numberOfPages == 1)
            trainsList = trains.trains.subList(0, trains.trains.size());
        else trainsList = trains.trains.subList(0, trains.visibleCount);
        Trains newTrains = new Trains(trainsList);

        trainsModel = new TrainsModel(newTrains);
        table.setModel(trainsModel.getModel());
    }
}
