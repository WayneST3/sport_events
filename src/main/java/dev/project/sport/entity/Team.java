package dev.project.sport.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * @author Wayne Stark
 * @since 08.05.2023
 */
@RedisHash("Team")
@Data
@Schema(description = "Команда")
public class Team implements Serializable {

    @Schema(description = "Уникальный идентификатор записи", required = true)
    private String id;
    @Schema(description = "Название команды", required = true)
    private String name;
    @Schema(description = "Страна", required = true)
    private String nationality;
    @Schema(description = "Вебсайт")
    private String website;
    @Schema(description = "Идентификатор владельца", required = true)
    private String ownerId;
    @Schema(description = "Ссылка на изображение")
    private String imageId;
    @Schema(description = "Активна/не активна")
    private String active;

}
