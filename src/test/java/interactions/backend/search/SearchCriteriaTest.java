package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SearchCriteriaTest {

    @Test
    void testSearchCriteria() {
        TypeAndValueCriteria initiator = new TypeAndValueCriteria("initiator", "value");
        TypeAndValueCriteria onBehalfOf = new TypeAndValueCriteria("onBehalfOf", "value");
        EntityCriteria entity = new EntityCriteria();
        entity.setIdentifierType("entity");
        entity.setIdentifierValue("value");
        entity.setOwner(new TypeAndValueCriteria("owner", "value"));

        SearchCriteria criteria = new SearchCriteria();
        criteria.setInitiator(initiator);
        criteria.setOnBehalfOf(onBehalfOf);
        criteria.setEntity(entity);

        assertEquals(initiator, criteria.getInitiator());
        assertEquals(onBehalfOf, criteria.getOnBehalfOf());
        assertEquals(entity, criteria.getEntity());
    }
}
