package sample.service.saloon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.dto.SaloonDto;
import sample.dto.builder.BedDtoBuilder;
import sample.entity.Bed;
import sample.entity.Saloon;
import sample.mapper.BedMapper;
import sample.mapper.HospitalizationMapper;
import sample.mapper.SaloonMapper;
import sample.repository.BedRepository;
import sample.repository.SaloonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaloonServiceImpl implements SaloonService {

    private SaloonMapper saloonMapper;
    private SaloonRepository saloonRepository;
    private BedRepository bedRepository;
    private BedMapper bedMapper;

    @Autowired
    public SaloonServiceImpl(SaloonRepository saloonRepository, BedRepository bedRepository) {
        this.saloonRepository = saloonRepository;
        this.bedRepository = bedRepository;
        this.saloonMapper = new SaloonMapper();
        this.bedMapper = new BedMapper();
        HospitalizationMapper hospitalizationMapper = new HospitalizationMapper();
        this.bedMapper.setHospitalizationMapper(new HospitalizationMapper());
        hospitalizationMapper.setBedMapper(bedMapper);
    }

    @Override
    public void create(SaloonDto saloonDto) {
        Saloon saloon = saloonRepository.save(saloonMapper.mapTo(saloonDto));
        List<Bed> beds = new ArrayList<>();
        for (int i = 1; i <= saloonDto.getNoOfBeds(); i++){
            Bed bed = bedMapper.mapTo(new BedDtoBuilder().setNumber(i).setSaloon(saloonDto).build());
            bed.setSaloon(saloon);
            beds.add(bed);
        }
        bedRepository.saveAll(beds);
    }

    @Override
    public List<SaloonDto> findAll() {
        List<SaloonDto> saloonDtos = new ArrayList<>();
        for (Saloon saloon : saloonRepository.findAll()) {
            saloonDtos.add(saloonMapper.mapFrom(saloon));
        }
        return saloonDtos;
    }

    @Override
    public SaloonDto findByNumber(int number) {
        Saloon saloon = saloonRepository.findByNumber(number);
        if (saloon != null)
            return saloonMapper.mapFrom(saloon);
        else return null;
    }

    @Override
    public void deleteByNumber(int number) {
        Saloon saloon = saloonRepository.findByNumber(number);
        if (saloon == null)
            return;
        bedRepository.deleteBySaloon(saloon.getId());
        saloonRepository.deleteByNumber(number);
    }
}
