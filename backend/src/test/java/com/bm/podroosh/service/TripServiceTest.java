package com.bm.podroosh.service;

import com.bm.podroosh.domain.Trip;
import com.bm.podroosh.domain.TripStatus;
import com.bm.podroosh.dto.TripRequest;
import com.bm.podroosh.dto.TripResponse;
import com.bm.podroosh.exceptions.TripNotFoundExceptionApi;
import com.bm.podroosh.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    private final LocalDate start = LocalDate.of(2026, 6, 1);
    private final LocalDate end = LocalDate.of(2026, 6, 15);

    // ---------- createTrip ----------

    @Test
    void createTrip_shouldSaveAndReturnResponse() {
        // given
        TripRequest request = new TripRequest(
                "Włochy 2026",
                "Toskania",
                null,
                start,
                end
        );

        Trip savedTrip = Trip.builder()
                .name("Włochy 2026")
                .description("Toskania")
                .status(TripStatus.CREATED)
                .startDate(start)
                .endDate(end)
                .build();
        savedTrip.setId(1L);

        when(tripRepository.save(any(Trip.class))).thenReturn(savedTrip);

        // when
        TripResponse response = tripService.createTrip(request);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Włochy 2026");
        assertThat(response.description()).isEqualTo("Toskania");
        assertThat(response.status()).isEqualTo(TripStatus.CREATED);
        assertThat(response.startDate()).isEqualTo(start);
        assertThat(response.endDate()).isEqualTo(end);

        ArgumentCaptor<Trip> captor = ArgumentCaptor.forClass(Trip.class);
        verify(tripRepository).save(captor.capture());

        Trip toSave = captor.getValue();
        assertThat(toSave.getName()).isEqualTo("Włochy 2026");
        assertThat(toSave.getStatus()).isEqualTo(TripStatus.CREATED);
        assertThat(toSave.getId()).isNull();
    }

    // ---------- getAllTrips ----------

    @Test
    void getAllTrips_shouldReturnMappedList() {
        // given
        Trip trip1 = trip(1L, "Trip 1");
        Trip trip2 = trip(2L, "Trip 2");

        when(tripRepository.findAll()).thenReturn(List.of(trip1, trip2));

        // when
        List<TripResponse> result = tripService.getAllTrips();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).id()).isEqualTo(trip1.getId());
        assertThat(result.get(0).name()).isEqualTo(trip1.getName());
        assertThat(result.get(1).id()).isEqualTo(trip2.getId());
        assertThat(result.get(1).name()).isEqualTo(trip2.getName());
    }

    @Test
    void getAllTrips_shouldReturnEmptyList() {
        when(tripRepository.findAll()).thenReturn(List.of());

        List<TripResponse> result = tripService.getAllTrips();

        assertThat(result).isEmpty();
    }

    // ---------- getTrip ----------

    @Test
    void getTrip_shouldReturnTrip() {
        // given
        Trip trip = trip(10L, "Grecja");
        when(tripRepository.findById(10L)).thenReturn(Optional.of(trip));

        // when
        TripResponse response = tripService.getTrip(10L);

        // then
        assertThat(response.id()).isEqualTo(trip.getId());
        assertThat(response.name()).isEqualTo(trip.getName());
        assertThat(response.status()).isEqualTo(TripStatus.CREATED);
    }

    @Test
    void getTrip_shouldThrowWhenNotFound() {
        when(tripRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tripService.getTrip(99L))
                .isInstanceOf(TripNotFoundExceptionApi.class);
        // ewentualnie:
        // .hasMessageContaining("99");
    }

    // ---------- updateTrip ----------

    @Test
    void updateTrip_shouldUpdateAllFields() {
        // given
        Trip existing = trip(5L, "Stara nazwa");
        existing.setDescription("Stary opis");
        existing.setStatus(TripStatus.CREATED);
        existing.setStartDate(start);
        existing.setEndDate(end);

        TripRequest request = new TripRequest(
                "Nowa nazwa",
                "Nowy opis",
                TripStatus.IN_PROGRESS,
                start.plusDays(1),
                end.plusDays(1)
        );

        when(tripRepository.findById(5L)).thenReturn(Optional.of(existing));

        // when
        TripResponse response = tripService.updateTrip(5L, request);

        // then
        assertThat(response.name()).isEqualTo(request.name());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.status()).isEqualTo(request.status());
        assertThat(response.startDate()).isEqualTo(request.startDate());
        assertThat(response.endDate()).isEqualTo(request.endDate());

        // dirty checking – nie musimy weryfikować save()
        verify(tripRepository).findById(5L);
        verify(tripRepository, never()).save(any());
    }

    @Test
    void updateTrip_shouldNotOverrideNullFields() {
        // given
        Trip existing = trip(5L, "Oryginalna nazwa");
        existing.setDescription("Oryginalny opis");
        existing.setStatus(TripStatus.CREATED);
        existing.setStartDate(start);
        existing.setEndDate(end);

        // status, startDate, endDate = null → nie nadpisujemy
        TripRequest request = new TripRequest(
                "Zmieniona nazwa",
                "Zmieniony opis",
                null,
                null,
                null
        );

        when(tripRepository.findById(5L)).thenReturn(Optional.of(existing));

        // when
        TripResponse response = tripService.updateTrip(5L, request);

        // then
        assertThat(response.name()).isEqualTo(request.name());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.status()).isEqualTo(TripStatus.CREATED); 
        assertThat(response.startDate()).isEqualTo(start);           
        assertThat(response.endDate()).isEqualTo(end);               
    }

    @Test
    void updateTrip_shouldThrowWhenNotFound() {
        when(tripRepository.findById(123L)).thenReturn(Optional.empty());

        TripRequest request = new TripRequest("X", "Y", null, null, null);

        assertThatThrownBy(() -> tripService.updateTrip(123L, request))
                .isInstanceOf(TripNotFoundExceptionApi.class);

        verify(tripRepository, never()).save(any());
    }

    // ---------- helper ----------

    private Trip trip(Long id, String name) {
        Trip trip = Trip.builder()
                .name(name)
                .description("desc")
                .status(TripStatus.CREATED)
                .startDate(start)
                .endDate(end)
                .build();
        trip.setId(id);
        return trip;
    }
}