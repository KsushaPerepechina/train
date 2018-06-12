package view;

import javax.swing.*;

import controller.*;
import controller.input.OpenInputAction;
import model.Trains;

import java.awt.*;

public class Window {
    public JFrame mainwindow;
    JPanel panel;
    JMenuBar menuBar;
    JToolBar toolBar;
    Trains trains;
    JTable table = new JTable();
    Table funTable=new Table();

    public Window(Trains trains) {
        this.trains = trains;

        mainwindow = new JFrame("Лабораторная работа №2");
        mainwindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel = new JPanel();
        menuBar();
        toolBar();
        funTable.table(table, trains,panel);
        mainwindow.setContentPane(panel);
        mainwindow.pack();
        mainwindow.setVisible(true);
    }

    void menuBar() {
        menuBar = new JMenuBar();
        mainwindow.setJMenuBar(menuBar);

        JMenu file = new JMenu("Файл");
        JMenuItem open = new JMenuItem("Открыть файл", new ImageIcon(".//.//res//open.png"));
        open.addActionListener(new OpenAction(trains, table));
        JMenuItem save = new JMenuItem("Сохранить файл", new ImageIcon(".//.//res//save.png"));
        save.addActionListener(new SaveAction(trains));
        JMenuItem exit = new JMenuItem("Выход", new ImageIcon(".//.//res//exit.png"));
        exit.addActionListener(new ExitAction());

        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);

        menuBar.add(file);

        JMenu array = new JMenu("Массив");
        JMenuItem input = new JMenuItem("Ввод в массив", new ImageIcon(".//.//res//input.png"));
        input.addActionListener(new OpenInputAction(trains, table));
        JMenuItem search = new JMenuItem("Поиск в массиве", new ImageIcon(".//.//res//search.png"));
        search.addActionListener(new OpenSearchDeleteAction(trains,table,"Поиск"));
        JMenuItem delete = new JMenuItem("Удаление в массиве", new ImageIcon(".//.//res//delete.png"));
        delete.addActionListener(new OpenSearchDeleteAction(trains,table,"Удаление"));

        array.add(input);
        array.add(search);
        array.add(delete);

        menuBar.add(array);
    }

    void toolBar() {
        toolBar = new JToolBar(SwingConstants.VERTICAL);
        panel.add(toolBar, BorderLayout.WEST);

        JButton open = new JButton(new ImageIcon(".//.//res//open.png"));
        open.setToolTipText("Открыть файл");
        open.addActionListener(new OpenAction(trains, table));
        JButton save = new JButton(new ImageIcon(".//.//res//save.png"));
        save.setToolTipText("Сохранить файл");
        save.addActionListener(new SaveAction(trains));
        JButton input = new JButton(new ImageIcon(".//.//res//input.png"));
        input.setToolTipText("Ввод в массив");
        input.addActionListener(new OpenInputAction(trains, table));
        JButton search = new JButton(new ImageIcon(".//.//res//search.png"));
        search.setToolTipText("Поиск в массиве");
        search.addActionListener(new OpenSearchDeleteAction(trains,table,"Поиск"));
        JButton delete = new JButton(new ImageIcon(".//.//res//delete.png"));
        delete.setToolTipText("Удаление в массиве");
        delete.addActionListener(new OpenSearchDeleteAction(trains,table,"Удаление"));

        toolBar.add(open);
        toolBar.add(save);
        toolBar.add(input);
        toolBar.add(search);
        toolBar.add(delete);
    }
}
