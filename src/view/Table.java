package view;

import controller.tableButtons.*;
import model.Trains;

import javax.swing.*;
import java.awt.*;

public class Table {
    void table(JTable table, Trains newTrains, JPanel panel) {
        Box tablePanel = Box.createVerticalBox();
        JScrollPane scrollPane = new JScrollPane(table);

        Box visibleCount = Box.createHorizontalBox();

        JButton no15 = new JButton("15");
        no15.addActionListener(new VisibleCount(newTrains, 15,table));

        JButton no20 = new JButton("20");
        no20.addActionListener(new VisibleCount(newTrains, 20,table));

        JButton no30 = new JButton("30");
        no30.addActionListener(new VisibleCount(newTrains, 30,table));

        JTextField currPageNum = new JTextField(String.valueOf(newTrains.selectedPage), 1);
        JLabel of = new JLabel("из");
        JTextField totalNumOfPages = new JTextField(String.valueOf(newTrains.numberOfPages), 1);

        visibleCount.add(currPageNum);
        visibleCount.add(Box.createHorizontalStrut(3));
        visibleCount.add(of);
        visibleCount.add(Box.createHorizontalStrut(3));
        visibleCount.add(totalNumOfPages);
        visibleCount.add(Box.createHorizontalStrut(300));
        visibleCount.add(no15);
        visibleCount.add(Box.createHorizontalStrut(3));
        visibleCount.add(no20);
        visibleCount.add(Box.createHorizontalStrut(3));
        visibleCount.add(no30);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 3, 1));

        JButton first = new JButton("<<");
        first.addActionListener(new FirstPage(newTrains, table));

        JButton previous = new JButton("Назад");
        previous.addActionListener(new PreviousPage(newTrains, table));

        JButton next = new JButton("Вперёд");
        next.addActionListener(new NextPage(newTrains, table));

        JButton last = new JButton(">>");
        last.addActionListener(new LastPage(newTrains, table));

        buttonPanel.add(first);
        buttonPanel.add(previous);
        buttonPanel.add(next);
        buttonPanel.add(last);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 4, 3, 1));

        JLabel rec = new JLabel("Записей:");
        JTextField currNumOfRecords = new JTextField(String.valueOf(newTrains.numOfRecords),3);

        infoPanel.add(rec);
        infoPanel.add(Box.createHorizontalStrut(30));
        infoPanel.add(currNumOfRecords);

        tablePanel.add(scrollPane);
        tablePanel.add(Box.createVerticalStrut(12));
        tablePanel.add(visibleCount);
        tablePanel.add(Box.createHorizontalStrut(12));
        tablePanel.add(buttonPanel);
        tablePanel.add(Box.createVerticalStrut(12));
        tablePanel.add(infoPanel);

        panel.add(tablePanel);

    }

}
