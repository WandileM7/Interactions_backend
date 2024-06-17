package interactions.backend.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class InteractionDTOTest {

    @Test
    void testToEntity() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Person onBehalfOf = new Person(new Identifier("source", "type", "value"), "Jane Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, null);
        String action = "view";
        LocalDateTime creationDate = LocalDateTime.now();
        String platform = "web";
        String correlationId = "123";

        InteractionDTO interactionDTO = new InteractionDTO();
        interactionDTO.setInitiator(initiator);
        interactionDTO.setOnBehalfOf(onBehalfOf);
        interactionDTO.setEntity(entity);
        interactionDTO.setAction(action);
        interactionDTO.setCreationDate(creationDate);
        interactionDTO.setPlatform(platform);
        interactionDTO.setCorrelationId(correlationId);

        Interaction interaction = interactionDTO.toEntity();

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(onBehalfOf, interaction.getOnBehalfOf());
        assertEquals(entity, interaction.getEntity());
        assertEquals(action, interaction.getAction());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
    }

    @Test
    void testFromEntity() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Person onBehalfOf = new Person(new Identifier("source", "type", "value"), "Jane Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"),null, null);
        String action = "view";
        LocalDateTime creationDate = LocalDateTime.now();
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, onBehalfOf, action, entity, creationDate, platform, correlationId);

        InteractionDTO interactionDTO = InteractionDTO.fromEntity(interaction);

        assertEquals(initiator, interactionDTO.getInitiator());
        assertEquals(onBehalfOf, interactionDTO.getOnBehalfOf());
        assertEquals(entity, interactionDTO.getEntity());
        assertEquals(action, interactionDTO.getAction());
        assertEquals(creationDate, interactionDTO.getCreationDate());
        assertEquals(platform, interactionDTO.getPlatform());
        assertEquals(correlationId, interactionDTO.getCorrelationId());
    }
}