package codaltec.initics.general.basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mike on 15/12/17.
 */
public class BaseDatos extends SQLiteOpenHelper {
    public final static String tabla_paginas = "paginas";
    public final static String identificador = "_id";
    public final static String nombre = "nombre";
    public final static String link = "link";
    public final static String type_text = "text";

    public BaseDatos(Context context) {
        super(context, "rss.sb", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE paginas(_id PRIMARY KEY AUTOINCREMENT ,nombre text not null, link TEXT NOT NULL  )",
                null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
