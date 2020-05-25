package sample.mapper;

import sample.dto.SaloonDto;
import sample.dto.builder.SaloonDtoBuilder;
import sample.entity.Saloon;
import sample.entity.builder.SaloonBuilder;

public class SaloonMapper implements Mapper<Saloon, SaloonDto> {

    @Override
    public Saloon mapTo(SaloonDto saloonDto) {
        return new SaloonBuilder()
                .setNoOfBeds(saloonDto.getNoOfBeds())
                .setNumber(saloonDto.getNumber())
                .build();
    }

    @Override
    public SaloonDto mapFrom(Saloon saloon) {
        return new SaloonDtoBuilder()
                .setNoOfBeds(saloon.getNoOfBeds())
                .setNumber(saloon.getNumber())
                .build();
    }
}
