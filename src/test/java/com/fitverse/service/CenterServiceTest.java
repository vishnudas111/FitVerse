package com.fitverse.service;

import com.fitverse.entity.Center;
import com.fitverse.exception.CenterException;
import com.fitverse.repository.CenterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterServiceTest {
    @Mock
    private CenterRepository centerRepository;

    @InjectMocks
    private CenterService centerService;

    @Test
    void addCenter_Success() {
        Center center = new Center("1", "Test Center", "Bangalore", List.of("Sunday"));
        when(centerRepository.save(center)).thenReturn(center);

        Center result = centerService.addCenter(center);

        assertNotNull(result);
        assertEquals("Test Center", result.getName());
        verify(centerRepository, times(1)).save(center);
    }

    @Test
    void getCenter_Success() throws CenterException {
        Center center = new Center("1", "Test Center", "Bangalore", List.of("Sunday"));
        when(centerRepository.findById("1")).thenReturn(Optional.of(center));

        Center result = centerService.getCenter("1");

        assertNotNull(result);
        assertEquals("Test Center", result.getName());
    }

    @Test
    void getCenter_NotFound() {
        when(centerRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(CenterException.class, () -> centerService.getCenter("1"));
    }

    @Test
    void getCentersByCity_Success() {
        Center center1 = new Center("1", "Center 1", "Bangalore", List.of("Sunday"));
        Center center2 = new Center("2", "Center 2", "Bangalore", List.of("Monday"));
        when(centerRepository.findByCity("Bangalore")).thenReturn(Arrays.asList(center1, center2));

        List<Center> results = centerService.getCentersByCity("Bangalore");

        assertEquals(2, results.size());
        assertEquals("Bangalore", results.get(0).getCity());
    }
}