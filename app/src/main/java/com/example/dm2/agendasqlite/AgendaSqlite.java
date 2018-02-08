package com.example.dm2.agendasqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AgendaSqlite extends SQLiteOpenHelper{

    //sentencia SQL para crear la tabla de usuarios
    String sqlCreate = "CREATE TABLE agenda3 (dni INTEGER PRIMARY KEY AUTOINCREMENT,"+"nombre TEXT," + " numero INTEGER)";

    public AgendaSqlite (Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,int version){
        super(contexto,nombre,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //se ejecuta la sentencia SQL de creacion de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //se elimina la varsion anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS agenda2");

        //se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}
