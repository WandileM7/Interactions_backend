package interactions.backend.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeAndValueCriteriaTest {

    @Test
    void testTypeAndValueCriteria() {
        String identifierType = "type";
        String identifierValue = "value";
        TypeAndValueCriteria criteria = new TypeAndValueCriteria(identifierType, identifierValue);

        assertEquals(identifierType, criteria.getIdentifierType());
        assertEquals(identifierValue, criteria.getIdentifierValue());

        criteria = new TypeAndValueCriteria();

        criteria.setIdentifierType(identifierType);
        criteria.setIdentifierValue(identifierValue);

        assertEquals(identifierType, criteria.getIdentifierType());
        assertEquals(identifierValue, criteria.getIdentifierValue());
    }
}
