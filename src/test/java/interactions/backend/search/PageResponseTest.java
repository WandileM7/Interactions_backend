package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageResponseTest {

    @Test
    void testPageResponseSetters() {
        PageResponse response = new PageResponse();
        response.setPageNumber(1);
        response.setPageSize(10);
        response.setSort(new PageResponse.Sort(false, true, false));
        response.setOffset(0);
        response.setPaged(true);
        response.setUnpaged(false);
        response.setTotalPages(5);
        response.setTotalElements(50);
        response.setLast(false);
        response.setNumberOfElements(10);
        response.setFirst(true);

        assertEquals(1, response.getPageNumber());
        assertEquals(10, response.getPageSize());
        assertFalse(response.getSort().isUnsorted());
        assertFalse(response.getSort().isSorted());
        assertFalse(response.getSort().isEmpty());
        assertEquals(0, response.getOffset());
        assertTrue(response.isPaged());
        assertFalse(response.isUnpaged());
        assertEquals(5, response.getTotalPages());
        assertEquals(50, response.getTotalElements());
        assertFalse(response.isLast());
        assertEquals(10, response.getNumberOfElements());
        assertTrue(response.isFirst());
    }
    @Test
    void testSortConstructor() {
        PageResponse.Sort sort = new PageResponse.Sort(true, false, true);
        assertFalse(sort.isUnsorted());
        assertFalse(sort.isSorted());
        assertFalse(sort.isEmpty());
    }
}