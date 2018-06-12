package controller.search;

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

public class SearchAction implements ActionListener {

    SearchDelete search;
    Trains trains;
    Trains newTrains;
    TrainsModel model;
    JTable table = new JTable();

    public SearchAction(SearchDelete search, Trains trains, JTable table, Trains newTrains) {
        this.search = search;
        this.trains = trains;
        this.table = table;
        this.newTrains = newTrains;
    }

    public void actionPerformed(ActionEvent event) {
        List<Train> filterTrains = new ArrayList<>();
        filterTrains.addAll(trains.trains);


        if (search.rbFirst.isSelected()) {
            int numberOfMonth = 0;
            for (String month : search.months) {
                if (month == search.departureMonth.getSelectedItem().toString()) {
                    numberOfMonth = search.months.indexOf(month) + 1;
                    break;
                }
            }

            String day;
            if ((int) search.departureDay.getSelectedItem() < 10)
                day = "0" + search.departureDay.getSelectedItem().toString();
            else day = search.departureDay.getSelectedItem().toString();

            String month;
            if (numberOfMonth < 10)
                month = "0" + Integer.toString(numberOfMonth);
            else month = Integer.toString(numberOfMonth);

            String date = day + "."
                    + month + "."
                    + search.departureYear.getSelectedItem().toString();

            for (int index = 0; index < filterTrains.size(); index++) {
                Pattern pattern = Pattern.compile("[\\s]*" + search.trainNumber.getText() + "[\\s]*");
                Matcher matcher = pattern.matcher(filterTrains.get(index).getNumber());
                if (!date.equals(filterTrains.get(index).getDepartureDate(Train.MEDIUM)) || !matcher.find()) {
                    filterTrains.remove(index);
                    index--;
                }
            }
        }

        if (search.rbThird.isSelected()) {
            for (int index = 0; index < filterTrains.size(); index++) {
                Pattern patternDep = Pattern.compile("[\\s]*" + search.departureStation.getText() + "[\\s]*");
                Pattern patternArr = Pattern.compile("[\\s]*" + search.arrivalStation.getText() + "[\\s]*");
                Matcher matcherDep = patternDep.matcher(filterTrains.get(index).getDepartureStation());
                Matcher matcherArr = patternArr.matcher(filterTrains.get(index).getArrivalStation());
                if (matcherDep.find() && matcherArr.find()) {

                }
                else {filterTrains.remove(index);
                index--;}
            }
        }

        if (search.rbSecond.isSelected()) {
            String fromHourDep;
            if ((int) search.fromDepartureHour.getSelectedItem() < 10)
                fromHourDep = "0" + search.fromDepartureHour.getSelectedItem().toString();
            else fromHourDep = search.fromDepartureHour.getSelectedItem().toString();

            String fromMinuteDep;
            if ((int) search.fromDepartureMinute.getSelectedItem() < 10)
                fromMinuteDep = "0" + search.fromDepartureMinute.getSelectedItem().toString();
            else fromMinuteDep = search.fromDepartureMinute.getSelectedItem().toString();

            String fromTimeDep = fromHourDep + ":"
                    + fromMinuteDep;


            String tillHourDep;
            if ((int) search.tillDepartureHour.getSelectedItem() < 10)
                tillHourDep = "0" + search.tillDepartureHour.getSelectedItem().toString();
            else tillHourDep = search.tillDepartureHour.getSelectedItem().toString();

            String tillMinuteDep;
            if ((int) search.tillDepartureMinute.getSelectedItem() < 10)
                tillMinuteDep = "0" + search.tillDepartureMinute.getSelectedItem().toString();
            else tillMinuteDep = search.tillDepartureMinute.getSelectedItem().toString();

            String tillTimeDep = tillHourDep + ":"
                    + tillMinuteDep;


            String fromHourArr;
            if ((int) search.fromArrivalHour.getSelectedItem() < 10)
                fromHourArr = "0" + search.fromArrivalHour.getSelectedItem().toString();
            else fromHourArr = search.fromArrivalHour.getSelectedItem().toString();

            String fromMinuteArr;
            if ((int) search.fromArrivalMinute.getSelectedItem() < 10)
                fromMinuteArr = "0" + search.fromArrivalMinute.getSelectedItem().toString();
            else fromMinuteArr = search.fromArrivalMinute.getSelectedItem().toString();

            String fromTimeArr = fromHourArr + ":"
                    + fromMinuteArr;


            String tillHourArr;
            if ((int) search.tillArrivalHour.getSelectedItem() < 10)
                tillHourArr = "0" + search.tillArrivalHour.getSelectedItem().toString();
            else tillHourArr = search.tillArrivalHour.getSelectedItem().toString();

            String tillMinuteArr;
            if ((int) search.tillArrivalMinute.getSelectedItem() < 10)
                tillMinuteArr = "0" + search.tillArrivalMinute.getSelectedItem().toString();
            else tillMinuteArr = search.tillArrivalMinute.getSelectedItem().toString();

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

        if (search.rbFourth.isSelected()) {
            String hour;
            if ((int) search.travelHour.getSelectedItem() < 10)
                hour =  search.travelHour.getSelectedItem().toString();
            else hour = search.travelHour.getSelectedItem().toString();

            String minute;
            if ((int) search.travelMinute.getSelectedItem() < 10)
                minute = "0" + search.travelMinute.getSelectedItem().toString();
            else minute = search.travelMinute.getSelectedItem().toString();

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
            newTrains.trains = filterTrains;
            model = new TrainsModel(newTrains);
            table.setModel(model.getModel());
        }
    }
}