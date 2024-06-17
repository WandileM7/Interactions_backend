package interactions.backend.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Person {
    private Identifier identifier;
    private String name;

    public Person(Identifier identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public static Person fromNestedData(Object nestedData) {
        if (nestedData instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) nestedData;
            Identifier identifier = Identifier.fromNestedData(map.get("identifier"));
            String name = (String) map.get("name");
            return new Person(identifier, name);
        }
        return null;
    }

}