package dev.project.sport.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Wayne Stark
 * @since 09.05.2023
 */
@RedisHash("Participant")
@Data
@Schema(description = "Участник")
public class Participant implements Serializable {

    public enum Gender {
        male, female
    }

    @Schema(description = "Уникальный идентификатор записи", required = true)
    private String id;
    @Schema(description = "Имя", required = true)
    private String firstname;
    @Schema(description = "Фамилия", required = true)
    private String lastname;
    @Schema(description = "Возраст", required = true)
    private Integer age;
    @Schema(description = "Национальность")
    private String nationality;
    @Schema(description = "Ссылка на команду")
    private String teamId;
    @Schema(description = "Ссылка на изображение")
    private String imageId;
    @Schema(description = "Пол", required = true)
    private Gender gender;
}
