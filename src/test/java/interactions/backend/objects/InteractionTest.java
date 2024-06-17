package interactions.backend.objects;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InteractionTest {

    @Test
    void testInteractionConstructor() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, action, entity, creationDate, platform, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithNullInitiator() {
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(null, action, entity, creationDate, platform, correlationId);

        assertNull(interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithNullAction() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, null, entity, creationDate, platform, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertNull(interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithNullEntity() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, action, null, creationDate, platform, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertNull(interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithNullCreationDate() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        String action = "view";
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, action, entity, null, platform, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertNull(interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithOnBehalfOf() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Person onBehalfOf = new Person(new Identifier("source", "type", "value"), "Jane Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String platform = "web";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, onBehalfOf, action, entity, creationDate, platform, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(onBehalfOf, interaction.getOnBehalfOf());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
    }

    @Test
    void testInteractionConstructorWithNullCorrelationId() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String platform = "web";

        Interaction interaction = new Interaction(initiator, action, entity, creationDate, platform, null);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertEquals(platform, interaction.getPlatform());
        assertNull(interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }

    @Test
    void testInteractionConstructorWithNullPlatform() {
        Person initiator = new Person(new Identifier("source", "type", "value"), "John Doe");
        Entity entity = new Entity(new Identifier("source", "type", "value"), null, Collections.emptyList());
        LocalDateTime creationDate = LocalDateTime.now();
        String action = "view";
        String correlationId = "123";

        Interaction interaction = new Interaction(initiator, action, entity, creationDate, null, correlationId);

        assertEquals(initiator, interaction.getInitiator());
        assertEquals(action, interaction.getAction());
        assertEquals(entity, interaction.getEntity());
        assertEquals(creationDate, interaction.getCreationDate());
        assertNull(interaction.getPlatform());
        assertEquals(correlationId, interaction.getCorrelationId());
        assertNull(interaction.getOnBehalfOf());
    }
}
