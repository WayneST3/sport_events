package dev.project.sport.controller;

import dev.project.sport.entity.Round;
import dev.project.sport.service.RoundService;
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
 * @since 10.05.2023
 */
@CrossOrigin
@Schema(description = "Раунды")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rounds")
public class RoundController {

    private final RoundService roundService;

    @Operation(summary = "Создание раунда",
            operationId = "create_round",
            description = "Создать запись о новом раунде")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Round round) {
        try {
            if (round.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Round result = roundService.create(round);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновление раунда",
            operationId = "update_round",
            description = "Обновить запись о существующем раунде")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Round round) {
        try {
            if (round.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Round result = roundService.update(round);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Удаление раунда",
            operationId = "delete_round",
            description = "Удалить запись о существующем раунде")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            if (!roundService.deleteById(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получение всех раундов",
            operationId = "find_rounds",
            description = "Получение всех добавленных раундов")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Round> rounds = roundService.findAll();
            return ResponseEntity.ok(rounds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Поиск раунда по идентификатору",
            operationId = "find_round_by_id",
            description = "Поиск определенного раунда по идентификатору")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Round result = roundService.findById(id);
            if (result == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
