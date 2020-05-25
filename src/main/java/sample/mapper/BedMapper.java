package sample.mapper;

import sample.dto.BedDto;
import sample.dto.HospitalizationDto;
import sample.dto.builder.BedDtoBuilder;
import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.builder.BedBuilder;

import java.util.HashSet;

public class BedMapper implements Mapper<Bed, BedDto> {

    private HospitalizationMapper hospitalizationMapper;
    private SaloonMapper saloonMapper = new SaloonMapper();

    public void setHospitalizationMapper(HospitalizationMapper hospitalizationMapper) {
        this.hospitalizationMapper = hospitalizationMapper;
    }

    @Override
    public Bed mapTo(BedDto bedDto) {
        HashSet<Hospitalization> hospitalizations = new HashSet<>();
        if (bedDto.getHospitalizations() != null)
            for (HospitalizationDto hospitalizationDto : bedDto.getHospitalizations()) {
                hospitalizations.add(hospitalizationMapper.mapTo(hospitalizationDto));
            }
        return new BedBuilder()
                .setHospitalizations(hospitalizations)
                .setSaloon(saloonMapper.mapTo(bedDto.getSaloon()))
                .setOccupied(bedDto.getOccupied())
                .setNumber(bedDto.getNumber())
                .build();
    }

    @Override
    public BedDto mapFrom(Bed bed) {
        HashSet<HospitalizationDto> hospitalizationDtos = new HashSet<>();
        if (bed.getHospitalizations() != null)
            for (Hospitalization hospitalization : bed.getHospitalizations()) {
                hospitalizationDtos.add(hospitalizationMapper.mapFrom(hospitalization));
            }
        return new BedDtoBuilder()
                .setSaloon(saloonMapper.mapFrom(bed.getSaloon()))
                .setHospitalizations(hospitalizationDtos)
                .setOccupied(bed.getOccupied())
                .setNumber(bed.getNumber())
                .build();
    }
}
