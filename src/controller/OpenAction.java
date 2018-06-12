package controller;

import controller.parser.DOM;
import controller.parser.HandlerSAX;
import model.Train;
import model.Trains;
import view.TrainsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Random;

public class OpenAction implements ActionListener {
    JFileChooser fileChooser;
    Trains trains;
    TrainsModel trainsModel;
    JTable table;
    final Random random = new Random();


    public OpenAction(Trains trains, JTable table) {
        this.trains = trains;
        this.table = table;
    }

    public void actionPerformed(ActionEvent event) {
        fileChooser = new JFileChooser(".//.//");
        int openFile = fileChooser.showDialog(null, "Открыть файл");
        if (openFile == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            //int whichParser = random.nextInt(2) + 1;
            int whichParser=2;
            if (whichParser == 1) {
                HandlerSAX handlerSAX = new HandlerSAX(file, trains);
                handlerSAX.main();
            } else {
                DOM DOMparser = new DOM(file, trains);
                DOMparser.parse();
            }

            if(trains.trains.size()%trains.visibleCount!=0)
                trains.numberOfPages=trains.trains.size()/trains.visibleCount+1;
            else
                trains.numberOfPages=trains.trains.size()/trains.visibleCount;

            trains.selectedPage=1;

            List<Train> trainsList=trains.trains.subList(0 , trains.visibleCount);
            Trains newTrains = new Trains(trainsList);

            trainsModel = new TrainsModel(newTrains);
            table.setModel(trainsModel.getModel());


        }
    }
}
