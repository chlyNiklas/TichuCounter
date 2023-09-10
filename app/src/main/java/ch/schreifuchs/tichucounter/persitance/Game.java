package ch.schreifuchs.tichucounter.persitance;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    private double id;
    private Timestamp startedAt;
    private Timestamp endedAt;

    public Game(){
        this.startedAt = new Timestamp(Instant.now().toEpochMilli());
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }


    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Timestamp endedAt) {
        this.endedAt = endedAt;
    }
}
