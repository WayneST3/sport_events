package dev.project.sport.controller;

import dev.project.sport.entity.Participant;
import dev.project.sport.service.ParticipantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@CrossOrigin
@Schema(description = "Участники")
@RestController
@RequiredArgsConstructor
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @Operation(summary = "Создание участника",
            operationId = "create_participant",
            description = "Создать запись о новом участнике")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Participant participant) {
        try {
            if (participant.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Participant result = participantService.create(participant);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновление участника",
            operationId = "update_participant",
            description = "Обновить запись о существующем участнике")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Participant participant) {
        try {
            if (participant.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Participant result = participantService.update(participant);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Удаление участника",
            operationId = "delete_participant",
            description = "Удалить запись о существующем участнике")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            if (!participantService.deleteById(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получение всех участников",
            operationId = "find_participants",
            description = "Получение всех добавленных участников")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Participant> participants = participantService.findAll();
            return ResponseEntity.ok(participants);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Поиск участника по идентификатору",
            operationId = "find_participant_by_id",
            description = "Поиск определенного участника по идентификатору")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Participant result = participantService.findById(id);
            if (result == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
