package htl.grieskirchen.mstadlbauer.gainz_projekt;

import java.util.List;

public class Workout {

    /**
     * name = Der Name des Workouts
     *
     * lasttimedate = Datum an dem das Workout zuletzt gemacht wurde
     */
    String name;
    String lasttimedate;


    /**
     * Konstruktor
     * @param name
     * @param lasttimedate
     */
    public Workout(String name, String lasttimedate){
        this.name = name;
        this.lasttimedate = lasttimedate;
    }

    public Workout(String name)
    {
        this.name = name;
    }

    /**
     * Getter und Setter Methoden
     */
    public String getName() {
        return name;
    }

    public void setLastdate(String lasttimedate) {
        this.lasttimedate = lasttimedate;
    }

    public String getLasttimedate() {
        return lasttimedate;
    }
}
