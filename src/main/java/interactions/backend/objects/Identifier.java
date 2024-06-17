package interactions.backend.objects;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Identifier {
    @NotNull
    private String source;
    @NotNull
    private String type;
    @NotNull
    private String value;

    public Identifier(String source, String type, String value) {
        this.source = source;
        this.type = type;
        this.value = value;
    }

    public static Identifier fromNestedData(Object nestedData) {
        if (nestedData instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) nestedData;
            String source = (String) map.get("source");
            String type = (String) map.get("type");
            String value = (String) map.get("value");
            return new Identifier(source, type, value);
        }
        return null;
    }
}