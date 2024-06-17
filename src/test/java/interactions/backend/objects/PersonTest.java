package interactions.backend.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;


class PersonTest {

    @Test
    void testPersonConstructor() {
        Identifier identifier = new Identifier("source", "type", "value");
        Person person = new Person(identifier, "John Doe");

        assertEquals(identifier, person.getIdentifier());
        assertEquals("John Doe", person.getName());
    }

    @Test
    void testPersonConstructorWithNullIdentifier() {
        Person person = new Person(null, "John Doe");

        assertNull(person.getIdentifier());
        assertEquals("John Doe", person.getName());
    }

    @Test
    void testPersonConstructorWithNullName() {
        Identifier identifier = new Identifier("source", "type", "value");
        Person person = new Person(identifier, null);

        assertEquals(identifier, person.getIdentifier());
        assertNull(person.getName());
    }

    @Test
    void testFromNestedDataValidInput() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("source", "source");
        identifierMap.put("type", "type");
        identifierMap.put("value", "value");
        nestedData.put("identifier", identifierMap);
        nestedData.put("name", "John Doe");

        Person person = Person.fromNestedData(nestedData);

        assertEquals("source", person.getIdentifier().getSource());
        assertEquals("type", person.getIdentifier().getType());
        assertEquals("value", person.getIdentifier().getValue());
        assertEquals("John Doe", person.getName());
    }

    @Test
    void testFromNestedDataInvalidInput() {
        Person person = Person.fromNestedData(new Object());
        assertNull(person);
    }

    @Test
    void testFromNestedDataWithNullIdentifier() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("name", "John Doe");

        Person person = Person.fromNestedData(nestedData);

        assertNull(person.getIdentifier());
        assertEquals("John Doe", person.getName());
    }

    @Test
    void testFromNestedDataWithNullName() {
        Map<String, Object> nestedData = new HashMap<>();
        Map<String, Object> identifierMap = new HashMap<>();
        identifierMap.put("source", "source");
        identifierMap.put("type", "type");
        identifierMap.put("value", "value");
        nestedData.put("identifier", identifierMap);

        Person person = Person.fromNestedData(nestedData);

        assertEquals("source", person.getIdentifier().getSource());
        assertEquals("type", person.getIdentifier().getType());
        assertEquals("value", person.getIdentifier().getValue());
        assertNull(person.getName());
    }
}