package com.example.myroomdatabase.database;

import androidx.room.TypeConverter;

import java.util.Date;


public class DateConverter {
    @TypeConverter
    public static Date toDate(Long milliesec){
        return milliesec==null? null : new Date(milliesec);
    }
    @TypeConverter
    public static Long fromDate(Date date){
        return date==null? null : date.getTime();
    }

}
