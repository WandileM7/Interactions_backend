package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchFilterTest {

    @Test
    void testSearchFilters() {
        SearchFilters filters = new SearchFilters();
        filters.setAction("action");
        filters.setStartDate("2023-01-01");
        filters.setEndDate("2023-12-31");
        filters.setPageSize(10);
        filters.setOffset(0);

        assertEquals("action", filters.getAction());
        assertEquals("2023-01-01", filters.getStartDate());
        assertEquals("2023-12-31", filters.getEndDate());
        assertEquals(10, filters.getPageSize());
        assertEquals(0, filters.getOffset());
    }
}