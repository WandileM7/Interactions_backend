package interactions.backend.search;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class SearchCriteria {
    @NotNull
    private TypeAndValueCriteria initiator;
    @NotNull
    private TypeAndValueCriteria onBehalfOf;
    @NotNull
    private EntityCriteria entity;
}
