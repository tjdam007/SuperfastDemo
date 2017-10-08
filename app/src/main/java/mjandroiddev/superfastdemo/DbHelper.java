package mjandroiddev.superfastdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "superfastdemo.db";
    private static final int DATABASE_VERSION = 1;
    private static DbHelper sInstance;
    private static SQLiteDatabase db;


    public synchronized static DbHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
            db = sInstance.getWritableDatabase();
        }
        return sInstance;
    }

    private DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        // register our models
        cupboard().register(City.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // this will ensure that all tables are created
        cupboard().withDatabase(db).createTables();
        // add indexes and other database tweaks in this method if you want
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this will upgrade tables, adding columns and new tables.
        // Note that existing columns will not be converted
        cupboard().withDatabase(db).upgradeTables();
        // do migration work if you have an alteration to make to your schema here

    }


    public ArrayList<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();
        Cursor cursor = cupboard()
                .withDatabase(db)
                .query(City.class)
                .getCursor();
        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    City city = new City(cursor.getLong(cursor.getColumnIndex("_id")),
                            cursor.getString(cursor.getColumnIndex("name")));
                    cities.add(city);
                }
            }
        } finally {
            cursor.close();
        }
        return cities;
    }

    public void insertIntoCity(City city) {
        cupboard().withDatabase(db)
                .put(city);
    }
}