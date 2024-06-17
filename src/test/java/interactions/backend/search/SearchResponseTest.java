package interactions.backend.search;
import interactions.backend.objects.Interaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class SearchResponseTest {

    @Test
    void testSearchResponse() {
        List<Interaction> content = new ArrayList<>();
        content.add(new Interaction());
        content.add(new Interaction());
        int totalCount = 2;
        PageResponse pageable = new PageResponse();
        pageable.setPageNumber(1);
        pageable.setPageSize(10);

        SearchResponse response = new SearchResponse(content, totalCount, pageable);

        assertEquals(content, response.getContent());
        assertEquals(totalCount, response.getTotalCount());
        assertEquals(pageable.getPageNumber(), response.getPageable().getPageNumber());
        assertEquals(pageable.getPageSize(), response.getPageable().getPageSize());
    }

    @Test
    void testSearchResponseDefaultConstructor() {
        SearchResponse response = new SearchResponse();
        response.setContent(new ArrayList<>());
        response.setTotalCount(0);
        PageResponse pageable = new PageResponse();
        response.setPageable(pageable);

        assertEquals(new ArrayList<>(), response.getContent());
        assertEquals(0, response.getTotalCount());
        assertEquals(pageable.getPageNumber(), response.getPageable().getPageNumber());
        assertEquals(pageable.getPageSize(), response.getPageable().getPageSize());
    }
}