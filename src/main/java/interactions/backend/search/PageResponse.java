package interactions.backend.search;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResponse {
    private int pageNumber;
    private int pageSize;
    private Sort sort;
    private int offset;
    private boolean paged;
    private boolean unpaged;
    private int totalPages;
    private long totalElements;
    private boolean last;
    private int numberOfElements;
    private boolean first;

    @Getter
    public static class Sort {
        private boolean unsorted;
        private boolean sorted;
        private boolean empty;

        public Sort(boolean a, boolean b, boolean c) {
        }
    }
}
