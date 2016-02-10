package masishta.parsig;

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
  // Database Version
  static final int DATABASE_VERSION = 1;

  // Database Name
  static final String DATABASE_NAME = "paarsig";

  // Database Path
  String DATABASE_PATH;

  // Table Name
  final String TABLE_INFO = "parsik_senses";

  // Parsig Table Columns Names
  private static final String _ID          = "ID";
  private static final String _PERSIAN     = "name";
  private static final String _DESCRIPTION = "desc";
  private static final String _ENGLISH     = "en";
  private static final String _DEUTSCH     = "de";
  private static final String _FRENCH      = "fr";
  private static final String _REFERENCE   = "refs";


  public DatabaseHandler (Context context)
  {
    super (context, DATABASE_NAME, null, DATABASE_VERSION);
    DATABASE_PATH = context.getDatabasePath (DATABASE_NAME).toString ();
  }

  // Creating Table
  @Override
  public void onCreate (SQLiteDatabase db)
  {

  }

  // Upgrading Database
  @Override
  public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
  {

  }

  // Return Search Result
  public List<ParsigInfoHolder> searchResult ()
  {
    List<ParsigInfoHolder> infoList = new ArrayList<ParsigInfoHolder> ();

    // Select All Query
    String selectQuery = "SELECT * FROM " + TABLE_INFO + ";";

    SQLiteDatabase db = this.getWritableDatabase ();
    Cursor cursor = db.rawQuery (selectQuery, null);

    return infoList;
  }
}
