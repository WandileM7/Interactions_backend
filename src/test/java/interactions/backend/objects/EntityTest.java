package interactions.backend.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


class EntityTest {

    @Test
    void testEntityConstructor() {
        Identifier identifier = new Identifier("source", "type", "value");
        Person owner = new Person(new Identifier("source", "type", "value"), "John Doe");
        List<Detail> details = new ArrayList<>();
        details.add(new Detail(1, "type"));

        Entity entity = new Entity(identifier, owner, details);

        assertEquals(identifier, entity.getIdentifier());
        assertEquals(owner, entity.getOwner());
        assertEquals(details, entity.getDetails());
    }

    @Test
    void testEntityConstructorWithNullIdentifier() {
        Person owner = new Person(new Identifier("source", "type", "value"), "John Doe");
        List<Detail> details = new ArrayList<>();
        details.add(new Detail(1, "type"));

        Entity entity = new Entity(null, owner, details);

        assertNull(entity.getIdentifier());
        assertEquals(owner, entity.getOwner());
        assertEquals(details, entity.getDetails());
    }

    @Test
    void testEntityConstructorWithNullOwner() {
        Identifier identifier = new Identifier("source", "type", "value");
        List<Detail> details = new ArrayList<>();
        details.add(new Detail(1, "type"));

        Entity entity = new Entity(identifier, details);

        assertEquals(identifier, entity.getIdentifier());
        assertNull(entity.getOwner());
        assertEquals(details, entity.getDetails());
    }

    @Test
    void testEntityConstructorWithNullDetails() {
        Identifier identifier = new Identifier("source", "type", "value");
        Person owner = new Person(new Identifier("source", "type", "value"), "John Doe");

        Entity entity = new Entity(identifier, owner, null);

        assertEquals(identifier, entity.getIdentifier());
        assertEquals(owner, entity.getOwner());
        assertNull(entity.getDetails());
    }

    @Test
    void testFromNestedDataValidInput() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("source", "source");
        identifierMap.put("type", "type");
        identifierMap.put("value", "value");
        nestedData.put("identifier", identifierMap);

        Map<String, Object> ownerMap = new HashMap<>();
        Map<String, Object> ownerIdentifierMap = new HashMap<>();
        ownerIdentifierMap.put("source", "source");
        ownerIdentifierMap.put("type", "type");
        ownerIdentifierMap.put("value", "value");
        ownerMap.put("identifier", ownerIdentifierMap);
        ownerMap.put("name", "John Doe");
        nestedData.put("owner", ownerMap);

        List<Map<String, Object>> detailMaps = new ArrayList<>();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("level", 1);
        detailMap.put("type", "type");
        detailMaps.add(detailMap);
        nestedData.put("details", detailMaps);

        Entity entity = Entity.fromNestedData(nestedData);

        assertEquals("source", entity.getIdentifier().getSource());
        assertEquals("type", entity.getIdentifier().getType());
        assertEquals("value", entity.getIdentifier().getValue());
        assertEquals("John Doe", entity.getOwner().getName());
        assertEquals(1, entity.getDetails().get(0).getLevel());
        assertEquals("type", entity.getDetails().get(0).getType());
    }

    @Test
    void testFromNestedDataInvalidInput() {
        Entity entity = Entity.fromNestedData(new Object());
        assertNull(entity);
    }

    @Test
    void testFromNestedDataWithNullIdentifier() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> ownerMap = new HashMap<>();
        Map<String, Object> ownerIdentifierMap = new HashMap<>();
        ownerIdentifierMap.put("source", "source");
        ownerIdentifierMap.put("type", "type");
        ownerIdentifierMap.put("value", "value");
        ownerMap.put("identifier", ownerIdentifierMap);
        ownerMap.put("name", "John Doe");
        nestedData.put("owner", ownerMap);

        List<Map<String, Object>> detailMaps = new ArrayList<>();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("level", 1);
        detailMap.put("type", "type");
        detailMaps.add(detailMap);
        nestedData.put("details", detailMaps);

        Entity entity = Entity.fromNestedData(nestedData);

        assertNull(entity.getIdentifier());
        assertEquals("John Doe", entity.getOwner().getName());
        assertEquals(1, entity.getDetails().get(0).getLevel());
        assertEquals("type", entity.getDetails().get(0).getType());
    }

    @Test
    void testFromNestedDataWithNullOwner() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("source", "source");
        identifierMap.put("type", "type");
        identifierMap.put("value", "value");
        nestedData.put("identifier", identifierMap);

        List<Map<String, Object>> detailMaps = new ArrayList<>();
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("level", 1);
        detailMap.put("type", "type");
        detailMaps.add(detailMap);
        nestedData.put("details", detailMaps);

        Entity entity = Entity.fromNestedData(nestedData);

        assertEquals("source", entity.getIdentifier().getSource());
        assertEquals("type", entity.getIdentifier().getType());
        assertEquals("value", entity.getIdentifier().getValue());
        assertNull(entity.getOwner());
        assertEquals(1, entity.getDetails().get(0).getLevel());
        assertEquals("type", entity.getDetails().get(0).getType());
    }

    @Test
    void testFromNestedDataWithNullDetails() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("source", "source");
        identifierMap.put("type", "type");
        identifierMap.put("value", "value");
        nestedData.put("identifier", identifierMap);

        Map<String, Object> ownerMap = new HashMap<>();
        Map<String, Object> ownerIdentifierMap = new HashMap<>();
        ownerIdentifierMap.put("source", "source");
        ownerIdentifierMap.put("type", "type");
        ownerIdentifierMap.put("value", "value");
        ownerMap.put("identifier", ownerIdentifierMap);
        ownerMap.put("name", "John Doe");
        nestedData.put("owner", ownerMap);

        Entity entity = Entity.fromNestedData(nestedData);

        assertEquals("source", entity.getIdentifier().getSource());
        assertEquals("type", entity.getIdentifier().getType());
        assertEquals("value", entity.getIdentifier().getValue());
        assertEquals("John Doe", entity.getOwner().getName());
        assertNull(entity.getDetails());
    }

    @Test
    void testFromNestedDataWithIncorrectTypes() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("identifier", "Not a map");  // Incorrect type
        nestedData.put("owner", new ArrayList<>());  // Incorrect type
        nestedData.put("details", "Not a list");  // Incorrect type

        Entity entity = Entity.fromNestedData(nestedData);

        assertNull(entity.getIdentifier());
        assertNull(entity.getOwner());
        assertNull(entity.getDetails());
    }

    @Test
    void testFromNestedDataWithNullValues() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("identifier", null);
        nestedData.put("owner", null);
        nestedData.put("details", null);

        Entity entity = Entity.fromNestedData(nestedData);

        assertNull(entity.getIdentifier());
        assertNull(entity.getOwner());
        assertNull(entity.getDetails());
    }
}
