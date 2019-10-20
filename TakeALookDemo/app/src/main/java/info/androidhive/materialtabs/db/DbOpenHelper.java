package info.androidhive.materialtabs.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import info.androidhive.materialtabs.activity.RecommendActivity;

public class DbOpenHelper {

    private static final String DATABASE_NAME = "takealookDB.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    public static final String SQL_RECOMMEND_TCONST = "SELECT tconst FROM basic_titles ORDER BY random() LIMIT 4;";

    private class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE0);
            db.execSQL(DataBases.CreateDB._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public DbOpenHelper READ() throws SQLException{
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getReadableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String tconst, String genres, String averRatings, String numVotes, String titleKor, String emoName , String empScore, String startYear){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TCONST, tconst);
        values.put(DataBases.CreateDB.TITLEKOR, titleKor);
        values.put(DataBases.CreateDB.GENRES, genres);
        values.put(DataBases.CreateDB.AVERRATINGS, averRatings);
        values.put(DataBases.CreateDB.NUMVOTES, numVotes);
        values.put(DataBases.CreateDB.EMONAME, emoName);
        values.put(DataBases.CreateDB.EMOSCORE, empScore);
        values.put(DataBases.CreateDB.STARTYEAR, startYear);
        return mDB.insert(DataBases.CreateDB._TABLENAME0, null, values);
    }

    // Update DB
    public boolean updateColumn(long id, String tconst, String genres, String averRatings, String numVotes, String titleKor, String emoName , String empScore, String startYear){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.TCONST, tconst);
        values.put(DataBases.CreateDB.TITLEKOR, titleKor);
        values.put(DataBases.CreateDB.GENRES, genres);
        values.put(DataBases.CreateDB.AVERRATINGS, averRatings);
        values.put(DataBases.CreateDB.NUMVOTES, numVotes);
        values.put(DataBases.CreateDB.EMONAME, emoName);
        values.put(DataBases.CreateDB.EMOSCORE, empScore);
        values.put(DataBases.CreateDB.STARTYEAR, startYear);
        return mDB.update(DataBases.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    }

    // mylist 초기화 # TABLENAME0 = basic_titles, TABLENAME1 = mylist
    public void deleteAllColumns() {
        mDB.delete(DataBases.CreateDB._TABLENAME0, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(DataBases.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }

    // Select DB = mylist 테이블 전체
    public Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }

    // Select DB = basic_titles Recommend imageView tconst output
    public Cursor selectRecommend(){
        String sql = "SELECT tconst FROM basic_titles ORDER BY random() LIMIT 1;";

        return mDB.rawQuery(sql, null);
    }

}