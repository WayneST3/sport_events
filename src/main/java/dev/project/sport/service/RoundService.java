package dev.project.sport.service;

import dev.project.sport.entity.Round;
import dev.project.sport.repository.MatchRepository;
import dev.project.sport.repository.RoundRepository;
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
public class RoundService {

    private final MatchRepository matchRepository;
    private final RoundRepository roundRepository;

    public Round create(Round round) {
        if ((round.getMatchId() == null) || !matchRepository.existsById(round.getMatchId()))
            ResponseEntity.notFound().build();
        return roundRepository.save(round);
    }

    public Round update(Round round) {
        if (!roundRepository.existsById(round.getId()))
            return null;
        if ((round.getMatchId() == null) || !matchRepository.existsById(round.getMatchId()))
            ResponseEntity.notFound().build();
        return roundRepository.save(round);
    }

    public boolean deleteById(String id) {
        if (!roundRepository.existsById(id))
            return false;
        roundRepository.deleteById(id);
        return true;
    }

    public Round findById(String id) {
        Optional<Round> round = roundRepository.findById(id);
        return round.orElse(null);
    }

    public List<Round> findAll() {
        return (List<Round>) roundRepository.findAll();
    }
}
