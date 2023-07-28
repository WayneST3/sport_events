package dev.project.sport.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@RedisHash("Match")
@Data
@Schema(description = "Матч")
public class Match implements Serializable {

    @Schema(description = "Уникальный идентификатор записи", required = true)
    private String id;
    @Schema(description = "Дата начала", required = true)
    private String startDate;
    @Schema(description = "Ссылка на турнир", required = true)
    private String tournamentId;
    @Schema(description = "Номер", required = true)
    private Integer number;
    @Schema(description = "Формат проведения", required = true)
    private String format;
    @Schema(description = "Идентификатор первого участника", required = true)
    private String firstCompetitorId;
    @Schema(description = "Идентификатор второго участника")
    private String secondCompetitorId;
}
