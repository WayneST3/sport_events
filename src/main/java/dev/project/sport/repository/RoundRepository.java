package dev.project.sport.repository;

import dev.project.sport.entity.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@Repository
public interface RoundRepository extends CrudRepository<Round, String> {
}
