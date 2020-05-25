package sample.service.bed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.dto.BedDto;
import sample.dto.SaloonDto;
import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.Saloon;
import sample.mapper.BedMapper;
import sample.mapper.HospitalizationMapper;
import sample.repository.BedRepository;
import sample.repository.SaloonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BedServiceImpl implements BedService {

    private BedRepository bedRepository;
    private SaloonRepository saloonRepository;
    private BedMapper bedMapper;

    @Autowired
    public BedServiceImpl(SaloonRepository saloonRepository, BedRepository bedRepository) {
        bedMapper = new BedMapper();
        HospitalizationMapper hospitalizationMapper = new HospitalizationMapper();
        bedMapper.setHospitalizationMapper(hospitalizationMapper);
        hospitalizationMapper.setBedMapper(bedMapper);
        this.bedRepository = bedRepository;
        this.saloonRepository = saloonRepository;
    }

    @Override
    public BedDto findByNumberAndSaloon(int number, SaloonDto saloonDto) {
        Saloon saloon = saloonRepository.findByNumber(saloonDto.getNumber());
        if (saloon == null)
            return null;
        return bedMapper.mapFrom(bedRepository.findByNumberAndSaloon(number, saloon));
    }

    @Override
    public List<BedDto> findAll() {
        List<BedDto> bedDtos = new ArrayList<>();
        for (Bed bed : bedRepository.findAll()) {
            bedDtos.add(bedMapper.mapFrom(bed));
        }
        return bedDtos;
    }

    @Override
    public List<BedDto> findAllBySaloon(SaloonDto saloonDto) {
        List<BedDto> bedDtos = new ArrayList<>();
        Saloon saloon = saloonRepository.findByNumber(saloonDto.getNumber());
        if (saloon == null) {
            return bedDtos;
        }
        for (Bed bed : bedRepository.findAllBySaloon(saloon)) {
            bedDtos.add(bedMapper.mapFrom(bed));
        }
        return bedDtos;
    }

    @Override
    public List<BedDto> findAllByIsOccupiedIsFalse() {
        List<BedDto> bedDtos = new ArrayList<>();
        for (Bed bed : bedRepository.findAllByIsOccupiedIsFalse()) {
            bedDtos.add(bedMapper.mapFrom(bed));
        }
        return bedDtos;
    }

    @Override
    public List<BedDto> findAllByIsOccupiedIsTrue() {
        List<BedDto> bedDtos = new ArrayList<>();
        for (Bed bed : bedRepository.findAllByIsOccupiedIsTrue()) {
            bedDtos.add(bedMapper.mapFrom(bed));
        }
        return bedDtos;
    }

    @Override
    public void updateOccupied(boolean isOccupied, BedDto bedDto) {
        Saloon saloon = saloonRepository.findByNumber(bedDto.getSaloon().getNumber());
        if (saloon == null)
            return;
        Bed bed = bedRepository.findByNumberAndSaloon(bedDto.getNumber(), saloon);
        if (bed == null)
            return;
        bedRepository.updateOccupied(isOccupied, bed.getId());
    }

    @Override
    public void delete(SaloonDto saloonDto) {
        Saloon saloon = saloonRepository.findByNumber(saloonDto.getNumber());
        if (saloon == null)
            return;
        bedRepository.deleteBySaloon(saloon.getId());
    }
}
