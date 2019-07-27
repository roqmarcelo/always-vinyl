package io.github.alwaysvinyl.domain.repository;

import io.github.alwaysvinyl.domain.model.CashbackPercentage;
import io.github.alwaysvinyl.domain.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Optional;

@Repository
public interface CashbackRepository extends CrudRepository<CashbackPercentage, Long> {

    Optional<CashbackPercentage> findByGenreAndDayOfWeek(Genre genre, DayOfWeek dayOfWeek);
}