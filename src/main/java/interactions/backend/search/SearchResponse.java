package interactions.backend.search;

import java.util.List;

import interactions.backend.objects.Interaction;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchResponse {
    private List<Interaction> content;
    private int totalCount;
    private PageResponse pageable;

    public SearchResponse() {
    }

    public SearchResponse(List<Interaction> data, int totalCount, PageResponse pageable) {
        this.content = data;
        this.totalCount = totalCount;
        this.pageable = pageable;
    }

    public List<Interaction> getInteractions() {
        return content;
    }
}
