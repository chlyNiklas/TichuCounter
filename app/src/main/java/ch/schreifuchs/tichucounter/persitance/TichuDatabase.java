package ch.schreifuchs.tichucounter.persitance;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Game.class, Score.class, Team.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class TichuDatabase extends RoomDatabase {
    private static TichuDatabase INSTANCE;
    public static synchronized TichuDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TichuDatabase.class, "TichuDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
