package dev.project.sport.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@RedisHash("Tournament")
@Data
@Schema(description = "Турнир")
public class Tournament implements Serializable {

    public enum Status {
        moderation, started, ended
    }

    @Schema(description = "Уникальный идентификатор записи", required = true)
    private String id;
    @Schema(description = "Название", required = true)
    private String name;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Место проведения")
    private String location;
    @Schema(description = "Дата начала")
    private String startDate;
    @Schema(description = "Вид спорта", required = true)
    private String sportType;
    @Schema(description = "Статус")
    private Status status;
}
