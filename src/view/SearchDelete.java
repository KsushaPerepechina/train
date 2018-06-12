package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;
import java.time.*;

import controller.*;
import controller.delete.DeleteAction;
import controller.search.SearchAction;
import model.Trains;

public class SearchDelete {
    public JDialog dialog;
    public JPanel panel;
    public JTextField trainNumber;
    public JTextField departureStation;
    public JTextField arrivalStation;
    public JComboBox fromDepartureHour;
    public JComboBox tillDepartureHour;
    public JComboBox fromDepartureMinute;
    public JComboBox tillDepartureMinute;
    public JComboBox departureDay;
    public JComboBox departureMonth;
    public JComboBox departureYear;
    public JComboBox fromArrivalHour;
    public JComboBox tillArrivalHour;
    public JComboBox fromArrivalMinute;
    public JComboBox tillArrivalMinute;
    public JComboBox travelMinute;
    public JComboBox travelHour;
    public JCheckBox rbFirst;
    public JCheckBox rbSecond;
    public JCheckBox rbThird;
    public JCheckBox rbFourth;
    public List<String> months;

    JTable table = new JTable();
    JTable mainTable;
    Trains trains;
    Trains newTrains =new Trains();
    String name;
    Table funTable=new Table();

    public SearchDelete(JFrame owner, Trains trains, String name, JTable mainTable) {
        this.name=name;
        dialog = new JDialog(owner, name, true);
        this.trains = trains;
        this.mainTable=mainTable;
        components();
        dialog.pack();
    }

    private void components() {
        panel = new JPanel();
        JPanel info = new JPanel();

        info.setLayout(new GridLayout(3, 2, 6, 12));

        JButton ok = new JButton("OK");
        ok.addActionListener(new OkDialogAction(dialog));
        JButton search = new JButton("Найти");
        if(name.equals("Поиск"))
        search.addActionListener(new SearchAction(this, trains, table, newTrains));
           else search.addActionListener(new DeleteAction(this, trains, table,mainTable, newTrains));

        info.add(number());
        info.add(stationOfDeparture());
        info.add(stationOfArrival());
        info.add(departureDate());
        info.add(departureTime());
        info.add(arrivalTime());
        info.add(travelTime());
        panel.add(checkBoxes(), BorderLayout.WEST);

        panel.add(info, BorderLayout.WEST);


        funTable.table(table, newTrains,panel);

        panel.add(ok);
        panel.add(search);

        dialog.setContentPane(panel);
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

    private Box departureDate() {
        Box departureDate = Box.createHorizontalBox();
        departureDate.setBorder(new TitledBorder("Дата отправления"));

        List<Integer> days = new ArrayList<>();
        for (int day = 1; day < 32; day++)
            days.add(day);
        departureDay = new JComboBox(days.toArray());


        months = new ArrayList<>();
        months.add(Month.JANUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.FEBRUARY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.MARCH.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.APRIL.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.MAY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.JUNE.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.JULY.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.AUGUST.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.SEPTEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.OCTOBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.NOVEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        months.add(Month.DECEMBER.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru")));
        departureMonth = new JComboBox(months.toArray());

        List<Integer> years = new ArrayList<>();
        for (int year = 2018; year < 2020; year++)
            years.add(year);
        departureYear = new JComboBox(years.toArray());


        departureDate.add(departureDay);
        departureDate.add(Box.createHorizontalStrut(6));
        departureDate.add(departureMonth);
        departureDate.add(Box.createHorizontalStrut(6));
        departureDate.add(departureYear);
        return departureDate;
    }

    private Box departureTime() {
        Box departureTime = Box.createHorizontalBox();
        departureTime.setBorder(new TitledBorder("Время отправления"));

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 61; minute++)
            minutes.add(minute);

        JLabel from = new JLabel("От");
        JLabel till = new JLabel("До");
        fromDepartureMinute = new JComboBox(minutes.toArray());
        tillDepartureMinute = new JComboBox(minutes.toArray());

        List<Integer> hours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
            hours.add(hour);
        fromDepartureHour = new JComboBox(hours.toArray());
        tillDepartureHour = new JComboBox(hours.toArray());

        departureTime.add(from);
        departureTime.add(Box.createHorizontalStrut(6));
        departureTime.add(fromDepartureHour);
        departureTime.add(Box.createHorizontalStrut(6));
        departureTime.add(fromDepartureMinute);
        departureTime.add(Box.createHorizontalStrut(6));
        departureTime.add(till);
        departureTime.add(Box.createHorizontalStrut(6));
        departureTime.add(tillDepartureHour);
        departureTime.add(Box.createHorizontalStrut(6));
        departureTime.add(tillDepartureMinute);
        return departureTime;
    }

    private Box arrivalTime() {
        Box arrivalTime = Box.createHorizontalBox();
        arrivalTime.setBorder(new TitledBorder("Время прибытия"));

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 61; minute++)
            minutes.add(minute);

        JLabel from = new JLabel("От");
        JLabel till = new JLabel("До");
        fromArrivalMinute = new JComboBox(minutes.toArray());
        tillArrivalMinute = new JComboBox(minutes.toArray());

        List<Integer> hours = new ArrayList<>();
        for (int hour = 0; hour < 24; hour++)
            hours.add(hour);
        fromArrivalHour = new JComboBox(hours.toArray());
        tillArrivalHour = new JComboBox(hours.toArray());

        arrivalTime.add(from);
        arrivalTime.add(Box.createHorizontalStrut(6));
        arrivalTime.add(fromArrivalHour);
        arrivalTime.add(Box.createHorizontalStrut(6));
        arrivalTime.add(fromArrivalMinute);
        arrivalTime.add(Box.createHorizontalStrut(6));
        arrivalTime.add(till);
        arrivalTime.add(Box.createHorizontalStrut(6));
        arrivalTime.add(tillArrivalHour);
        arrivalTime.add(Box.createHorizontalStrut(6));
        arrivalTime.add(tillArrivalMinute);
        return arrivalTime;
    }

    private Box travelTime() {
        Box travelTime = Box.createHorizontalBox();
        travelTime.setBorder(new TitledBorder("Время в пути"));

        List<Integer> minutes = new ArrayList<>();
        for (int minute = 0; minute < 61; minute++)
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

    private Box checkBoxes() {
        Box radioButtons = Box.createVerticalBox();
        radioButtons.setBorder(new TitledBorder("Критерии поиска"));

        JRadioButton rbFirst = new JRadioButton("Номер поезда и дата отправления");
        JRadioButton rbSecond = new JRadioButton("Время отправления и время прибытия");
        JRadioButton rbThird = new JRadioButton("Станция отправления и станция прибытия");
        JRadioButton rbFourth = new JRadioButton("Время в пути");
        ButtonGroup group = new ButtonGroup();

        group.add(rbFirst);
        group.add(rbSecond);
        group.add(rbThird);
        group.add(rbFourth);

        radioButtons.add(rbFirst);
        radioButtons.add(rbSecond);
        radioButtons.add(rbThird);
        radioButtons.add(rbFourth);
        return radioButtons;
    }
}


