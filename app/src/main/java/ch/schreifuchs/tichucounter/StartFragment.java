package ch.schreifuchs.tichucounter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ch.schreifuchs.tichucounter.databinding.FragmentPointsBinding;
import ch.schreifuchs.tichucounter.databinding.FragmentStartBinding;
import ch.schreifuchs.tichucounter.persitance.GameDao;
import ch.schreifuchs.tichucounter.persitance.TichuDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {
    private FragmentStartBinding binding;


    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentStartBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GameDao gameDao = TichuDatabase.getInstance(getContext()).getGameDao();
        TextView stateText = binding.statusText;
        if (gameDao.findLast() == null) {
            stateText.setText("there is no last game");
        } else {
            stateText.setText("Want to start game from" + gameDao.findLast().getStartedAt());
        }
    }
}