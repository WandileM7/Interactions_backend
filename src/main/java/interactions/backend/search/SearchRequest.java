package interactions.backend.search;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class SearchRequest {
    @NotNull
    @Valid
    private SearchCriteria criteria;

    @NotNull
    @Valid
    private SearchFilters filters;

    @Override
    public String toString() {
        return "SearchRequest{" +
               "criteria=" + criteria +
               ", filters=" + filters +
               '}';
    }
}