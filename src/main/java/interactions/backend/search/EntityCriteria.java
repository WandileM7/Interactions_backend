package interactions.backend.search;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityCriteria  {
    @NotNull
    private String identifierType;
    @NotNull
    private String identifierValue;
    @NotNull
    private TypeAndValueCriteria owner;
}
