package com.example.agenda;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
        public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE agenda(id INTEGER PRIMARY KEY, fecha TEXT NOT NULL, asunto TEXT NOT NULL, actividad TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
