package interactions.backend.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Detail {
    private int level;
    private String type;

    public Detail(int level, String type) {
        this.level = level;
        this.type = type;
    }

    public static Detail fromNestedData(Object nestedData) {
        if (nestedData instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) nestedData;
            int level = (int) map.get("level");
            String type = (String) map.get("type");
            return new Detail(level, type);
        }
        return null;
    }
}