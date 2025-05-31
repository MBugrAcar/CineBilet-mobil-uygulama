package com.example.cinebilet;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeatSelectionRepository {
    private static SeatSelectionRepository instance;

    // Seçilen koltuklar için depolama: Map<FilmID + SeansID, SeçilenKoltuklarListesi>
    private final Map<String, List<Integer>> seatSelections;

    private SeatSelectionRepository() {
        seatSelections = new HashMap<>();
    }

    public static synchronized SeatSelectionRepository getInstance() {
        if (instance == null) {
            instance = new SeatSelectionRepository();
        }
        return instance;
    }

    // Koltuk seçimini ekle
    public void selectSeat(String movieId, String sessionId, int seatId) {
        String key = generateKey(movieId, sessionId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seatSelections.putIfAbsent(key, new ArrayList<>());
        }
        if (!seatSelections.get(key).contains(seatId)) {
            seatSelections.get(key).add(seatId);
        }
    }

    // Koltuk seçimini kaldır
    public void deselectSeat(String movieId, String sessionId, int seatId) {
        String key = generateKey(movieId, sessionId);
        if (seatSelections.containsKey(key)) {
            seatSelections.get(key).remove((Integer) seatId);
        }
    }

    // Belirli bir film/seans için seçilen koltukları getir
    public List<Integer> getSelectedSeats(String movieId, String sessionId) {
        String key = generateKey(movieId, sessionId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return seatSelections.getOrDefault(key, new ArrayList<>());
        }
        return java.util.Collections.emptyList();
    }

    // Tüm seçilen koltuklar
    public Map<String, List<Integer>> getAllSelections() {
        return seatSelections;
    }

    // Key üretmek için yardımcı metot
    private String generateKey(String movieId, String sessionId) {
        return movieId + "_" + sessionId;
    }
}
