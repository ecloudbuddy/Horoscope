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

import java.util.Calendar;


public class DB {

    private Calendar calendar = Calendar.getInstance();

    private static final String DB_NAME = "horoscope";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_GENERAL = "general_horoscope";
    private static final String DB_TABLE_DETAILED = "detailed_horoscope";
    private static final String DB_TABLE_FOR_CHECK = "check_current_date";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SIGN_NAME = "sign";
    private static final String COLUMN_TODAY_DATE = "today";
    private static final String COLUMN_TODAY_INFO = "today_info";
    private static final String COLUMN_YESTERDAY_DATE = "yesterday";
    private static final String COLUMN_YESTERDAY_INFO = "yesterday_info";
    private static final String COLUMN_TOMORROW_DATE = "tomorrow";
    private static final String COLUMN_TOMORROW_INFO = "tomorrow_info";
    private static final String COLUMN_WEEK = "week";
    private static final String COLUMN_WEEK_INFO = "week_info";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_MONTH_INFO = "month_info";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_YEAR_INFO = "year_info";

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

    private static final String DB_FOR_CHECK_CREATE =
            "create table " + DB_TABLE_FOR_CHECK + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TODAY_DATE + " integer, " +
                    COLUMN_WEEK + " integer, " +
                    COLUMN_MONTH + " integer, " +
                    COLUMN_YEAR + " integer" +
                    ");";

    private String[] sign_name = {"Овен", "Телец", "Близнецы", "Рак", "Лев", "Дева", "Весы", "Скорпион", "Стрелец", "Козерог", "Водолей", "Рыбы"};

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

    // get all data from table DB_TABLE_FOR_CHECK
    public Cursor getAllDataForCheckTable() {
        return mDB.query(DB_TABLE_FOR_CHECK, null, null, null, null, null, null);
    }

    public void updateForYesterdayColumns() {

        ContentValues cv_updateG = new ContentValues();
        Cursor cursorG = mDB.query(DB_TABLE_GENERAL, new String[]{COLUMN_TODAY_DATE, COLUMN_TODAY_INFO}, null, null, null, null, null);
        if (cursorG != null) {
            if (cursorG.moveToFirst()) {
                do {
                    cv_updateG.put(COLUMN_YESTERDAY_DATE, cursorG.getColumnName(cursorG.getColumnIndex(COLUMN_TODAY_DATE)));
                    cv_updateG.put(COLUMN_YESTERDAY_INFO, cursorG.getColumnName(cursorG.getColumnIndex(COLUMN_TODAY_INFO)));
                    mDB.update(DB_TABLE_GENERAL, cv_updateG, null, null);
                } while (cursorG.moveToNext());
            }
            cursorG.close();
        }

        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_TODAY_DATE, COLUMN_TODAY_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                do {
                    cv_updateD.put(COLUMN_YESTERDAY_DATE, cursorD.getColumnName(cursorD.getColumnIndex(COLUMN_TODAY_DATE)));
                    cv_updateD.put(COLUMN_YESTERDAY_INFO, cursorD.getColumnName(cursorD.getColumnIndex(COLUMN_TODAY_INFO)));
                    mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                } while (cursorD.moveToNext());
            }
            cursorD.close();
        }
    }

    public void updateForTodayColumns() {
        ContentValues cv_updateG = new ContentValues();
        Cursor cursorG = mDB.query(DB_TABLE_GENERAL, new String[]{COLUMN_TOMORROW_DATE, COLUMN_TOMORROW_INFO}, null, null, null, null, null);
        if (cursorG != null) {
            if (cursorG.moveToFirst()) {
                do {
                    cv_updateG.put(COLUMN_TODAY_DATE, cursorG.getColumnName(cursorG.getColumnIndex(COLUMN_TOMORROW_DATE)));
                    cv_updateG.put(COLUMN_TODAY_INFO, cursorG.getColumnName(cursorG.getColumnIndex(COLUMN_TOMORROW_INFO)));
                    mDB.update(DB_TABLE_GENERAL, cv_updateG, null, null);
                } while (cursorG.moveToNext());
            }
            cursorG.close();
        }

        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_TOMORROW_DATE, COLUMN_TOMORROW_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                do {
                    cv_updateD.put(COLUMN_TODAY_DATE, cursorD.getColumnName(cursorD.getColumnIndex(COLUMN_TOMORROW_DATE)));
                    cv_updateD.put(COLUMN_TODAY_INFO, cursorD.getColumnName(cursorD.getColumnIndex(COLUMN_TOMORROW_INFO)));
                    mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                } while (cursorD.moveToNext());
            }
            cursorD.close();
        }
    }

    public void updateForTomorrowColumns(String[] tomorrowDateG, String[] tomorrowInfoG, String[] tomorrowDateD, String[] tomorrowInfoD) {
        ContentValues cv_updateG = new ContentValues();
        Cursor cursorG = mDB.query(DB_TABLE_GENERAL, new String[]{COLUMN_TOMORROW_DATE, COLUMN_TOMORROW_INFO}, null, null, null, null, null);
        if (cursorG != null) {
            if (cursorG.moveToFirst()) {
                do {
                    for (int i = 0; i < tomorrowInfoG.length; i++) {
                        cv_updateG.put(COLUMN_TODAY_DATE, tomorrowDateG[i]);
                        cv_updateG.put(COLUMN_TODAY_INFO, tomorrowInfoG[i]);
                        mDB.update(DB_TABLE_GENERAL, cv_updateG, null, null);
                    }
                } while (cursorG.moveToNext());
            }
            cursorG.close();
        }

        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_TOMORROW_DATE, COLUMN_TOMORROW_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                do {
                    for (int i = 0; i < tomorrowInfoD.length; i++) {
                        cv_updateD.put(COLUMN_TODAY_DATE, tomorrowDateD[i]);
                        cv_updateD.put(COLUMN_TODAY_INFO, tomorrowInfoD[i]);
                        mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                    }
                } while (cursorD.moveToNext());
            }
            cursorD.close();
        }
    }

    public void updateForWeekColumns(String[] weekDateD, String[] weekInfoD) {
        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_WEEK, COLUMN_WEEK_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                for (int i = 0; i < weekDateD.length; i++) {
                    do {
                        cv_updateD.put(COLUMN_WEEK, weekDateD[i]);
                        cv_updateD.put(COLUMN_WEEK_INFO, weekInfoD[i]);
                        mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                    } while (cursorD.moveToNext());
                }
            }
            cursorD.close();
        }
    }

    public void updateForMonthColumns(String[] monthDateD, String[] monthInfoD) {
        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_MONTH, COLUMN_MONTH_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                for (int i = 0; i < monthDateD.length; i++) {
                    do {
                        cv_updateD.put(COLUMN_MONTH, monthDateD[i]);
                        cv_updateD.put(COLUMN_MONTH_INFO, monthInfoD[i]);
                        mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                    } while (cursorD.moveToNext());
                }
            }
            cursorD.close();
        }
    }

    public void updateForYearColumns(String[] yearDateD, String[] yearInfoD) {
        ContentValues cv_updateD = new ContentValues();
        Cursor cursorD = mDB.query(DB_TABLE_DETAILED, new String[]{COLUMN_YEAR, COLUMN_YEAR_INFO}, null, null, null, null, null);
        if (cursorD != null) {
            if (cursorD.moveToFirst()) {
                for (int i = 0; i < yearDateD.length; i++) {
                    do {
                        cv_updateD.put(COLUMN_YEAR, yearDateD[i]);
                        cv_updateD.put(COLUMN_YEAR_INFO, yearInfoD[i]);
                        mDB.update(DB_TABLE_DETAILED, cv_updateD, null, null);
                    } while (cursorD.moveToFirst());
                }
            }
            cursorD.close();
        }
    }

    public void updateForCheckTable() {
        ContentValues cv_update = new ContentValues();
        cv_update.put(COLUMN_TODAY_DATE, Calendar.DAY_OF_MONTH);
        cv_update.put(COLUMN_WEEK, Calendar.WEEK_OF_YEAR);
        cv_update.put(COLUMN_MONTH, Calendar.MONTH);
        cv_update.put(COLUMN_YEAR, Calendar.YEAR);
        String where = COLUMN_ID + "=" + 1;
        mDB.update(DB_TABLE_FOR_CHECK, cv_update, where, null);
    }

    public void addRecordCheckTable() {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TODAY_DATE, Calendar.DAY_OF_MONTH);
        cv.put(COLUMN_WEEK, Calendar.WEEK_OF_YEAR);
        cv.put(COLUMN_MONTH, Calendar.MONTH);
        cv.put(COLUMN_YEAR, Calendar.YEAR);
        mDB.update(DB_TABLE_FOR_CHECK, cv, null, null);
    }

    // add record to DB_TABLE_GENERAL
    public void addRecordGeneral(String yesterday, String yesterday_info,
                                 String today, String today_info,
                                 String tomorrow, String tomorrow_info) {
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
    public void addRecordDetailed(String yesterday, String yesterday_info,
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
            for (String aSign_name1 : sign_name) {
                cv.put(COLUMN_SIGN_NAME, aSign_name1);
                db.insert(DB_TABLE_GENERAL, null, cv);
            }

            db.execSQL(DB_DETAILED_CREATE);
            for (String aSign_name : sign_name) {
                cv.put(COLUMN_SIGN_NAME, aSign_name);
                db.insert(DB_TABLE_DETAILED, null, cv);
            }

            db.execSQL(DB_FOR_CHECK_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}

