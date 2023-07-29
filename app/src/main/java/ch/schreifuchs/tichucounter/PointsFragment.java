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
import ch.schreifuchs.tichucounter.lib.Team;
import ch.schreifuchs.tichucounter.lib.ThreeWaySwitch;
import ch.schreifuchs.tichucounter.lib.Tichu;
import kotlin.Triple;

public class PointsFragment extends Fragment {

    private FragmentPointsBinding binding;
    private final Triple<Integer, Tichu, ThreeWaySwitch>[] tichuSwitches = new Triple[4];
    private final Team[] teams = new Team[2];

    private final int[] currentBasePoints = new int[2];

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

        teams[0] = new Team();
        teams[1] = new Team();


        // Slider
        Slider pointsSlider = binding.pointsSlider;
        TextView labelTeamA = binding.pointsSliderLabelA;
        TextView labelTeamB = binding.pointsSliderLabelB;
        pointsSlider.addOnChangeListener((slider, value, fromUser) -> {
            currentBasePoints[0] = (int) value;
            currentBasePoints[1] = (int) (100 - value);

            for (int i = 0; i < currentBasePoints.length; i++) {
                if (currentBasePoints[i] < -25) currentBasePoints[i] = 0;
                if (currentBasePoints[i] > 125) currentBasePoints[i] = 200;
            }

            labelTeamA.setText(String.valueOf(currentBasePoints[0]));
            labelTeamB.setText(String.valueOf(currentBasePoints[1]));
        });

        // Tichu 3 way Switches
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
        //save
        binding.saveButton.setOnClickListener(e -> save());

        //undo
        binding.undoButton.setOnClickListener(e -> undo());

    }

    private void save() {
        for (int i = 0; i < teams.length; i++) {
            int score = currentBasePoints[i];
            for (Triple<Integer, Tichu, ThreeWaySwitch> tichuSwitch : tichuSwitches
            ) {
                if (tichuSwitch.getFirst().equals(i)) {
                    switch (tichuSwitch.getThird().getState()) {
                        case TRUE:
                            if (tichuSwitch.getSecond().equals(Tichu.BIG_TICHU)) {
                                score += 200;
                            } else {
                                score += 100;
                            }
                            break;
                        case FALSE:
                            if (tichuSwitch.getSecond().equals(Tichu.BIG_TICHU)) {
                                score -= 200;
                            } else {
                                score -= 100;
                            }
                            break;
                    }
                }
            }
            teams[i].addToScore(score);
        }
        reset();
    }

    private void reset() {
        for (Triple<Integer, Tichu, ThreeWaySwitch> tichuSwitch : tichuSwitches) {
            tichuSwitch.getThird().reset();
        }
        updateScore();
    }
    private void undo() {
        for (Team team: teams) {
            team.undo();
        }
        updateScore();
    }
    private void updateScore() {
        binding.scoreA.setText(String.valueOf(teams[0].getScore()));
        binding.scoreB.setText(String.valueOf(teams[1].getScore()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}