package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityCriteriaTest {

    @Test
    void testEntityCriteria() {
        EntityCriteria criteria = new EntityCriteria();
        criteria.setIdentifierType("type");
        criteria.setIdentifierValue("value");
        TypeAndValueCriteria owner = new TypeAndValueCriteria("owner", "value");
        criteria.setOwner(owner);

        assertEquals("type", criteria.getIdentifierType());
        assertEquals("value", criteria.getIdentifierValue());
        assertEquals(owner, criteria.getOwner());
    }
}