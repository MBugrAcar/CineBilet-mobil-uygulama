package com.example.cinebilet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cinebilet.databinding.FragmentSixthBinding;

import java.util.ArrayList;

public class SixthFragment extends Fragment {

    private FragmentSixthBinding binding;
    private SeatsViewModel seatsViewModel;

    // Sınıf düzeyinde değişkenler
    private String movieId;
    private String sessionId;

    // Seçilen koltuklar
    private ArrayList<Integer> selectedSeats = new ArrayList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSixthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel'i bağla
        seatsViewModel = new ViewModelProvider(requireActivity()).get(SeatsViewModel.class);

        // Bundle'dan film ve seans bilgilerini al
        Bundle args = getArguments();
        if (args != null) {
            movieId = args.getString("movieId", "default_movie");
            sessionId = args.getString("sessionId", "default_session");
        }

        // Koltukları ayarla (5x8 düzenindeki tüm butonlar için)
        for (int i = 1; i <= 40; i++) {
            String buttonId = "buttonSeat" + i;
            int resId = getResources().getIdentifier(buttonId, "id", getActivity().getPackageName());
            Button button = view.findViewById(resId);
            setupSeatButton(button, i);
        }

        // Ödeme sayfasına gitme butonu
        binding.buttonSixth.setOnClickListener(v -> {
            // Seçilen koltukları PaymentFragment'a aktarmak için Bundle kullanıyoruz
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("selectedSeats", selectedSeats);

            // PaymentFragment'a geçiş
            NavHostFragment.findNavController(SixthFragment.this)
                    .navigate(R.id.action_SixthFragment_to_PaymentFragment, bundle);
        });
    }

    private void setupSeatButton(Button button, int seatId) {
        // Başlangıç durumu - Koltuk seçili mi kontrol et
        final boolean[] isSelected = {seatsViewModel.isSeatSelected(movieId, sessionId, seatId)};
        updateSeatButtonColor(button, isSelected[0]);

        button.setOnClickListener(v -> {
            if (isSelected[0]) {
                seatsViewModel.deselectSeat(movieId, sessionId, seatId);
                selectedSeats.remove(Integer.valueOf(seatId));  // Seçilen koltuktan kaldır
            } else {
                seatsViewModel.selectSeat(movieId, sessionId, seatId);
                selectedSeats.add(seatId);  // Seçilen koltuğu listeye ekle
            }

            isSelected[0] = !isSelected[0]; // Yeni durumu güncelle
            updateSeatButtonColor(button, isSelected[0]);
        });
    }

    private void updateSeatButtonColor(Button button, boolean isSelected) {
        if (isSelected) {
            button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            button.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
