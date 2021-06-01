package htl.grieskirchen.mstadlbauer.gainz_projekt;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    /**
     * name = Der Name des Workouts
     *
     * lasttimedate = Datum an dem das Workout zuletzt gemacht wurde
     *
     * uebungen = alle Übungen die im Workout sind
     */
    String name;
    String lasttimedate;
    List<Uebungen> uebungen = new ArrayList<>();


    /**
     * Konstruktor
     * @param name
     * @param lasttimedate
     */
    public Workout(String name, String lasttimedate, List<Uebungen> uebungen){
        this.name = name;
        this.lasttimedate = lasttimedate;
        this.uebungen = uebungen;
    }

    public Workout(String name,  List<Uebungen> uebungen){
        this.name = name;
        this.uebungen = uebungen;
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

    public void addUebung(Uebungen uebungen) {
        this.uebungen.add(uebungen);
    }

    public String toString(){
        String workout = "";
        workout += name + ";" + lasttimedate + ";";
        for (Uebungen ue : uebungen) {
            workout += ue.toString() + ";";
        }
        return workout;
    }
}
