package dev.project.sport.controller;

import dev.project.sport.entity.Tournament;
import dev.project.sport.service.TournamentService;
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
@Schema(description = "Турниры")
@RestController
@RequiredArgsConstructor
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @Operation(summary = "Создание турнира",
            operationId = "create_tournament",
            description = "Создать запись о новом турнире")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Tournament tournament) {
        try {
            if (tournament.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Tournament result = tournamentService.create(tournament);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновление турнира",
            operationId = "update_tournament",
            description = "Обновить запись о существующем турнире")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Tournament tournament) {
        try {
            if (tournament.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Tournament result = tournamentService.update(tournament);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Удаление турнира",
            operationId = "delete_tournament",
            description = "Удалить запись о существующем турнира")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            if (!tournamentService.deleteById(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получение всех турниров",
            operationId = "find_tournaments",
            description = "Получение всех добавленных турниров")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Tournament> tournaments = tournamentService.findAll();
            return ResponseEntity.ok(tournaments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Поиск турнира по идентификатору",
            operationId = "find_tournament_by_id",
            description = "Поиск определенного турнира по идентификатору")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Tournament result = tournamentService.findById(id);
            if (result == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
