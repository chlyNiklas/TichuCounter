package ch.schreifuchs.tichucounter.persitance;

import androidx.room.TypeConverter;

import java.sql.Timestamp;

public class Converters {
    @TypeConverter
    public static Timestamp fromTimestamp(Long value) {
        return value == null ? null : new Timestamp(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    }

}