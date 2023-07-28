package dev.project.sport.controller;

import dev.project.sport.entity.Match;
import dev.project.sport.service.MatchService;
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
@Schema(description = "Матчи")
@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "Создание матча",
            operationId = "create_match",
            description = "Создать запись о новом матче")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Match match) {
        try {
            if (match.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Match result = matchService.create(match);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновление матча",
            operationId = "update_match",
            description = "Обновить запись о существующем матче")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Match match) {
        try {
            if (match.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Match result = matchService.update(match);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Удаление матча",
            operationId = "delete_match",
            description = "Удалить запись о существующем матче")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            if (!matchService.deleteById(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получение всех матчей",
            operationId = "find_matches",
            description = "Получение всех добавленных матчей")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Match> matches = matchService.findAll();
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Поиск матча по идентификатору",
            operationId = "find_match_by_id",
            description = "Поиск определенного матча по идентификатору")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Match result = matchService.findById(id);
            if (result == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
