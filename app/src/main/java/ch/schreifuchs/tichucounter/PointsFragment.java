package ch.schreifuchs.tichucounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.slider.Slider;

import java.util.Objects;

import ch.schreifuchs.tichucounter.databinding.FragmentPointsBinding;
import ch.schreifuchs.tichucounter.lib.ThreeWaySwitch;
import ch.schreifuchs.tichucounter.lib.Tichu;
import kotlin.Triple;

public class PointsFragment extends Fragment {

    private FragmentPointsBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPointsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Slider
        Slider pointsSlider = binding.pointsSlider;
        TextView labelTeamA = binding.pointsSliderLabelA;
        TextView labelTeamB = binding.pointsSliderLabelB;
        pointsSlider.addOnChangeListener((slider, value, fromUser) -> {
            int[] values = {(int) value, (int) (100 - value)};

            for (int i = 0; i < values.length; i++) {
                if (values[i] < -25) values[i] = 0;
                if (values[i] > 125) values[i] = 200;
            }

            labelTeamA.setText(String.valueOf(values[0]));
            labelTeamB.setText(String.valueOf(values[1]));
        });

        // Tichu 3 way Switches

        Triple<Integer, Tichu, ThreeWaySwitch>[] tichuSwitches = new Triple<>[4];
        tichuSwitches[0] = new Triple<>(0, Tichu.TICHU, binding.tichuA);
        tichuSwitches[1] = new Triple<>(0, Tichu.BIG_TICHU, binding.bigTichuA);
        tichuSwitches[2] = new Triple<>(1, Tichu.TICHU, binding.tichuB);
        tichuSwitches[3] = new Triple<>(1, Tichu.BIG_TICHU, binding.bigTichuB);


        for (Triple<Integer, Tichu, ThreeWaySwitch> tichuSwitch : tichuSwitches) {
            tichuSwitch.getThird().setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (progress == 2) {
                        for (Triple<Integer, Tichu, ThreeWaySwitch> tSwitch : tichuSwitches) {
                            if (!tSwitch.getFirst().equals(tichuSwitch.getFirst()) && tSwitch.getThird().getProgress() == 2) {
                                tichuSwitch.getThird().reset();
                                return;
                            }
                        }
                    }
                    if (progress != 1) {
                        for (Triple<Integer, Tichu, ThreeWaySwitch> tSwitch : tichuSwitches) {
                            if (Objects.equals(tSwitch.getFirst(), tichuSwitch.getFirst()) && tSwitch.getThird() != tichuSwitch.getThird()) {
                                tSwitch.getThird().setEnabled(false);
                            }
                        }
                    } else {
                        for (Triple<Integer, Tichu, ThreeWaySwitch> tSwitch : tichuSwitches) {
                            if (tSwitch.getThird().getProgress() != 1 && Objects.equals(tSwitch.getFirst(), tichuSwitch.getFirst())) {
                                return;
                            }
                        }
                        for (Triple<Integer, Tichu, ThreeWaySwitch> tSwitch : tichuSwitches) {
                            if (Objects.equals(tSwitch.getFirst(), tichuSwitch.getFirst())) {
                                tSwitch.getThird().setEnabled(true);
                            }
                        }
                    }

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}