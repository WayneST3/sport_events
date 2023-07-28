package dev.project.sport.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@RedisHash("Round")
@Data
@Schema(description = "Раунд")
public class Round implements Serializable {

    @Schema(description = "Уникальный идентификатор записи", required = true)
    private String id;
    @Schema(description = "Номер", required = true)
    private String number;
    @Schema(description = "Идентификатор матча")
    private String matchId;
    @Schema(description = "Очки первого участника")
    private Integer firstParticipantScore;
    @Schema(description = "Очки второго участника")
    private Integer secondParticipantScore;
}
