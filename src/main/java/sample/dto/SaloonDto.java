package sample.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaloonDto {

    @NotNull
    @Min(1)
    private int number;
    @NotNull
    @Min(1)
    private int noOfBeds;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }
}
