package com.example.cinebilet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cinebilet.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // button_second için işlem
        binding.buttonSecond5.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 1", Toast.LENGTH_SHORT).show();

            // Bundle oluştur ve film, seans bilgilerini ekle
            Bundle bundle = new Bundle();
            bundle.putString("movieId", "movie_1"); // Film kimliği
            bundle.putString("sessionId", "session_2"); // Seans kimliği

            // FourthFragment'e geçiş yap ve bundle'ı ilet
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FourthFragment, bundle);
        });


        // button_second1 için işlem
        binding.buttonSecond.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 1", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);
        });
        binding.buttonSecond6.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 1", Toast.LENGTH_SHORT).show();

            // Bundle oluştur ve film, seans bilgilerini ekle
            Bundle bundle = new Bundle();
            bundle.putString("movieId", "movie_1"); // Film kimliği
            bundle.putString("sessionId", "session_3"); // Seans kimliği

            // FifthFragment'e geçiş yap ve bundle'ı ilet
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_FifthFragment, bundle);
        });
        binding.buttonSecond1.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 2", Toast.LENGTH_SHORT).show();

            // Bundle oluştur ve film, seans bilgilerini ekle
            Bundle bundle = new Bundle();
            bundle.putString("movieId", "movie_2"); // Film kimliği
            bundle.putString("sessionId", "session_1"); // Seans kimliği

            // FifthFragment'e geçiş yap ve bundle'ı ilet
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_SixthFragment, bundle);
        });
        binding.buttonSecond7.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 2", Toast.LENGTH_SHORT).show();

            // Bundle oluştur ve film, seans bilgilerini ekle
            Bundle bundle = new Bundle();
            bundle.putString("movieId", "movie_2"); // Film kimliği
            bundle.putString("sessionId", "session_2"); // Seans kimliği

            // FifthFragment'e geçiş yap ve bundle'ı ilet
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_SeventhFragment, bundle);
        });
        binding.buttonSecond8.setOnClickListener(v -> {
            Toast.makeText(getContext(), "SALON 2", Toast.LENGTH_SHORT).show();

            // Bundle oluştur ve film, seans bilgilerini ekle
            Bundle bundle = new Bundle();
            bundle.putString("movieId", "movie_2"); // Film kimliği
            bundle.putString("sessionId", "session_3"); // Seans kimliği

            // FifthFragment'e geçiş yap ve bundle'ı ilet
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_EighthFragment, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
