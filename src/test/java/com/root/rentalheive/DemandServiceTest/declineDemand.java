package com.root.rentalheive.DemandServiceTest;

import com.root.rentalheive.entities.Demand;
import com.root.rentalheive.enums.DemandStatus;
import com.root.rentalheive.repositories.DemandeRepository;
import com.root.rentalheive.services.DemandServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class declineDemand {
    @Mock
    private DemandeRepository demandeRepository;

    @InjectMocks
    private DemandServiceImp demandService;
    @Test
    public void declineExistingDemand() {
//        Long id = 1L;
//        demandeRepository.declineDemand(id);

        Demand demand = mock(Demand.class);

        when(demandeRepository.declineDemand(any(Long.class), eq(DemandStatus.DECLINED))).thenReturn(1);
        when(demandeRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(demand));

        Map response = demandService.declineDemand(1L);
        assertEquals("Demand declined successfully", response.get("message"));
        assertEquals(demand, response.get("demand"));

    }
    @Test
    public void declineNonExistingDemand() {
        Long id = 1L;
        when(demandeRepository.declineDemand(any(Long.class), eq(DemandStatus.DECLINED))).thenReturn(0);
//        when(demandeRepository.findById(1L)).thenReturn(Optional.ofNullable(null)); // not needed

        Map response = demandService.declineDemand(id);
        assertEquals("Failed to decline demand. Demand not found.", response.get("message"));
        assertEquals(null, response.get("demand"));

        verify(demandeRepository, times(1)).declineDemand(eq(1L), eq(DemandStatus.DECLINED));
        // Verify that findById was not called since no rows were updated
        verify(demandeRepository, never()).findById(any(Long.class));
    }


}

