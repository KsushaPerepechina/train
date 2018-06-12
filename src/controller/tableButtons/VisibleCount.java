package controller.tableButtons;

import model.Train;
import model.Trains;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VisibleCount implements ActionListener {

    Trains trains;
    TrainsModel trainsModel;
    int visibleCount;
    JTable mainTable = new JTable();

    public VisibleCount(Trains trains, int visibleCount, JTable mainTable) {
        this.trains = trains;
        this.visibleCount = visibleCount;
        this.mainTable = mainTable;
    }

    public void actionPerformed(ActionEvent event) {

        trains.visibleCount = visibleCount;
        if (trains.trains.size() % trains.visibleCount != 0)
            trains.numberOfPages = trains.trains.size() / trains.visibleCount + 1;
        else
            trains.numberOfPages = trains.trains.size() / trains.visibleCount;

        trains.selectedPage = 1;
        List<Train> trainsList;

        if (trains.trains.size() < visibleCount)
            trainsList = trains.trains.subList(0, trains.trains.size());
        else
             trainsList = trains.trains.subList(0, trains.visibleCount);
        Trains newTrains = new Trains(trainsList);

        trainsModel = new TrainsModel(newTrains);
        mainTable.setModel(trainsModel.getModel());
    }
}
