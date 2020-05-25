package sample.mapper;

import sample.dto.HospitalizationDto;
import sample.dto.builder.HospitalizationDtoBuilder;
import sample.entity.Hospitalization;
import sample.entity.builder.HospitalizationBuilder;

public class HospitalizationMapper implements Mapper<Hospitalization, HospitalizationDto> {

    private BedMapper bedMapper;
    private PatientMapper patientMapper = new PatientMapper();

    public void setBedMapper(BedMapper bedMapper) {
        this.bedMapper = bedMapper;
    }

    @Override
    public Hospitalization mapTo(HospitalizationDto hospitalizationDto) {
        return new HospitalizationBuilder()
                .setBed(bedMapper.mapTo(hospitalizationDto.getBed()))
                .setPatient(patientMapper.mapTo(hospitalizationDto.getPatient()))
                .setAdmissionDate(hospitalizationDto.getAdmissionDate())
                .setReleaseDate(hospitalizationDto.getReleaseDate())
                .build();
    }

    @Override
    public HospitalizationDto mapFrom(Hospitalization hospitalization) {
        return new HospitalizationDtoBuilder()
                .setBed(bedMapper.mapFrom(hospitalization.getBed()))
                .setPatient(patientMapper.mapFrom(hospitalization.getPatient()))
                .setAdmissionDate(hospitalization.getAdmissionDate())
                .setReleaseDate(hospitalization.getReleaseDate())
                .build();
    }
}
