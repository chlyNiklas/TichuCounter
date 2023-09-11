package ch.schreifuchs.tichucounter.persitance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDao {
    @Insert
    void insertAll(Game... games);

    @Delete
    void delete(Game game);

    @Query("SELECT * FROM Game WHERE endedAt IS NULL AND startedAt IS (SELECT MAX(startedAt) FROM Game WHERE endedAt IS NULL);")
    Game findLast();
    @Query("SELECT * FROM Game WHERE id IS :id;")
    Game findById(long id);

    @Query("SELECT * FROM Game;")
    List<Game> findAll();
}
