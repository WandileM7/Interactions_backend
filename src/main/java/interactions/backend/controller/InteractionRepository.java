package interactions.backend.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import interactions.backend.objects.Interaction;

import java.util.List;

public interface InteractionRepository extends MongoRepository<Interaction, String> {

    List<Interaction> findByInitiatorIdentifierTypeAndInitiatorIdentifierValue(String identifierType, String identifierValue, Pageable pageable);
    List<Interaction> findByOnBehalfOfIdentifierTypeAndOnBehalfOfIdentifierValue(String identifierType, String identifierValue, Pageable pageable);
}