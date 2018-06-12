package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Train {
    public static final String FULL ="FULL";
    public static final String MEDIUM ="MEDIUM";

    String number;
    String departureStation;
    String arrivalStation;
    LocalDateTime departureDateTime = LocalDateTime.of(2018, 01, 01, 00, 00);
    LocalDateTime arrivalDateTime = LocalDateTime.of(2018, 01, 01, 00, 00);
    LocalDate departureDate = LocalDate.of(2018, 01, 01);
    LocalTime travelTime = LocalTime.of(00,00);
    LocalTime departureTime = LocalTime.of(00, 00);
    LocalTime arrivalTime = LocalTime.of(00, 00);
    LocalTime fromDepartureTime = LocalTime.of(00, 00);
    LocalTime tillDepartureTime = LocalTime.of(00, 00);
    LocalTime fromArrivalTime = LocalTime.of(00, 00);
    LocalTime tillArrivalTime = LocalTime.of(00, 00);

    public LocalTime returnDepartureTime(){
        return this.departureTime;
    }

    public LocalTime returnArrivalTime(){
        return this.arrivalTime;
    }

    public LocalTime returnFromDepartureTime(String time){
        addFromDepartureTime(time);
        return this.fromDepartureTime;
    }

    public LocalTime returnTillDepartureTime(String time){
        addTillDepartureTime(time);
        return this.tillDepartureTime;
    }

    public LocalTime returnFromArrivalTime(String time){
        addFromArrivalTime(time);
        return this.fromArrivalTime;
    }

    public LocalTime returnTillArrivalTime(String time){
        addTillArrivalTime(time);
        return this.tillArrivalTime;
    }

    public String addNumber(String number) {

        this.number = number;
        return this.number;
    }

    public String addDepartureStation(String departureStation) {

        this.departureStation = departureStation;
        return this.departureStation;
    }

    public String addArrivalStation(String arrivalStation) {

        this.arrivalStation = arrivalStation;
        return this.arrivalStation;
    }

    public void addDepartureDateTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
        departureDateTime = LocalDateTime.parse(string, formatter);
    }

    public void addArrivalDateTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm");
        arrivalDateTime = LocalDateTime.parse(string, formatter);
    }

    public void addDepartureDate(String string){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        departureDate = LocalDate.parse(string, formatter);
    }

    public void addTravelTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        travelTime = LocalTime.parse(string, formatter);
    }

    public void addDepartureTime(String string){
        departureTime = LocalTime.parse(string);
    }

    public void addArrivalTime(String string){
        arrivalTime = LocalTime.parse(string);
    }

    public void addFromDepartureTime(String string){
        fromDepartureTime = LocalTime.parse(string);
    }

    public void addTillDepartureTime(String string){
        tillDepartureTime = LocalTime.parse(string);
    }

    public void addFromArrivalTime(String string){
        fromArrivalTime = LocalTime.parse(string);
    }

    public void addTillArrivalTime(String string){
        tillArrivalTime = LocalTime.parse(string);
    }

    public String getNumber() {
        return number;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public String getDepartureDateTime(String type) {
        String dateTime = null;
        if (!departureDateTime.isEqual(LocalDateTime.of(2018, 01, 01, 00, 00)))
            if(type==FULL)
            { dateTime =
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                            .withLocale(new Locale("ru"))
                            .format(departureDateTime);}
            else {
                dateTime =
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                                .withLocale(new Locale("ru"))
                                .format(departureDateTime);
            }
        return dateTime;
    }

    public String getDepartureDate (String type){
        String date = null;
        if (!departureDate.isEqual(LocalDate.of(2018, 01, 01)))
            if(type==FULL)
            { date =
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                            .withLocale(new Locale("ru"))
                            .format(departureDate);}
            else {
                date =
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                                .withLocale(new Locale("ru"))
                                .format(departureDate);
            }
        return date;
    }

    public String getArrivalDateTime(String type) {
        String dateTime = null;
        if (!arrivalDateTime.isEqual(LocalDateTime.of(2018, 01, 01, 00, 00)))
            if(type==FULL)
            { dateTime =
                    DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT)
                            .withLocale(new Locale("ru"))
                            .format(arrivalDateTime);}
            else {
                dateTime =
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                                .withLocale(new Locale("ru"))
                                .format(arrivalDateTime);
            }
        return dateTime;
    }

    public String getTravelTime() {
        String time;
        time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(new Locale("ru"))
                .format(travelTime);
        return time;
    }



   /* public String getTravelTime(){
        if(!this.arrivalDateTime.isAfter(this.departureDateTime)) {
            JOptionPane.showMessageDialog
                    (null, "Несоответствие данных: дата прибытия раньше даты отправления", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        LocalDateTime trav = this.arrivalDateTime;
        trav.minusYears(this.departureDateTime.getLong(ChronoField.YEAR));
        trav.minusMonths(this.departureDateTime.getLong(ChronoField.MONTH_OF_YEAR));
        trav.minusDays(this.departureDateTime.getLong(ChronoField.DAY_OF_MONTH));
        trav.minusHours(this.departureDateTime.getLong(ChronoField.HOUR_OF_DAY));
        trav.minusMinutes(this.departureDateTime.getLong(ChronoField.MINUTE_OF_HOUR));

        String time;
        time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(new Locale("ru"))
                .format(travelTime);
        return time;
    }*/


}
