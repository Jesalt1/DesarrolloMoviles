package com.example.aplicaciondeprueba;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBasePractice extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NetCore.sqlite";
    private static final int DATABASE_VERSION = 1;

    // Nombre de la tabla y columnas
    private static final String Tabla_Usuario = "CREATE TABLE Usuarios (id integer primary key autoincrement, " +
            "Cedula TEXT," +
            "Nombres TEXT," +
            "Apellidos TEXT," +
            "Edad TEXT,"+
            "Rating FLOAT)";
    public DataBasePractice(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(Tabla_Usuario);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
