package interactions.backend.objects;


import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DetailTest {

    @Test
    void testDetailConstructor() {
        Detail detail = new Detail(1, "type");

        assertEquals(1, detail.getLevel());
        assertEquals("type", detail.getType());
    }

    @Test
    void testFromNestedDataValidInput() {
        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("level", 1);
        nestedData.put("type", "type");

        Detail detail = Detail.fromNestedData(nestedData);

        assertEquals(1, detail.getLevel());
        assertEquals("type", detail.getType());
    }

    @Test
    void testFromNestedDataInvalidInput() {
        Detail detail = Detail.fromNestedData(new Object());
        assertNull(detail);
    }
}