import javax.swing.*;

import model.Trains;
import view.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Trains trains = new Trains();
        Window mainwindow = new Window(trains);


    }
}
//программный расчёт времени в пути
//расширить поля таблицы
//прикрутить календарь
//генерация xml
//число всех доступных записей на стр
//номер текущей активной стр
//число доступных страниц
