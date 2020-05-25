package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sample.entity.Bed;
import sample.entity.Hospitalization;
import sample.entity.Patient;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {

    Hospitalization findFirstByPatientAndAdmissionDateAndReleaseDateNull(Patient patient, Date admissionDate);
    List<Hospitalization> findAllByPatient(Patient patient);
    List<Hospitalization> findAllByBed(Bed bed);
    @Transactional
    @Modifying
    @Query("UPDATE Hospitalization h SET h.releaseDate = :releaseDate WHERE h.id = :id")
    void release(@Param("releaseDate") Date releaseDate, @Param("id") Long id);
    List<Hospitalization> findAllByPatientAndReleaseDateBefore(Patient patient, Date releaseDate);
    List<Hospitalization> findAllByReleaseDateIsNull();
}
