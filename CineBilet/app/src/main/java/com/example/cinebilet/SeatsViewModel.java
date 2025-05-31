package com.example.cinebilet;

import androidx.lifecycle.ViewModel;

public class SeatsViewModel extends ViewModel {
    private final SeatSelectionRepository repository = SeatSelectionRepository.getInstance();

    public void selectSeat(String movieId, String sessionId, int seatId) {
        repository.selectSeat(movieId, sessionId, seatId);
    }

    public void deselectSeat(String movieId, String sessionId, int seatId) {
        repository.deselectSeat(movieId, sessionId, seatId);
    }

    public boolean isSeatSelected(String movieId, String sessionId, int seatId) {
        return repository.getSelectedSeats(movieId, sessionId).contains(seatId);
    }

    public String getSelectedSeatsSummary(String movieId, String sessionId) {
        return "Selected Seats: " + repository.getSelectedSeats(movieId, sessionId).toString();
    }

    public SeatSelectionRepository getRepository() {
        return repository;
    }
}
