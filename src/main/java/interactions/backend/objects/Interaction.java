package interactions.backend.objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
@Document(collection = "interactions")
public class Interaction{
    private Person onBehalfOf;
    @NotNull
    private Person initiator;

    @NotBlank
    private String action;

    @NotNull
    private Entity entity;

    @NotNull
    private LocalDateTime creationDate;

    @NotBlank
    private String platform;

    private String correlationId;

    public Interaction(Person initiator, String action, Entity entity, LocalDateTime creationDate, String platform, String correlationId) {
        this.action = action;
        this.creationDate = creationDate;
        this.entity = entity;
        this.platform = platform;
        this.initiator = initiator;
        this.correlationId = correlationId;
    }

    public Interaction(Person initiator, Person onBehalfOf, String action, Entity entity, LocalDateTime creationDate, String platform, String correlationId) {
        this.action = action;
        this.creationDate = creationDate;
        this.entity = entity;
        this.onBehalfOf = onBehalfOf;
        this.platform = platform;
        this.initiator = initiator;
        this.correlationId = correlationId;
    }

    public Interaction() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interaction that = (Interaction) o;
        return Objects.equals(onBehalfOf, that.onBehalfOf) &&
                Objects.equals(initiator, that.initiator) &&
                Objects.equals(action, that.action) &&
                Objects.equals(entity, that.entity) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(platform, that.platform) &&
                Objects.equals(correlationId, that.correlationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onBehalfOf, initiator, action, entity, creationDate, platform, correlationId);
    }
}