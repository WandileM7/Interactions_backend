package interactions.backend.impl;

import interactions.backend.controller.InteractionRepository;
import interactions.backend.objects.Interaction;
import interactions.backend.objects.InteractionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InteractionCreateImplTest {

    @Mock
    private InteractionRepository interactionRepository;

    @InjectMocks
    private InteractionCreateImpl interactionCreateImpl;

    @Test
    void testCreateInteraction() {
        InteractionDTO interactionDTO = new InteractionDTO();
        Interaction savedInteraction = new Interaction();
        when(interactionRepository.save(any(Interaction.class))).thenReturn(savedInteraction);

        Interaction result = interactionCreateImpl.createInteraction(interactionDTO);

        verify(interactionRepository, times(1)).save(any(Interaction.class));
        assertEquals(InteractionDTO.fromEntity(savedInteraction).toEntity(), result);
    }
}