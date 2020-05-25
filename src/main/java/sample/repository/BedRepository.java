package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sample.entity.Bed;
import sample.entity.Saloon;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BedRepository extends JpaRepository<Bed, Long> {

    Bed findByNumberAndSaloon(int number, Saloon saloon);
    List<Bed> findAllBySaloon(Saloon saloon);
    List<Bed> findAllByIsOccupiedIsFalse();
    List<Bed> findAllByIsOccupiedIsTrue();

    @Transactional
    @Modifying
    @Query("UPDATE Bed b SET b.isOccupied = :isOccupied WHERE b.id = :id")
    void updateOccupied(@Param("isOccupied") boolean isOccupied, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE from Bed b WHERE b.saloon.id = :saloonId")
    void deleteBySaloon(long saloonId);
}
