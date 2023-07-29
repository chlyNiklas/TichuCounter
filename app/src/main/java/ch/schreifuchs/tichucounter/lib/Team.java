package ch.schreifuchs.tichucounter.lib;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final List<Integer> scoreOperations = new ArrayList<>();

    public Team() {
    }

    public int getScore() {
        int score = 0;
        for (Integer operation: scoreOperations) {
            score += operation;
        }
        return score;
    }

    public void addToScore(int summand) {
        scoreOperations.add(summand);
    }

    public void undo() {
        scoreOperations.remove(-scoreOperations.size() -1);
    }
}

