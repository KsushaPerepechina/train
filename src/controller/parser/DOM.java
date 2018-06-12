package controller.parser;

import model.Train;
import model.Trains;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class DOM {
    DocumentBuilderFactory documentBuilderFactory;
    Document doc = null;
    Document newDoc = null;
    Trains trains = new Trains();
    File file;


    public DOM(File file, Trains trains) {

        this.trains = trains;
        this.file = file;
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            doc = documentBuilder.parse(file);
        } catch (SAXException e) {
            System.exit(1);
        } catch (ParserConfigurationException e) {
            System.err.println(e);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stepThrough(Node start, Train train) {
        if (start.getParentNode().getNodeName() == "number") {
            System.out.println(start.getNodeValue());
            train.addNumber(start.getNodeValue());
        } else if (start.getParentNode().getNodeName() == "departureStation") {
            System.out.println(start.getNodeValue());
            train.addDepartureStation(start.getNodeValue());
        } else if (start.getParentNode().getNodeName() == "arrivalStation") {
            System.out.println(start.getNodeValue());
            train.addArrivalStation(start.getNodeValue());
        } else if (start.getParentNode().getNodeName() == "departureDateTime") {
            System.out.println(start.getNodeValue());
            train.addDepartureDateTime(start.getNodeValue());
        } else if (start.getParentNode().getNodeName() == "arrivalDateTime") {
            System.out.println(start.getNodeValue());
            train.addArrivalDateTime(start.getNodeValue());
        } else if (start.getParentNode().getNodeName() == "travelTime") {
            System.out.println(start.getNodeValue());
            train.addTravelTime(start.getNodeValue());
        }
        for (Node child = start.getFirstChild();
             child != null;
             child = child.getNextSibling()) {
            if (train.getNumber() != null && train.getDepartureDateTime(train.FULL) != null && train.getArrivalDateTime(train.FULL) != null && train.getTravelTime() != null) {
                {
                    trains.trains.add(train);
                    train = new Train();
                }
            }
            stepThrough(child, train);

        }
    }

    public void parse() {
        Element root = doc.getDocumentElement();
        System.out.println("The root element is " + root.getNodeName());
        NodeList children = root.getChildNodes(); //root.getElementsByTagName("train");
        System.out.println("There are " + children.getLength()
                + " nodes in this документ.");

        Train train = new Train();
        stepThrough(root, train);
        Iterator iterator = trains.trains.iterator();
        for (int current = 0, next = 1; next < trains.trains.size(); current++, next++) {
            if (trains.trains.get(current) == trains.trains.get(next))
                trains.trains.remove(next);
        }


    }

    public void save() {

        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter
                    (new FileWriter(file));
            writer.writeStartDocument("utf-8", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement("trains");
            writer.writeCharacters("\n");

            for (Train train : trains.trains) {
                writer.writeStartElement("train");
                writer.writeCharacters("\n");

                writer.writeStartElement("number");
                writer.writeCharacters(train.getNumber());
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeStartElement("departureStation");
                writer.writeCharacters(train.getDepartureStation());
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeStartElement("arrivalStation");
                writer.writeCharacters(train.getArrivalStation());
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeStartElement("departureDateTime");
                writer.writeCharacters(train.getDepartureDateTime(train.MEDIUM));
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeStartElement("arrivalDateTime");
                writer.writeCharacters(train.getArrivalDateTime(train.MEDIUM));
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeStartElement("travelTime");
                writer.writeCharacters(train.getTravelTime());
                writer.writeEndElement();
                writer.writeCharacters("\n");


                writer.writeEndElement();
                writer.writeCharacters("\n");

            }
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndDocument();
            writer.flush();

        } catch (UnsupportedOperationException UnsupportedOperationException) {

        } catch (Exception eSave) {
            JOptionPane.showMessageDialog
                    (null, "Невозможно сохранить файл", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog
                (null, "Файл сохранен", "Успех", JOptionPane.INFORMATION_MESSAGE);


    }
}
