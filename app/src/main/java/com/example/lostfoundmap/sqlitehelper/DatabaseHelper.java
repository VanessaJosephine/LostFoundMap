package com.example.lostfoundmap.sqlitehelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create advert table
        String CREATE_TABLE_COMMAND = "CREATE TABLE "
                + Util.TABLE_NAME + "("
                + Util.ADVERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Util.NAME +" TEXT,"
                + Util.TYPE +" TEXT,"
                + Util.PHONE +" TEXT,"
                + Util.DESCRIPTION +" TEXT,"
                + Util.DATE +" TEXT,"
                + Util.LOCATION +" TEXT,"
                + Util.LATITUDE +" TEXT,"
                + Util.LONGITUDE +" TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    // INSERT
    public long insertAdvert(Advert advert){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.NAME, advert.getName());
        contentValues.put(Util.TYPE, advert.getType());
        contentValues.put(Util.PHONE, advert.getPhone());
        contentValues.put(Util.DESCRIPTION, advert.getDesc());
        contentValues.put(Util.DATE, advert.getDate());
        contentValues.put(Util.LOCATION, advert.getLocation());
        contentValues.put(Util.LATITUDE, advert.getLatitude());
        contentValues.put(Util.LONGITUDE, advert.getLongitude());

        long rowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return rowId;
    }
    // DELETE
    public void deleteAdvert(String advertName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME, "name=?", new String[]{advertName});
        db.close();
    }
    // GET
    public ArrayList<Advert> getAllAdverts(){
        ArrayList<Advert> adverts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                adverts.add(new Advert(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return adverts;
    }
}