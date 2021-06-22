package htl.grieskirchen.mstadlbauer.gainz_projekt;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Körperdaten {

    private double gewicht;
    private LocalDate localDate;

    public Körperdaten(double gewicht, LocalDate localDate)
    {
        this.gewicht = gewicht;
        this.localDate = localDate;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @NonNull
    @Override
    public String toString() {
        return gewicht + ";" + localDate.toString();
    }
}
