package controller.input;

import model.Train;
import model.Trains;
import view.Input;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InputAction implements ActionListener {
    Input input;
    Trains trains = new Trains();
    TrainsModel model = new TrainsModel(trains);
    JTable mainTable = new JTable();

    public InputAction(Input input, Trains trains, JTable mainTable) {
        this.input = input;
        this.trains = trains;
        this.mainTable = mainTable;
    }

    public void actionPerformed(ActionEvent event) {
        Train train = new Train();
        train.addNumber(input.trainNumber.getText());
        train.addDepartureStation(input.departureStation.getText());
        train.addArrivalStation(input.arrivalStation.getText());

        int numberOfMonth = 0;
        for (String monthDep : input.monthsD) {
            if (monthDep == input.departureMonth.getSelectedItem().toString()) {
                numberOfMonth = input.monthsD.indexOf(monthDep) + 1;
                break;
            }
        }

        String dayDep;
        if ((int) input.departureDay.getSelectedItem() < 10)
            dayDep = "0" + input.departureDay.getSelectedItem().toString();
        else dayDep = input.departureDay.getSelectedItem().toString();

        String monthDep;
        if (numberOfMonth < 10)
            monthDep = "0" + Integer.toString(numberOfMonth);
        else monthDep = Integer.toString(numberOfMonth);

        train.addDepartureDate(dayDep + "."
                + monthDep + "."
                + input.departureYear.getSelectedItem().toString());

        String hourDep;
        if ((int) input.departureHour.getSelectedItem() < 10)
            hourDep = "0" + input.departureHour.getSelectedItem().toString();
        else hourDep = input.departureHour.getSelectedItem().toString();

        String minuteDep;
        if ((int) input.departureMinute.getSelectedItem() < 10)
            minuteDep = "0" + input.departureMinute.getSelectedItem().toString();
        else minuteDep = input.departureMinute.getSelectedItem().toString();

        train.addDepartureTime(hourDep + ":"
                + minuteDep);

        train.addDepartureDateTime(dayDep + "."
                + monthDep + "."
                + input.departureYear.getSelectedItem().toString() + " "
                + hourDep + ":"
                + minuteDep);

        numberOfMonth = 0;
        for (String monthArr : input.monthsA) {
            if (monthArr == input.arrivalMonth.getSelectedItem().toString()) {
                numberOfMonth = input.monthsA.indexOf(monthArr) + 1;
                break;
            }
        }

        String dayArr;
        if ((int) input.arrivalDay.getSelectedItem() < 10)
            dayArr = "0" + input.arrivalDay.getSelectedItem().toString();
        else dayArr = input.arrivalDay.getSelectedItem().toString();

        String monthArr;
        if (numberOfMonth < 10)
            monthArr = "0" + Integer.toString(numberOfMonth);
        else monthArr = Integer.toString(numberOfMonth);

        String hourArr;
        if ((int) input.arrivalHour.getSelectedItem() < 10)
            hourArr = "0" + input.arrivalHour.getSelectedItem().toString();
        else hourArr = input.arrivalHour.getSelectedItem().toString();

        String minuteArr;
        if ((int) input.arrivalMinute.getSelectedItem() < 10)
            minuteArr = "0" + input.arrivalMinute.getSelectedItem().toString();
        else minuteArr = input.arrivalMinute.getSelectedItem().toString();

        train.addArrivalTime(hourArr + ":"
                + minuteArr);

        train.addArrivalDateTime(dayArr + "."
                + monthArr + "."
                + input.arrivalYear.getSelectedItem().toString() + " "
                + hourArr + ":"
                + minuteArr);

        String hourTr;
        if ((int) input.travelHour.getSelectedItem() < 10)
            hourTr = "0" + input.travelHour.getSelectedItem().toString();
        else hourTr  = input.travelHour.getSelectedItem().toString();

        String minuteTr;
        if ((int) input.travelMinute.getSelectedItem() < 10)
            minuteTr  = "0" + input.travelMinute.getSelectedItem().toString();
        else minuteTr  = input.travelMinute.getSelectedItem().toString();

        train.addTravelTime(hourTr  + ":"
                + minuteTr);

        //controller.add(train)
        //window.update()
        trains.trains.add(train);

        if (trains.selectedPage == trains.numberOfPages) {
            List<Train> trainsList = trains.trains.subList((trains.selectedPage) * trains.visibleCount, trains.trains.size());
            Trains newTrains = new Trains(trainsList);
            if (trainsList.size() < trains.visibleCount + 1) {
                model = new TrainsModel(newTrains,trains);
                mainTable.setModel(model.getModel());
            }
            else trains.numberOfPages++;
        }
        JOptionPane.showMessageDialog
                (null, "Поезд добавлен", "Успех", JOptionPane.INFORMATION_MESSAGE);
    }
}