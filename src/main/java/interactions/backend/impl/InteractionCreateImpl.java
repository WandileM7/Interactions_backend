package interactions.backend.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import interactions.backend.controller.InteractionRepository;
import interactions.backend.objects.Interaction;
import interactions.backend.objects.InteractionDTO;
import interactions.backend.service.InteractionCreate;

@Slf4j
@Service
@RequiredArgsConstructor
public class InteractionCreateImpl implements InteractionCreate {
    private final InteractionRepository interactionRepository;

    @Override
    public Interaction createInteraction(InteractionDTO interactionDTO) {
        log.debug("Creating interaction: {}", interactionDTO);
        Interaction interaction = interactionDTO.toEntity();
        return interactionRepository.save(interaction);
    }
}