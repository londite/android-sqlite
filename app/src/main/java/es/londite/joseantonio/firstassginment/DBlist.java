package es.londite.joseantonio.firstassginment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;


/**
 * Created by JoseAntonio on 05/03/2015.
 */
public class DBlist extends SQLiteOpenHelper {

    public static final String COL_ID = "_id";
    public static final String COL_TEXT = "texto";

    private static final String TAG ="DBlist";
    private static final String DATABASE_NAME = "dbName";
    private static final String DATABASE_TABLE= "tableOne";
    private static final int DATABASE_VERSION= 1;//Starts in 1

    private static final String TABLE_CREATE = "create table tableOne(_id int not null, texto text not null);";

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    Context ctx;



    public DBlist(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context ctx) {
            super(ctx, DATABASE_NAME,null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tableOne ");
            onCreate(db);

        }
    }

    public DBlist open(){
        db = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertInDB(int _id, String texto){
        ContentValues content = new ContentValues();
        content.put(COL_ID, _id);
        content.put(COL_TEXT, texto);
        return db.insertOrThrow(DATABASE_TABLE, null, content);
    }

    public Cursor returnData (){
        return db.query(DATABASE_TABLE, new String[]{COL_ID, COL_TEXT}, null, null, null, null, null);
    }

  /*  public DBlist(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    */



}
