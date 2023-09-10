package ch.schreifuchs.tichucounter.persitance;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


import java.sql.Timestamp;
import java.time.Instant;

@Entity(foreignKeys = {
        @ForeignKey(entity = Game.class, parentColumns = "id", childColumns = "game", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Team.class, parentColumns = "id", childColumns = "team", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)
})
public class Score {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private int score;
    private double game;
    private double team;
    private Timestamp createdAt;

    public Score(int score, Game game, Team team) {
        this.score = score;
        this.game = game.getId();
        this.team = team.getId();
        this.createdAt = new Timestamp(Instant.now().toEpochMilli());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getGame() {
        return game;
    }

    public void setGame(double game) {
        this.game = game;
    }

    public double getTeam() {
        return team;
    }

    public void setTeam(double team) {
        this.team = team;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}