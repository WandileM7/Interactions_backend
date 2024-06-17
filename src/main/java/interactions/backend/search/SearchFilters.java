package interactions.backend.search;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFilters {
    @NotNull
    private String action;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    private Integer pageSize;
    private Integer offset;
}
