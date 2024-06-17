package interactions.backend.search;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeAndValueCriteria {
    @NotBlank
    private String identifierType;
    @NotBlank
    private String identifierValue;

    public TypeAndValueCriteria(String identifierType, String identifierValue) {
        this.identifierType = identifierType;
        this.identifierValue = identifierValue;
    }

    public TypeAndValueCriteria() {
    }
}