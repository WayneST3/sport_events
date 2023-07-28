package dev.project.sport.service;

import dev.project.sport.entity.Match;
import dev.project.sport.repository.MatchRepository;
import dev.project.sport.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@Service
@RequiredArgsConstructor
public class MatchService {

    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    public Match create(Match match) {
        if ((match.getTournamentId() == null) || !tournamentRepository.existsById(match.getTournamentId()))
            ResponseEntity.notFound().build();
        return matchRepository.save(match);
    }

    public Match update(Match match) {
        if (!matchRepository.existsById(match.getId()))
            return null;
        if ((match.getTournamentId() == null) || !tournamentRepository.existsById(match.getTournamentId()))
            ResponseEntity.notFound().build();
        return matchRepository.save(match);
    }

    public boolean deleteById(String id) {
        if (!matchRepository.existsById(id))
            return false;
        matchRepository.deleteById(id);
        return true;
    }

    public Match findById(String id) {
        Optional<Match> match = matchRepository.findById(id);
        return match.orElse(null);
    }

    public List<Match> findAll() {
        return (List<Match>) matchRepository.findAll();
    }
}
