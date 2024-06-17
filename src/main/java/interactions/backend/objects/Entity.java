package interactions.backend.objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "entities")
public class Entity {

    private Identifier identifier;
    private Person owner;
    private List<Detail> details;

    public Entity( Identifier identifier, Person owner,  List<Detail> details) {
        this.identifier = identifier;
        this.owner = owner;
        this.details = details;
    }
    public Entity(Identifier identifier, List<Detail> details){
        this.identifier = identifier;
        this.details = details;
    }

    public static Entity fromNestedData(Object nestedData) {
        String own = "owner";
        String det = "details";
        String id = "identifier";
        if (!(nestedData instanceof Map)) {
            return null;
        }
        Map<String, Object> map = (Map<String, Object>) nestedData;
        Identifier identifier = map.containsKey(id) ? Identifier.fromNestedData(map.get(id)) : null;
        Person owner = map.containsKey(own) ? Person.fromNestedData(map.get(own)) : null;
        List<Detail> details = null;
        if (map.containsKey(det) && map.get(det) instanceof List) {
            details = ((List<Map<String, Object>>) map.get(det))
                    .stream()
                    .map(Detail::fromNestedData)
                    .collect(Collectors.toList());
        }
        return new Entity(identifier, owner, details);
    }
}