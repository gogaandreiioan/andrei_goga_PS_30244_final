package sample.dto.builder;

import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.SaloonDto;

import java.util.Set;

public class BedDtoBuilder {

    private BedDto bedDto;

    public BedDtoBuilder() {
        bedDto = new BedDto();
        bedDto.setOccupied(false);
    }

    public BedDtoBuilder setHospitalizations(Set<HospitalizationDto> hospitalizations) {
        bedDto.setHospitalization(hospitalizations);
        return this;
    }

    public BedDtoBuilder setSaloon(SaloonDto saloon) {
        bedDto.setSaloon(saloon);
        return this;
    }

    public BedDtoBuilder setOccupied(boolean isOccupied) {
        bedDto.setOccupied(isOccupied);
        return this;
    }

    public BedDtoBuilder setNumber(int number) {
        bedDto.setNumber(number);
        return this;
    }

    public BedDto build() {
        return bedDto;
    }
}
