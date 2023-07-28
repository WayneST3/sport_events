package dev.project.sport.service;

import dev.project.sport.entity.Tournament;
import dev.project.sport.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@Service
@RequiredArgsConstructor
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public Tournament create(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament update(Tournament tournament) {
        if (!tournamentRepository.existsById(tournament.getId()))
            return null;
        return tournamentRepository.save(tournament);
    }

    public boolean deleteById(String id) {
        if (!tournamentRepository.existsById(id))
            return false;
        tournamentRepository.deleteById(id);
        return true;
    }

    public Tournament findById(String id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return tournament.orElse(null);
    }

    public List<Tournament> findAll() {
        return (List<Tournament>) tournamentRepository.findAll();
    }
}
