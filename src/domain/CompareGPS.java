package domain;

import java.util.Comparator;

public class CompareGPS implements Comparator<GPS> {

    public int compare(GPS a, GPS b) {
        if (a.distanta > b.distanta) {
            return 1;
        } else if (a.distanta < b.distanta) {
            return -1;
        } else {
            return 0;
        }
    }


}


