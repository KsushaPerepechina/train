package controller.delete;

import model.Train;
import model.Trains;
import view.SearchDelete;
import view.TrainsModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteAction implements ActionListener {

    SearchDelete delete;
    Trains trains;
    Trains newTrains;
    TrainsModel model;
    JTable table = new JTable();
    JTable mainTable = new JTable();

    public DeleteAction(SearchDelete delete, Trains trains, JTable table, JTable mainTable, Trains newTrains) {
        this.delete = delete;
        this.trains = trains;
        this.newTrains = newTrains;
        this.table = table;
        this.mainTable = mainTable;
    }

    public void actionPerformed(ActionEvent event) {
        List<Train> filterTrains = new ArrayList<>();
        filterTrains.addAll(trains.trains);

        if (delete.rbFirst.isSelected()) {
            int numberOfMonth = 0;
            for (String month : delete.months) {
                if (month == delete.departureMonth.getSelectedItem().toString()) {
                    numberOfMonth = delete.months.indexOf(month) + 1;
                    break;
                }
            }

            String day;
            if ((int) delete.departureDay.getSelectedItem() < 10)
                day = "0" + delete.departureDay.getSelectedItem().toString();
            else day = delete.departureDay.getSelectedItem().toString();

            String month;
            if (numberOfMonth < 10)
                month = "0" + Integer.toString(numberOfMonth);
            else month = Integer.toString(numberOfMonth);

            String date = day + "."
                    + month + "."
                    + delete.departureYear.getSelectedItem().toString();

            for (int index = 0; index < filterTrains.size(); index++) {
                Pattern pattern = Pattern.compile("[\\s]*" + delete.trainNumber.getText() + "[\\s]*");
                Matcher matcher = pattern.matcher(filterTrains.get(index).getNumber());
                if (!date.equals(filterTrains.get(index).getDepartureDate(Train.MEDIUM)) || !matcher.find()) {
                    filterTrains.remove(index);
                    index--;
                }
            }
        }

        if (delete.rbThird.isSelected()) {
            for (int index = 0; index < filterTrains.size(); index++) {
                Pattern patternDep = Pattern.compile("[\\s]*" + delete.departureStation.getText() + "[\\s]*");
                Pattern patternArr = Pattern.compile("[\\s]*" + delete.arrivalStation.getText() + "[\\s]*");
                Matcher matcherDep = patternDep.matcher(filterTrains.get(index).getDepartureStation());
                Matcher matcherArr = patternArr.matcher(filterTrains.get(index).getArrivalStation());
                if (matcherDep.find() && matcherArr.find()) {

                }
                else {filterTrains.remove(index);
                    index--;}
            }
        }

        if (delete.rbSecond.isSelected()) {
            String fromHourDep;
            if ((int) delete.fromDepartureHour.getSelectedItem() < 10)
                fromHourDep = "0" + delete.fromDepartureHour.getSelectedItem().toString();
            else fromHourDep = delete.fromDepartureHour.getSelectedItem().toString();

            String fromMinuteDep;
            if ((int) delete.fromDepartureMinute.getSelectedItem() < 10)
                fromMinuteDep = "0" + delete.fromDepartureMinute.getSelectedItem().toString();
            else fromMinuteDep = delete.fromDepartureMinute.getSelectedItem().toString();

            String fromTimeDep = fromHourDep + ":"
                    + fromMinuteDep;


            String tillHourDep;
            if ((int) delete.tillDepartureHour.getSelectedItem() < 10)
                tillHourDep = "0" + delete.tillDepartureHour.getSelectedItem().toString();
            else tillHourDep = delete.tillDepartureHour.getSelectedItem().toString();

            String tillMinuteDep;
            if ((int) delete.tillDepartureMinute.getSelectedItem() < 10)
                tillMinuteDep = "0" + delete.tillDepartureMinute.getSelectedItem().toString();
            else tillMinuteDep = delete.tillDepartureMinute.getSelectedItem().toString();

            String tillTimeDep = tillHourDep + ":"
                    + tillMinuteDep;


            String fromHourArr;
            if ((int) delete.fromArrivalHour.getSelectedItem() < 10)
                fromHourArr = "0" + delete.fromArrivalHour.getSelectedItem().toString();
            else fromHourArr = delete.fromArrivalHour.getSelectedItem().toString();

            String fromMinuteArr;
            if ((int) delete.fromArrivalMinute.getSelectedItem() < 10)
                fromMinuteArr = "0" + delete.fromArrivalMinute.getSelectedItem().toString();
            else fromMinuteArr = delete.fromArrivalMinute.getSelectedItem().toString();

            String fromTimeArr = fromHourArr + ":"
                    + fromMinuteArr;


            String tillHourArr;
            if ((int) delete.tillArrivalHour.getSelectedItem() < 10)
                tillHourArr = "0" + delete.tillArrivalHour.getSelectedItem().toString();
            else tillHourArr = delete.tillArrivalHour.getSelectedItem().toString();

            String tillMinuteArr;
            if ((int) delete.tillArrivalMinute.getSelectedItem() < 10)
                tillMinuteArr = "0" + delete.tillArrivalMinute.getSelectedItem().toString();
            else tillMinuteArr = delete.tillArrivalMinute.getSelectedItem().toString();

            String tillTimeArr = tillHourArr + ":"
                    + tillMinuteArr;


            for (int index = 0; index < filterTrains.size(); index++) {
                Train train = filterTrains.get(index);
                LocalTime departureTime = train.returnDepartureTime();
                LocalTime arrivalTime = train.returnArrivalTime();
                if (!(departureTime.isBefore(train.returnTillDepartureTime(tillTimeDep))
                        && departureTime.isAfter(train.returnFromDepartureTime(fromTimeDep))
                        &&(arrivalTime.isBefore(train.returnTillArrivalTime(tillTimeArr))
                        && arrivalTime.isAfter(train.returnFromArrivalTime(fromTimeArr)))))
                {
                    filterTrains.remove(index);
                    index--;
                }
            }
        }

        if (delete.rbFourth.isSelected()) {
            String hour;
            if ((int) delete.travelHour.getSelectedItem() < 10)
                hour =  delete.travelHour.getSelectedItem().toString();
            else hour = delete.travelHour.getSelectedItem().toString();

            String minute;
            if ((int) delete.travelMinute.getSelectedItem() < 10)
                minute = "0" + delete.travelMinute.getSelectedItem().toString();
            else minute = delete.travelMinute.getSelectedItem().toString();

            String time = hour + ":" + minute;

            for (int index = 0; index < filterTrains.size(); index++) {
                if (!time.equals(filterTrains.get(index).getTravelTime())) {
                    filterTrains.remove(index);
                    index--;
                }
            }
        }

        if (filterTrains.isEmpty()) {
            table.setModel(new DefaultTableModel());
            JOptionPane.showMessageDialog
                    (null, "Ничего не найдено", "Ошибка", JOptionPane.ERROR_MESSAGE);
        } else {
            Trains newTrains = new Trains(filterTrains);
            model = new TrainsModel(newTrains);
            table.setModel(model.getModel());
            if (JOptionPane.showConfirmDialog(null, "Вы точно хотите удалить элементы?") == JOptionPane.YES_OPTION) {
                trains.trains.removeAll(filterTrains);

                List<Train> trainsList;
                if (trains.trains.size() > trains.visibleCount) {
                    if (trains.selectedPage == trains.numberOfPages)
                        trainsList = trains.trains.subList((trains.selectedPage - 1) * trains.visibleCount, trains.trains.size());
                    else
                        trainsList = trains.trains.subList((trains.selectedPage - 1) * trains.visibleCount, trains.selectedPage * trains.visibleCount);
                } else {
                    trainsList = trains.trains;
                    trains.numberOfPages--;
                }

                newTrains.trains = trainsList;
                if (trains.numberOfPages == 1)
                    model = new TrainsModel(newTrains);
                else
                    model = new TrainsModel(newTrains, trains);
                mainTable.setModel(model.getModel());

                JOptionPane.showMessageDialog
                        (null, "Удалено " + filterTrains.size() + " элементов", "Успех", JOptionPane.INFORMATION_MESSAGE);

            }


        }
    }
}