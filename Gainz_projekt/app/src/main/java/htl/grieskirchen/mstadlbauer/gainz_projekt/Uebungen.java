package htl.grieskirchen.mstadlbauer.gainz_projekt;

public class Uebungen {

    /**
     * name = Name der Übung
     * wh = Wiederholungen
     * saetze = Sätze
     */
    private String name;
    private int wh;
    private int saetze;

    /**
     * Konstruktor
     */
    public Uebungen(String name, int wh, int saetze) {
        this.name = name;
        this.wh = wh;
        this.saetze = saetze;
    }


    /**
     * Getter und Setter Methoden
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWh() {
        return wh;
    }

    public void setWh(int wh) {
        this.wh = wh;
    }

    public int getSaetze() {
        return saetze;
    }

    public void setSaetze(int saetze) {
        this.saetze = saetze;
    }

    public String toString() {
        return name + "," + wh + "," + saetze;
    }
}
