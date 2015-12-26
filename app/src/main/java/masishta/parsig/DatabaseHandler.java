package masishta.parsig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masishta on 10/19/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    // Static Variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mosayeb";

    // Table Name
    private static final String TABLE_INFO = "info";

    // Info Table Columns Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String Key_DESCRIPTION = "description";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Table
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_INFO_TABLE = "CREATE TABLE " + TABLE_INFO + "("
                + KEY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + Key_DESCRIPTION + " TEXT " + ");";

        db.execSQL(CREATE_INFO_TABLE);
    }

    // Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INFO);

        // Create table again
        onCreate(db);
    }

    // Database Create, Read, Update, Delete Operations
    // Adding new info
    void addInfo(Info info)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, info.getName());
        values.put(Key_DESCRIPTION, info.getDescription());

        // Inserting Row
        db.insert(TABLE_INFO, null, values);
        db.close();
    }

    // Reading single info
    Info getInfo(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INFO, new String[] {KEY_ID, KEY_NAME, Key_DESCRIPTION},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Info info = new Info(cursor.getInt(0),
                cursor.getString(1), cursor.getString(2));

        return info;
    }

    // Reading All Info
    public List<Info> getAllInfo()
    {
        List<Info> infoList = new ArrayList<Info>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_INFO + ";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Info info = new Info();
                info.setID(cursor.getInt(0));
                info.setName(cursor.getString(1));
                info.setDescription(cursor.getString(2));

                // Adding info to list
                infoList.add(info);
            } while (cursor.moveToNext());
        }

        return infoList;
    }

    // Updating single info
    public int updateInfo(Info info)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, info.getName());
        values.put(Key_DESCRIPTION, info.getDescription());

        // Updating row
        return db.update(TABLE_INFO, values, KEY_ID + " =?", new String[]{
                String.valueOf(info.getID())});
    }

    //
}
