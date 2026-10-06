package com.example.instegramprojectroom.RoomDatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ImageConvertor {

    @TypeConverter
    private byte[] getByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        return stream.toByteArray();
    }
    @TypeConverter
    private Bitmap getBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }




}
