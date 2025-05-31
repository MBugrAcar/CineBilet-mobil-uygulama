package com.example.cinebilet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cinebilet.databinding.FragmentPaymentBinding;

import java.util.ArrayList;
import java.util.List;

public class PaymentFragment extends Fragment {

    private FragmentPaymentBinding binding;
    private String movieId;
    private String sessionId;
    private List<Integer> selectedSeats;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPaymentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Bundle'dan seçilen koltuklar ve film/seans bilgilerini al
        Bundle args = getArguments();
        if (args != null) {
            movieId = args.getString("movieId");
            sessionId = args.getString("sessionId");
            selectedSeats = args.getIntegerArrayList("selectedSeats");
        }

        RadioGroup radioGroup = binding.radioGroupTicketType;
        TextView totalPriceTextView = binding.textViewTotalPrice;
        TextView selectedSeatsTextView = binding.textViewSelectedSeats;

        // Seçilen koltukları ve film/seans bilgilerini görüntüle
        if (selectedSeats != null && !selectedSeats.isEmpty()) {
            String selectedSeatsText = "Seçilen Koltuklar: " + selectedSeats.toString();
            selectedSeatsTextView.setText(selectedSeatsText);
        } else {
            selectedSeatsTextView.setText("Henüz koltuk seçilmedi.");
        }

        // Bilet türü değiştiğinde fiyat güncelle
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            Ticket ticket = null;
            if (checkedId == R.id.radioStudentTicket) {
                ticket = new StudentTicket();
            } else if (checkedId == R.id.radioFullTicket) {
                ticket = new FullTicket();
            }

            if (ticket != null) {
                int totalPrice = ticket.calculateTotalPrice(selectedSeats.size());  // Koltuk sayısına göre fiyat hesapla
                totalPriceTextView.setText("Toplam Tutar: " + totalPrice + "₺");
            }
        });

        // Ödeme onaylama işlemi
        binding.buttonPayment.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(getContext(), "Lütfen bir bilet seçin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Seçilen bilet türünü ve toplam fiyatı göster
            RadioButton selectedButton = view.findViewById(selectedId);
            String ticketType = selectedButton.getText().toString();
            String totalPrice = totalPriceTextView.getText().toString();

            Toast.makeText(getContext(), ticketType + "\n" + totalPrice, Toast.LENGTH_SHORT).show();

            // Seçilen bilgileri Bundle ile bir sonraki sayfaya gönder
            Bundle bundle = new Bundle();
            bundle.putString("movieId", movieId);
            bundle.putString("sessionId", sessionId);
            bundle.putIntegerArrayList("selectedSeats", new ArrayList<>(selectedSeats));

            // Navigasyonu gerçekleştirme
            NavHostFragment.findNavController(PaymentFragment.this)
                    .navigate(R.id.action_PaymentFragment_to_FirstFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
