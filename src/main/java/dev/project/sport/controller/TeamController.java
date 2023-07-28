package dev.project.sport.controller;

import dev.project.sport.entity.Team;
import dev.project.sport.service.TeamService;
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
 * @since 08.05.2023
 */
@CrossOrigin
@Schema(description = "Команды")
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Operation(summary = "Создание команды",
            operationId = "create_team",
            description = "Создать запись о новой команде игроков")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Team team) {
        try {
            if (team.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Team result = teamService.create(team);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Обновление команды",
            operationId = "update_team",
            description = "Обновить запись о существующей команде игроков")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Team team) {
        try {
            if (team.getId() == null)
                return ResponseEntity.badRequest().body("Идентификатор не задан");
            Team result = teamService.update(team);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Удаление команды",
            operationId = "delete_team",
            description = "Удалить запись о существующей команде игроков")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            if (!teamService.deleteById(id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Получение всех команд",
            operationId = "find_teams",
            description = "Получение всех добавленных команд игроков")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            List<Team> teamList = teamService.findAll();
            return ResponseEntity.ok(teamList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Поиск команды игроков по идентификатору",
            operationId = "find_team_by_id",
            description = "Поиск определенной команды игроков по идентификатору")
    @ApiResponse(responseCode = "" + HttpServletResponse.SC_OK,
            content = @Content(mediaType = "application/json"))
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Team result = teamService.findById(id);
            if (result == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
