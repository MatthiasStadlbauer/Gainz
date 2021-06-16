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
    private String name;
    private String lasttimedate;
    private List<Uebungen> uebungen = new ArrayList<>();
    private double lat;
    private double lon;
    private String addresse;


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

    public Workout(String name, String lasttimedate, List<Uebungen> uebungen, String addresse){
        this.name = name;
        this.lasttimedate = lasttimedate;
        this.uebungen = uebungen;
        this.addresse = addresse;
    }

    public Workout(String name, String lasttimedate, List<Uebungen> uebungen, String addresse, long lat, long lon)
    {
        this.name = name;
        this.lasttimedate = lasttimedate;
        this.uebungen = uebungen;
        this.addresse = addresse;
        this.lat = lat;
        this.lon = lon;
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


    public void setName(String name) {
        this.name = name;
    }

    public void setLasttimedate(String lasttimedate) {
        this.lasttimedate = lasttimedate;
    }

    public List<Uebungen> getUebungen() {
        return uebungen;
    }

    public void setUebungen(List<Uebungen> uebungen) {
        this.uebungen = uebungen;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public void setAddresse(String addresse){this.addresse = addresse;}

    public String getAddresse() {
        return addresse;
    }

    /**
     * toString Methode
     */
    //TODO aendern für GPS bei allen Activtys und der toString methode
    public String toString(){
        String workout = "";
        workout += name + ";" + lasttimedate + ";" + lat + ";" + lon + ";" + addresse + ";";
        for (Uebungen ue : uebungen) {
            workout += ue.toString() + ";";
        }
        return workout;
    }
}
