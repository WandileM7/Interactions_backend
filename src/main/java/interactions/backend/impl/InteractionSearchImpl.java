package interactions.backend.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import interactions.backend.objects.Interaction;
import interactions.backend.search.PageResponse;
import interactions.backend.search.SearchCriteria;
import interactions.backend.search.SearchFilters;
import interactions.backend.search.SearchRequest;
import interactions.backend.search.SearchResponse;
import interactions.backend.search.TypeAndValueCriteria;
import interactions.backend.search.DateConverter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import interactions.backend.service.InteractionSearch;

@Slf4j
@Service
@RequiredArgsConstructor
public class InteractionSearchImpl implements InteractionSearch {
    private final MongoOperations mongoOperations;
    private final DateConverter dateConverter;

    @Override
    public SearchResponse searchInteractions(SearchRequest searchRequest) {
        SearchCriteria criteria = searchRequest.getCriteria();
        SearchFilters filters = searchRequest.getFilters();

        Criteria queryCriteria = new Criteria();
        List<Criteria> andCriteria = new ArrayList<>();

        // Initiator criteria
        andCriteria.add(createIdentifierCriteria("initiator", criteria.getInitiator()));

        // onBehalfOf criteria
        andCriteria.add(createIdentifierCriteria("onBehalfOf", criteria.getOnBehalfOf()));

        // Entity criteria
        Criteria entityCriteria = createIdentifierCriteria("entity", new TypeAndValueCriteria(criteria.getEntity().getIdentifierType(), criteria.getEntity().getIdentifierValue()));

        if ( criteria.getEntity().getOwner().getIdentifierType() != null && !criteria.getEntity().getOwner().getIdentifierType().isEmpty() || !criteria.getEntity().getOwner().getIdentifierValue().isEmpty()) {
            entityCriteria = entityCriteria.andOperator(createIdentifierCriteria("entity.owner", criteria.getEntity().getOwner()));
        }

        andCriteria.add(entityCriteria);

        queryCriteria.andOperator(andCriteria.toArray(new Criteria[0]));

        if (!filters.getAction().isEmpty()) {
            queryCriteria.and("action").is(filters.getAction());
        }

        if (filters.getStartDate() != null && !filters.getStartDate().isEmpty() && filters.getEndDate() != null && !filters.getEndDate().isEmpty()) {
            try {
                String startDate = dateConverter.convertToStartingTimestamp(filters.getStartDate());
                String endDate = dateConverter.convertToEndingTimestamp(filters.getEndDate());
                queryCriteria.and("creationDate").gte(startDate).lte(endDate);
            } catch (ParseException e) {
                log.error("Error parsing dates: Start Date - {}, End Date - {}", filters.getStartDate(), filters.getEndDate(), e);
            }
        } else if (filters.getStartDate() != null && !filters.getStartDate().isEmpty()) {
            try {
                String startDate = dateConverter.convertToStartingTimestamp(filters.getStartDate());
                queryCriteria.and("creationDate").gte(startDate);
            } catch (ParseException e) {
                log.error("Error parsing start date: {}", filters.getStartDate(), e);
            }
        } else if (filters.getEndDate() != null && !filters.getEndDate().isEmpty()) {
            try {
                String endDate = dateConverter.convertToEndingTimestamp(filters.getEndDate());
                queryCriteria.and("creationDate").lte(endDate);
            } catch (ParseException e) {
                log.error("Error parsing end date: {}", filters.getEndDate(), e);
            }
        }
        int pageSize = filters.getPageSize() != null ? filters.getPageSize() : 10;
        int offset = filters.getOffset() != null ? filters.getOffset() : 0;
        Pageable pageable = PageRequest.of(offset / pageSize, pageSize);

        Query query = new Query(queryCriteria).with(pageable);

        List<Interaction> matchingInteractions = mongoOperations.find(query, Interaction.class);

        log.debug("Executing count with criteria: {}", queryCriteria);

        long totalCount = mongoOperations.count(new Query(queryCriteria), Interaction.class);
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageNumber(offset / pageSize);
        pageResponse.setPageSize(pageSize);
        pageResponse.setSort(new PageResponse.Sort(true, false, true));
        pageResponse.setOffset(offset);
        pageResponse.setPaged(true);
        pageResponse.setUnpaged(false);
        pageResponse.setTotalPages(totalPages);
        pageResponse.setTotalElements(totalCount);
        pageResponse.setLast(offset + pageSize >= totalCount);
        pageResponse.setNumberOfElements(matchingInteractions.size());
        pageResponse.setFirst(offset == 0);

        return new SearchResponse(matchingInteractions, (int) totalCount, pageResponse);
    }

    Criteria createIdentifierCriteria(String fieldPrefix, TypeAndValueCriteria criteria) {
        Criteria identifierCriteria = new Criteria();

        if (!criteria.getIdentifierType().isEmpty()) {
            identifierCriteria.and(fieldPrefix + ".identifier.type").is(criteria.getIdentifierType());
        }

        if (!criteria.getIdentifierValue().isEmpty()) {
            identifierCriteria.and(fieldPrefix + ".identifier.value").is(criteria.getIdentifierValue());
        }

        return identifierCriteria;
    }


}