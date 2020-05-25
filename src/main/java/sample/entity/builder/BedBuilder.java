package sample.entity.builder;

import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.Saloon;

import java.util.Set;

public class BedBuilder {

    private Bed bed;

    public BedBuilder() {
        bed = new Bed();
    }

    public BedBuilder setHospitalizations(Set<Hospitalization> hospitalizations) {
        bed.setHospitalizations(hospitalizations);
        return this;
    }

    public BedBuilder setSaloon(Saloon saloon) {
        bed.setSaloon(saloon);
        return this;
    }

    public BedBuilder setOccupied(boolean isOccupied) {
        bed.setOccupied(isOccupied);
        return this;
    }

    public BedBuilder setNumber(int number) {
        bed.setNumber(number);
        return this;
    }

    public Bed build() {
        return bed;
    }

}
