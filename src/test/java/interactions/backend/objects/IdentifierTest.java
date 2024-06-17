package interactions.backend.objects;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

class IdentifierTest {

    @Test
    void testIdentifierConstructor() {
        Identifier identifier = new Identifier("source", "type", "value");

        assertEquals("source", identifier.getSource());
        assertEquals("type", identifier.getType());
        assertEquals("value", identifier.getValue());
    }

    @Test
    void testFromNestedDataValidInput() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("source", "source");
        nestedData.put("type", "type");
        nestedData.put("value", "value");

        Identifier identifier = Identifier.fromNestedData(nestedData);

        assertEquals("source", identifier.getSource());
        assertEquals("type", identifier.getType());
        assertEquals("value", identifier.getValue());
    }

    @Test
    void testFromNestedDataInvalidInput() {
        Identifier identifier = Identifier.fromNestedData(new Object());
        assertNull(identifier);
    }


}