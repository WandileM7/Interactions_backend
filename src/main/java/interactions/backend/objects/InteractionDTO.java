package interactions.backend.objects;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class InteractionDTO {
    private String action;
    private LocalDateTime creationDate;
    private String platform;
    private String correlationId;
    private Person initiator;
    private Person onBehalfOf;
    private Entity entity;

    public Interaction toEntity() {
        return new Interaction(
                this.initiator,
                this.onBehalfOf,
                this.action,
                this.entity,
                this.creationDate,
                this.platform,
                this.correlationId
        );
    }

    // Convert Entity to DTO
    public static InteractionDTO fromEntity(Interaction interaction) {
        InteractionDTO dto = new InteractionDTO();
        dto.setAction(interaction.getAction());
        dto.setCreationDate(interaction.getCreationDate());
        dto.setPlatform(interaction.getPlatform());
        dto.setCorrelationId(interaction.getCorrelationId());
        dto.setInitiator(interaction.getInitiator());
        dto.setOnBehalfOf(interaction.getOnBehalfOf());
        dto.setEntity(interaction.getEntity());
        return dto;
    }
}