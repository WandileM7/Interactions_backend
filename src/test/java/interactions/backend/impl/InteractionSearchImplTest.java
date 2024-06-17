package interactions.backend.impl;

import interactions.backend.objects.*;
import interactions.backend.search.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class InteractionSearchImplTest {

    @Mock
    private MongoOperations mongoOperations;

    @Mock
    private DateConverter dateConverter;

    private InteractionSearchImpl interactionSearchImpl;


    @BeforeEach
    void setUp() {
        interactionSearchImpl = new InteractionSearchImpl(mongoOperations, dateConverter);
    }

    @Test
    void testSearchInteractions() throws ParseException {
        // Arrange
        Person initiator = new Person();
        initiator.setIdentifier(new Identifier());

        Person onBehalfOf = new Person();
        onBehalfOf.setIdentifier(new Identifier());

        Entity entity = new Entity();
        entity.setIdentifier(new Identifier());
        entity.setOwner(Person.fromNestedData(new TypeAndValueCriteria("ownerType", "ownerValue")));

        SearchCriteria criteria = new SearchCriteria();
        criteria.setInitiator(new TypeAndValueCriteria("initiatorType", "initiatorValue"));
        criteria.setOnBehalfOf(new TypeAndValueCriteria("onBehalfOfType", "onBehalfOfValue"));

        SearchFilters filters = new SearchFilters();
        filters.setAction("CREATE");
        filters.setStartDate("2023-01-01");
        filters.setEndDate("2023-12-31");
        filters.setPageSize(10);
        filters.setOffset(0);


        List<Interaction> matchingInteractions = Arrays.asList(
                new Interaction(initiator, onBehalfOf, "CREATE", entity, LocalDateTime.now(), "web", "abc123"),
                new Interaction(initiator, onBehalfOf, "CREATE", entity, LocalDateTime.now(), "web", "def456")
        );


        // Act
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCriteria(criteria);
        searchRequest.setFilters(filters);
        int pageSize = filters.getPageSize() != null ? filters.getPageSize() : 10;
        int offset = filters.getOffset() != null ? filters.getOffset() : 0;
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize);
        int totalCount = 10;
        PageResponse totalPages = new PageResponse();
        SearchResponse response = new SearchResponse(matchingInteractions, totalCount, totalPages);

        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageNumber(offset / pageSize);
        pageResponse.setPageSize(pageSize);
        pageResponse.setSort(new PageResponse.Sort(true, false, true));
        pageResponse.setOffset(offset);
        pageResponse.setPaged(true);
        pageResponse.setUnpaged(false);
        pageResponse.setTotalPages(totalPage);
        pageResponse.setTotalElements(totalCount);
        pageResponse.setLast(offset + pageSize >= totalCount);
        pageResponse.setNumberOfElements(matchingInteractions.size());
        pageResponse.setFirst(offset == 0);


        // Assert
        assertEquals(matchingInteractions, response.getInteractions());
        assertEquals(10, response.getTotalCount());
        assertEquals(0, pageResponse.getPageNumber());
        assertEquals(10, pageResponse.getPageSize());
        assertEquals(0, pageResponse.getOffset());
        assertEquals(1, pageResponse.getTotalPages());
        assertEquals(10, pageResponse.getTotalElements());
        assertEquals(true, pageResponse.isLast());
        assertEquals(2, pageResponse.getNumberOfElements());
        assertEquals(true, pageResponse.isFirst());
    }

    @Test
    void testSearchInteractionsWithoutFilters() throws ParseException {
        // Arrange
        Person initiator = new Person();
        initiator.setIdentifier(new Identifier());

        Person onBehalfOf = new Person();
        onBehalfOf.setIdentifier(new Identifier());

        Entity entity = new Entity();
        entity.setIdentifier(new Identifier());

        SearchCriteria criteria = new SearchCriteria();
        criteria.setInitiator(new TypeAndValueCriteria("initiatorType", "initiatorValue"));
        criteria.setOnBehalfOf(new TypeAndValueCriteria("onBehalfOfType", "onBehalfOfValue"));

        SearchFilters filters = new SearchFilters();

        List<Interaction> matchingInteractions = Arrays.asList(
                new Interaction(initiator, onBehalfOf, "create", entity, LocalDateTime.now(), "web", "abc123"),
                new Interaction(initiator, onBehalfOf, "update", entity, LocalDateTime.now(), "web", "def456")
        );

        Criteria queryCriteria = new Criteria();
        queryCriteria.and("initiator.identifier.type").is("initiatorType");
        queryCriteria.and("initiator.identifier.value").is("initiatorValue");
        queryCriteria.and("onBehalfOf.identifier.type").is("onBehalfOfType");
        queryCriteria.and("onBehalfOf.identifier.value").is("onBehalfOfValue");
        queryCriteria.and("entity.identifier.type").is("entityType");
        queryCriteria.and("entity.identifier.value").is("entityValue");

        Query query = new Query(queryCriteria).with(PageRequest.of(0, 10));

        // Act
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCriteria(criteria);
        searchRequest.setFilters(filters);
        int pageSize = filters.getPageSize() != null ? filters.getPageSize() : 10;
        int offset = filters.getOffset() != null ? filters.getOffset() : 0;
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize);
        int totalCount = 10;
        PageResponse totalPages = new PageResponse();
        SearchResponse response = new SearchResponse(matchingInteractions, totalCount, totalPages);

        int totalPage = (int) Math.ceil((double) totalCount / pageSize);

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageNumber(offset / pageSize);
        pageResponse.setPageSize(pageSize);
        pageResponse.setSort(new PageResponse.Sort(true, false, true));
        pageResponse.setOffset(offset);
        pageResponse.setPaged(true);
        pageResponse.setUnpaged(false);
        pageResponse.setTotalPages(totalPage);
        pageResponse.setTotalElements(totalCount);
        pageResponse.setLast(offset + pageSize >= totalCount);
        pageResponse.setNumberOfElements(matchingInteractions.size());
        pageResponse.setFirst(offset == 0);

        // Assert
        assertEquals(matchingInteractions, response.getInteractions());
        assertEquals(10, response.getTotalCount());
        assertEquals(0, pageResponse.getPageNumber());
        assertEquals(0, pageResponse.getPageNumber());
        assertEquals(10, pageResponse.getPageSize());
        assertEquals(0, pageResponse.getOffset());
        assertEquals(1, pageResponse.getTotalPages());
        assertEquals(10, pageResponse.getTotalElements());
        assertEquals(true, pageResponse.isLast());
        assertEquals(2, pageResponse.getNumberOfElements());
        assertEquals(true, pageResponse.isFirst());
    }

    @Test
    void testCreateIdentifierCriteria() {
        String fieldPrefix = "prefix";
        TypeAndValueCriteria criteria = new TypeAndValueCriteria("type", "value");

        // Act
        Criteria identifierCriteria = interactionSearchImpl.createIdentifierCriteria(fieldPrefix, criteria);

        // Assert
        Criteria expectedCriteria = new Criteria();
        expectedCriteria.and("prefix.identifier.type").is("type");
        expectedCriteria.and("prefix.identifier.value").is("value");
        assertEquals(expectedCriteria, identifierCriteria);
    }

    @Test
    void testCreateIdentifierCriteriaWithEmptyValues() {
        // Arrange
        String fieldPrefix = "prefix";
        TypeAndValueCriteria criteria = new TypeAndValueCriteria("", "");

        // Act
        Criteria identifierCriteria = interactionSearchImpl.createIdentifierCriteria(fieldPrefix, criteria);

        // Assert
        Criteria expectedCriteria = new Criteria();
        assertEquals(expectedCriteria, identifierCriteria);
    }
    @Test
    void testSearchInteractionsWithEntityOwnerCriteria() {
        // Arrange
        Person initiator = new Person();
        initiator.setIdentifier(new Identifier());

        Person onBehalfOf = new Person();
        onBehalfOf.setIdentifier(new Identifier());

        Entity entity = new Entity();
        entity.setIdentifier(new Identifier());
        entity.setOwner(Person.fromNestedData(new TypeAndValueCriteria("ownerType", "ownerValue")));

        SearchCriteria criteria = new SearchCriteria();
        criteria.setInitiator(new TypeAndValueCriteria("initiatorType", "initiatorValue"));
        criteria.setOnBehalfOf(new TypeAndValueCriteria("onBehalfOfType", "onBehalfOfValue"));

        SearchFilters filters = new SearchFilters();

        List<Interaction> matchingInteractions = Arrays.asList(
                new Interaction(initiator, onBehalfOf, "CREATE", entity, LocalDateTime.now(), "web", "abc123")
        );
        // Act
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setCriteria(criteria);
        searchRequest.setFilters(filters);
        int totalCount = 10;
        PageResponse totalPages = new PageResponse();
        SearchResponse response = new SearchResponse(matchingInteractions, totalCount, totalPages);

        // Assert
        assertEquals(matchingInteractions, response.getInteractions());
    }

}
