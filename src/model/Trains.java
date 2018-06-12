package model;

import java.util.ArrayList;
import java.util.List;

public class Trains {
    public List<Train> trains;
    public int numberOfPages;
    public int selectedPage;
    public int numOfRecords;
    public int visibleCount=15;
    public Trains() {
        trains = new ArrayList<>();
    }
    public Trains(List trains) {
        this.trains = trains;
    }

}
