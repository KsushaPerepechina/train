package view;

import model.Train;
import model.Trains;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainsModel {
    private DefaultTableModel model;

    public TrainsModel(Trains trains) {
        model = new DefaultTableModel(trains.trains.size(), 7);
        List<String> header = new ArrayList<>();

        header.add("№");
        header.add("Номер поезда");
        header.add("Станция отправления");
        header.add("Станция прибытия");
        header.add("Дата и время отправления");
        header.add("Дата и время  прибытия");
        header.add("Время в пути");

        for (Train train : trains.trains) {
            model.setValueAt(trains.trains.indexOf(train) + 1, trains.trains.indexOf(train), 0);
            model.setValueAt(train.getNumber(), trains.trains.indexOf(train), 1);
            model.setValueAt(train.getDepartureStation(), trains.trains.indexOf(train), 2);
            model.setValueAt(train.getArrivalStation(), trains.trains.indexOf(train), 3);
            model.setValueAt(train.getDepartureDateTime(train.MEDIUM), trains.trains.indexOf(train), 4);
            model.setValueAt(train.getArrivalDateTime(train.MEDIUM), trains.trains.indexOf(train), 5);
            model.setValueAt(train.getTravelTime(), trains.trains.indexOf(train), 6);
        }
        model.setColumnIdentifiers(header.toArray());
    }

    public TrainsModel(Trains trains, Trains oldTrains) {
        model = new DefaultTableModel(trains.trains.size(), 7);
        List<String> header = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        if(oldTrains.selectedPage==0)
            for(int number = oldTrains.selectedPage* oldTrains.visibleCount; number<(oldTrains.selectedPage+1)* oldTrains.visibleCount; number++)
                numbers.add(number);
        else if(oldTrains.selectedPage== oldTrains.numberOfPages)
            for(int number = (oldTrains.selectedPage-1)* oldTrains.visibleCount; number< oldTrains.trains.size(); number++)
                numbers.add(number);
        else
            for(int number = (oldTrains.selectedPage-1)* oldTrains.visibleCount; number< oldTrains.selectedPage* oldTrains.visibleCount; number++)
                numbers.add(number);
        Iterator iterator=numbers.iterator();

        header.add("№");
        header.add("Номер поезда");
        header.add("Станция отправления");
        header.add("Станция прибытия");
        header.add("Дата и время отправления");
        header.add("Дата и время  прибытия");
        header.add("Время в пути");

        for (Train train : trains.trains) {
            model.setValueAt(trains.trains.indexOf(train) + 1, trains.trains.indexOf(train), 0);
            model.setValueAt(train.getNumber(), trains.trains.indexOf(train), 1);
            model.setValueAt(train.getDepartureStation(), trains.trains.indexOf(train), 2);
            model.setValueAt(train.getArrivalStation(), trains.trains.indexOf(train), 3);
            model.setValueAt(train.getDepartureDateTime(train.MEDIUM), trains.trains.indexOf(train), 4);
            model.setValueAt(train.getArrivalDateTime(train.MEDIUM), trains.trains.indexOf(train), 5);
            model.setValueAt(train.getTravelTime(), trains.trains.indexOf(train), 6);
        }
        model.setColumnIdentifiers(header.toArray());
    }
    public DefaultTableModel getModel() {
        return model;
    }
}
