package interactions.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import interactions.backend.search.SearchRequest;
import interactions.backend.search.SearchResponse;
import interactions.backend.service.InteractionCreate;
import interactions.backend.service.InteractionSearch;
import interactions.backend.objects.InteractionDTO;



@Slf4j
@RestController
@RequiredArgsConstructor
public class InteractionController {
    private final InteractionCreate interactionCreate;
    private final InteractionSearch interactionSearch;

    @PostMapping(value = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResponse> searchInteractions(@RequestBody SearchRequest searchRequest) throws ParseException {
        log.info("Received request to search interactions: {}", searchRequest);
        SearchResponse searchResponse = interactionSearch.searchInteractions(searchRequest);
        log.info("Found {} interactions", searchResponse.getContent().size());
        return ResponseEntity.ok(searchResponse);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InteractionDTO> createInteraction(@RequestBody InteractionDTO interactionDTO) {
        log.info("Received request to create interaction: {}", interactionDTO);
        InteractionDTO createdInteraction = InteractionDTO.fromEntity(interactionCreate.createInteraction(interactionDTO));
        log.info("Interaction created: {}", createdInteraction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInteraction);
    }
}

