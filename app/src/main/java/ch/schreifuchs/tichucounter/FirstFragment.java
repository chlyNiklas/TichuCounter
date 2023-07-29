package ch.schreifuchs.tichucounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import ch.schreifuchs.tichucounter.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Slider pointsSlider = binding.pointsSlider;
        TextView teamA = binding.pointsSliderLabelA;
        TextView teamB = binding.pointsSliderLabelB;
        pointsSlider.addOnChangeListener((slider, value, fromUser) -> {
            int[] values = {(int) value, (int) (100 - value)};

            for (int i = 0; i < values.length; i++) {
                if (values[i] < -25) values[i] = 0;
                if (values[i] > 125) values[i] = 200;
            }

            teamA.setText(String.valueOf(values[0]));
            teamB.setText(String.valueOf(values[1]));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}