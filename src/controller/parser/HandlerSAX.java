package controller.parser;

import java.io.File;

import model.*;

public class HandlerSAX {
    File file;
    Trains trains;

    public HandlerSAX(File file, Trains trains) {
        this.file = file;
        this.trains = trains;
    }

    public void main() {

        SAXparser parser = new SAXparser(file.getAbsolutePath());
        String line = parser.startElement("trains");
        if (line != parser.notFound)
            while (line != parser.lastElement("trains")) {
                Train train = new Train();
                if (line == parser.startElement("trains"))
                    line = parser.nextElement(line);
                else line = parser.nextElement();
                if (line == parser.startElement("train") && line != parser.notFound) {
                    while (line != parser.lastElement("train")) {

                        if (line == parser.startElement("number") && line != parser.notFound) {
                            train.addNumber(parser.characters(parser.startTag("number"), parser.lastTag("number")));
                            System.out.println(train.getNumber());
                        } else if (line == parser.startElement("departureStation") && line != parser.notFound) {
                            train.addDepartureStation(parser.characters(parser.startTag("departureStation"), parser.lastTag("departureStation")));
                            System.out.println(train.getDepartureStation());
                        } else if (line == parser.startElement("arrivalStation") && line != parser.notFound) {
                            train.addArrivalStation(parser.characters(parser.startTag("arrivalStation"), parser.lastTag("arrivalStation")));
                            System.out.println(train.getArrivalStation());
                        } else if (line == parser.startElement("departureDateTime") && line != parser.notFound) {
                            train.addDepartureDateTime(parser.characters(parser.startTag("departureDateTime"), parser.lastTag("departureDateTime")));
                            System.out.println(train.getDepartureDateTime(train.MEDIUM));
                        } else if (line == parser.startElement("arrivalDateTime") && line != parser.notFound) {
                            train.addArrivalDateTime(parser.characters(parser.startTag("arrivalDateTime"), parser.lastTag("arrivalDateTime")));
                            System.out.println(train.getArrivalDateTime(train.MEDIUM));
                        } else if (line.equals(parser.startElement("travelTime")) && !line.equals(parser.notFound)) {
                            train.addTravelTime(parser.characters(parser.startTag("travelTime"), parser.lastTag("travelTime")));
                            System.out.println(train.getTravelTime());
                        }
                        line = parser.nextElement();
                    }
                    trains.trains.add(train);
                }
            }

    }
}
