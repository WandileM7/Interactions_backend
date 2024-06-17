package interactions.backend.service;

import interactions.backend.objects.Interaction;
import interactions.backend.objects.InteractionDTO;

public interface InteractionCreate {
    Interaction createInteraction(InteractionDTO interactionDTO);
}