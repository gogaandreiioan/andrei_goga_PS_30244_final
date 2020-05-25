package sample.dto.builder;

import sample.dto.SaloonDto;

public class SaloonDtoBuilder {

    private SaloonDto saloonDto;

    public SaloonDtoBuilder() {
        saloonDto = new SaloonDto();
    }

    public SaloonDtoBuilder setNoOfBeds(int noOfBeds) {
        saloonDto.setNoOfBeds(noOfBeds);
        return this;
    }

    public SaloonDtoBuilder setNumber(int number) {
        saloonDto.setNumber(number);
        return this;
    }

    public SaloonDto build() {
        return saloonDto;
    }
}
