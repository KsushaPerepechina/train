package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;
import java.time.*;

import controller.*;
import controller.input.InputAction;
import model.Trains;

public class Input {
    public JDialog dialog;
    public JPanel panel;
    public JTextField trainNumber;
    public JTextField departureStation;
    public JTextField arrivalStation;
    public JComboBox arrivalMinute;
    public JComboBox arrivalHour;
    public JComboBox arrivalDay;
    public JComboBox arrivalMonth;
    public JComboBox arrivalYear;
    public JComboBox departureMinute;
    public JComboBox departureHour;
    public JComboBox departureYear;
    public JComboBox departureDay;
    public JComboBox departureMonth;
    public JComboBox travelMinute;
    public JComboBox travelHour;
    JTable mainTable;
    Trains trains =new Trains();
    public List<String> monthsD;
    public List<String> monthsA;

    public Input(JFrame owner, String dialogName, Trains trains, JTable mainTable) {
        dialog = new JDialog(owner, dialogName, true);
        this.trains = trains;
        this.mainTable=mainTable;
        components();
        dialog.pack();
    }

    private void components() {
        panel = new JPanel();
        JPanel info = new JPanel();

        info.setLayout(new GridLayout(2, 2, 6, 12));

        info.add(number());
        info.add(stationOfDeparture());
        info.add(stationOfArrival());
        info.add(departureDateTime());
        info.add(arrivalDateTime());
        info.add(travelTime());

        panel.add(info, BorderLayout.NORTH);

        panel.add(buttons(), BorderLayout.SOUTH);

        dialog.setContentPane(panel);
    }

    private Box buttons() {
        Box buttons = Box.createHorizontalBox();

        JButton ok = new JButton("OK");
        ok.addActionListener(new OkDialogAction(dialog));
        JButton input = new JButton("Ввести");
        input.addActionListener(new InputAction(this, trains,mainTable));

        buttons.add(ok);
        buttons.add(Box.createHorizontalStrut(3));
        buttons.add(input);
        return buttons;
    }

    private Box number() {
        Box number = Box.createHorizontalBox();
        number.setBorder(new TitledBorder("Номер поезда"));
        trainNumber = new JTextField(20);
        number.add(trainNumber);
        return number;
    }

    private Box stationOfDeparture() {
        Box station = Box.createHorizontalBox();
        station.setBorder(new TitledBorder("Станция отправления"));
        departureStation = new JTextField(20);
        station.add(departureStation);
        return station;
    }

    private Box stationOfArrival() {
        Box station = Box.createHorizontalBox();
        station.setBorder(new TitledBorder("Станция прибытия"));
        arrivalStation = new JTextField(20);
        station.add(arrivalStation);
        return station;
    }

    private Box departureDateTime() {
        Box departureDateTime = Box.createHorizontalBox();
        departureDateTime.setBorder(new TitledBorder("Дата и время отправления"));

        List<Integer> days = new ArrayList<>();
        for (int day = 1; day < 32; day++)
            days.add(day);
        departureDay = new JComboBox(days.toArray());


        monthsD = new ArrayList<>();
        monthsD.add(Month.JANUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.FEBRUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.MARCH.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.APRIL.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.MAY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.JUNE.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.JULY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.AUGUST.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.SEPTEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.OCTOBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.NOVEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsD.add(Month.DECEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        departureMonth = new JComboBox(monthsD.toArray());

        List<Integer> years = new ArrayList<>();
        for (int year = 2018; year < 2020; year++)
            years.add(year);
        departureYear = new JComboBox(years.toArray());

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 60; minute++)
            minutes.add(minute);
        departureMinute = new JComboBox(minutes.toArray());

        List<Integer> hours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
            hours.add(hour);
        departureHour = new JComboBox(hours.toArray());

        departureDateTime.add(departureDay);
        departureDateTime.add(Box.createHorizontalStrut(6));
        departureDateTime.add(departureMonth);
        departureDateTime.add(Box.createHorizontalStrut(6));
        departureDateTime.add(departureYear);
        departureDateTime.add(Box.createHorizontalStrut(6));
        departureDateTime.add(departureHour);
        departureDateTime.add(Box.createHorizontalStrut(6));
        departureDateTime.add(departureMinute);
        return departureDateTime;
    }

    private Box arrivalDateTime() {
        Box arrivalDateTime = Box.createHorizontalBox();
        arrivalDateTime.setBorder(new TitledBorder("Дата и время прибытия"));

        List<Integer> days = new ArrayList<>();
        for (int day = 1; day < 32; day++)
            days.add(day);
        arrivalDay = new JComboBox(days.toArray());


        monthsA = new ArrayList<>();
        monthsA.add(Month.JANUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.FEBRUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.MARCH.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.APRIL.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.MAY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.JUNE.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.JULY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.AUGUST.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.SEPTEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.OCTOBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.NOVEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        monthsA.add(Month.DECEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        arrivalMonth = new JComboBox(monthsA.toArray());

        List<Integer> years = new ArrayList<>();
        for (int year = 2018; year < 2020; year++)
            years.add(year);
        arrivalYear = new JComboBox(years.toArray());

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 60; minute++)
            minutes.add(minute);
        arrivalMinute = new JComboBox(minutes.toArray());

        List<Integer> hours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
            hours.add(hour);
        arrivalHour = new JComboBox(hours.toArray());

        arrivalDateTime.add(arrivalDay);
        arrivalDateTime.add(Box.createHorizontalStrut(6));
        arrivalDateTime.add(arrivalMonth);
        arrivalDateTime.add(Box.createHorizontalStrut(6));
        arrivalDateTime.add(arrivalYear);
        arrivalDateTime.add(Box.createHorizontalStrut(6));
        arrivalDateTime.add(arrivalHour);
        arrivalDateTime.add(Box.createHorizontalStrut(6));
        arrivalDateTime.add(arrivalMinute);
        return arrivalDateTime;
    }

    private Box travelTime() {
        Box travelTime = Box.createHorizontalBox();
        travelTime.setBorder(new TitledBorder("Время в пути"));

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 60; minute++)
            minutes.add(minute);
        travelMinute = new JComboBox(minutes.toArray());

        List<Integer> hours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
            hours.add(hour);
        travelHour = new JComboBox(hours.toArray());

        travelTime.add(travelHour);
        travelTime.add(Box.createHorizontalStrut(6));
        travelTime.add(travelMinute);
        return travelTime;
    }
}
