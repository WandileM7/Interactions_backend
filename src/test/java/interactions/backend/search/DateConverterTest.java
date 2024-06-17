package interactions.backend.search;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateConverterTest {

    private final DateConverter converter = new DateConverter();

    @Test
    void testConvertToStartingTimestamp() throws ParseException {
        String dateStr = "2023-05-13";
        String expected = "2023-05-13T00:00:00.000Z";
        String result = converter.convertToStartingTimestamp(dateStr);
        assertEquals(expected, result);
    }

    @Test
    void testConvertToEndingTimestamp() throws ParseException {
        String dateStr = "2023-05-13";
        String expected = "2023-05-13T23:59:59.999Z";
        String result = converter.convertToEndingTimestamp(dateStr);
        assertEquals(expected, result);
    }
}