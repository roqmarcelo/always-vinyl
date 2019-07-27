package io.github.alwaysvinyl.domain.repository;

import io.github.alwaysvinyl.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> findByDateSoldGreaterThanEqual(LocalDate startDate, Pageable pageable);

    Page<Sale> findByDateSoldLessThanEqual(LocalDate endDate, Pageable pageable);

    Page<Sale> findByDateSoldBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    Page<Sale> findBy(Pageable pageable);
}