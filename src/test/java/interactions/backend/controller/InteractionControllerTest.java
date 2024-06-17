package interactions.backend.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import interactions.backend.impl.InteractionCreateImpl;
import interactions.backend.search.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import interactions.backend.service.InteractionCreate;
import interactions.backend.service.InteractionSearch;
import interactions.backend.objects.Interaction;
import interactions.backend.objects.InteractionDTO;

import java.util.ArrayList;
import java.util.List;

class InteractionControllerTest {
    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private InteractionCreateImpl interactionCreateImpl;

    @Mock
    private InteractionCreate interactionCreate;

    @Mock
    private InteractionSearch interactionSearch;

    @InjectMocks
    private InteractionController interactionController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchInteractions() throws Exception {
        InteractionDTO interactionDTO = new InteractionDTO();
        Interaction savedInteraction = new Interaction();
        when(interactionRepository.save(any(Interaction.class))).thenReturn(savedInteraction);

        Interaction result = interactionCreateImpl.createInteraction(interactionDTO);
        SearchCriteria criteria = new SearchCriteria();
        SearchFilters filters = new SearchFilters();
        SearchRequest request = new SearchRequest();
        request.setCriteria(criteria);
        request.setFilters(filters);

        List<Interaction> content = new ArrayList<>();
        content.add(savedInteraction);
        content.add(result);
        int totalCount = 2;
        PageResponse pageable = new PageResponse();
        pageable.setPageNumber(1);
        pageable.setPageSize(10);

        SearchResponse response = new SearchResponse(content, totalCount, pageable);

        when(interactionSearch.searchInteractions(request)).thenReturn(response);

        ResponseEntity<SearchResponse> result1 = interactionController.searchInteractions(request);

        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals(response, result1.getBody());
    }

    @Test
    void testCreateInteraction() {
        InteractionDTO interactionDTO = new InteractionDTO();
        Interaction createdInteraction = new Interaction();

        when(interactionCreate.createInteraction(interactionDTO)).thenReturn(createdInteraction);
        when(interactionRepository.save(any(Interaction.class))).thenReturn(createdInteraction);

        ResponseEntity<InteractionDTO> result = interactionController.createInteraction(interactionDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }
}
