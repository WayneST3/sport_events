package dev.project.sport.service;

import dev.project.sport.entity.Participant;
import dev.project.sport.entity.Team;
import dev.project.sport.repository.ParticipantRepository;
import dev.project.sport.repository.TeamRepository;
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
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final TeamRepository teamRepository;

    public Participant create(Participant participant) {
        if ((participant.getTeamId() != null) && teamRepository.existsById(participant.getTeamId()))
            ResponseEntity.notFound().build();
        return participantRepository.save(participant);
    }

    public Participant update(Participant participant) {
        if (!participantRepository.existsById(participant.getId()))
            return null;
        return participantRepository.save(participant);
    }

    public boolean deleteById(String id) {
        if (!participantRepository.existsById(id))
            return false;
        participantRepository.deleteById(id);
        return true;
    }

    public Participant findById(String id) {
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.orElse(null);
    }

    public List<Participant> findAll() {
        return (List<Participant>) participantRepository.findAll();
    }
}
