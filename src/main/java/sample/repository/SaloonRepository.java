package sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.entity.Saloon;

import javax.transaction.Transactional;

@Repository
public interface SaloonRepository extends JpaRepository<Saloon, Long> {

    Saloon findByNumber(int number);

    @Transactional
    void deleteByNumber(int number);
}
