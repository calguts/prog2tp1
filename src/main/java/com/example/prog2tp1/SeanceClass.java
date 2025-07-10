package com.example.prog2tp1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class SeanceClass {
    DayOfWeek jour;
    LocalTime debutTimeSection;
    LocalTime finTimeSection;

    public SeanceClass(DayOfWeek jour, LocalTime debutTimeSection, LocalTime finTimeSection) {
        this.jour = jour;
        this.debutTimeSection = debutTimeSection;
        this.finTimeSection = finTimeSection;
    }

    public SeanceClass(boolean exists) {
        this.jour = null;
        this.debutTimeSection = null;
        this.finTimeSection = null;
    }

    public DayOfWeek getJour() {
        return jour;
    }

    public void setJour(DayOfWeek jour) {
        this.jour = jour;
    }

    public LocalTime getDebutTimeSection() {
        return debutTimeSection;
    }

    public void setDebutTimeSection(LocalTime debutTimeSection) {
        this.debutTimeSection = debutTimeSection;
    }

    public LocalTime getFinTimeSection() {
        return finTimeSection;
    }

    public void setFinTimeSection(LocalTime finTimeSection) {
        this.finTimeSection = finTimeSection;
    }

}
