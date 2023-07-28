package dev.project.sport.repository;

import dev.project.sport.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wayne Stark
 * @since 08.05.2023
 */
@Repository
public interface TeamRepository extends CrudRepository<Team, String> {
}
