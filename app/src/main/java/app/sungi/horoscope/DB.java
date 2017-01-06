package app.sungi.horoscope;

/**
 * Created by SUNGI on  Янв. 05. 2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    private static final String DB_NAME = "horoscope";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_GENERAL = "general_horoscope";
    private static final String DB_TABLE_DETAILED = "detailed_horoscope";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SIGN_NAME = "sign";
    public static final String COLUMN_TODAY_DATE = "today";
    public static final String COLUMN_TODAY_INFO = "today_info";
    public static final String COLUMN_YESTERDAY_DATE = "yesterday";
    public static final String COLUMN_YESTERDAY_INFO = "yesterday_info";
    public static final String COLUMN_TOMORROW_DATE = "tomorrow";
    public static final String COLUMN_TOMORROW_INFO = "tomorrow_info";
    public static final String COLUMN_WEEK = "week";
    public static final String COLUMN_WEEK_INFO = "week_info";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_MONTH_INFO = "month_info";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_YEAR_INFO = "year_info";

    private static final String DB_GENERAL_CREATE =
            "create table " + DB_TABLE_GENERAL + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_SIGN_NAME + " text, " +
                    COLUMN_YESTERDAY_DATE + " text, " +
                    COLUMN_YESTERDAY_INFO + " text, " +
                    COLUMN_TODAY_DATE + " text, " +
                    COLUMN_TODAY_INFO + " text, " +
                    COLUMN_TOMORROW_DATE + " text, " +
                    COLUMN_TOMORROW_INFO + " text" +
                    ");";

    private static final String DB_DETAILED_CREATE =
            "create table " + DB_TABLE_DETAILED + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_SIGN_NAME + " text, " +
                    COLUMN_YESTERDAY_DATE + " text, " +
                    COLUMN_YESTERDAY_INFO + " text, " +
                    COLUMN_TODAY_DATE + " text, " +
                    COLUMN_TODAY_INFO + " text, " +
                    COLUMN_TOMORROW_DATE + " text, " +
                    COLUMN_TOMORROW_INFO + " text, " +
                    COLUMN_WEEK + " text, " +
                    COLUMN_WEEK_INFO + " text, " +
                    COLUMN_MONTH + " text, " +
                    COLUMN_MONTH_INFO + " text, " +
                    COLUMN_YEAR + " text, " +
                    COLUMN_YEAR_INFO + " text" +
                    ");";

    public String[] sign_name = {"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};

    private final Context mCtx;


    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    // open connection to database
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // close connection to database
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    // get all data from table DB_TABLE_GENERAL
    public Cursor getAllDataGeneralTable() {
        return mDB.query(DB_TABLE_GENERAL, null, null, null, null, null, null);
    }

    // get all data from table DB_TABLE_DETAILED
    public Cursor getAllDataDetailedTable() {
        return mDB.query(DB_TABLE_DETAILED, null, null, null, null, null, null);
    }

    // add record to DB_TABLE_GENERAL
    public void addRecGeneral(String yesterday, String yesterday_info, String today, String today_info, String tomorrow, String tomorrow_info) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_YESTERDAY_DATE, yesterday);
        cv.put(COLUMN_YESTERDAY_INFO, yesterday_info);
        cv.put(COLUMN_TODAY_DATE, today);
        cv.put(COLUMN_TODAY_INFO, today_info);
        cv.put(COLUMN_TOMORROW_DATE, tomorrow);
        cv.put(COLUMN_TOMORROW_INFO, tomorrow_info);
        mDB.insert(DB_TABLE_GENERAL, null, cv);
    }

    // add record to DB_TABLE_DETAILED
    public void addRecDetailed(String yesterday, String yesterday_info,
                               String today, String today_info,
                               String tomorrow, String tomorrow_info,
                               String week, String week_info,
                               String month, String month_info,
                               String year, String year_info) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_YESTERDAY_DATE, yesterday);
        cv.put(COLUMN_YESTERDAY_INFO, yesterday_info);
        cv.put(COLUMN_TODAY_DATE, today);
        cv.put(COLUMN_TODAY_INFO, today_info);
        cv.put(COLUMN_TOMORROW_DATE, tomorrow);
        cv.put(COLUMN_TOMORROW_INFO, tomorrow_info);
        cv.put(COLUMN_WEEK, week);
        cv.put(COLUMN_WEEK_INFO, week_info);
        cv.put(COLUMN_MONTH, month);
        cv.put(COLUMN_MONTH_INFO, month_info);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_YEAR_INFO, year_info);
        mDB.insert(DB_TABLE_DETAILED, null, cv);
    }

    // delete record from DB_TABLE_GENERAL
    public void delRecGeneral(long id) {
        mDB.delete(DB_TABLE_GENERAL, COLUMN_ID + " = " + id, null);
    }

    // delete record from DB_TABLE_DETAILED
    public void delRecDetailed(long id) {
        mDB.delete(DB_TABLE_DETAILED, COLUMN_ID + " = " + id, null);
    }

    // class for create and management  database
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // create and inset databases
        @Override
        public void onCreate(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();

            db.execSQL(DB_GENERAL_CREATE);
            for (int i = 0; i < sign_name.length; i++) {
                cv.put(COLUMN_SIGN_NAME, sign_name[i]);
                db.insert(DB_TABLE_GENERAL, null, cv);
            }

            db.execSQL(DB_DETAILED_CREATE);
            for (int i = 0; i < sign_name.length; i++) {
                cv.put(COLUMN_SIGN_NAME, sign_name[i]);
                db.insert(DB_TABLE_DETAILED, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
