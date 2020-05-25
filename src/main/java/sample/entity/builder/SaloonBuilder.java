package sample.entity.builder;

import sample.entity.Saloon;

public class SaloonBuilder {

    private Saloon saloon;

    public SaloonBuilder() {
        saloon = new Saloon();
    }

    public SaloonBuilder setNoOfBeds(int noOfBeds) {
        saloon.setNoOfBeds(noOfBeds);
        return this;
    }

    public SaloonBuilder setNumber(int number) {
        saloon.setNumber(number);
        return this;
    }

    public Saloon build() {
        return saloon;
    }
}
