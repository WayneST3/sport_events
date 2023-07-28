package dev.project.sport.service;

import dev.project.sport.entity.Team;
import dev.project.sport.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Wayne Stark
 * @since 08.05.2023
 */
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team create(Team team) {
        return teamRepository.save(team);
    }

    public Team update(Team team) {
        if (!teamRepository.existsById(team.getId()))
            return null;
        return teamRepository.save(team);
    }

    public boolean deleteById(String id) {
        if (!teamRepository.existsById(id))
            return false;
        teamRepository.deleteById(id);
        return true;
    }

    public Team findById(String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElse(null);
    }

    public List<Team> findAll() {
        return (List<Team>) teamRepository.findAll();
    }
}
