package ch.schreifuchs.tichucounter.persitance;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Team {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String memberOne;
    private String memberTwo;
    private Timestamp createdAt;

    public Team(String name, String memberOne, String memberTwo) {
        this.name = name;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
        this.createdAt = new Timestamp(Instant.now().toEpochMilli());
    }

    public String getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(String memberOne) {
        this.memberOne = memberOne;
    }

    public String getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(String memberTwo) {
        this.memberTwo = memberTwo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
