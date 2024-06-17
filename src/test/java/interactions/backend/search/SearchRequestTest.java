package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchRequestTest {

    @Test
    void testSearchRequestToString() {
        SearchCriteria criteria = new SearchCriteria();
        SearchFilters filters = new SearchFilters();
        SearchRequest request = new SearchRequest();
        request.setCriteria(criteria);
        request.setFilters(filters);

        String expectedString = "SearchRequest{criteria=" + criteria + ", filters=" + filters + "}";
        assertEquals(expectedString, request.toString());
    }
}
